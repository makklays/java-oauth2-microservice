# Java OAuth2 Microservice

<p align="left">
    <!--img src="./src/main/resources/static/images/schema-oauth2-microservice.png" /-->
</p>
<p align="left">
    <img src="./src/main/resources/static/images/banco4.jpg" width="170" />
    <img src="./src/main/resources/static/images/banco1.jpg" width="170" />
    <img src="./src/main/resources/static/images/banco3.jpg" width="170" />
    <img src="./src/main/resources/static/images/cards.jpg" width="170" />
</p>

### Overview 

This microservice handles authentication and authorization using OAuth2 and JWT (signature with private and public keys).
It provides secure token issuance, validation, and user authentication for client applications and downstream services 
via the API Gateway microservice.

This microservice issues a JWT token (signed with a private key) to the user. The user then uses the JWT to access data 
from other microservices. The user sends the JWT token to the API Gateway microservice, which verifies the JWT on its 
side using the public key. If the JWT token has expired, it is refreshed using the Refresh Token.

In this microservice, you can use the following URLs:

`https://oauth2-microservice/api/v1/oauth2/login`

`https://oauth2-microservice/api/v1/oauth2/refresh-token`

### Features

- OAuth2 authentication and authorization
- JWT token issuance and validation
- Refresh token mechanism



