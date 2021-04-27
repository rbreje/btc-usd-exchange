# btc-usd-exchange

This is a simple microservice developed using SpringBoot, Java, Lombok, SpringJPA, H2 & Gradle as technologies. The H2 database beyond it is populated constantly by a separate thread which is making requests to a more widely used API provided by cex.io. The update time is configurable into the application properties.

# REST API Endpoints

* /api/v1/btc-usd/latest

This endpoint is used to retrieve the latest record saved into the database. You can call this endpoint and the application will return the latest value everytime.

* /api/v1/btc-usd/range/{startDate}/{endDate}

This endpoint is used to retrieve a list of rates between two specific dates. The date format is configurable by the application deployer.
