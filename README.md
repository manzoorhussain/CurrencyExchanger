# Currency Exchange and Discount Calculation Application

## Overview

This Spring Boot application integrates with a third-party currency exchange API to retrieve real-time exchange rates and calculate the total payable amount for a bill in a specified currency after applying applicable discounts.

## Features

- **Currency Exchange**: Integrates with [Open Exchange Rates](https://open.er-api.com/) to retrieve real-time currency exchange rates.
- **Discount Calculation**: Applies various discount rules based on user type and customer tenure.
- **Authentication**: Secures the API endpoints to ensure only authorized users can access them.
- **API Endpoint**: Exposes a `/api/calculate` endpoint to accept bill details and return the net payable amount.

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JUnit and Mockito for testing
- Maven for build management

## API Endpoint

### Calculate Bill

- **Endpoint**: `POST /api/calculate`
- **Request Body**:
  ```json
  {
  "items": [
    {
      "name": "Apple",
      "category": "Fruit",
      "price": 1.2
    },
    {
      "name": "Milk",
      "category": "Dairy",
      "price": 0.99
    }
  ],
  "totalAmount": 2.19,
  "currency": "USD",
  "user": {
    "userType": "EMPLOYEE",
    "customerSince": "2019-08-24T14:15:22Z"
  }
}

## Response
1.410433584

## cURL
curl --location --request POST 'http://localhost:8080/api/calculate?targetCurrency=EUR' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=35FA944021E8A70893867A39CE6C0B80' \
--data-raw '{
"items": [
{
"name": "Apple",
"category": "Fruit",
"price": 1.2
},
{
"name": "Milk",
"category": "Dairy",
"price": 0.99
}
],
"totalAmount": 2.19,
"currency": "USD",
"user": {
"userType": "EMPLOYEE",
"customerSince": "2019-08-24T14:15:22Z"
}
}
'


## Setup Instructions

### Clone the Repository:
git clone  https://github.com/manzoorhussain/CurrencyExchanger.git

### Build the Project:
mvn clean install


### Run the Application:
mvn spring-boot:run


## Testing
mvn test


## API Integration
This application integrates with the Open Exchange Rates API for retrieving real-time exchange rates.
Make sure to replace your-api-key in the application properties with your actual API key.
We use this end point to fetch actual exchange rate (https://open.er-api.com/v6/latest/{base})