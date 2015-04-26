package http

import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.server._
import akka.http.server.Directives._
import akka.http.server.directives.LoggingMagnet._

trait Routing {

  val route: Route =
  logRequestResult(forRequestResponseFromMarker(s"[${this.getClass.getSimpleName}]")) {
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
