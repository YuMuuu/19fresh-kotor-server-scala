import Handler.{Route, Routes}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging


object Boot extends App with LazyLogging {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

//  lazy val config = ConfigFactory.load() // デフォルトで application.conf が読まれる
//  lazy val host = config.getString("http.host")
//  lazy val port = config.getInt("http.port")



  val route = Routes
}