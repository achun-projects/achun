#!/bin/bash

cd /home/luokong/workspace/achun/achun-file
git pull
mvn clean install -DskipTests

cd achun-file-app
mvn clean package -DskipTests

java -jar ./target/achun-file-app.jar