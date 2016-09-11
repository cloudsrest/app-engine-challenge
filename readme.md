## Using MySQL in Spring Boot via Spring Data JPA and Hibernate

### Build and run

#### Configurations

The `application.properties` file has production properties in it, to use your own database, 
create a file `src/main/resources/application-default.properties` that provides local values.
For example:
```
$ cat src/main/resources/application-default.properties
spring.datasource.url = jdbc:mysql://localhost:3306/challengedb?useSSL=false
spring.datasource.username = root
spring.datasource.password = root
```

#### Prerequisites

- Java 8
- Maven > 3.0


#### To set up local environment

- install: maven, mysql
- go to your mysql instance and run ```create database challengedb```
- clone repo 
- create a file under this path ```src/main/resources/application-default.properties``` that has (modify username/password to your set up):
spring.datasource.url = jdbc:mysql://localhost:3306/challengedb?useSSL=false
spring.datasource.username = root
spring.datasource.password =
- run ```mvn spring-boot:run```  ... this will start your app, and also create the schema
- in a sql editor run the sql in <root_dir>/starterData.sql 
 for example: mysql -uroot -p challengedb <  starterData.sql 
- now there will be users in your db and you should be able to log in as admin/admin or <any_username>/pass
example usernames are: nturner, fcarr, and smcgrath


#### Start localhost

* Go on the project's root folder, then type:
```
    $ mvn spring-boot:run
```
* App is running here : http://localhost:8080/

#### One time setup for Cloud SDK to be able to deploy to Google App Engine
* Install Cloud SDK (Refer to https://cloud.google.com/sdk/docs/quickstarts)
* Set the Cloud SDK installation directory in pom.xml as following example:
```
    <gcloud.directory>/Users/cyoun/dev/google-cloud-sdk</gcloud.directory>
```
* You can also provide Cloud SK installation directory at the time of deployment. If you choose to do so instead of pom.xml, see next section.

#### Deploy to Google App Engine

* Open `application.properties` and make sure spring.datasource.url points to the cloudSQL instance, not localhost database
* Go on the project's root folder, then type:
```
    $ mvn gcloud:deploy
```
* Or you can do the following, if you want to provide Cloud SK installation directory
```
    $ mvn gcloud:deploy -Dgcloud.gcloud_directory=/Users/cyoun/dev/google-cloud-sdk
```

* App is running here : https://blissful-cell-141318.appspot.com/


### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  with the database:
    * `/create?email=[email]&name=[name]`: create a new user with an auto-generated id and email and name as passed values.
    * `/delete?id=[id]`: delete the user with the passed id.
    * `/get-by-email?email=[email]`: retrieve the id for the user with the passed email address.
    * `/update?id=[id]&email=[email]&name=[name]`: update the email and the name for the user indentified by the passed id.
