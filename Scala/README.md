# FRIDGE

A web application that will recommend you recipes depending on the ingredients you have in your fridge. 

A scala application

Pulling recipe from Naver Open API: Search

### Scala Backend

GET url/?query=____________

query = string

Request: http://localhost:9000/?
=양파, 버섯...

Response: {Response{ DishInfo{...}, DishInfo{...}, ...}} 

DishInfo(title: String, link: String, thumbnail: String, ingredients: String)
