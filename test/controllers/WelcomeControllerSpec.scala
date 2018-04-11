package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}
import services.GreetingService

class WelcomeControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  object FakeMorningGreeter extends GreetingService {
    override def greeting: String = "Good Morning"
  }

  object FakeAfternoonGreeter extends GreetingService {
    override def greeting: String = "Good Afternoon"
  }

  "WelcomeController GET" should {

    "return a successful response" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome().apply(FakeRequest())
      status(result) mustBe OK
    }

    "respond to the /welcome url" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
    }

    "render a title and h1 correctly" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome().apply(FakeRequest())
      contentAsString(result) must include ("<title>Welcome</title>")
      contentAsString(result) must include ("<h1>Good Morning</h1>")
    }

    "say good afternoon in the afternoon and have title" in {
      val controller = new WelcomeController(FakeAfternoonGreeter)
      val result = controller.welcome().apply(FakeRequest())
      contentAsString(result) must include ("<title>Welcome</title>")
      contentAsString(result) must not include "<h1>Good Morning</h1>"
      contentAsString(result) must include ("<h1>Good Afternoon</h1>")
    }
  }
}
