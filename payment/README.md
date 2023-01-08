# How to build docker image

First, you need to:

1. Open Docker Desktop
2. Login to your Docker account. If you don't have access, you may follow the instructions at this
   link: https://docs.docker.com/docker-hub/access-tokens/
3. Enter the following commands:

```
mvn clean install -DskipTests
docker image build -t andreim98/payment-api .
docker push andreim98/payment-api
```
