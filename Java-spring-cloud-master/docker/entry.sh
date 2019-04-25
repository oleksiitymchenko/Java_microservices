#!/bin/sh

until java -jar /usr/src/Java-spring-cloud-master/target/SpringBootMSC-0.0.1-SNAPSHOT.jar
do
    echo "Waiting for volume..."
done