## Using MySQL in Spring Boot via Spring Data JPA and Hibernate

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0

#### Start localhost

* Go on the project's root folder, then type:

    $ mvn spring-boot:run

* App is running here : http://localhost:8080/

#### One time setup for Cloud SDK to be able to deploy to Google App Engine
* Install Cloud SDK (Refer to https://cloud.google.com/sdk/docs/quickstarts)
* Set the Cloud SDK installation directory in pom.xml as following example:
```
    <gcloud.directory>/Users/cyoun/dev/google-cloud-sdk</gcloud.directory>
```


#### Deploy to Google App Engine

* Go on the project's root folder, then type:

    $ mvn gcloud:deploy

* App is running here : https://blissful-cell-141318.appspot.com/

lean up
### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  with the database:
    * `/create?email=[email]&name=[name]`: create a new user with an auto-generated id and email and name as passed values.
    * `/delete?id=[id]`: delete the user with the passed id.
    * `/get-by-email?email=[email]`: retrieve the id for the user with the passed email address.
    * `/update?id=[id]&email=[email]&name=[name]`: update the email and the name for the user indentified by the passed id.
