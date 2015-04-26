package http

import akka.event.Logging._
import akka.http.server._
import akka.http.server.Directives._
import scala.concurrent.ExecutionContext.Implicits.global
import akka.http.server.directives.LoggingMagnet._

trait Routing {

  val route: Route =
  logRequestResult(forRequestResponseFromMarkerAndLevel((s"[${this.getClass.getSimpleName}]", DebugLevel))) {
    path("hello" / "world") {
      get {
        complete {
          "Hello world!"
        }
      }
    } ~ path("index") {
      getFromResource("view/index.html")
    }
  }

}
