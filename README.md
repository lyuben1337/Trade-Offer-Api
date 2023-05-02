## Prerequisites:
- Java JDK version 17+ should be installed in the system

Check it here https://www.oracle.com/java/technologies/downloads/#java17
or here https://adoptium.net/temurin/releases/ 

- Gradle should be installed in the system
https://gradle.org/install/

## How to Build:
To build the application execute the following commands in the project folder (where build.gradle are located): 

```bash
gradle build # this will build the project
```

After the build is completed, the folder `/build/libs/` will be created with a compiled `.jar` ready to be launched.

## How to Run:
Now you can launch the server at port 8085 (if the option --server.port=8085 is not provided the default port is 8080) and with admin login and password for secured endpoints (if the options --trade-offer-api.login=mylogin and --trade-offer-api.password=mypassword are not provided the default login is admin and password is admin):
```bash
java -jar build/libs/<file_name>.jar --server.port=8085 --trade-offer-api.login=mylogin --trade-offer-api.password=mypassword
```
It may take up to around 15 sec for the server to start 
