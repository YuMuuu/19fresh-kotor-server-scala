package handler

import Handler.Routes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class RoutersSpec extends WordSpec with Matchers with ScalatestRouteTest {
  val route = Routes().route

  "The service in Certification" should {
    "return a signup" in {
      Post("/oauth/signup") ~> route ~> check {
        responseAs[String] shouldEqual "sign_up"
      }
    }
    "return a signin" in {
      Post("/oauth/signin") ~> route ~> check {
        responseAs[String] shouldEqual "sign_in"
      }
    }
  }
  "The service in User" should {
    "return a get create_user " in {
      Post("/users") ~> route ~> check {
        println(responseAs[String])
        responseAs[String] shouldEqual "create_user"
      }
    }
    "return a get update_user " in {
      Put("/users") ~> route ~> check {
        println(responseAs[String])
        responseAs[String] shouldEqual "update_user"
      }
    }
    "return a get userInfo " in {
      Get("/users/username") ~> route ~> check {
        println(responseAs[String])
        responseAs[String] shouldEqual "get_userInfo"
      }
    }
  }
  "The service in Tweet" in {
    Post("/tweets") ~> route ~> check {
      println(responseAs[String])
      responseAs[String] shouldEqual "post_tweet"
    }

  }
  "The service in Follow and Follower " in {

  }

}
