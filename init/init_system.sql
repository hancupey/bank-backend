-- 初始化银行信贷系统-系统管理模块表结构
-- 包含：用户管理、机构管理、角色权限管理、流程管理

USE bank_db;

-- ========================================
-- 1. 机构表
-- ========================================
DROP TABLE IF EXISTS sys_organization;
CREATE TABLE sys_organization (
    id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    name          VARCHAR(100) NOT NULL COMMENT '机构名称',
    code          VARCHAR(50)  NULL COMMENT '机构编码',
    parent_id     BIGINT       NULL COMMENT '父机构 ID',
    address       VARCHAR(500) NULL COMMENT '地址',
    phone         VARCHAR(20)  NULL COMMENT '联系电话',
    email         VARCHAR(100) NULL COMMENT '邮箱',
    level         INT          NOT NULL DEFAULT 1 COMMENT '机构层级',
    sort          INT          NULL DEFAULT 0 COMMENT '排序',
    enabled       TINYINT      NOT NULL DEFAULT 1 COMMENT '是否启用：1-启用，0-禁用',
    create_time   DATETIME     NULL COMMENT '创建时间',
    update_time   DATETIME     NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_parent_id (parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构表';

-- ========================================
-- 2. 用户表
-- ========================================
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    username      VARCHAR(50)  NOT NULL COMMENT '用户名',
    password      VARCHAR(100) NOT NULL COMMENT '密码（加密）',
    real_name     VARCHAR(50)  NULL COMMENT '真实姓名',
    phone         VARCHAR(20)  NULL COMMENT '手机号',
    email         VARCHAR(100) NULL COMMENT '邮箱',
    org_id        BIGINT       NULL COMMENT '所属机构 ID',
    enabled       TINYINT      NOT NULL DEFAULT 1 COMMENT '是否启用：1-启用，0-禁用',
    create_time   DATETIME     NULL COMMENT '创建时间',
    update_time   DATETIME     NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username),
    INDEX idx_org_id (org_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ========================================
-- 3. 角色表
-- ========================================
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    name          VARCHAR(50)  NOT NULL COMMENT '角色名称',
    description   VARCHAR(200) NULL COMMENT '角色描述',
    enabled       TINYINT      NOT NULL DEFAULT 1 COMMENT '是否启用：1-启用，0-禁用',
    create_time   DATETIME     NULL COMMENT '创建时间',
    update_time   DATETIME     NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ========================================
-- 4. 权限表
-- ========================================
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
    id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    name          VARCHAR(100) NOT NULL COMMENT '权限名称',
    code          VARCHAR(100) NOT NULL COMMENT '权限编码',
    description   VARCHAR(200) NULL COMMENT '权限描述',
    url           VARCHAR(200) NULL COMMENT '权限 URL',
    type          VARCHAR(50)  NULL COMMENT '权限类型：MENU-菜单/BUTTON-按钮',
    sort          INT          NOT NULL DEFAULT 0 COMMENT '排序',
    parent_id     BIGINT       NULL COMMENT '父权限 ID',
    enabled       TINYINT      NOT NULL DEFAULT 1 COMMENT '是否启用：1-启用，0-禁用',
    create_time   DATETIME     NULL COMMENT '创建时间',
    update_time   DATETIME     NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_code (code),
    INDEX idx_parent_id (parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ========================================
-- 5. 流程表
-- ========================================
DROP TABLE IF EXISTS sys_process;
CREATE TABLE sys_process (
    id              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    name            VARCHAR(200) NOT NULL COMMENT '流程名称',
    code            VARCHAR(50)  NULL COMMENT '流程编码',
    description     VARCHAR(500) NULL COMMENT '流程描述',
    type            VARCHAR(50)  NULL COMMENT '流程类型',
    status          VARCHAR(50)  NULL COMMENT '流程状态',
    org_id          BIGINT       NULL COMMENT '所属机构 ID',
    create_user_id  BIGINT       NULL COMMENT '创建用户 ID',
    sort            INT          NOT NULL DEFAULT 0 COMMENT '排序',
    config          VARCHAR(1000) NULL COMMENT '流程配置（JSON）',
    enabled         TINYINT      NOT NULL DEFAULT 1 COMMENT '是否启用：1-启用，0-禁用',
    create_time     DATETIME     NULL COMMENT '创建时间',
    update_time     DATETIME     NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_org_id (org_id),
    INDEX idx_create_user_id (create_user_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程表';

-- ========================================
-- 6. 用户角色关联表
-- ========================================
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    user_id   BIGINT NOT NULL COMMENT '用户 ID',
    role_id   BIGINT NOT NULL COMMENT '角色 ID',
    PRIMARY KEY (user_id, role_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ========================================
-- 7. 角色权限关联表
-- ========================================
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission (
    role_id       BIGINT NOT NULL COMMENT '角色 ID',
    permission_id BIGINT NOT NULL COMMENT '权限 ID',
    PRIMARY KEY (role_id, permission_id),
    INDEX idx_permission_id (permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ========================================
-- 8. 初始化数据
-- ========================================

-- 8.1 初始化机构数据
INSERT INTO sys_organization (name, code, level, sort, enabled, create_time) VALUES
('总行', 'HEAD', 1, 1, 1, NOW()),
('北京分行', 'BJ', 2, 1, 1, NOW()),
('上海分行', 'SH', 2, 2, 1, NOW()),
('深圳分行', 'SZ', 2, 3, 1, NOW());

-- 8.2 初始化角色数据
INSERT INTO sys_role (name, description, enabled, create_time) VALUES
('系统管理员', '拥有系统全部权限', 1, NOW()),
('机构管理员', '拥有本机构管理权限', 1, NOW()),
('普通用户', '普通业务用户', 1, NOW()),
('审计员', '查看和审计权限', 1, NOW());

-- 8.3 初始化权限数据
INSERT INTO sys_permission (name, code, type, sort, enabled, create_time) VALUES
-- 系统管理菜单
('系统管理', 'system', 'MENU', 100, 1, NOW()),
('用户管理', 'system:user', 'MENU', 101, 1, NOW()),
('用户新增', 'system:user:add', 'BUTTON', 1, 1, NOW()),
('用户修改', 'system:user:update', 'BUTTON', 2, 1, NOW()),
('用户删除', 'system:user:delete', 'BUTTON', 3, 1, NOW()),
('用户查询', 'system:user:query', 'BUTTON', 4, 1, NOW()),
('机构管理', 'system:org', 'MENU', 102, 1, NOW()),
('机构新增', 'system:org:add', 'BUTTON', 1, 1, NOW()),
('机构修改', 'system:org:update', 'BUTTON', 2, 1, NOW()),
('机构删除', 'system:org:delete', 'BUTTON', 3, 1, NOW()),
('机构查询', 'system:org:query', 'BUTTON', 4, 1, NOW()),
('角色管理', 'system:role', 'MENU', 103, 1, NOW()),
('角色新增', 'system:role:add', 'BUTTON', 1, 1, NOW()),
('角色修改', 'system:role:update', 'BUTTON', 2, 1, NOW()),
('角色删除', 'system:role:delete', 'BUTTON', 3, 1, NOW()),
('角色查询', 'system:role:query', 'BUTTON', 4, 1, NOW()),
('权限管理', 'system:permission', 'MENU', 104, 1, NOW()),
('权限新增', 'system:permission:add', 'BUTTON', 1, 1, NOW()),
('权限修改', 'system:permission:update', 'BUTTON', 2, 1, NOW()),
('权限删除', 'system:permission:delete', 'BUTTON', 3, 1, NOW()),
('权限查询', 'system:permission:query', 'BUTTON', 4, 1, NOW()),
('流程管理', 'system:process', 'MENU', 105, 1, NOW()),
('流程新增', 'system:process:add', 'BUTTON', 1, 1, NOW()),
('流程修改', 'system:process:update', 'BUTTON', 2, 1, NOW()),
('流程删除', 'system:process:delete', 'BUTTON', 3, 1, NOW()),
('流程查询', 'system:process:query', 'BUTTON', 4, 1, NOW()),
('流程视图', 'system:process:view', 'BUTTON', 5, 1, NOW());

-- 8.4 初始化用户数据（密码：123456，BCrypt 加密）
-- BCrypt 加密后的密码：$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lEkuN3GftqUhQ7sI6
INSERT INTO sys_user (username, password, real_name, phone, email, org_id, enabled, create_time) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lEkuN3GftqUhQ7sI6', '系统管理员', '13800000000', 'admin@bank.com', 1, 1, NOW()),
('user1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lEkuN3GftqUhQ7sI6', '张三', '13800000001', 'zhangsan@bank.com', 2, 1, NOW()),
('user2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lEkuN3GftqUhQ7sI6', '李四', '13800000002', 'lisi@bank.com', 3, 1, NOW());

-- 8.5 分配用户角色
-- admin - 系统管理员
INSERT INTO sys_user_role (user_id, role_id) VALUES (1, 1);
-- user1 - 机构管理员
INSERT INTO sys_user_role (user_id, role_id) VALUES (2, 2);
-- user2 - 普通用户
INSERT INTO sys_user_role (user_id, role_id) VALUES (3, 3);

-- 8.6 分配角色权限
-- 系统管理员拥有全部权限
INSERT INTO sys_role_permission (role_id, permission_id) 
SELECT 1, id FROM sys_permission;

-- 机构管理员拥有部分权限
INSERT INTO sys_role_permission (role_id, permission_id) VALUES
(2, 2), (2, 6),  -- 用户管理、用户查询
(2, 7), (2, 11), -- 机构管理、机构查询
(2, 12), (2, 16),-- 角色管理、角色查询
(2, 22), (2, 26);-- 流程管理、流程查询

-- 普通用户拥有基础权限
INSERT INTO sys_role_permission (role_id, permission_id) VALUES
(3, 6),  -- 用户查询
(3, 11), -- 机构查询
(3, 16), -- 角色查询
(3, 26); -- 流程查询

-- ========================================
-- 9. 视图和索引优化（可选）
-- ========================================

-- 创建用户机构视图
CREATE OR REPLACE VIEW v_user_org AS
SELECT 
    u.id AS user_id,
    u.username,
    u.real_name,
    u.phone,
    u.email,
    u.enabled AS user_enabled,
    o.id AS org_id,
    o.name AS org_name,
    o.code AS org_code
FROM sys_user u
LEFT JOIN sys_organization o ON u.org_id = o.id;

-- 创建角色权限视图
CREATE OR REPLACE VIEW v_role_permission AS
SELECT 
    r.id AS role_id,
    r.name AS role_name,
    p.id AS permission_id,
    p.name AS permission_name,
    p.code AS permission_code,
    p.type AS permission_type
FROM sys_role r
INNER JOIN sys_role_permission rp ON r.id = rp.role_id
INNER JOIN sys_permission p ON rp.permission_id = p.id;

-- 创建用户角色权限视图
CREATE OR REPLACE VIEW v_user_role_permission AS
SELECT 
    u.id AS user_id,
    u.username,
    r.id AS role_id,
    r.name AS role_name,
    p.id AS permission_id,
    p.code AS permission_code
FROM sys_user u
INNER JOIN sys_user_role ur ON u.id = ur.user_id
INNER JOIN sys_role r ON ur.role_id = r.id
INNER JOIN sys_role_permission rp ON r.id = rp.role_id
INNER JOIN sys_permission p ON rp.permission_id = p.id;
