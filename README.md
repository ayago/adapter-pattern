
# Hexagonal Architecture Demonstration Project

This is a sample spring boot project which demonstrates how we can use Hexagonal Architecture (aka 
Ports and Adapters pattern) to isolate the domain layer from the http and database adapters.  We want to 
isolate to use this pattern so that we can decouple the domain from the application or infrastructure. 

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
2. Run the app using the main class `DomainPatternApplication`
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

## Improvements

1. Totally isolate the domain layer by moving the domain packages on a separate gradle project without
any dependencies to spring boot
2. Move @Valid on to aggregate (e.g. Order) from service (e.g. OrderService) so that the aggregate fully
enforces the transaction invariant
3. Add unit tests