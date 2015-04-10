import akka.actor.ActorSystem
import akka.http.Http
import akka.http.model._
import akka.http.model.HttpMethods._
import akka.http.server._
import akka.http.server.Directives._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{RunnableFlow, Sink, Source}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Service {
  implicit val system: ActorSystem
  implicit val materializer: ActorFlowMaterializer
  val host: String
  val port: Int

  val route: Route =
    path("") {
      get {
        complete {
          "Hello world!"
        }
      }
  }

  lazy val requestHandler: HttpRequest => Future[HttpResponse] = Route.asyncHandler(route)

  lazy val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http(system).bind(interface = host, port = port)

  lazy val runnableFlow: RunnableFlow[Future[Http.ServerBinding]] = serverSource.to(
    Sink.foreach { connection =>
      println("accepted new connection from " + connection.remoteAddress)
      connection handleWithAsyncHandler requestHandler
    })
}

object HttpService extends App with Service {
  implicit val system = ActorSystem()
  implicit val materializer = ActorFlowMaterializer()

  val port = 8080
  val host = "0.0.0.0"
  val bindingFuture = runnableFlow.run()
  println(s"start on $host:$port")
}