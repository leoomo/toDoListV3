# Todo 应用后端 API

基于 Java Spring Boot 3.0 的 RESTful 待办事项管理 API，支持完整的 CRUD、状态切换、批量删除、分页、过滤、健康检查、Swagger 文档等。

## 技术栈
- Java 17+
- Spring Boot 3.0
- Spring Data JPA
- mariadb
- Lombok
- Swagger (springdoc-openapi)

## 数据库配置
- 地址：localhost:3306
- 用户名：root
- 密码：Zh777888
- 数据库名：todoapp
- 建表 SQL：见 `db/todoapp.sql`

## API接口规范

### 基础信息
- **基础URL**: `http://localhost:8000`
- **API前缀**: `/api/v1`
- **数据格式**: JSON
- **HTTP状态码**: 遵循RESTful规范

### 后端API接口文档

## 📋 文档概览

- **版本**: v0.1.0
- **最后更新**: 2025-08-04
- **Base URL**: `http://localhost:8000`
- **API版本**: `/api/v1`
- **内容类型**: `application/json`
- **认证方式**: 暂无（可扩展JWT认证）

## 🔗 在线文档

- **Swagger UI**: http://localhost:8000/docs
- **ReDoc**: http://localhost:8000/redoc
- **OpenAPI JSON**: http://localhost:8000/openapi.json

---

## 🏥 系统接口

### 健康检查

检查API服务状态

```http
GET /health
```

**响应示例**:
```json
{
  "status": "healthy",
  "service": "TodoListV2 API"
}
```

**状态码**:
- `200 OK` - 服务正常

### 根路径

获取API基本信息

```http
GET /
```

**响应示例**:
```json
{
  "message": "欢迎使用 TodoListV2 API",
  "version": "0.1.0",
  "docs": "/docs",
  "redoc": "/redoc"
}
```

**状态码**:
- `200 OK` - 成功

---

## 📝 待办事项接口

### 获取待办事项列表

获取所有待办事项，支持筛选和分页

```http
GET /api/v1/todos/
```

**查询参数**:

| 参数名 | 类型 | 必填 | 默认值 | 描述 |
|--------|------|------|--------|------|
| `completed` | string | 否 | - | 筛选条件：`true`（已完成）、`false`（未完成）、`all`（全部） |
| `limit` | integer | 否 | 50 | 限制返回数量，范围：1-100 |
| `offset` | integer | 否 | 0 | 偏移量，用于分页 |

**请求示例**:
```bash
# 获取所有待办事项
curl -X GET "http://localhost:8000/api/v1/todos/"

# 获取已完成的待办事项
curl -X GET "http://localhost:8000/api/v1/todos/?completed=true"

# 分页获取待办事项
curl -X GET "http://localhost:8000/api/v1/todos/?limit=10&offset=0"
```

**响应示例**:
```json
{
  "items": [
    {
      "id": 1,
      "title": "完成项目文档",
      "description": "编写技术架构文档",
      "completed": false,
      "created_at": "2025-08-04T12:00:00",
      "updated_at": "2025-08-04T12:00:00"
    }
  ],
  "total": 1,
  "limit": 50,
  "offset": 0
}
```

**状态码**:
- `200 OK` - 成功获取

### 获取单个待办事项

根据ID获取特定的待办事项

```http
GET /api/v1/todos/{todo_id}
```

**路径参数**:

| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| `todo_id` | integer | 是 | 待办事项ID |

**请求示例**:
```bash
curl -X GET "http://localhost:8000/api/v1/todos/1"
```

**响应示例**:
```json
{
  "id": 1,
  "title": "完成项目文档",
  "description": "编写技术架构文档",
  "completed": false,
  "created_at": "2025-08-04T12:00:00",
  "updated_at": "2025-08-04T12:00:00"
}
```

**状态码**:
- `200 OK` - 成功获取
- `404 Not Found` - 待办事项不存在

### 创建待办事项

创建新的待办事项

```http
POST /api/v1/todos/
```

**请求体**:

| 字段名 | 类型 | 必填 | 描述 | 验证规则 |
|--------|------|------|------|----------|
| `title` | string | 是 | 待办事项标题 | 长度：1-255字符 |
| `description` | string | 否 | 待办事项描述 | 长度：0-1000字符 |

**请求示例**:
```bash
curl -X POST "http://localhost:8000/api/v1/todos/" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "学习 FastAPI",
    "description": "完成 TodoListV2 项目开发"
  }'
```

**响应示例**:
```json
{
  "id": 2,
  "title": "学习 FastAPI",
  "description": "完成 TodoListV2 项目开发",
  "completed": false,
  "created_at": "2025-08-04T12:30:00",
  "updated_at": "2025-08-04T12:30:00"
}
```

**状态码**:
- `201 Created` - 成功创建
- `422 Unprocessable Entity` - 数据验证失败

### 更新待办事项

更新现有的待办事项

```http
PUT /api/v1/todos/{todo_id}
```

**路径参数**:

| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| `todo_id` | integer | 是 | 待办事项ID |

**请求体**:

| 字段名 | 类型 | 必填 | 描述 | 验证规则 |
|--------|------|------|------|----------|
| `title` | string | 否 | 待办事项标题 | 长度：1-255字符 |
| `description` | string | 否 | 待办事项描述 | 长度：0-1000字符 |
| `completed` | boolean | 否 | 完成状态 | true/false |

**请求示例**:
```bash
curl -X PUT "http://localhost:8000/api/v1/todos/1" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "更新后的标题",
    "description": "更新后的描述",
    "completed": true
  }'
```

**响应示例**:
```json
{
  "id": 1,
  "title": "更新后的标题",
  "description": "更新后的描述",
  "completed": true,
  "created_at": "2025-08-04T12:00:00",
  "updated_at": "2025-08-04T12:30:00"
}
```

**状态码**:
- `200 OK` - 成功更新
- `404 Not Found` - 待办事项不存在
- `422 Unprocessable Entity` - 数据验证失败

### 删除单个待办事项

删除指定的待办事项

```http
DELETE /api/v1/todos/{todo_id}
```

**路径参数**:

| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| `todo_id` | integer | 是 | 待办事项ID |

**请求示例**:
```bash
curl -X DELETE "http://localhost:8000/api/v1/todos/1"
```

**响应**: 无响应体

**状态码**:
- `204 No Content` - 成功删除
- `404 Not Found` - 待办事项不存在

### 批量删除已完成项目

删除所有已完成的待办事项

```http
DELETE /api/v1/todos/completed
```

**请求示例**:
```bash
curl -X DELETE "http://localhost:8000/api/v1/todos/completed"
```

**响应**: 无响应体

**状态码**:
- `204 No Content` - 成功删除

### 批量删除所有项目

删除所有待办事项

```http
DELETE /api/v1/todos/all
```

**请求示例**:
```bash
curl -X DELETE "http://localhost:8000/api/v1/todos/all"
```

**响应**: 无响应体

**状态码**:
- `204 No Content` - 成功删除

---

## 📊 数据模型

### Todo 对象

```json
{
  "id": 1,
  "title": "待办事项标题",
  "description": "待办事项描述",
  "completed": false,
  "created_at": "2025-08-04T12:00:00",
  "updated_at": "2025-08-04T12:00:00"
}
```

**字段说明**:

| 字段名 | 类型 | 描述 |
|--------|------|------|
| `id` | integer | 唯一标识符，自动生成 |
| `title` | string | 待办事项标题，必填 |
| `description` | string | 待办事项描述，可选 |
| `completed` | boolean | 完成状态，默认false |
| `created_at` | datetime | 创建时间，自动生成 |
| `updated_at` | datetime | 更新时间，自动更新 |

### TodoList 响应

```json
{
  "items": [
    {
      "id": 1,
      "title": "待办事项1",
      "description": "描述1",
      "completed": false,
      "created_at": "2025-08-04T12:00:00",
      "updated_at": "2025-08-04T12:00:00"
    }
  ],
  "total": 1,
  "limit": 50,
  "offset": 0
}
```

**字段说明**:

| 字段名 | 类型 | 描述 |
|--------|------|------|
| `items` | array | 待办事项列表 |
| `total` | integer | 总数量 |
| `limit` | integer | 当前限制 |
| `offset` | integer | 当前偏移量 |

---

## ⚠️ 错误处理

### 错误响应格式

```json
{
  "detail": "错误描述信息"
}
```

### 验证错误格式

```json
{
  "detail": [
    {
      "type": "missing",
      "loc": ["body", "title"],
      "msg": "Field required",
      "input": {}
    }
  ]
}
```

### 常见状态码

| 状态码 | 描述 | 说明 |
|--------|------|------|
| `200` | OK | 请求成功 |
| `201` | Created | 资源创建成功 |
| `204` | No Content | 删除成功，无返回内容 |
| `400` | Bad Request | 请求参数错误 |
| `404` | Not Found | 资源不存在 |
| `422` | Unprocessable Entity | 数据验证失败 |
| `500` | Internal Server Error | 服务器内部错误 |

---

## 🔐 认证与安全

### 当前状态

- **认证方式**: 暂无
- **访问控制**: 公开访问
- **数据验证**: 服务端验证

### 未来扩展

- JWT Token 认证
- API Key 认证
- 用户权限管理
- 请求频率限制

---

## 🧪 测试示例

### 完整的工作流程

```bash
# 1. 检查服务健康状态
curl -X GET "http://localhost:8000/health"

# 2. 创建待办事项
curl -X POST "http://localhost:8000/api/v1/todos/" \
  -H "Content-Type: application/json" \
  -d '{"title": "测试任务", "description": "这是一个测试任务"}'

# 3. 获取所有待办事项
curl -X GET "http://localhost:8000/api/v1/todos/"

# 4. 更新待办事项状态
curl -X PUT "http://localhost:8000/api/v1/todos/1" \
  -H "Content-Type: application/json" \
  -d '{"completed": true}'

# 5. 筛选已完成的待办事项
curl -X GET "http://localhost:8000/api/v1/todos/?completed=true"

# 6. 删除待办事项
curl -X DELETE "http://localhost:8000/api/v1/todos/1"
```

### 错误处理测试

```bash
# 测试数据验证错误
curl -X POST "http://localhost:8000/api/v1/todos/" \
  -H "Content-Type: application/json" \
  -d '{"description": "只有描述，没有标题"}'

# 测试资源不存在
curl -X GET "http://localhost:8000/api/v1/todos/999999"

# 测试无效的查询参数
curl -X GET "http://localhost:8000/api/v1/todos/?limit=1000"
```

---

## 📈 性能说明

### 响应时间

- **健康检查**: < 10ms
- **列表查询**: < 50ms
- **单个操作**: < 30ms
- **批量操作**: < 100ms

### 限制

- **分页限制**: 最大100条/页
- **标题长度**: 最大255字符
- **描述长度**: 最大1000字符
- **并发请求**: 无限制（当前）

---

## 🔄 版本控制

### API版本策略

- **当前版本**: v1
- **版本前缀**: `/api/v1`
- **向后兼容**: 保证兼容性
- **废弃通知**: 提前3个月通知

### 版本历史

| 版本 | 发布日期 | 主要变更 |
|------|----------|----------|
| v1.0.0 | 2025-08-04 | 初始版本，基础CRUD功能 |

---

## 📞 支持与反馈

### 联系方式

- **项目地址**: [GitHub Repository]
- **问题反馈**: [GitHub Issues]
- **文档更新**: 定期更新

### 常见问题

**Q: 如何处理大量数据的分页？**
A: 使用 `limit` 和 `offset` 参数进行分页，建议每页不超过50条。

**Q: 是否支持批量操作？**
A: 目前支持批量删除已完成项目和所有项目，未来会支持批量创建和更新。

**Q: 数据是否持久化？**
A: 是的，数据存储在SQLite数据库中，重启服务后数据不会丢失。

---

*最后更新: 2025-08-04* 