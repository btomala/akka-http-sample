package http

import akka.http.scaladsl.model.{HttpCharsets, MediaTypes}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpecLike}

class RoutingSpec extends Routing with WordSpecLike with Matchers with ScalatestRouteTest {

  import TildeArrow._

  "Routing" should {
    val hello = "Hello world!"
    s"respond '$hello'" when {
      "is asked on /hello/world" in {
        Get("/hello/world") ~> route ~> check {
          status shouldBe OK
          mediaType shouldBe MediaTypes.`text/plain`
          charset shouldBe HttpCharsets.`UTF-8`
          responseAs[String] should equal (hello)
        }
      }
    }

    "be serve index.html file" when {
      "is asked on /index" in {
        Get("/index") ~> route ~> check {
          status shouldBe OK
          mediaType shouldBe MediaTypes.`text/html`
          charset shouldBe HttpCharsets.`UTF-8`
          responseAs[String] should include ("<title>Hello World</title>")
        }
      }
    }

    "be serve twirl.html file" when {
      "is asked on /twirl" in {
        Get("/domain") ~> route ~> check {
          status shouldBe OK
          mediaType shouldBe MediaTypes.`text/html`
          charset shouldBe HttpCharsets.`UTF-8`
          val response = responseAs[String]
          response should include ("Bob")
        }
      }
    }

    "reject request" when {
      "path is unknonwn" in {
        Get("/sdfs") ~> route ~> check(rejections)
      }
    }
  }
}
