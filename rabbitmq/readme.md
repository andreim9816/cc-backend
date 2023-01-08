# How to build docker image

First, you need to:

1. Open Docker Desktop
2. Login to your Docker account. If you don't have access, you may follow the instructions at this
   link: https://docs.docker.com/docker-hub/access-tokens/
3. Enter the following commands:

```
docker image build -t andreim98/rabbitmq .
docker push andreim98/rabbitmq
```
