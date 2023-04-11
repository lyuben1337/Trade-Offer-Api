## Prerequisites:
- Java JDK version 17+ should be installed in the system

Check it here https://www.oracle.com/java/technologies/downloads/#java17
or here https://adoptium.net/temurin/releases/ 

## How to Build:
To build the application execute the following commands in the project folder (where pom.xml and mvnw are located): 

```bash
./mvnw clean package # this will build the project
```
For the first time it will download and install Maven version configured in the project settings (`v.3.8.6`)
Next time the cached version will be used without redownloading.

After the build is completed, the folder `/target` will be created with a compiled `.jar` ready to be launched.

## How to Run:
Now you can launch the server for example at port `8085`
(if the option `--server.port=8085` is not provided the default port is `8080`):
```bash
java -jar ./target/*.jar --server.port=8085
```
It may take up to around 15 sec for the server to start 
