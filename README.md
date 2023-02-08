# Telecom operations microservice

Customer phone numbers are currently, being stored in database. Customer and phone numbers have 1:N mapping. 
Few capabilities associated with phone numbers are provided in this microservice. Please refer to the [API](#API) section below.

## Requirements

The project requires [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) or
higher.

The project makes use of Gradle and uses
the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html), which means you don't need Gradle
installed.

## Useful Gradle commands

The project makes use of Gradle and uses the Gradle wrapper. Below are some gradle commands for some common tasks such as building
the project or running it.

### List all Gradle tasks

List all the tasks that Gradle can do, such as `build` and `test`.

```console
$ ./gradlew tasks
```

### Build the project

Compiles the project, runs the test and then creates an executable JAR file

```console
$ ./gradlew build
```

Run the application using Java and the executable JAR file produced by the Gradle `build` task. The application will be
listening to port `8080`.

```console
$ java -jar build/libs/telecom-ms-operations-0.0.1-SNAPSHOT.jar
```

### Run the tests


- Run tests

  ```console
  $ ./gradlew test
  ```

### Run the application

Run the application which will be listening on port `8080`.

```console
$ ./gradlew bootRun
```

## API

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be 
running for the following endpoints to work. 

### Get all phone numbers

Endpoint 

```text
GET /phone-number/all
```
Retrieving phone numbers using CURL

```console
$ curl "http://localhost:8080/phone-number/all"
```
Example output

```json
[
  {
    "phoneNumber": "0477878878",
    "customerId": 0,
    "activated": true
  },
  {
    "phoneNumber": "0255504321",
    "customerId": 1,
    "activated": false
  },
  {
    "phoneNumber": "0477878874",
    "customerId": 2,
    "activated": false
  },
  {
    "phoneNumber": "0477878873",
    "customerId": 3,
    "activated": true
  }
]
```

### View all phone numbers of a given customer

Endpoint

```text
GET /customer/{customerId}/phone-numbers
```
Parameters

| Parameter      | Description                                  |
|----------------|----------------------------------------------|
| `customerId`   | customer id associated with the phone number |

Retrieving all phone numbers of a given customer using CURL

```console
$ curl "http://localhost:8080/customer/12/phone-numbers"
```

Example output

```json
[
  {
    "phoneNumber": "0477878878",
    "customerId": 3,
    "activated": true
  },
  {
    "phoneNumber": "0255504321",
    "customerId": 4,
    "activated": false
  }
]
```

### Activate a phone number

Endpoint 

```text
PATCH /customer/{customerId}/{phoneNumber}/activate
```

Parameters

| Parameter      | Description                                  |
|----------------|----------------------------------------------|
| `customerId`   | customer id associated with the phone number |
| `phoneNumber`  | phone number that needs to be activated      |

Example of request body

```json
{
  "activated": true
}
```

Example output

```json
  {
    "phoneNumber": "0477878878",
    "customerId": 3,
    "activated": true
  }
```

### Swagger Hub Url
[API spec published in Swagger Hub](https://app.swaggerhub.com/apis/PRIYANKAGOPALAN25/telecom-ms-operations/1.0.0#/PhoneNumber)






