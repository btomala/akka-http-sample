package http

import akka.actor.ActorSystem
import akka.http.Http
import akka.http.model._
import akka.http.server._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{RunnableFlow, Sink, Source}
import com.typesafe.config.Config

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HttpService private (config: Config)(implicit system: ActorSystem, materializer: ActorFlowMaterializer) extends Routing {

  val host: String = config.getString("akka.http.interface")
  val port: Int = config.getInt("akka.http.port")

  lazy val requestHandler: HttpRequest => Future[HttpResponse] = Route.asyncHandler(route)

  lazy val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http().bind(interface = host, port = port)

  lazy val runnableFlow: RunnableFlow[Future[Http.ServerBinding]] = serverSource.to(
    Sink.foreach(connection => connection handleWithAsyncHandler requestHandler))

  val bindingFuture = runnableFlow.run()
  println(s"start on $host:$port")
}

object HttpService {
  private var service: Option[HttpService] = None

  def getService: Option[HttpService] = service

  def run(config: Config)(implicit system: ActorSystem, materializer: ActorFlowMaterializer) {
    service = service.orElse(Option(new HttpService(config)))
  }
}