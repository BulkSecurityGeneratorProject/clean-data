**Table of Contents**

- [Problem](#problem)
- [Development](#development)
- [Design Decisions](#design-decisions)
- [Screenshots](#screenshots)

## Problem

As part of the system architecture at a data processing company, you need to design a Service to clean data produced by another service (the client).  

The data is a list of category sub-category pairs. For example, one set of data might be:

Category | Subcategory
------------ | -------------
PERSON | Bob Jones
PLACE |	Washington
PERSON | Mary
COMPUTER |	Mac
PERSON |	Bob Jones
OTHER |	Tree
ANIMAL |	Dog
PLACE |	Texas
FOOD |	Steak
ANIMAL |	Cat
PERSON | Mac

There is a list of valid categories managed by your service. By default, the valid categories are:

Category |
------------
PERSON |
PLACE |
ANIMAL |
COMPUTER |
OTHER |

When your service receives data from a client, it must process the data, removing duplicate (category, subcategory) pairs and invalid categories. The order of entries in the input data must be preserved, with the duplicates and invalid categories removed. The output must also include the count of entries for each valid category, sorted by the number of valid, unique entries.
Sample output for the sample input:

Category | Subcategory
------------ | -------------
Category |	Subcategory
PERSON	 | Bob Jones
PLACE	| Washington
PERSON	| Mary
COMPUTER	| Mac
OTHER	| Tree
ANIMAL	| Dog
PLACE	| Texas
ANIMAL	| Cat
PERSON	| Mac

Category |	Count
------------ | -------------
PERSON	| 3
PLACE	| 2
ANIMAL	| 2
COMPUTER	| 1
OTHER	| 1

In addition to processing input data, your service must also provide the ability to add to,  delete from, and list the valid categories. Once the category list is modified, subsequent processing requests will apply the new category list to the input data. While a real-world system would use a permanent datastore for the category information (so the current list of categories would be preserved if the service needs to be restarted), it is sufficient for this exercise to maintain the category list in memory.
Please implement a REST Service that provides the above functionality. It is up to you to define the input and output data formats, as well as the REST endpoints that are used for data processing and category management. You must also describe how to deploy and monitor your service, as well as any changes that would be required to load balance your service.
## Development

To run the project, clone it from github. In the root directory run:

    ./mvnw

To access the administration console for the three web services go to http://localhost:8080

The REST Service Endpoints
* http://localhost:8080/api/category
* http://localhost:8080/api/subcategory-entries

Swagger Endpoint
* http://localhost:8080/swagger-ui/index.html    

## Design Decisions

* Used Jhipster to create shell for the application. It creates Java based web applications using industry standards and best practices. One drawback is that it creates a lot of unnecessary code, however pros outweighs cons. Removing unnecessary code is pretty simple task. Once the extra code is peeled away your left with a well structured application that leverages common design patterns.
* The Tech Stack used here is Java 8, SpringBoot, SpringMVC, Angular, Hibernate ,HazelCast,  PostGres and Liquibase. Services can be easily deployed to docker containers for microservices.
*  OAuth2 Security is included. Requestors must authenticate and receive bearer token and pass this for each request. It includes a expiry time that indicates how long the token is valid. Ideally this service would be put behind an API gateway to provide access control,security filtering, analytics and logging features. 
* Application level monitoring and logs can be viewed from the administration console. In a production enterprise system would likely look at third party instrumentation (splunk,new relic,cloudwatch,etc...) to provide more robust monitoring options.
* For persistence operations, most are straight forward utilizing Spring JPA Repository.
* UnitTest Cases were created for Service Layer operations that clean data and get count info. Service side code coverage should be near 100%. Unit test code coverage for UI layer at 95%.
* All business logic resides in the the services layer separate from Resources classes, with the idea being keep  REST Resources simple, storing business logic together in service classes.
*  The application can be auto-scaled, it includes HazelCast Web Session clustering enabled. Deploying this is rather simple and can be done in roughly an hour on AWS. For a quick deployment I would recommend deploying the war for this service on to AWS Elastic Bean Stalk which has an Apache HTTP + Tomcat setup and tie it to a PostGres RDS instance. Autoscaling Policy can be configured within EBS to start the application with a minimum of 2 instances and maximum of five. New instances are added when the Average CPU Utilization is greater than 75% for five consecutive minutes. Policy should also include an alarm to notify system administrators each time a new instance is created.
*  Debated on whether to include a UI or not. Swagger is enabled on the application so one could test the service operations by passing raw JSON strings within Swagger UI. Opted to include a custom UI to simulate the real world need for most applications to have an Administration Console.
* The database schema could be improved by adding a unique constraint on SubcategoryEntry entity for category and subcategory fields.  

## Screenshots
Screenshots of the working application located in /documenation/screenshots folder, see featured screen grabs below.

###### Home Page
![Image of Home Page](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/logged-in.png
)
###### Navigation
![Image of Navigation](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/navigation.png
)
###### Categories List
![Image of Categories List](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/categories.png
)
###### Add Category
![Image of Add Categories](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/add-category.png)
###### Subcategories API Admin Client
![Image of Sample Client](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/add-subcategories-client.png)
###### Subcategories Processing Summary
![Image of Output Screen 1](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/add-subcategories-client-output.png)
###### Swagger
![Image of Swagger](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/swagger.png)
###### Monitoring Application Metrics
![Image of Metrics](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/application-metrics.png)
###### Monitoring Logs
![Image of Monitoring Logs](https://github.com/compucloud/clean-data/blob/master/documentation/screenshots/logging.png)
