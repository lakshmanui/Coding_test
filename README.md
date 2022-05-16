# Getting Started with SpringBoot Reward Points Application

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)

## Running the application locally

- Import the project from github url
- Run Maven clean install verify
- Run the application as Springboot App
- Open browser and load this url 
- (http://localhost:8080/calculateRewardPoints)

| **name** | **orderId** |     **purchaseDate**     | **price** |
|:--------:|:-----------:|:------------------------:|:---------:|
|   AAAA   |      1      | 2022-01-13T08:15:54.051Z |    533    |
|   AAAA   |      2      | 2022-03-13T08:15:54.051Z |     65    |
|   AAAA   |      3      | 2022-05-13T08:15:54.051Z |   272.32  |
|   AAAA   |      4      | 2022-05-10T08:15:54.051Z |   100.32  |
|   BBBB   |      5      | 2022-01-01T08:15:54.051Z |   100.32  |
|   BBBB   |      6      | 2022-01-15T08:15:54.051Z |   75.32   |
|   BBBB   |      7      | 2022-04-13T08:15:54.051Z |   533.32  |

- Change the customers data as per your requirement in data.json file located in static folder
**Output**
<img width="625" alt="Screen Shot 2022-05-16 at 1 48 22 PM" src="https://user-images.githubusercontent.com/16219395/168652842-d5b3a5c1-c325-428f-a423-765822c12fa7.png">


## Running unit tests

- Right click on the test file and perform run as JUnit Test 

