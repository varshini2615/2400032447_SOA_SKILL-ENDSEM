# Spring Boot JWT Generation

A small Spring Boot REST API that accepts a JSON username and role, then returns a signed JSON Web Token (JWT).

## Requirements

- Java 17+
- Maven 3.6+

## Run the application

From this project directory:

```bash
mvn spring-boot:run
```

The application starts at `http://localhost:8080`.

## Swagger UI

Open:

`http://localhost:8080/swagger-ui.html`

Expand **JWT**, select `POST /api/jwt/generate`, click **Try it out**, and submit:

```json
{
  "username": "john",
  "role": "ADMIN"
}
```

## Test with curl

```bash
curl -X POST http://localhost:8080/api/jwt/generate \
  -H "Content-Type: application/json" \
  -d '{"username":"john","role":"ADMIN"}'
```

Example response shape:

```json
{
  "tokenType": "Bearer",
  "token": "eyJ...",
  "username": "john",
  "role": "ADMIN"
}
```

The token's JWT payload contains:
- `sub`: username
- `role`: supplied role
- `iat`: issued-at time
- `exp`: expiration time

## Project structure

```text
spring-boot-jwt-generation/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/jwtgeneration/
    │   │   ├── JwtGenerationApplication.java
    │   │   ├── config/JwtConfig.java
    │   │   ├── controller/JwtController.java
    │   │   ├── dto/JwtRequest.java
    │   │   ├── dto/JwtResponse.java
    │   │   └── service/JwtService.java
    │   └── resources/application.properties
    └── test/java/com/example/jwtgeneration/
        └── JwtGenerationApplicationTests.java
```

## Important security note

The key in `application.properties` is only a demo key. Do not use it in production.
Set `app.jwt.secret` to a securely generated Base64-encoded random key of at least
32 bytes, preferably through an environment variable or secret manager. Never commit
production signing keys to source control.

For example, generate a key with:

```bash
openssl rand -base64 32
```

Then set the environment variable `APP_JWT_SECRET` to the generated value. Spring
Boot maps it to `app.jwt.secret`.
