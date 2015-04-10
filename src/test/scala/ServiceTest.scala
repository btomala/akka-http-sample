
import akka.http.model.{HttpCharsets, MediaTypes}
import akka.http.model.StatusCodes._
import akka.http.testkit.ScalatestRouteTest
import http.Service
import org.scalatest.{Matchers, WordSpecLike}

class ServiceTest extends Service with WordSpecLike with Matchers with ScalatestRouteTest {
  override val host: String = ""
  override val port: Int = 64000

  import TildeArrow._

  "Service" should {
    val hello = "Hello world!"
    s"respond '$hello'" in {
      Get("/") ~> route ~> check {
        status shouldBe OK
        mediaType shouldBe MediaTypes.`text/plain`
        charset shouldBe HttpCharsets.`UTF-8`
        responseAs[String] should equal (hello)
      }
    }

    s"be rejected'" in {
      Get("/sdfs") ~> route ~> check(rejections)
    }
  }
}
