# 环境变量配置说明 🔧

## 数据库配置

### 必需的环境变量

| 变量名 | 描述 | 示例值 | 默认值 |
|--------|------|--------|--------|
| `DB_PASSWORD` | 数据库密码 | `your_actual_password` | `your_password_here` |

### 可选的环境变量

| 变量名 | 描述 | 示例值 | 默认值 |
|--------|------|--------|--------|
| `SERVER_PORT` | 服务器端口 | `8000` | `8000` |
| `SPRING_PROFILES_ACTIVE` | Spring配置文件 | `dev` | `default` |
| `LOGGING_LEVEL_COM_TODOLIST_BACKEND` | 应用日志级别 | `DEBUG` | `DEBUG` |
| `LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_WEB` | Web日志级别 | `DEBUG` | `DEBUG` |
| `LOGGING_LEVEL_ORG_HIBERNATE_SQL` | SQL日志级别 | `DEBUG` | `DEBUG` |

## 配置方法

### 方法1：环境变量（推荐）

```bash
# Linux/macOS
export DB_PASSWORD=your_actual_password
mvn spring-boot:run

# Windows
set DB_PASSWORD=your_actual_password
mvn spring-boot:run
```

### 方法2：.env文件

创建 `.env` 文件（注意：不要提交到版本控制）：

```bash
# .env
DB_PASSWORD=your_actual_password
SERVER_PORT=8000
SPRING_PROFILES_ACTIVE=dev
```

### 方法3：IDE配置

在IDE中设置环境变量：
- IntelliJ IDEA: Run Configuration → Environment Variables
- Eclipse: Run Configuration → Environment

### 方法4：Docker环境变量

```bash
docker run -e DB_PASSWORD=your_actual_password -p 8000:8000 todolist-backend
```

## 安全注意事项

⚠️ **重要安全提醒**：

1. **永远不要**将真实的数据库密码提交到版本控制系统
2. **使用**环境变量或外部配置文件存储敏感信息
3. **确保**`.env`文件已添加到`.gitignore`
4. **定期**更换数据库密码
5. **限制**数据库用户权限

## 开发环境设置

### 快速开始

1. 设置环境变量：
```bash
export DB_PASSWORD=your_actual_password
```

2. 启动应用：
```bash
mvn spring-boot:run
```

3. 验证连接：
```bash
curl http://localhost:8000/health
```

## 生产环境配置

### 推荐配置

```bash
# 生产环境环境变量
export DB_PASSWORD=strong_production_password
export SPRING_PROFILES_ACTIVE=prod
export LOGGING_LEVEL_COM_TODOLIST_BACKEND=INFO
export LOGGING_LEVEL_ORG_HIBERNATE_SQL=WARN
```

### 容器化部署

```yaml
# docker-compose.yml
version: '3.8'
services:
  backend:
    image: todolist-backend
    environment:
      - DB_PASSWORD=${DB_PASSWORD}
      - SPRING_PROFILES_ACTIVE=prod
    ports:
      - "8000:8000"
```

## 故障排除

### 常见问题

1. **数据库连接失败**
   - 检查 `DB_PASSWORD` 环境变量是否正确设置
   - 验证数据库服务是否正常运行
   - 确认数据库用户权限

2. **环境变量未生效**
   - 重启应用服务器
   - 检查环境变量名称是否正确
   - 确认环境变量设置方法

3. **权限问题**
   - 确保数据库用户有足够权限
   - 检查防火墙设置
   - 验证网络连接

## 相关文件

- `application.yml` - 主配置文件
- `application-test.yml` - 测试环境配置
- `.gitignore` - Git忽略文件配置
- `README.md` - 项目说明文档 