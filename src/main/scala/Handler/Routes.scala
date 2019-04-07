package Handler
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext

class Routes()(implicit val system: ActorSystem,
               materializer: ActorMaterializer,
               ec: ExecutionContext) {
  lazy val config = ConfigFactory.load() // デフォルトで application.conf が読まれる
  lazy val host = config.getString("http.host")
  lazy val port = config.getInt("http.port")

  val route: Route =
    post {
      pathPrefix("oauth") {
        //認証
        path("signup") {
          //サインアップする
          complete("sign_up")
        } ~
          path("signin") {

            //サインインする
            complete("sign_in")
          }
      }
    } ~
      post {
        pathPrefix("users") {
          ///userを作成する
          complete("create_user")
        }
      } ~
      put {
        pathPrefix("users") {
          //userを更新する
          complete("update_user")
        }
      } ~
      get {
        pathPrefix("users") {
          path("username") {
            //"user情報を取得する
            complete("get_userInfo")
          }
        }
      } ~
      post {
        //ツイートする
        pathPrefix("tweets") {
          complete("post_tweet")
        }
      } ~
      get {
        //ツイート情報を取得する
        pathPrefix("tweets") {
          pathPrefix("tweets_id") {
            complete("tweet情報を取得する")
          }
        }
      } ~
      post {
        //ツイート情報を取得する，解除する
        pathPrefix("tweets") {
          pathPrefix("retweet") {
            complete("tweet情報を取得する，解除する")
          }
        }
//        pathPrefix("timelines") {
//          get {
//            //タイムラインを取得する
//            complete("タイムラインを取得する")
//          }
//        }
//      } ~
//      pathPrefix("followers/username") {
//        get {
//          //フォロワーを取得
//          complete("フォロワーを取得すする")
//        }
//      } ~
//      pathPrefix("follows/username") {
//        get {
//          //フォローを取得
//          complete("ふぉろーを取得する")
//        }
//      } ~
//      pathPrefix("follow/username") {
//        post {
//          //フォローする
//          complete("フォローする")
//        }
      }

  val bindingFuture = Http().bindAndHandle(route, host, port)

}

object Routes {
  def apply()(implicit system: ActorSystem,
              materializer: ActorMaterializer,
              ec: ExecutionContext) = new Routes()
  def route()(implicit system: ActorSystem,
              materializer: ActorMaterializer,
              ec: ExecutionContext) = new Routes().route
}
