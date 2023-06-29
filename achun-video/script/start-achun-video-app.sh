#!/bin/bash

cd /home/luokong/workspace/achun/achun-video

# 执行git pull并将结果保存在变量中
git_output=$(git pull)

# 检查git pull的输出中是否包含"Already up to date."（代表没有新的更新）
if [[ $git_output != *"Already up to date."* ]]; then
    # 如果有新的更新，就执行Maven构建和打包
    mvn clean install -DskipTests
    cd achun-video-app
    mvn clean package -DskipTests
else
    # 如果没有新的更新，就打印消息并跳过Maven构建和打包
    echo "No new updates, skipping Maven build and package."
fi

cd /home/luokong/workspace/achun/achun-video/achun-video-app

java -jar /home/luokong/workspace/achun/achun-video/achun-video-app/target/achun-video-app.jar
