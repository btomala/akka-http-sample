package http

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.testkit.TestKit
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import org.scalatest.OptionValues._

class HttpServiceTest extends TestKit(ActorSystem("ServiceTest")) with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll = system.shutdown()

  implicit val materializer = ActorFlowMaterializer()

  val port = 9999
  val config: Config = ConfigFactory.load

  "Http Service " should {
    s"be running on port $port" in {
      HttpService.run(config)
      HttpService.getService.value.port shouldBe port
    }
  }

}
