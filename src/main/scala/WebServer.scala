import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import com.typesafe.config.ConfigFactory
import http.HttpService

object WebServer extends App {

  implicit val system = ActorSystem("akka-http-sample")
  implicit val materializer = ActorFlowMaterializer()

  val config = ConfigFactory.load

  HttpService.run(config)(system, materializer)

  private def shutdown = system.shutdown()
  sys.addShutdownHook(shutdown)

}
