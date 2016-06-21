# weather-app
### Requirements: 
JDK 1.8.0_40 or higher & Maven 3.3 or higher

### Implementation Details:
1. The application mantains a self-sustaining weather cache which is updated every 5 minutes from openWeatherMap. 
2. This is done to ensure optimal response times for concurrent weather requests for the same city since weather patterns usually dont change every 5 minutes. The server then returns the cached reponses rather than doing a remote lookup to OpenWeatherMap.
3. I have leveraged Spring framework(spring-boot libaries specifically - hence there is no XML configuration) to build a RESTful service which follows the traditional MVC pattern - now supported via Spring MVC framework.
4. I have used the OpenWeatherMap JSON response objects & de-serialized them to domain objects which are then used by the views.

### Setup instructions:
1. Clone the project to your local file system.
2. Run "mvn clean install" - this should build the project & run the tests.
3. Post a good build, run "mvn spring-boot:run" from the command line. This will deploy the war file to local tomcat container packaged with spring-boot libs & start tomcat locally.
4. On a browser , open the following URL : localhost:8080/input.
5. You should now see the input form. Enter a city of your choice & press Submit.
6. Post submitting the request , you will see an HTML response back with weather details of the city.

### Todo:
1. Due to lack of time , I could not add more unit tests. For a production rollout, I would increase unit test coverage & add  integration tests.
2. I would add property files per environment - dev/test/uat/prod.
3. Due to lack of time , the weather details(dates/sunrise & sunsets) dont have the precise formatting as specified in the question.This would need to be amended as well before a production rollout.
4. The service also provides startup & shutdown hooks which could be exposed via an "AdminController" to a admin UI - which could then be used for phased startup & shutdown of the service.




