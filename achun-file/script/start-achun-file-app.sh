#!/bin/bash

cd /root/buckets/workspace/gitee/achun/achun-file
git reset --hard origin/master
mvn clean install -DskipTests
cd /root/buckets/workspace/gitee/achun/achun-file/achun-file-app/target

java -jar achun-file-app.jar