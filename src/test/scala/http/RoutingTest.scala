package http


import akka.http.model.{HttpCharsets, MediaTypes}
import akka.http.model.StatusCodes._
import akka.http.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpecLike}

class RoutingTest extends Routing with WordSpecLike with Matchers with ScalatestRouteTest {

  import TildeArrow._

  "Routing" should {
    val hello = "Hello world!"
    s"respond '$hello'" when {
      "is asked for main page" in {
        Get("/hello/world") ~> route ~> check {
          status shouldBe OK
          mediaType shouldBe MediaTypes.`text/plain`
          charset shouldBe HttpCharsets.`UTF-8`
          responseAs[String] should equal (hello)
        }
      }
    }

    "be serve html file" when {
      "is asked for index page" in {
        Get("/index") ~> route ~> check {
          status shouldBe OK
          mediaType shouldBe MediaTypes.`text/html`
          charset shouldBe HttpCharsets.`UTF-8`
          responseAs[String] should include ("<title>Hello World</title>")
        }
      }
    }

    "be rejected" when {
      "address is unknonwn" in {
        Get("/sdfs") ~> route ~> check(rejections)
      }
    }
  }
}
