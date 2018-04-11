package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}

class WelcomeControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  val controller = new WelcomeController

  "WelcomeController GET" should {

    "return a successful response" in {
      val result = controller.welcome().apply(FakeRequest())
      status(result) mustBe OK
    }

    "respond to the /welcome url" in {
      val result = controller.welcome().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
    }

    "render a title and h1 correctly" in {
      val result = controller.welcome().apply(FakeRequest())
      contentAsString(result) must include ("<title>Welcome</title>")
      contentAsString(result) must include ("<h1>Hello</h1>")
    }
  }
}
