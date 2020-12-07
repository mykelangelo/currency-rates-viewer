# currency-rates-viewer

Coding challenge (authorization is working, rates are calculated and displayed, login and registration works, but no
tests and no UI was provided)

## Please note

- Currently, authentication/authorization is done with Basic auth, it can be replaced with JWT solution.
- CSRF can be enabled with Cookie-based CSRF for Angular apps.

## How to run Redis and Postgres

1. go to root folder of the project
2. run ./gradlew composeUp

## How to shut down Redis and Postgres

1. go to root folder of the project
2. run ./gradlew composeDown

## How to run application

1. go to root folder of the project
2. specify your OpenExchange app id as environment variable OPEN_EXCHANGE_RATES_APP_ID (either in the terminal session
   or globally in .bash_profile)
3. run ./gradlew bootRun -Dspring.profiles.active=local
4. work with the app in your REST Client by following the sample CurrencyRatesViewerApplication.http (password should be
   BCrypt-hashed and supplied in `hash` field)

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
