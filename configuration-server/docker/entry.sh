#!/bin/sh

until java -jar /usr/src/configuration-server/target/configuration-server-0.0.1-SNAPSHOT.jar
do
    echo "Waiting for volume..."
done