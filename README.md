# Feedback Form

## Feature modelling
1. Use PostgresSQL relational database to store application internal state
2. Create webpage with simple feedback form
3. Create webpage that displays all received feedback forms
4. Write unit tests for business logic

# Documentation

## How to run

1. `sbt run`
    * with PostgreSQL running and credentials inside `application.conf`
2. go to http://localhost:9000/

## Functional Relational Mapping (FRM) library
* [Play Slick documentation](https://www.playframework.com/documentation/2.8.x/PlaySlick)
### Entity class generation
* run `sbt slick` to generate entity classes
* setting is in `build.sbt` inside `slick` variable
* [example tutorial](https://github.com/slick/slick-codegen-example)
