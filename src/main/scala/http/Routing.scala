package http

import akka.http.server._
import akka.http.server.Directives._
import scala.concurrent.ExecutionContext.Implicits.global

trait Routing { 

  val route: Route =
    path("") {
      get {
        complete {
          "Hello world!"
        }
      }
    }

}
