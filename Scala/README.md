# HttpServer

Pulling recipe from Naver Open API: Search


## Run
```
sbt run
```


## Request

**Sample Request:   http://localhost:9000/?query={list of ingredients}**

**Url** >  http://localhost:9000

**Parameter** >  query: string (ex. '양파, 버섯, 바나나')

**Response**:
List of DishInfos

``` Json
{"Recipes": [DishInfo, DishInfo, ...]}
```
Where DishInfo is
``` Scala
case class DishInfo(title: String, link: String, thumbnail: String, ingredients: String)
``` 


## Dependencies
+ ScalaVersion := 2.13.3
+ akkaHttpV := 10.2.1
+ akkaV := 2.6.10
+ Scala Scraper
+ fasterXML/Jackson


## Next Steps

1. Optimize search result filtering
2. Add parameters for specifying # of results wanted
3. Diversify results based on different combinations of querys
4. Improve ingredient extraction from document