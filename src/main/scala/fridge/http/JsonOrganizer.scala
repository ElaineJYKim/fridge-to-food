package fridge.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

case class SearchResult(lastBuildDate: String, total: Int, start: Int, display: Int, items: Seq[Item])
case class Item(title:String, link: String, description: String, thumbnail: String)

case class DishInfo(title: String, link: String, thumbnail: String, ingredients: Seq[Ingredient])
case class Ingredient(name: String, amount: Double, unit: String)


object Mapper {
  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)

  def test(): Unit = {
    val json: String =
      """
        |{
        |"lastBuildDate": "Tue, 15 Dec 2020 12:27:38 +0900",
        |"total": 34762,
        |"start": 1,
        |"display": 10,
        |"items": [
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=1142928&cid=40942&categoryId=31830",
        |"description": " <b>주식</b>회사의 자본을 이루는 단위로서의 금액 및 이를 전제로 한 주주의 권리·의무(주주권).  <b>주식</b>회사는 자본단체이므로 자본이 없이는 성립할 수 없다. 자본은 사원인 주주(株主)의 출자이며, 권리와 의무의... ",
        |"thumbnail": ""
        |
        |},
        |{
        |"title": "어린이도 <b>주식</b> 투자를 할 수 있나요",
        |"link": "https://terms.naver.com/entry.nhn?docId=2847648&cid=47305&categoryId=47305",
        |"description": "세준이네 아빠는 매일 아침 신문이 배달되어 오면 <b>주식</b> 시세표부터 찾아보세요.어떤 날은 주가가 올랐다며 좋아하시고, 또 어떤 날은 주가가 떨어졌다고 속상해 하시지요.<b>주식</b>이 뭐기에 아빠가 그렇게 신경을... ",
        |"thumbnail": "http://openapi-dbscthumb.phinf.naver.net/2831_000_1/20150910120228891_YA8I1ND3B.jpg/s_gn20_46_i1.jpg?type=m160_160"
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=3379189&cid=47332&categoryId=47332",
        |"description": "<b>주식</b>회사가 자금을 조달받기 위해 투자자로부터 돈을 받고 발행하는 증서",
        |"thumbnail": ""
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=549954&cid=46630&categoryId=46630",
        |"description": " <b>주식</b>회사의 사원인 주주가 출자자로서 회사에 대하여 갖는 지분. [내용] <b>주식</b>이라고 할 때는 두 가지의 의미가 있다. 첫째, <b>주식</b>은 자본의 구성부분이다. 즉, <b>주식</b>회사의 자본은 비율적 단위로 세분화되는데... ",
        |"thumbnail": "http://openapi-dbscthumb.phinf.naver.net/2644_000_9/20180912214107311_TFA30KLEG.jpg/9b29a892-24f6-41.jpg?type=m160_160"
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=1142927&cid=40942&categoryId=32097",
        |"description": " 식단 구성상으로 보아 포만감을 느끼기 위하여 일상의 끼니 때 많이 먹는 식품.  한국을 비롯한 동양 여러 나라에서는 쌀이 <b>주식</b>이고 서양에서는 육류이다. 한국에서는 청동기 시대에 해당하는 BC 6세기 경의... ",
        |"thumbnail": "http://openapi-dbscthumb.phinf.naver.net/2765_000_25/20180917204912089_VQB27NTLF.jpg/325472.jpg?type=m160_160"
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=1691220&cid=41908&categoryId=41930",
        |"description": "이러한 자본 결합의 제도화된 형식을 회사라 부르고, <b>주식</b>에 의해 자본 결합이 행해지는 것이 <b>주식</b>회사이다. 다만 자본 결합의 유일한 형식이 <b>주식</b>회사인 것은 아니며, 역사상으로나 일본의 법제상으로도... ",
        |"thumbnail": ""
        |
        |},
        |{
        |"title": "<b>주식</b>이란",
        |"link": "https://terms.naver.com/entry.nhn?docId=3431632&cid=58438&categoryId=58438",
        |"description": "<b>주식</b>이란 <b>주식</b>회사가 발행한 출자증권으로서 이를 보유한 주주는 <b>주식</b> 보유수에 따라 회사의 순이익과 순자산에 대한 지분청구권을 갖는다. 주주는 주주평등의 원칙에 따라 주주가 갖는 <b>주식</b> 수에 따라... ",
        |"thumbnail": "http://openapi-dbscthumb.phinf.naver.net/4522_000_1/20160721182835161_4CJYIFFR1.jpg/bg19_75_i1.jpg?type=m160_160"
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=3656055&cid=42131&categoryId=42131",
        |"description": "<b>주식</b>은 상법상 <b>주식</b>회사의 자본의 구성단위로서의 금액과 주주의 회사에 대한 권리 · 의무를 내용으로 하는 지위라는 두 가지 의미가 있다.일반적으로 주주의 회사에 대한 권리 · 의무를 내용으로 하는... ",
        |"thumbnail": ""
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=1593904&cid=50346&categoryId=50346",
        |"description": "<b>주식</b>영양학사전 일반적인 식사의 중심이 되는 주요한 음식물. 식사 중에서 <b>주식</b>이 차지하는 비율이 큰 것은 우리나라 식생활의 특징으로, 유럽에서는 <b>주식</b>, 부식의 구별이 우리나라만큼 명확하지 않다.... ",
        |"thumbnail": ""
        |
        |},
        |{
        |"title": "<b>주식</b>",
        |"link": "https://terms.naver.com/entry.nhn?docId=2074925&cid=42107&categoryId=42107",
        |"description": "<b>주식</b>은 <b>주식</b>회사에 출자를 한 주주가 주주로서 회사에 대해 가지는 법률상의 권리·의무 단위인 주주권(株主權)으로서의 뜻이 있다. <b>주식</b>의 본질에 대하여는 주주가 주주라는 지위에서 가지고 있는 권리와... ",
        |"thumbnail": ""
        |
        |}
        |]
        |}
        |""".stripMargin

    val r = mapper.readValue[SearchResult](json)
    println(r.items)
  }

  def main(args: Array[String]): Unit = {
    test()
  }
}