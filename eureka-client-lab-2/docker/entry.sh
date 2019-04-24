#!/bin/sh
sleep 25
until java -jar /usr/src/eureka-client-lab-2/target/eureka-client-lab-2-0.0.1-SNAPSHOT.jar
do
    echo "Waiting for volume..."
done