# currency-rates-viewer
Trial task (authorization is working, rates are calculated and displayed, login and registration works, but no tests and no UI was provided)

## How to run Redis and Postgres
1. go to root folder of the project
2. run docker-compose up

## How to shut down Redis and Postgres
1. go to root folder of the project
2. run docker-compose down

## How to run application
1. go to root folder of the project
2. run docker-compose up
3. run ./gradlew
4. work with the app in your browser
5. run docker-compose down

## How to check if app is up
1. visit localhost:8080/actuator/health
2. you should see {"status":"UP"}

## How to remove all data
1. run docker-compose down --volumes

## How to register and login and see rates finally
1. run the application
2. visit POST /register in Postman and provide user details in request body (e.g. {"email":"i@i.ua", "hash":"hash"})
3. visit POST /login in Postman and provide valid user details in request body (e.g. {"email":"i@i.ua", "hash":"hash"})
4. visit GET /rates/{currency}, where currency is one of "EUR", "USD", "GBP", "NZD", "AUD", "JPY"
