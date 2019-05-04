#!/bin/sh

until java -jar /usr/src/RabbitMqLogger/target/logger-0.0.1-SNAPSHOT.jar
do
    echo "Waiting for volume..."
done