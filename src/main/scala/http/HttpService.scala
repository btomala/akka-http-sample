package http

import scala.concurrent.Future

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import com.typesafe.config.Config

class HttpService private (config: Config)(implicit system: ActorSystem, materializer: ActorMaterializer) extends Routing {

  val host: String = config.getString("akka.http.interface")
  val port: Int = config.getInt("akka.http.port")

  lazy val requestHandler: HttpRequest => Future[HttpResponse] = Route.asyncHandler(route)

  lazy val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http().bind(interface = host, port = port)

  lazy val bindingFuture: Future[Http.ServerBinding] = serverSource.to(
    Sink.foreach(connection => connection handleWithAsyncHandler requestHandler)).run()

  system.log.info(s"start on $host:$port")
}

object HttpService {
  private var service: Option[HttpService] = None

  def getService: Option[HttpService] = service

  def run(config: Config)(implicit system: ActorSystem, materializer: ActorMaterializer) {
    service = service.orElse(Option(new HttpService(config)))
  }
}