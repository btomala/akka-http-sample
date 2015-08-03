import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import http.HttpService

object WebServer extends App {

  implicit val system = ActorSystem("akka-http-sample")
  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load

  HttpService.run(config)(system, materializer)

  private def shutdown = system.shutdown()
  sys.addShutdownHook(shutdown)

}
