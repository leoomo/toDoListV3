# TodoListV3 🚀

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Next.js](https://img.shields.io/badge/Next.js-15.4.5-black.svg)](https://nextjs.org/)
[![React](https://img.shields.io/badge/React-19.1.0-blue.svg)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.0-blue.svg)](https://www.typescriptlang.org/)
[![MariaDB](https://img.shields.io/badge/MariaDB-10.6+-red.svg)](https://mariadb.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> 现代化的待办事项管理应用，采用前后端分离架构，提供完整的CRUD操作和现代化的用户界面。

## 📋 目录

- [✨ 特性](#-特性)
- [🛠️ 技术栈](#️-技术栈)
- [🚀 快速开始](#-快速开始)
- [📁 项目结构](#-项目结构)
- [🔧 API文档](#-api文档)
- [🧪 测试](#-测试)
- [📦 部署](#-部署)
- [🤝 贡献指南](#-贡献指南)
- [📄 许可证](#-许可证)

## ✨ 特性

### 🎯 核心功能
- ✅ **完整的CRUD操作** - 创建、读取、更新、删除待办事项
- ✅ **状态管理** - 标记完成/未完成状态
- ✅ **智能过滤** - 按完成状态筛选任务
- ✅ **分页支持** - 支持大量数据的分页显示
- ✅ **批量操作** - 一键删除已完成或全部任务
- ✅ **实时更新** - 数据变更实时反映在界面上

### 🎨 用户体验
- 🎨 **现代化UI** - 基于Tailwind CSS的响应式设计
- 📱 **移动友好** - 完美适配各种设备尺寸
- ⚡ **快速响应** - 优化的性能和加载速度
- 🔔 **智能提示** - 操作成功/失败的用户反馈
- 🎯 **直观操作** - 简洁明了的用户界面

### 🔧 技术特性
- 🏗️ **前后端分离** - 灵活的架构设计
- 🔒 **数据验证** - 完善的输入验证和错误处理
- 🌐 **CORS支持** - 跨域资源共享配置
- 📚 **API文档** - 自动生成的Swagger文档
- 🧪 **测试覆盖** - 完整的单元测试和集成测试

## 🛠️ 技术栈

### 后端技术
| 技术 | 版本 | 说明 |
|------|------|------|
| **Java** | 17+ | 编程语言 |
| **Spring Boot** | 3.2.0 | 应用框架 |
| **Spring Data JPA** | 3.2.0 | 数据访问层 |
| **MariaDB** | 10.6+ | 关系型数据库 |
| **Hibernate** | 6.3.1 | ORM框架 |
| **Swagger** | 3.0 | API文档 |
| **JUnit 5** | 5.10.2 | 单元测试 |

### 前端技术
| 技术 | 版本 | 说明 |
|------|------|------|
| **Next.js** | 15.4.5 | React框架 |
| **React** | 19.1.0 | UI库 |
| **TypeScript** | 5.0 | 类型安全 |
| **Tailwind CSS** | 4.0 | 样式框架 |
| **Axios** | 1.11.0 | HTTP客户端 |

## 🚀 快速开始

### 环境要求

- **Java**: 17 或更高版本
- **Node.js**: 18 或更高版本
- **MariaDB**: 10.6 或更高版本
- **Maven**: 3.6 或更高版本

### 1. 克隆项目

```bash
git clone https://github.com/yourusername/todolist-v3.git
cd todolist-v3
```

### 2. 数据库配置

```sql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS todoapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE todoapp;
```

### 3. 后端启动

```bash
# 进入后端目录
cd backend

# 配置数据库连接（可选，默认配置已包含）
# 编辑 src/main/resources/application.yml

# 启动后端服务
mvn spring-boot:run
```

后端服务将在 `http://localhost:8000` 启动

### 4. 前端启动

```bash
# 进入前端目录
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端应用将在 `http://localhost:3000` 启动

### 5. 验证安装

- 🌐 **前端应用**: http://localhost:3000
- 🔧 **后端API**: http://localhost:8000
- 📚 **API文档**: http://localhost:8000/docs
- 💚 **健康检查**: http://localhost:8000/health

## 📁 项目结构

```
todolist-v3/
├── backend/                    # 后端项目
│   ├── src/
│   │   ├── main/java/
│   │   │   └── com/todolist/backend/
│   │   │       ├── controller/     # REST API控制器
│   │   │       ├── service/        # 业务逻辑层
│   │   │       ├── repository/     # 数据访问层
│   │   │       ├── model/          # JPA实体
│   │   │       ├── dto/            # 数据传输对象
│   │   │       ├── config/         # 配置类
│   │   │       └── exception/      # 异常处理
│   │   └── resources/
│   │       └── application.yml     # 应用配置
│   ├── src/test/                   # 测试代码
│   ├── db/                         # 数据库脚本
│   └── pom.xml                     # Maven配置
├── frontend/                   # 前端项目
│   ├── src/
│   │   ├── app/                    # Next.js App Router
│   │   ├── components/             # React组件
│   │   ├── hooks/                  # 自定义Hooks
│   │   ├── lib/                    # 工具库
│   │   └── types/                  # TypeScript类型定义
│   ├── public/                     # 静态资源
│   └── package.json                # 依赖配置
├── spec-req.md                 # 项目需求文档
├── INTEGRATION_SUMMARY.md      # 联调测试总结
└── README.md                   # 项目说明文档
```

## 🔧 API文档

### 基础信息
- **API版本**: v1
- **基础URL**: `http://localhost:8000/api/v1`
- **数据格式**: JSON
- **字符编码**: UTF-8

### 核心接口

| 方法 | 路径 | 描述 | 状态 |
|------|------|------|------|
| `GET` | `/health` | 健康检查 | ✅ |
| `GET` | `/api/v1/todos` | 获取所有待办事项 | ✅ |
| `GET` | `/api/v1/todos/{id}` | 获取单个待办事项 | ✅ |
| `POST` | `/api/v1/todos` | 创建待办事项 | ✅ |
| `PUT` | `/api/v1/todos/{id}` | 更新待办事项 | ✅ |
| `DELETE` | `/api/v1/todos/{id}` | 删除待办事项 | ✅ |
| `DELETE` | `/api/v1/todos/completed` | 删除已完成 | ✅ |
| `DELETE` | `/api/v1/todos/all` | 删除全部 | ✅ |

### 数据模型

```typescript
interface Todo {
  id: number;
  title: string;
  description?: string;
  completed: boolean;
  createdAt: string;
  updatedAt: string;
}

interface TodoListResponse {
  items: Todo[];
  total: number;
  limit: number;
  offset: number;
}
```

📖 **完整API文档**: http://localhost:8000/docs

## 🧪 测试

### 后端测试

```bash
cd backend

# 运行所有测试
mvn test

# 运行特定测试
mvn test -Dtest=TodoServiceTest

# 生成测试报告
mvn surefire-report:report
```

### 前端测试

```bash
cd frontend

# 运行类型检查
npm run type-check

# 运行代码检查
npm run lint

# 构建项目
npm run build
```

### 联调测试

```bash
# 确保后端和前端都在运行
# 访问 http://localhost:3000 进行功能测试
```

## 📦 部署

### 开发环境

```bash
# 后端
cd backend
mvn spring-boot:run

# 前端
cd frontend
npm run dev
```

### 生产环境

```bash
# 后端打包
cd backend
mvn clean package
java -jar target/todo-backend-0.1.0.jar

# 前端构建
cd frontend
npm run build
npm start
```

### Docker部署（推荐）

```dockerfile
# 后端Dockerfile
FROM openjdk:17-jdk-slim
COPY target/todo-backend-0.1.0.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/app.jar"]
```

```bash
# 构建和运行
docker build -t todolist-backend .
docker run -p 8000:8000 todolist-backend
```

## 🤝 贡献指南

我们欢迎所有形式的贡献！请遵循以下步骤：

### 1. Fork项目
点击右上角的"Fork"按钮，创建您的项目副本。

### 2. 创建分支
```bash
git checkout -b feature/your-feature-name
```

### 3. 提交更改
```bash
git add .
git commit -m "feat: add your feature description"
```

### 4. 推送分支
```bash
git push origin feature/your-feature-name
```

### 5. 创建Pull Request
在GitHub上创建Pull Request，我们会尽快 review。

### 代码规范

- **后端**: 遵循Java代码规范，使用Spring Boot最佳实践
- **前端**: 遵循TypeScript规范，使用ESLint和Prettier
- **提交信息**: 使用[Conventional Commits](https://www.conventionalcommits.org/)规范

### 提交类型

- `feat`: 新功能
- `fix`: 修复bug
- `docs`: 文档更新
- `style`: 代码格式调整
- `refactor`: 代码重构
- `test`: 测试相关
- `chore`: 构建过程或辅助工具的变动

## 📄 许可证

本项目采用 [MIT许可证](LICENSE) - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

感谢所有为这个项目做出贡献的开发者！

## 📞 联系我们

- **项目主页**: [GitHub Repository](https://github.com/yourusername/todolist-v3)
- **问题反馈**: [Issues](https://github.com/yourusername/todolist-v3/issues)
- **讨论区**: [Discussions](https://github.com/yourusername/todolist-v3/discussions)

---

⭐ 如果这个项目对您有帮助，请给我们一个星标！ 