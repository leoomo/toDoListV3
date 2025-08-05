-- Todo应用数据库建表脚本
-- 数据库名: todoapp
-- 创建时间: 2025-01-27

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS todoapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE todoapp;

-- 创建todos表
CREATE TABLE IF NOT EXISTS todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    title VARCHAR(255) NOT NULL COMMENT '待办事项标题',
    description TEXT COMMENT '待办事项描述',
    completed BOOLEAN NOT NULL DEFAULT FALSE COMMENT '完成状态',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_completed (completed),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='待办事项表';

-- 插入测试数据
INSERT INTO todos (title, description, completed, created_at, updated_at) VALUES 
('完成项目文档', '编写技术架构文档和API文档', false, NOW(), NOW()),
('学习Spring Boot', '深入学习Spring Boot 3.0的新特性', false, NOW(), NOW()),
('代码审查', '对现有代码进行全面的代码审查', true, NOW(), NOW()),
('单元测试', '为所有功能编写单元测试', false, NOW(), NOW()),
('部署应用', '将应用部署到生产环境', false, NOW(), NOW());

-- 查看表结构
DESCRIBE todos;

-- 查看测试数据
SELECT * FROM todos; 