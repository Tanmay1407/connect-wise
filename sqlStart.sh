#!/bin/bash
echo "Starting Docker Compose..."
docker-compose up -d

echo "Waiting for MySQL to initialize..."
while ! docker exec local-mysql-server mysqladmin ping -h"localhost" --silent; do
    sleep 2
done

echo "MySQL is ready!"
echo "You can now start your Spring Boot application."
