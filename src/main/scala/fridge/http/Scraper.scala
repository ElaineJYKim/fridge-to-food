package fridge.http

import org.jsoup.Jsoup
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._
import scala.util.matching.Regex

// https://index.scala-lang.org/ruippeixotog/scala-scraper/scala-scraper/2.2.0?target=_2.13

object Scraper {
  val browser = JsoupBrowser()

  def extractor(link:String): Option[String] = {
    val doc = browser.get(link)

    if (isRecipe(doc)) {
      getIngredients(doc) match {
        case "" => None
        case ingredients => Some(ingredients)
      }
    } else None

  }

  def isRecipe(doc: Scraper.browser.DocumentType): Boolean = {
    //doc >/~ validator(allText(".tmp_agenda"))(_.contains("재료")) match {
    //  case Left(_) => Some(getIngredients(doc))
    //  case Right(_) => None
    //}

    val tmp_agenda = doc >?> text(".tmp_agenda")
    tmp_agenda match {
      case Some(txt) => txt contains "재료"
      case None => false
    }
  }

  def getIngredients(doc: Scraper.browser.DocumentType): String = {
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

}
