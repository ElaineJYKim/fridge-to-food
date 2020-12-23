package fridge.http

import fridge.http.Search._
import fridge.http.Scraper._

import scala.concurrent.Future

object SearchExtract {

  val MAXRESULTS = 5

  def formatQuery(ingredients: List[String]): String = {
    ingredients  mkString(", ")
  }

  def extract(item: Item): Option[DishInfo] = {
    // Returns None if item does not contain a recipe
    // Else: Returns DishInfo
    extractor(item.link) match {
      case Some(ingredients) => Some(DishInfo(html2text(item.title),item.link, item.thumbnail, ingredients))
      case None => None
    }
  }

  def filterItems(items: Seq[Item]): Seq[DishInfo] = {
    // Returns sequence of DishInfo that contains recipes

    var filteredItems = Seq[DishInfo]()
    var count = 0

    items.iterator.takeWhile(_ => count<MAXRESULTS).foreach{ item =>
      val extracted = extract(item)
      if (extracted.isDefined) {
        filteredItems = filteredItems :+ extracted.get
        count += 1
      }
    }

    filteredItems
  }

  def searchRecipes(userQuery: String): Future[Either[String, Response]] = {
    // Returns Sequence of valid DishInfos
    // Seq Max Len = MAXRESULTS
      search(userQuery).map {
          case Right(seqItems) => Right(Response(filterItems(seqItems)))
          case Left(errorMessage) => Left(errorMessage)
      }
  }

}