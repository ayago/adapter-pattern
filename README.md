
# Adapter Pattern/Hexagonal Demonstration Project

This is a sample spring boot project which shows how to isolate the domain layer
from the http and database adapters.  We want to isolate the domain layer so that we can decouple
it from the application or infrastructure. 

## Adapter Pattern/Hexagonal

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

## Improvements

1. Totally isolate the domain layer by moving the domain packages on a separate gradle project without
any dependencies to spring boot
2. Move @Valid on to aggregate (e.g. Order) from service (e.g. OrderService) so that the aggregate fully
enforces the transaction invariant