# achun

[小阿蠢|官网地址](http://achun.site)

小阿蠢是一个文件助手。

文件助手应该是什么样子的？
我暂时还没有具体的答案，但我想，它应该是好用的，智能的，能帮我管理好文件的，而不只是帮我存储文件。




## 介绍

做这个项目完全是自己兴趣驱动的，所有的需求都来自于我自己。
之所以自己来做也是因为没有找到合适的，好用的文件管理工具。
而网盘之类的，本质上还是在解决存储的问题，而且，不是基于本地的。会存在成本问题导致使用不佳或者其他的问题。

## 规划

目前这个项目实现的功能有：相册管理，视频管理。之后会继续优化这两个功能。
也会再继续添加音乐播放，文档管理，文本阅读等模块。

#### 软件架构

使用以下技术：
- SpringBoot3
- Java17
- MySQL8
- Vue3
- ElementUI


#### 安装教程

需要自己打包，等项目成熟后，会提供docker安装。
等待更新

#### 使用说明

等待更新

## 开发进度和未来计划
目前已经实现了文件的上传，下载，本地探测等。
实现了相册，视频的基础功能。

下一步的目标是继续优化维护已有功能，在此基础上逐步添加新特性。
2023年Q3季度添加音频项目，实现在移动端的基础功能。因为现学现做android开发，进度不一定能保证。

Q4季度把用户和安全这方面再加强下，部署一个示例项目开放至公网。

明年的事情，明年再说。


## 技术栈

### 模块

| 项目                   | 说明                      |
|----------------------|-------------------------|
| achun-app-support    | app支持项目                 |
| achun-client-support | client支持项目              |
| achun-eureka         | SpringCloud eureka组件    |
| achun-gateway        | SpringCloud 网关组件        |
| achun-file           | 文件信息服务                  |
| achun-updown         | 文件上传下载服务，包括文件的转码处理和删除等等 |
| achun-user           | 用户信息服务                  |
| achun-gallery        | 相册服务                    |
| achun-video          | 视频服务                    |

### 框架

| 技术          | 版本       | 说明       | 文档                                   |
|-------------|----------|----------|--------------------------------------|
| SpringBoot  | 3.1.0    |          | [文档](https://docs.spring.io/spring-boot/docs/current/reference/html/) |
| SpringCloud | 2022.0.2 |          | [文档](https://docs.spring.io/spring-cloud/docs/current/reference/html/) |
| MySQL       | 8.0.32   |          | [文档](https://dev.mysql.com/doc/refman/8.0/en/) |
| RabbitMQ    |          |          | [文档](https://www.rabbitmq.com/documentation.html) |
| Redis       |          |          | [文档](https://redis.io/docs/about/) |
| Hutool      | 5.8.18   |   一个小而全的Java工具类库       | [文档](https://doc.hutool.cn/pages/index/) |
| Sa-Token    |  1.35.0.RC     | 用于用户登录校验 | [文档](https://sa-token.cc/doc.html#/) |
