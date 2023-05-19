#!/bin/bash

cd /home/luokong/workspace/achun/achun-file
git reset --hard origin/master
mvn clean install -DskipTests
cd /home/luokong/workspace/achun/achun-file/achun-file-app/target

java -jar achun-file-app.jar