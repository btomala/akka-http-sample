
import akka.http.model.{HttpCharsets, HttpCharset, MediaTypes}
import akka.http.model.StatusCodes._
import akka.http.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class ServiceTest extends WordSpec with Matchers with ScalatestRouteTest with Service {
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
    val faild = "The requested resource could not be found."
    s"respond '$faild'" in {
      Get("/sdfs") ~> route ~> check(rejections)
    }
  }
}
