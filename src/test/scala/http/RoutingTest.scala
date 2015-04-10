package http


import akka.http.model.StatusCodes._
import akka.http.model.{HttpCharsets, MediaTypes}
import akka.http.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpecLike}

class RoutingTest extends Routing with WordSpecLike with Matchers with ScalatestRouteTest {

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
