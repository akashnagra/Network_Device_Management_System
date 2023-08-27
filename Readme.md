# Network Device Management System

Welcome to the Network Device Management System! This project is developed using Java Spring Boot and provides a RESTful API for managing network devices.

## Project Overview

The Network Device Management System is a Java Spring Boot application that allows users to manage network devices. It provides APIs for adding, fetching, updating, and deleting devices, as well as retrieving statistics related to device data.

## Prerequisites

- Java 9 or higher, (Use java 17 recomended)
- Maven
- Mysql Database 

## Installation and Setup

1. change the database `Username` and `Password` according to your mysql Credentials in `resources/application.properties` file.
    spring.datasource.username=`your mysql username`
    spring.datasource.password=`your mysql password`

2. Create Database by using Mysql workbench or command line 
   Database Name: - `network_devices`

3. You can change the port if needed Default is 8080. 

4. Build the project: `mvn clean install`
5. Run the application: `mvn spring-boot:run`
6. Access the application API at: `http://localhost:8080`

## Usage

Once the application is up and running, you can use tools like Postman or cURL to interact with the APIs. Refer to the [API Endpoints](api-endpoints) section for details on available endpoints and their usage.

## API Endpoints

- `GET http://localhost:8080/Devices`: Get a list of all devices.
- `GET http://localhost:8080/Devices/{deviceId}`: Get details of a specific device.
- `POST http://localhost:8080/Devices`: Add a new device.

   Sample Data: 
    {
        "name": "Device Name",
        "version": "Device Version",
        "brand": "Device Brand",
        "price": Device price in numeric 
    }
- `POST http://localhost:8080/Devices/add-multiple` : Add Multiple devices or list of devices

   Sample Data: 
    [
        {
            "name": "Device Name",
            "version": "Device Version",
            "brand": "Device Brand",
            "price": Device price in numeric 
        },
        {
            "name": "Device Name",
            "version": "Device Version",
            "brand": "Device Brand",
            "price": Device price in numeric 
        }
    ]
- `PUT http://localhost:8080/Devices/{deviceId}`: Update information of an existing device.
- `DELETE http://localhost:8080/Devices/{deviceId}`: Delete a device.
- `GET http://localhost:8080/Devices/stats/total`: Get the total number of devices.
- `GET http://localhost:8080/Devices/stats/brand-distribution`: Get device distribution by brand.

