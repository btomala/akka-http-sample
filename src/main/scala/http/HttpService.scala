package http

import akka.actor.ActorSystem
import akka.http.Http
import akka.http.model._
import akka.http.server._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{RunnableFlow, Sink, Source}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

abstract class Service extends Routing {
  implicit val system: ActorSystem
  implicit val materializer: ActorFlowMaterializer

  val host: String
  val port: Int

  lazy val requestHandler: HttpRequest => Future[HttpResponse] = Route.asyncHandler(route)

  lazy val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http(system).bind(interface = host, port = port)

  lazy val runnableFlow: RunnableFlow[Future[Http.ServerBinding]] = serverSource.to(
    Sink.foreach( connection => connection handleWithAsyncHandler requestHandler ))
}

object HttpService extends Service {
  implicit val system = ActorSystem()
  implicit val materializer = ActorFlowMaterializer()

  val port = 8080
  val host = "0.0.0.0"
  def run {
    val bindingFuture = runnableFlow.run()
    println(s"start on $host:$port")
  }
}