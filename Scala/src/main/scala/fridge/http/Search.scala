package fridge.http

import java.io.IOException
import java.net.URLEncoder

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes.{BadRequest, OK}
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration._
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.unmarshalling.Unmarshal
import fridge.http.JsonUtil._

object Search {
  // REQUEST & RESPONSE from api
  // use Search(userQuery) to retrieve Future[SearchResult]

  // import fridge.akka.AkkaManager._
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  def request(userQuery: String): HttpRequest = {

    // Request Parameters
    val DISPLAY = 20
    val START = 1

    // Request Info
    val url = "https://openapi.naver.com/v1/search/encyc.json"
    val clientId = "Qud3Z3xub5BWci2OcQ9Z"
    val clientSecret = "KAX1A5c1fk"

    // convert string to UTF-8
    val query = URLEncoder.encode(userQuery, "UTF-8")

    val header = Seq(
      RawHeader("X-Naver-Client-Id", s"$clientId"),
      RawHeader("X-Naver-Client-Secret", s"$clientSecret")
    )

    val request = HttpRequest(
      HttpMethods.GET,
      url + s"?query=$query&display=$DISPLAY&start=$START&sort=sim",
    ).withHeaders(header)

    request
  }

  def sendRequest(userQuery: String): Future[Either[String, Seq[Item]]] = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request(userQuery))
//    val entityFuture: Future[HttpEntity.Strict] = responseFuture.flatMap {
//      response => response.entity.toStrict(2.seconds)
//    }
//    entityFuture.map(entity => entity.data.utf8String)
    val entityFuture = responseFuture.flatMap { response =>
      response.status match {
        case OK => response.entity.toStrict(2.seconds).map(entity => Right(fromJson[SearchResult](entity.data.utf8String).items))
        case BadRequest => Future.successful(Left(s"$userQuery: Bad Request"))
        case _ => Unmarshal(response.entity).to[String].flatMap { entity =>
          val error = s"Naver Search request failed with status code ${response.status} and query $userQuery"
          Future.failed(new IOException(error))
        }
      }
    }
    entityFuture
  }

  def search(userQuery:String): Future[Either[String, Seq[Item]]] = {
    val responseFuture = sendRequest(userQuery)
//    responseFuture.map(result =>
//      fromJson[SearchResult](result)
//    ).map(searchResults => searchResults.items)
    responseFuture
  }
}

