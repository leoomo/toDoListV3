# Todo Backend API

基于 Java Spring Boot 3.0 的 RESTful 待办事项管理 API，支持完整的 CRUD、状态切换、批量删除、分页、过滤、健康检查、Swagger 文档等。

## 技术栈

- Java 17+
- Spring Boot 3.2.0
- Spring Data JPA
- MariaDB
- Lombok
- Swagger (springdoc-openapi)
- JUnit 5 + Mockito

## 功能特性

- ✅ 完整的 CRUD 操作
- ✅ 状态切换（完成/未完成）
- ✅ 批量删除（已完成项目/所有项目）
- ✅ 分页和过滤
- ✅ 数据验证
- ✅ 全局异常处理
- ✅ CORS 跨域支持
- ✅ Swagger API 文档
- ✅ 健康检查
- ✅ 单元测试和集成测试

## 快速开始

### 环境要求

- Java 17+
- Maven 3.6+
- MariaDB 10.5+

### 数据库设置

1. 启动 MariaDB 服务
2. 执行建表脚本：

```bash
mysql -u root -p < db/todoapp.sql
```

### 运行应用

1. 克隆项目并进入后端目录：

```bash
cd backend
```

2. 编译项目：

```bash
mvn clean compile
```

3. 运行应用：

```bash
mvn spring-boot:run
```

4. 访问应用：

- API 基础地址：http://localhost:8000
- Swagger 文档：http://localhost:8000/docs
- ReDoc 文档：http://localhost:8000/redoc
- 健康检查：http://localhost:8000/health

### 运行测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=TodoServiceTest

# 生成测试报告
mvn surefire-report:report
```

## API 接口

### 基础信息

- **基础URL**: `http://localhost:8000`
- **API前缀**: `/api/v1`
- **数据格式**: JSON
- **HTTP状态码**: 遵循RESTful规范

### 主要接口

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | `/health` | 健康检查 |
| GET | `/` | API信息 |
| GET | `/api/v1/todos/` | 获取待办事项列表 |
| GET | `/api/v1/todos/{id}` | 获取单个待办事项 |
| POST | `/api/v1/todos/` | 创建待办事项 |
| PUT | `/api/v1/todos/{id}` | 更新待办事项 |
| DELETE | `/api/v1/todos/{id}` | 删除单个待办事项 |
| DELETE | `/api/v1/todos/completed` | 批量删除已完成项目 |
| DELETE | `/api/v1/todos/all` | 批量删除所有项目 |

### 查询参数

- `completed`: 筛选条件（true/false/all）
- `limit`: 限制返回数量（1-100，默认50）
- `offset`: 偏移量，用于分页（默认0）

### 请求示例

```bash
# 获取所有待办事项
curl -X GET "http://localhost:8000/api/v1/todos/"

# 获取已完成的待办事项
curl -X GET "http://localhost:8000/api/v1/todos/?completed=true"

# 创建待办事项
curl -X POST "http://localhost:8000/api/v1/todos/" \
  -H "Content-Type: application/json" \
  -d '{"title": "学习Spring Boot", "description": "深入学习Spring Boot 3.0"}'

# 更新待办事项
curl -X PUT "http://localhost:8000/api/v1/todos/1" \
  -H "Content-Type: application/json" \
  -d '{"completed": true}'

# 删除待办事项
curl -X DELETE "http://localhost:8000/api/v1/todos/1"
```

## 项目结构

```
src/
├── main/
│   ├── java/com/todolist/backend/
│   │   ├── controller/          # 控制器层
│   │   ├── service/             # 服务层
│   │   ├── repository/          # 数据访问层
│   │   ├── model/               # 实体类
│   │   ├── dto/                 # 数据传输对象
│   │   ├── config/              # 配置类
│   │   ├── exception/           # 异常处理
│   │   └── TodoBackendApplication.java
│   └── resources/
│       └── application.yml      # 配置文件
├── test/
│   └── java/com/todolist/backend/
│       ├── controller/          # 控制器测试
│       ├── service/             # 服务层测试
│       └── TodoBackendApplicationTests.java
└── resources/
    └── application-test.yml     # 测试配置
```

## 配置说明

### 数据库配置

```yaml
spring:
  datasource:
    url: jdbc:mariadb://localhost:3306/todoapp
    username: root
    password: ${DB_PASSWORD:your_password_here}
    driver-class-name: org.mariadb.jdbc.Driver
```

### Swagger配置

```yaml
springdoc:
  api-docs:
    path: /openapi.json
  swagger-ui:
    path: /docs
```

## 开发指南

### 添加新功能

1. 在 `model` 包中定义实体类
2. 在 `repository` 包中创建数据访问接口
3. 在 `service` 包中实现业务逻辑
4. 在 `controller` 包中创建API接口
5. 编写相应的单元测试

### 代码规范

- 使用 Lombok 减少样板代码
- 遵循 RESTful API 设计原则
- 使用 DTO 进行数据传输
- 添加适当的日志记录
- 编写完整的单元测试

## 部署

### 打包

```bash
mvn clean package
```

### 运行JAR文件

```bash
java -jar target/todo-backend-0.1.0.jar
```

### Docker部署

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/todo-backend-0.1.0.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 故障排除

### 常见问题

1. **数据库连接失败**
   - 检查 MariaDB 服务是否启动
   - 验证数据库连接配置
   - 确认数据库和表是否存在

2. **端口占用**
   - 修改 `application.yml` 中的端口配置
   - 或停止占用端口的其他服务

3. **CORS 问题**
   - 检查 CORS 配置
   - 确认前端请求头设置

## 贡献指南

1. Fork 项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

## 许可证

MIT License

## 联系方式

- 项目地址：GitHub Repository
- 问题反馈：GitHub Issues 