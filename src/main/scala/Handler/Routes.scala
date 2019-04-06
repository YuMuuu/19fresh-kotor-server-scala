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
    pathPrefix("oauth") {
      //認証
      pathPrefix("signup") {
        pathEnd {
          post {
            //サインアップする
            complete("サインアップする")
          }
        }
      } ~ pathPrefix("signin") {
        pathEnd {
          post {
            //サインインする
            complete("サインインする")
          }
        }
      }
    } ~ pathPrefix("users") {
      //ユーザ
      pathPrefix("{username}") {
        pathEnd {
          get {
            //"user情報を取得する
            complete("user情報を取得する")
          }
        }
      }
      pathEnd {
        post {
          ///userを作成する
          complete("userを作成する")
        }
      }
      pathEnd {
        put {
          //userを更新する
          complete("userを更新する")
        }
      }
    } ~ pathPrefix("tweets") {
      pathEnd {
        //ツイート
        post {
          //ツイートする
          complete("ツイートする")
        }
      }
      pathPrefix("{tweets_id}") {
        pathEnd {
          get {
            //ツイート情報を取得する
            complete("tweet情報を取得する")
          }
        }
        pathPrefix("retweet") {
          pathEnd {
            post {
              //ツイート情報を取得する，解除する
              complete("tweet情報を取得する，解除する")
            }
          }
        }
      }
      pathPrefix("timelines") {
        pathEnd {
          get {
            //タイムラインを取得する
            complete("タイムラインを取得する")
          }
        }
      }
    } ~ pathPrefix("followers/{username}") {
      pathEnd {
        get {
          //フォロワーを取得
          complete("フォロワーを取得すする")
        }
      }
    } ~ pathPrefix("follows/{username}") {
      pathEnd {
        get {
          //フォローを取得
          complete("ふぉろーを取得する")
        }
      }
    } ~ pathPrefix("follow/{username}") {
      pathEnd {
        post {
          //フォローする
          complete("フォローする")
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, host, port)

}

object Routes {
  def apply()(implicit system: ActorSystem,
              materializer: ActorMaterializer,
              ec: ExecutionContext) = new Routes()
}
