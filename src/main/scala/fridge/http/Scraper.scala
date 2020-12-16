package fridge.http

import org.jsoup.Jsoup
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._
import scala.util.matching.Regex

// https://index.scala-lang.org/ruippeixotog/scala-scraper/scala-scraper/2.2.0?target=_2.13

object Scrapper {
  val browser = JsoupBrowser()

  def isRecipe(link: String): Option[String] = {
    // TODO: BROKEN --> ALL LINKS ARE PASSED TO LEFT 
    val doc = browser.get(link)
    doc >/~ validator(allText(".tmp_agenda"))(_.contains("재료")) match {
      case Left(_) => Some(getIngredients(doc)) //
      case Right(_) => None
    }
  }

  def getIngredients(doc: Scrapper.browser.DocumentType): String = {
    // TODO: from Return String --> Seq[Ingredient]
    // TODO: get the regex right.
    // TODO: clean up so I don't mutate variables

    val pTextList = doc >> texts("#size_ct p")
    val ingredientPattern = "\\p{L}+\\s*\\d+\\p{L}*,\\s".r  // ingredientPattern.findFirstIn(text) -> Option[match]
    var found = false
    var res = ""

    pTextList.iterator.takeWhile(_ => !found).foreach{ text =>
      if (text contains "재료") {
        found = true
        res += text
      }
    }

    res
  }

  // TODO: Find the right placement for this function
  def html2text(html: String): String = Jsoup.parse(html).text

  def main(args: Array[String]): Unit = {
    isRecipe("https://www.youtube.com/watch?v=-5KAN9_CzSA") match {
        case Some(_) => print("passed")
        case None => print("filtered")
    }
  }
}
