# Java 21 - Javalin 6 - Hibernate 7

## Requirements

 - Java 21
 - Docker (running)

## Tech stack

- Java 21
- Javalin 6
- Hibernate 7
- PostgreSQL
- Docker


## Test

```
###
POST http://localhost:8080/students
Content-Type: application/json

{
  "name": "student1"
}

###
GET http://localhost:8080/students

###
POST http://localhost:8080/courses
Content-Type: application/json

{
  "name": "course1"
}

###
GET http://localhost:8080/courses

###
POST http://localhost:8080/register
Content-Type: application/json

{
  "idStudent": 1,
  "idCourse": 1
}


```