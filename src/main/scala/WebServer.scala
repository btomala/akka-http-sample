import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import com.typesafe.config.ConfigFactory
import http.HttpService

object WebServer extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorFlowMaterializer()

  val config = ConfigFactory.load

  HttpService.run(config)(system, materializer)
}
