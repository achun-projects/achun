#!/bin/bash

## 拉取代码
#cd /root/workspace/gitee/achun/
#git pull

# 构建项目
cd /root/workspace/gitee/achun/achun-file/
mvn clean install -DskipTests

# 启动项目
cd /root/workspace/gitee/achun/achun-file/achun-file-app/target
/usr/bin/java -jar --add-opens java.base/java.math=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.time=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED achun-file-app.jar
