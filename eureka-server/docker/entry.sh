#!/bin/sh

until java -jar /usr/src/eureka-server/target/demo-0.0.1-SNAPSHOT.jar
do
    echo "Waiting for volume..."
done