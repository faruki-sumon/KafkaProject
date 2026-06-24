# Kafka Microservices Project

This repository contains two simple Spring Boot microservices that demonstrate producing and consuming Kafka messages:

- KafkaDelivery — produces location updates to a Kafka topic.
- KafkaEndUser — consumes the location updates and logs them.

Repository layout

```
KafkaDelivery/
KafkaEndUser/
```

Quick links

- Delivery app entry: [KafkaDeliveryApplication.java](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/KafkaDeliveryApplication.java#L1)
- Delivery controller: [DeliveryController.java](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/controller/DeliveryController.java#L1)
- Delivery service: [KafkaService.java](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/service/KafkaService.java#L1)
- Delivery constants: [Constants.java](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/constants/Constants.java#L1)
- Delivery config: [KafkaConfig.java](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/config/KafkaConfig.java#L1)

- End-user app entry: [KafkaEndUserApplication.java](KafkaEndUser/src/main/java/com/KafkaUserEnd/KafkaEndUser/KafkaEndUserApplication.java#L1)
- End-user consumer config: [KafkaConfig.java](KafkaEndUser/src/main/java/com/KafkaUserEnd/KafkaEndUser/Config/KafkaConfig.java#L1)
- End-user constants: [Constants.java](KafkaEndUser/src/main/java/com/KafkaUserEnd/KafkaEndUser/Config/Constants.java#L1)
- End-user properties: [application.properties](KafkaEndUser/src/main/resources/application.properties#L1)

Prerequisites

- Java 11 or newer
- Maven (the projects include `mvnw` wrapper; use `./mvnw` or `mvnw.cmd` on Windows)
- Docker & Docker Compose (recommended for running Kafka locally)
 

Build and run (per service)

From the repository root run either (Linux/macOS):

```bash
cd KafkaDelivery
./mvnw spring-boot:run

# in another terminal
cd KafkaEndUser
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd` instead of `./mvnw`:

```powershell
cd KafkaDelivery
mvnw.cmd spring-boot:run

# new terminal
cd KafkaEndUser
mvnw.cmd spring-boot:run
```

Or build jars and run them:

```bash
cd KafkaDelivery
./mvnw clean package
java -jar target/*.jar

cd ../KafkaEndUser
./mvnw clean package
java -jar target/*.jar
```

Configuration

- Both services are configured to use a Kafka broker at `localhost:9092` by default. See:
  - [KafkaDelivery application.properties](KafkaDelivery/src/main/resources/application.properties#L1)
  - [KafkaEndUser application.properties](KafkaEndUser/src/main/resources/application.properties#L1)

Topics

- Topic used: `Location-Update-Topic` (defined in Delivery constants and consumed by EndUser). See:
  - [KafkaDelivery constants](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/constants/Constants.java#L1)
  - [KafkaEndUser constants](KafkaEndUser/src/main/java/com/KafkaUserEnd/KafkaEndUser/Config/Constants.java#L1)

HTTP endpoints

- Delivery service exposes a single endpoint that produces messages:

  - POST `/location/update` — triggers producing a large number of location messages (the controller currently sends 100,000 messages in a loop). See [DeliveryController.java](KafkaDelivery/src/main/java/com/KafkaDeliveryBoy/KafkaDelivery/controller/DeliveryController.java#L1).

  Use with caution — the endpoint generates a high message volume for demo/testing only.

Consumer behavior

- The `KafkaEndUser` service listens to `Location-Update-Topic` and logs each consumed location. See [KafkaConfig.java](KafkaEndUser/src/main/java/com/KafkaUserEnd/KafkaEndUser/Config/KafkaConfig.java#L1).

Testing quickly

- Start Kafka locally (docker-compose above).
- Start both services.
- Trigger the producer:

```bash
curl -X POST http://localhost:8080/location/update
```

- Confirm the consumer logs printed locations in the `KafkaEndUser` service console.

Troubleshooting

- If services fail to connect to Kafka, verify `localhost:9092` is reachable and that your Docker host/advertised listener settings match your environment.
- Adjust `server.port` in `KafkaEndUser/src/main/resources/application.properties` or set `SPRING_APPLICATION_JSON`/env var to override.
- If you see very high throughput causing pressure, reduce or change the loop in `DeliveryController.updateLocation()`.

Next steps / improvements

- Add graceful rate limiting or batching in the producer.
- Add schema (Avro/JSON schema) for message payloads.
- Add health checks and readiness probes.
- Add CI pipeline for building and testing.

License

This project is provided as-is for demo purposes.
