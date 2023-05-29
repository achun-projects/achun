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




#!/bin/bash

cd /home/luokong/workspace/achun/achun-gallery

# 执行git pull并将结果保存在变量中
git_output=$(git pull)

# 检查git pull的输出中是否包含"Already up to date."（代表没有新的更新）
if [[ $git_output != *"Already up to date."* ]]; then
    # 如果有新的更新，就执行Maven构建和打包
    mvn clean install -DskipTests
    cd achun-gallery-app
    mvn clean package -DskipTests
else
    # 如果没有新的更新，就打印消息并跳过Maven构建和打包
    echo "No new updates, skipping Maven build and package."
fi

cd /home/luokong/workspace/achun/achun-gallery/achun-gallery-app

java -jar /home/luokong/workspace/achun/achun-gallery/achun-gallery-app/target/achun-gallery-app.jar
