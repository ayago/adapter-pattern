
# Hexagonal Architecture Demonstration Project

This is a sample spring boot project which demonstrates how we can use Hexagonal Architecture (aka 
Ports and Adapters pattern) to isolate the domain layer from the http and database adapters.  We want to 
use this pattern so that we can decouple the domain from the application or infrastructure. 

## Project implementation

The project is separated by three packages:

* API - the http adapter layer
* Repositories - the database adapter layer
* Domain - the domain layer which contains the beans and objects to enforce a business transaction

## Spring boot

Spring boot is used on the following

* API - create APIs and Advices using spring-boot-web-starter and initialise an embedded web container using 
spring-boot-starter-undertow
* Repositories - use @Repository bean stereotype for repository implementations
* Domain - use @Service bean stereotype for domain services and used @Validated to enable JSR303 validation
on the domain at Service layer

## How to play with the project

1. Build the gradle project using the wrapper included
2. Run the app using the main class `HexagonalApplication`
3. Send post request to `http://localhost:8080/api/orders` using the following
request body 

`
{
	"orderedItems": [
		{
			"notes": "gdsadsadjasgdjsagjdsajdgsajdgsajdgsajgdjsagdjsagdjasgdjsagdjasgj",
			"itemCode": 12345,
			"quantity": -1
		}
	]
}
`

4. You should be able to get an HTTP 422 error response with body

`
{
    "message": "Cannot process request with field violations",
    "fieldErrors": [
        {
            "field": "command.orderedItems[0].quantity",
            "code": "{javax.validation.constraints.Min.message}",
            "message": "must be greater than or equal to 1"
        },
        {
            "field": "command.orderedItems[0].notes",
            "code": "{javax.validation.constraints.Max.message}",
            "message": "must be less than or equal to 100"
        }
    ]
}
`

## Opportunities from using this approach

Some opportunities we can get from using the Hexagonal Architecture are as follows:

1. Can use different http adapter implementation such as using Jersey instead of
spring without affecting the business logic.
2. Can use a different database without affecting the business logic.
3. Can ensure that changes in business logic will be enforced on different adapters.
4. Can use different adapters such as Kafka for an event driven approach

## Domain Driven Design (DDD)

Hexagonal Architecture is a natural fit to DDD. Using the former allows the DDD
service to be exposed to different adapters such as REST, SOAP without affecting the
internals of the service. In addition, the Repositories needed by the services can be 
updated without affecting the business logic. This gives us an opportunity to scale or 
change the underlying db without worries of affecting the domain. However, it should be 
noted that we still need to ensure that the underlying repository should work accordingly
since these are responsible for restoring the state of the domain aggregate and as well
as saving the state of the aggregate.

In this project, the adapters that are implemented as required by the domain are

1. Repositories
2. Rest Controllers

## Improvements

1. Totally isolate the domain layer by moving the domain packages on a separate gradle project without
any dependencies to spring boot. By doing this, the domain can still be exposed using other frameworks
such as Vert.x, Akka, Jakarta, etc.

2. Move @Valid on to aggregate (e.g. Order) from service (e.g. OrderService) so that the aggregate fully
enforces the transaction invariant

3. Add unit tests