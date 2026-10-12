# shoe-store-training-backend · 鞋类商城实训后端

仓库地址：[ssy040413/shoe-store-training-backend](https://github.com/ssy040413/shoe-store-training-backend)。

这是一个 Java 鞋类商城实训项目的后端，包含商品、品牌、分类、颜色、尺码、库存、商品图片、订单、用户、管理员、广告和友情链接等接口，也保留了学生、教师相关练习代码。

维护状态：历史实训项目已完成；本次整理后作为归档资料保留，供学习和参考。

## 技术与目录

- Java 8、Maven、Spring Boot 2.4.6
- MyBatis 2.1.4、MySQL、PageHelper、Knife4j
- `src/main/java/com/xys/`：入口 `DemoApplication`，以及 Controller、Service、Mapper、Entity 等后端源码
- `src/main/resources/mapper/`：MyBatis SQL 映射
- `src/main/resources/sql/order_tables.sql`：订单与订单明细表及示例数据
- `uploads/images/`：保留的实训图片素材

这个仓库不包含配套前端。构建产物、IDE 本地配置和新增运行时上传文件由 `.gitignore` 排除，已有图片素材继续保留。

## 本地准备

需要 JDK 8、Maven 和可连接的 MySQL。原配置使用本机 `3306` 端口、数据库名 `user`，服务端口为 `8080`。

**完整数据库初始化脚本待补。** 当前 SQL 只覆盖订单相关表，不能建立所有接口所需的数据库；配置中引用的 `schema.sql`、`data.sql` 也未随仓库提供。请先从原实训环境恢复完整数据库，再启动服务。不要把现有订单 SQL 当成完整初始化方案，也不要反复执行其中的示例插入语句。

| 环境变量 | 默认值 | 用途 |
|---|---|---|
| `DB_USERNAME` | `root` | MySQL 用户名，建议使用本地实训专用账号 |
| `DB_PASSWORD` | 空字符串 | MySQL 密码；数据库有密码时必须设置 |
| `SPRING_DATASOURCE_URL` | 配置文件内的本机 MySQL URL | 可选，覆盖 Spring 数据源地址、端口和数据库名 |
| `SERVER_PORT` | `8080` | 可选，覆盖服务端口 |

数据库凭据从环境变量读取。`.env` 文件不会由 Spring Boot 自动加载，请在启动进程的环境中设置变量；本地密码和配置不要提交到 Git。

## 启动

以下为 Bash / Zsh 命令，在仓库根目录执行。读取密码时不会回显；使用无密码的本地数据库时可直接按回车。

```bash
export DB_USERNAME=root
printf 'MySQL password: '
read -r -s DB_PASSWORD
printf '\n'
export DB_PASSWORD

# 地址不同于原实训环境时，按实际数据库修改：
# export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/user?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false'

mvn org.springframework.boot:spring-boot-maven-plugin:2.4.6:run
```

成功启动后，后端默认地址为 `http://localhost:8080`。这个启动命令需要 Maven 下载依赖，并且不代表已解决完整数据库缺失的问题。

## 复现待补项

- 完整 MySQL 表结构、必要的初始化数据及配套前端仍待补齐。
- `FileUploadUtil.java`、`FileUploadConfig.java` 和 `application.yml` 中保留了原 Windows 实训机的文件路径。换设备运行前，需要将上传目录与静态资源映射一起适配；本次整理没有修改这些业务配置。
- 本次只进行仓库清理和配置静态检查，没有进行依赖安装、应用启动或业务接口验收。
