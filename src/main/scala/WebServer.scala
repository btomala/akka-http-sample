import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import com.typesafe.config.ConfigFactory
import http.HttpService
import akka.kernel.Bootable

object WebServer extends Bootable {

  implicit val system = ActorSystem("akka-http-sample")
  implicit val materializer = ActorFlowMaterializer()

  val config = ConfigFactory.load

  def startup = {
    HttpService.run(config)(system, materializer)
  }

  def shutdown = system.shutdown()

}
