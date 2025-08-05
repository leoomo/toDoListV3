# 贡献指南 🤝

感谢您对 TodoListV3 项目的关注！我们欢迎所有形式的贡献，包括但不限于：

- 🐛 Bug 报告
- 💡 功能建议
- 📝 文档改进
- 🔧 代码优化
- 🧪 测试用例
- 🌐 国际化支持

## 📋 目录

- [🚀 快速开始](#-快速开始)
- [🔧 开发环境](#-开发环境)
- [📝 代码规范](#-代码规范)
- [🧪 测试指南](#-测试指南)
- [📤 提交PR](#-提交pr)
- [🎯 项目路线图](#-项目路线图)

## 🚀 快速开始

### 1. Fork 项目

点击 GitHub 页面右上角的 "Fork" 按钮，创建您自己的项目副本。

### 2. 克隆仓库

```bash
# 克隆您的 fork
git clone https://github.com/YOUR_USERNAME/todolist-v3.git

# 添加上游仓库
git remote add upstream https://github.com/original-owner/todolist-v3.git

# 验证远程仓库
git remote -v
```

### 3. 创建分支

```bash
# 更新主分支
git checkout main
git pull upstream main

# 创建功能分支
git checkout -b feature/your-feature-name
# 或者
git checkout -b fix/your-bug-fix
```

## 🔧 开发环境

### 后端开发环境

```bash
# 进入后端目录
cd backend

# 安装依赖（Maven会自动下载）
mvn clean install

# 启动开发服务器
mvn spring-boot:run
```

### 前端开发环境

```bash
# 进入前端目录
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

### 数据库设置

```sql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS todoapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE todoapp;
```

## 📝 代码规范

### 后端代码规范

#### Java 代码风格

- 使用 **4个空格** 进行缩进
- 类名使用 **PascalCase**
- 方法名和变量名使用 **camelCase**
- 常量使用 **UPPER_SNAKE_CASE**

```java
public class TodoService {
    private static final String DEFAULT_SORT_FIELD = "created_at";
    
    public TodoResponse createTodo(TodoRequest request) {
        // 实现逻辑
    }
}
```

#### Spring Boot 最佳实践

- 使用 `@RestController` 注解控制器
- 使用 `@Service` 注解服务层
- 使用 `@Repository` 注解数据访问层
- 使用 DTO 进行数据传输

```java
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    
    private final TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
}
```

### 前端代码规范

#### TypeScript 规范

- 使用 **2个空格** 进行缩进
- 组件名使用 **PascalCase**
- 函数名和变量名使用 **camelCase**
- 接口名使用 **PascalCase**

```typescript
interface TodoItem {
  id: number;
  title: string;
  completed: boolean;
}

const TodoComponent: React.FC<TodoProps> = ({ todo }) => {
  const handleToggle = () => {
    // 处理逻辑
  };
  
  return (
    <div className="todo-item">
      {/* JSX 内容 */}
    </div>
  );
};
```

#### React 最佳实践

- 使用函数组件和 Hooks
- 使用 TypeScript 进行类型检查
- 使用 Tailwind CSS 进行样式设计
- 使用 ESLint 和 Prettier 进行代码格式化

### 提交信息规范

我们使用 [Conventional Commits](https://www.conventionalcommits.org/) 规范：

```bash
# 格式
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

#### 提交类型

| 类型 | 描述 | 示例 |
|------|------|------|
| `feat` | 新功能 | `feat: add user authentication` |
| `fix` | Bug修复 | `fix: resolve API endpoint issue` |
| `docs` | 文档更新 | `docs: update README installation guide` |
| `style` | 代码格式 | `style: format code with prettier` |
| `refactor` | 代码重构 | `refactor: simplify todo creation logic` |
| `test` | 测试相关 | `test: add unit tests for TodoService` |
| `chore` | 构建工具 | `chore: update dependencies` |

#### 提交示例

```bash
# 简单提交
git commit -m "feat: add todo filtering functionality"

# 带作用域的提交
git commit -m "feat(api): add pagination support for todo list"

# 带详细描述的提交
git commit -m "fix: resolve CORS issue in production

- Add proper CORS configuration for production environment
- Update security headers
- Fix cross-origin request handling

Closes #123"
```

## 🧪 测试指南

### 后端测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=TodoServiceTest

# 运行特定测试方法
mvn test -Dtest=TodoServiceTest#testCreateTodo

# 生成测试报告
mvn surefire-report:report
```

### 前端测试

```bash
# 运行类型检查
npm run type-check

# 运行代码检查
npm run lint

# 运行测试（如果有配置）
npm test

# 构建项目
npm run build
```

### 测试覆盖率要求

- **后端**: 至少 80% 的代码覆盖率
- **前端**: 至少 70% 的代码覆盖率

## 📤 提交PR

### 1. 准备提交

```bash
# 确保代码通过所有测试
mvn test  # 后端
npm run lint  # 前端

# 提交更改
git add .
git commit -m "feat: your feature description"

# 推送到您的 fork
git push origin feature/your-feature-name
```

### 2. 创建 Pull Request

1. 访问您的 GitHub fork 页面
2. 点击 "Compare & pull request" 按钮
3. 填写 PR 标题和描述
4. 选择目标分支（通常是 `main`）
5. 提交 PR

### 3. PR 描述模板

```markdown
## 📝 描述
简要描述您的更改

## 🎯 类型
- [ ] Bug修复
- [ ] 新功能
- [ ] 文档更新
- [ ] 代码重构
- [ ] 性能优化
- [ ] 测试相关

## 🔗 相关Issue
Closes #123

## 🧪 测试
- [ ] 后端测试通过
- [ ] 前端测试通过
- [ ] 手动测试完成

## 📸 截图（如果适用）
添加相关截图

## 📋 检查清单
- [ ] 代码遵循项目规范
- [ ] 添加了必要的测试
- [ ] 更新了相关文档
- [ ] 提交信息符合规范
```

### 4. 代码审查

- 所有 PR 都需要通过代码审查
- 请及时响应审查意见
- 如果需要修改，请在同一分支上提交新的提交

## 🎯 项目路线图

### 短期目标 (1-2个月)

- [ ] 用户认证和授权
- [ ] 任务分类和标签
- [ ] 搜索功能
- [ ] 数据导出功能

### 中期目标 (3-6个月)

- [ ] 实时协作功能
- [ ] 移动端应用
- [ ] 数据同步
- [ ] 高级统计功能

### 长期目标 (6个月以上)

- [ ] AI 智能助手
- [ ] 多语言支持
- [ ] 插件系统
- [ ] 企业版功能

## 🤝 社区准则

### 行为准则

- 尊重所有贡献者
- 保持友好和专业的交流
- 欢迎新手提问
- 提供建设性的反馈

### 沟通渠道

- **GitHub Issues**: Bug报告和功能建议
- **GitHub Discussions**: 一般讨论和问题
- **Pull Requests**: 代码贡献

## 📞 联系我们

如果您有任何问题或建议，请通过以下方式联系我们：

- 📧 邮箱: your-email@example.com
- 💬 GitHub Discussions: [项目讨论区](https://github.com/yourusername/todolist-v3/discussions)
- 🐛 GitHub Issues: [问题反馈](https://github.com/yourusername/todolist-v3/issues)

---

感谢您的贡献！🎉 