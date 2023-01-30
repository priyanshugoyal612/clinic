# “Vets and Pets” Application By Priyanshu Goyal
This application is for clinic for Vets and Pets, This contains one repository or main project i.e. 
clinic (compete(Backend+ Frontend)) - Spring Boot Application

Technical clarification
• Spring Boot
• Spring Security
• Spring Thymeleaf
• Maven
• H2 database(H2 is used because  extremely fast database engine and in memory database does not require any set of installation for this kind of application)


## How to run
* To run Spring Boot Application please do followings:

* Check for JDK on your environment(Spring Boot created with Java 17)
* Open the path that you downloaded the Backend project and go to the same level on pom.file
  * For windows: mvnw.cmd clean test install 
  * For Unix : mvnw clean test install
  
* To Start the application
  * Windows: mvnw.cmd spring-boot:run
  * Unix: mvnw spring-boot:run

* After application started you can check
  * http://localhost:9200/
  