package http


import akka.http.model.StatusCodes._
import akka.http.model.{HttpCharsets, MediaTypes}
import akka.http.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpecLike}

import scala.xml.pull.XMLEvent

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

    "be serve html file" in {
      Get("/index") ~> route ~> check {
        status shouldBe OK
        responseAs[String] should include ("<title>Hello World</title>")
      }
    }

    "be rejected'" in {
      Get("/sdfs") ~> route ~> check(rejections)
    }
  }
}
