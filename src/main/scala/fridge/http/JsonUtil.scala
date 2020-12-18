package fridge.http

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

case class SearchResult(lastBuildDate: String, total: Int, start: Int, display: Int, items: Seq[Item])
case class Item(title:String, link: String, description: String, thumbnail: String)

case class DishInfo(title: String, link: String, thumbnail: String, ingredients: String) // TODO: ingredients: Seq[Ingredient]
case class Ingredient(name: String, amount: Double, unit: String)

case class Response(Recipes: Seq[DishInfo])

object JsonUtil {
  // https://coderwall.com/p/o--apg/easy-json-un-marshalling-in-scala-with-jackson

  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toJson(value: Map[Symbol, Any]): String = {
    toJson(value map { case (k,v) => k.name -> v})
  }

  def toJson(value: Any): String = {
    mapper.writeValueAsString(value)
  }

  def toMap[V](json:String)(implicit m: Manifest[V]) = fromJson[Map[String,V]](json)

  def fromJson[T](json: String)(implicit m : Manifest[T]): T = {
    mapper.readValue[T](json)
  }

}
