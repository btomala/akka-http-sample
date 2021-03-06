package http

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import org.scalatest.OptionValues._

class HttpServiceSpec extends TestKit(ActorSystem("ServiceTest")) with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll = system.shutdown()

  implicit val materializer = ActorMaterializer()

  val port = 9999
  val config: Config = ConfigFactory.load

  "Http Service " should {
    s"be running on port $port" in {
      HttpService.run(config)
      HttpService.getService.value.port shouldBe port
    }
  }

}
