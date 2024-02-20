
# REST-interview-test

***Overview***
Spring Boot REST application for storing and retrieving customer information. 

## Setup / Installation

- Ensure Docker is installed and running (You will need it to run the PostgreSQL database)
- Clone this repository
- Open the project in IntelliJ
- Reload All Maven Projects (Possibly not necessary depending on your settings, but can't hurt)
- Maven clean, then Maven compile

Basic setup should now be complete.

***Running the project***
All the run configurations should already be stored in the project and IntelliJ should recognise them. 

To run the project, either run the "docker-compose up" run configuration, or go to your console and run the command "docker-compose up". This should create a new docker container running a PostgreSQL database with a table named customer. For connection details see *"Connection details for the database"*.

Once you have a database up and running, simply run the "Run" runtime configuration. This should spin up the spring boot application and open to connections on port 8080.

In order to interact with the API, I've created a postman collection that can simply be imported into postman. To do so, you can find details under *"Postman setup"*

**Connection details for the database**:
URL: jdbc:postgresql://localhost:5246/example
Username: example
Password: example

**Postman setup**
You can find the postman json file within the project files under `rest-test-exercise/useful-files/postman_collection.json`. To import this, open postman, click import (around the top left) and drag / drop the json file into the box that opens. Once this is done, you should see a new collection named Rest Test with pre-defined requests for getting customers by id, and adding new customers via a CSV file.

**Running JUnit Tests**
I've added some JUnit tests for ensuring the endpoints are behaving correctly and return the correct responses.
To run, simply run the "Run Tests" run configuration.

## API Documentation
**Controllers**
- Customer Controller (*CustomerController.java*)
	- Get Customers (`/api/customer` || GET || Params: "ids" - Comma separated integers that should match the ids of the customers that you want to request (e.g. 1,2,3))
	- Create Customers (`/api/customer` || POST || Body: "file" - CSV file with the following headers:
	**Please note:** The columns in the CSV can be in **any** order / capitalisation.
		- id
		- name
		- addressLineOne
		- addressLineTwo
		- town
		- county
		- country
		- postcode

**JPA**
- CustomerEntity
	- This is the customer entity that matches to the customer table in the database.
	- Has a static method for converting to the POJO transactional version of Customer (`convertAllToPojo` / `convertToPojo`). Customer also has similar methods for converting back to Entities.

**Services**
- CustomerService
	- A "One stop shop" for all things customer data related. This is where you request and save customer data.
- CSVService
	- Whilst this is within the Services package, this isn't a registered spring service (So don't try and autowire it, just instantiate it with no params)
	- This hold the logic for breaking down a CSV file into a list of customers
