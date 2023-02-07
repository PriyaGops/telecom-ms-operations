# Telecom operations microservice

Customer phone numbers are currently, being stored in database. Customer and phone numbers have 1:N mapping. 
Few capabilities associated with phone numbers are provided in this microservice. Please refer to the [API](#API) section below.

## API

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be 
running for the following endpoints to work. 

### Get all phone numbers

Endpoint 

```text
GET /phone-numbers
```
Retrieving phone numbers using CURL

```console
$ curl "http://localhost:8080/phone-numbers"
```
Example output

```json
[
  {
    "phoneNumber": "0477878878",
    "type": "Mobile",
    "status": "activated"
  },
  {
    "phoneNumber": "0255504321",
    "type": "Landline",
    "status": "inactive"
  },
  {
    "phoneNumber": "0477878874",
    "type": "Mobile",
    "status": "activated"
  },
  {
    "phoneNumber": "0477878873",
    "type": "Mobile",
    "status": "activated"
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
    "type": "Mobile",
    "status": "activated"
  },
  {
    "phoneNumber": "0255504321",
    "type": "Landline",
    "status": "inactive"
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
  "status" : "activated"
}
```

Example output

```json
  {
    "phoneNumber": "0477878878",
    "type": "Mobile",
    "status": "activated"
  }
```






