#!/bin/bash

## 拉取代码
#cd /root/workspace/gitee/gallery/
#git pull

# 构建项目
cd /root/workspace/gitee/achun/achun-gallery/
mvn clean install -DskipTests

# 启动项目
cd /root/workspace/gitee/achun/achun-gallery/achun-gallery-app/target
/usr/bin/java -jar --add-opens java.base/java.math=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.time=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED achun-gallery-app.jar
