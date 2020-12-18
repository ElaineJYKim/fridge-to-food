package fridge.http

import fridge.http.SearchExtract._
import fridge.http.JsonUtil._
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http


//"%ec%96%91%ed%8c%8c%2c%20%eb%b2%84%ec%84%af"
object HttpServer extends App{

  import fridge.akka.AkkaManager._

  val route = {
    parameter("query") { userQuery =>
      complete {
        searchRecipes(userQuery).map { response =>
          toJson(response)
        }
      }
    }
  }


  val config = ConfigFactory.load()

  Http().newServerAt(config.getString("http.interface"), config.getInt("http.port")).bind(route)

}