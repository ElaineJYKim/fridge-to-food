package fridge.http

import fridge.http.SearchExtract._
import fridge.http.JsonUtil._
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http

object HttpServer extends App{

  import fridge.akka.AkkaManager._

  val route = {
    parameter("query") { userQuery =>
      complete {
        searchRecipes(userQuery).map {
          case Right(response) => toJson(response)
          case Left(errorMessage) => errorMessage
        }
      }
    }
  }

  val config = ConfigFactory.load()

  Http().newServerAt(config.getString("http.interface"), config.getInt("http.port")).bind(route)

}