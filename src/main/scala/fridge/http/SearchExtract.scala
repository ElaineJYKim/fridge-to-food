package fridge.http

import fridge.http.Search._
import fridge.http.Scrapper._

import scala.concurrent.Future

object SearchExtract {

  val MAXRESULTS = 5

  def formatQuery(ingredients: List[String]): String = {
    // Returns Ingredients in query format of __,__,__, ..
    ingredients  mkString(", ")
  }

  def extract(item: Item): Option[DishInfo] = {
    // Returns None if item does not contain a recipe
    // Else: Returns DishInfo

    isRecipe(item.link) match {
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

  def searchRecipes(userQuery: List[String]): Future[Seq[DishInfo]] = {
    // Returns Sequence of valid DishInfos
    // Seq Max Len = MAXRESULTS

    val query = formatQuery(userQuery)
    val itemsSeqFuture: Future[Seq[Item]] = search(query)
    itemsSeqFuture.map(itemsSeq => filterItems(itemsSeq))
  }

  def main(args: Array[String]): Unit = {
//    searchRecipes(List("양파","버섯","마늘")).map { seq =>
//      seq.map(info => println(info.link))
//    }
  }

}