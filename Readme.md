## Building movie REST services with Spring Boot and Mysql


## Steps to run movie service with Mysql

1. **Clone the application**

	```bash
	git clone 
	```

2. **Run docker images**
    
	```bash
	docker-compose up
	```

## Access movie REST API(s)

1. **Postman File**

	```bash
	movie-api-collection.postman_collection.json
	```

2. **Swagger**

	```bash
	http://localhost:8080/swagger-ui.html 
	```
 
 ## CRUD Operations
 
 |Operation|Request Method|URL|Response Code|      
 |----|-----|-------| -------|     
 |Create|POST|/movies|201 Created| 
 |Read|GET|/movies/{movieId}|200 OK|
 |Update|PUT|/movies/{movieId}|200 OK|
 | |PATCH|/movies/{movieId}|200 OK| 
 |Delete|DELETE|/movies/{movieId}|204 No Content |
