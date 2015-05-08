package http

import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.LoggingMagnet._

trait Routing {

  val route: Route =
  logRequestResult(forRequestResponseFromMarker("")) {
    path("hello" / "world") {
      get {
        complete {
          "Hello world!"
        }
      }
    } ~ path("index") {
      getFromResource("view/index.html")
    } ~ path("json" / "api" / "user") {
      get {
        complete {
          """{ "test" : "json" }"""
        }
      }
    }
  }

}
