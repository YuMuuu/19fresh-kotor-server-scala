package Handler
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext

class Routes(implicit val system: ActorSystem,
             materializer: ActorMaterializer,
             ec: ExecutionContext) {
  lazy val config = ConfigFactory.load() // デフォルトで application.conf が読まれる
  lazy val host = config.getString("http.host")
  lazy val port = config.getInt("http.port")

  def route: Route =
    path("/oauth") {
      //認証
      path("/signup") {
        post {
          //サインアップする
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
                       "<h1>Say hello to akka-http</h1>"))
        }
      } ~ path("/signin") {
        post {
          //サインインする
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
                       "<h1>Say hello to akka-http</h1>"))
        }
      }
    }~ path("/users") {
      //ユーザ
      path("/{username") {
        get {
          //"user情報を取得する
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
                       "<h1>Say hello to akka-http</h1>"))
        }
      }
      post {
        ///userを作成する
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
                     "<h1>Say hello to akka-http</h1>"))
      }
      put {
        //userを更新する
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
                     "<h1>Say hello to akka-http</h1>"))
      }
    } ~ path("/tweets") {
      //ツイート
      post{
        //ツイートする
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
            "<h1>Say hello to akka-http</h1>"))
      }
      path("/{tweets_id}") {
        get {
          //ツイート情報を取得する
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
              "<h1>Say hello to akka-http</h1>"))
        }
        path("/retweet"){
          post {
            //ツイート情報を取得する，解除する
            complete(
              HttpEntity(ContentTypes.`text/html(UTF-8)`,
                "<h1>Say hello to akka-http</h1>"))
          }
        }
      }
      path("/timelines") {
        get {
          //タイムラインを取得する
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
              "<h1>Say hello to akka-http</h1>"))
        }
      }
    } ~ path("/followers/{username}") {
      get {
        //フォロワーを取得
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
            "<h1>Say hello to akka-http</h1>"))
      }
    } ~ path("/follows/{username}") {
      get {
        //フォローを取得
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
            "<h1>Say hello to akka-http</h1>"))
      }
    } ~ path("/follow/{username}") {
      post {
        //フォローする
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
            "<h1>Say hello to akka-http</h1>"))
      }
    }

  val bindingFuture = Http().bindAndHandle(route, host, port)

}

object Routes {
  def apply(implicit system: ActorSystem,
            materializer: ActorMaterializer,
            ec: ExecutionContext) = new Routes()
}
