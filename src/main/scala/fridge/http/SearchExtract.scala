package fridge.http

import fridge.http.Search._

import scala.concurrent.Future

object SearchExtract {

  def formatQuery(ingredients: List[String]): String = {
    // Returns Ingredients in query format of __,__,__, ..
    ingredients  mkString(", ")
  }

  def extract(item: Item): Option[DishInfo] = {
    // Returns None if item does not contain a recipe
    // Else: Returns DishInfo
    // Item(title:String, link: String, description: String, thumbnail: String)

    ???
  }

  def filterItems(items: Seq[Item]): Seq[DishInfo] = {
    // Returns sequence of DishInfo that contains recipes

    var filteredItems = Seq[DishInfo]()

    for (item <- items) {
      val extracted = extract(item)
      if (extracted != None) {
        filteredItems = filteredItems :+ extracted.get
      }
    }

    filteredItems
  }

  def finalResults(userQuery: List[String]): Future[Seq[DishInfo]] = {
    // Returns Sequence of valid DishInfos

    val query = formatQuery(userQuery)
    val itemsSeqFuture: Future[Seq[Item]] = search(query)
    itemsSeqFuture.map(itemsSeq => filterItems(itemsSeq))
  }

  def main(args: Array[String]): Unit = {
    print(formatQuery(List("hi", "bye", "smile")))
  }

}