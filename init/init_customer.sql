-- 初始化银行信贷系统-客户相关表结构

-- 1. 创建数据库（如已存在可跳过）
CREATE DATABASE IF NOT EXISTS bank_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

USE bank_db;

-- 2. 公共客户表（对应 Customer 实体）
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  id            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  customer_code VARCHAR(50)  NOT NULL COMMENT '客户号',
  customer_type VARCHAR(20)  NOT NULL COMMENT '客户类型: COMPANY/INDIVIDUAL/GROUP/OVERSEAS',
  name          VARCHAR(200) NOT NULL COMMENT '客户名称',
  status        VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE' COMMENT '状态: ACTIVE/INACTIVE/BLACKLIST 等',
  created_at    DATETIME     NOT NULL COMMENT '创建时间',
  updated_at    DATETIME     NOT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_customer_code (customer_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户基础信息表';

-- 3. 公司客户扩展表
DROP TABLE IF EXISTS company_customer;
CREATE TABLE company_customer (
  id                    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  customer_id           BIGINT       NOT NULL COMMENT '关联 customer.id',
  social_credit_code    VARCHAR(50)  NOT NULL COMMENT '统一社会信用代码',
  registered_capital    DECIMAL(18,2)     NULL COMMENT '注册资本',
  legal_person_name     VARCHAR(100) NULL COMMENT '法人代表',
  industry              VARCHAR(100) NULL COMMENT '所属行业',
  registered_address    VARCHAR(255) NULL COMMENT '注册地址',
  contact_phone         VARCHAR(50)  NULL COMMENT '联系电话',
  PRIMARY KEY (id),
  UNIQUE KEY uk_company_customer_id (customer_id),
  CONSTRAINT fk_company_customer_customer
    FOREIGN KEY (customer_id) REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司客户扩展信息表';

-- 4. 个人客户扩展表
DROP TABLE IF EXISTS individual_customer;
CREATE TABLE individual_customer (
  id                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  customer_id        BIGINT       NOT NULL COMMENT '关联 customer.id',
  id_type            VARCHAR(20)  NOT NULL COMMENT '证件类型: ID_CARD/PASSPORT 等',
  id_number          VARCHAR(50)  NOT NULL COMMENT '证件号码',
  gender             VARCHAR(10)  NULL COMMENT '性别: MALE/FEMALE/UNKNOWN',
  birthday           DATE         NULL COMMENT '出生日期',
  mobile_phone       VARCHAR(50)  NULL COMMENT '手机号',
  email              VARCHAR(100) NULL COMMENT '电子邮箱',
  address            VARCHAR(255) NULL COMMENT '联系地址',
  PRIMARY KEY (id),
  UNIQUE KEY uk_individual_customer_id (customer_id),
  KEY idx_individual_id_number (id_number),
  CONSTRAINT fk_individual_customer_customer
    FOREIGN KEY (customer_id) REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='个人客户扩展信息表';

-- 5. 集团客户扩展表
DROP TABLE IF EXISTS group_customer;
CREATE TABLE group_customer (
  id                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  customer_id        BIGINT       NOT NULL COMMENT '关联 customer.id',
  group_code         VARCHAR(50)  NOT NULL COMMENT '集团编码',
  group_level        VARCHAR(50)  NULL COMMENT '集团层级',
  member_company_cnt INT          NULL COMMENT '成员公司数量',
  contact_name       VARCHAR(100) NULL COMMENT '集团联系人',
  contact_phone      VARCHAR(50)  NULL COMMENT '联系人电话',
  PRIMARY KEY (id),
  UNIQUE KEY uk_group_customer_id (customer_id),
  UNIQUE KEY uk_group_code (group_code),
  CONSTRAINT fk_group_customer_customer
    FOREIGN KEY (customer_id) REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='集团客户扩展信息表';

-- 6. 境外客户扩展表
DROP TABLE IF EXISTS overseas_customer;
CREATE TABLE overseas_customer (
  id                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  customer_id        BIGINT       NOT NULL COMMENT '关联 customer.id',
  country_region     VARCHAR(100) NOT NULL COMMENT '国别/地区',
  tax_id             VARCHAR(50)  NULL COMMENT '境外税号',
  overseas_address   VARCHAR(255) NULL COMMENT '境外地址',
  contact_name       VARCHAR(100) NULL COMMENT '联系人',
  contact_phone      VARCHAR(50)  NULL COMMENT '联系电话',
  PRIMARY KEY (id),
  UNIQUE KEY uk_overseas_customer_id (customer_id),
  CONSTRAINT fk_overseas_customer_customer
    FOREIGN KEY (customer_id) REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='境外客户扩展信息表';

-- 7. 初始化测试数据

-- 7.1 基础客户数据
INSERT INTO customer (customer_code, customer_type, name, status, created_at, updated_at)
VALUES
  ('C0001', 'COMPANY',   '汉促科技有限公司',         'ACTIVE',  NOW(), NOW()),
  ('C0002', 'INDIVIDUAL','张三',                    'ACTIVE',  NOW(), NOW()),
  ('C0003', 'GROUP',     '汉促集团有限公司',         'ACTIVE',  NOW(), NOW()),
  ('C0004', 'OVERSEAS',  'Hancu Tech Pte. Ltd.',   'ACTIVE',  NOW(), NOW());

-- 7.2 公司客户扩展数据（假设 id 从 1 开始自增，与上面插入顺序一致）
INSERT INTO company_customer (customer_id, social_credit_code, registered_capital, legal_person_name,
                              industry, registered_address, contact_phone)
VALUES
  (1, '91310000MA1K123456', 10000000.00, '李四', '软件和信息技术服务业',
   '上海市浦东新区软件园 1 号楼', '021-88886666');

-- 7.3 个人客户扩展数据
INSERT INTO individual_customer (customer_id, id_type, id_number, gender, birthday,
                                 mobile_phone, email, address)
VALUES
  (2, 'ID_CARD', '310101199001017890', 'MALE', '1990-01-01',
   '13800000000', 'zhangsan@example.com', '上海市徐汇区宜山路 100 号');

-- 7.4 集团客户扩展数据
INSERT INTO group_customer (customer_id, group_code, group_level, member_company_cnt,
                            contact_name, contact_phone)
VALUES
  (3, 'HC-G-001', '集团总部', 5, '王总', '021-66668888');

-- 7.5 境外客户扩展数据
INSERT INTO overseas_customer (customer_id, country_region, tax_id, overseas_address,
                               contact_name, contact_phone)
VALUES
  (4, 'Singapore', 'SG123456789', '10 Anson Road #10-01, Singapore',
   'Jason Lee', '+65-6123-4567');

