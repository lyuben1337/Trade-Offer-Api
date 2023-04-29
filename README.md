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
For the first time it will download and install Maven version configured in the project settings (`v.3.8.6`)
Next time the cached version will be used without redownloading.

After the build is completed, the folder `/target` will be created with a compiled `.jar` ready to be launched.

## How to Run:
Now you can launch the server:
```bash
java -jar ./target/*.jar
```
It may take up to around 15 sec for the server to start 
