-- 创建订单主表
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID (主键)',
  `order_sn` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户ID',
  `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '实际支付金额',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态 (0:待付款, 1:待发货, 2:运输中, 3:已完成, 4:已取消, 5:申请退货, 6:已退货)',
  `receiver_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人电话',
  `receiver_address` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人详细地址',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_sn` (`order_sn`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单主表';

-- 创建订单详情表
CREATE TABLE IF NOT EXISTS `order_items` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情项ID (主键)',
  `order_id` bigint(20) NOT NULL COMMENT '所属订单ID',
  `shoe_id` bigint(20) DEFAULT NULL COMMENT '购买的鞋子ID',
  `product_sku` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品SKU (冗余)',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称 (冗余)',
  `product_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品主图 (冗余)',
  `product_price` decimal(10,2) NOT NULL COMMENT '购买时单价',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '该项总金额',
  PRIMARY KEY (`item_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_shoe_id` (`shoe_id`),
  CONSTRAINT `FK_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单详情表';

-- 插入测试订单数据
INSERT INTO `orders` (`order_sn`, `user_id`, `total_amount`, `pay_amount`, `status`, `receiver_name`, `receiver_phone`, `receiver_address`) VALUES
('ORD20241201001', 1, 599.00, 599.00, 0, '张三', '13800138001', '北京市朝阳区建国路1号'),
('ORD20241201002', 2, 899.00, 899.00, 1, '李四', '13800138002', '上海市浦东新区陆家嘴路2号'),
('ORD20241201003', 1, 1299.00, 1299.00, 2, '张三', '13800138001', '北京市朝阳区建国路1号'),
('ORD20241201004', 3, 799.00, 799.00, 3, '王五', '13800138003', '广州市天河区珠江路3号'),
('ORD20241201005', 2, 1599.00, 1599.00, 4, '李四', '13800138002', '上海市浦东新区陆家嘴路2号'),
('ORD20241201006', 4, 699.00, 699.00, 5, '赵六', '13800138004', '深圳市南山区科技路4号'),
('ORD20241201007', 1, 999.00, 999.00, 6, '张三', '13800138001', '北京市朝阳区建国路1号');

-- 插入测试订单详情数据
INSERT INTO `order_items` (`order_id`, `shoe_id`, `product_sku`, `product_name`, `product_image`, `product_price`, `quantity`, `total_price`) VALUES
(1, 1014, 'AIR-MAX-001-42', 'Nike Air Max 270', '/uploads/images/2025/08/08/1e74116217b94c57b60c0b6e13da745a.jpg', 599.00, 1, 599.00),
(2, 1015, 'JORDAN-001-43', 'Air Jordan 1 Retro', '/uploads/images/2025/08/08/2e74116217b94c57b60c0b6e13da745b.jpg', 899.00, 1, 899.00),
(3, 1014, 'AIR-MAX-001-42', 'Nike Air Max 270', '/uploads/images/2025/08/08/1e74116217b94c57b60c0b6e13da745a.jpg', 599.00, 1, 599.00),
(3, 1016, 'ADIDAS-001-42', 'Adidas Ultraboost 22', '/uploads/images/2025/08/08/3e74116217b94c57b60c0b6e13da745c.jpg', 700.00, 1, 700.00),
(4, 1017, 'PUMA-001-41', 'Puma RS-X', '/uploads/images/2025/08/08/4e74116217b94c57b60c0b6e13da745d.jpg', 799.00, 1, 799.00),
(5, 1015, 'JORDAN-001-43', 'Air Jordan 1 Retro', '/uploads/images/2025/08/08/2e74116217b94c57b60c0b6e13da745b.jpg', 899.00, 1, 899.00),
(5, 1016, 'ADIDAS-001-42', 'Adidas Ultraboost 22', '/uploads/images/2025/08/08/3e74116217b94c57b60c0b6e13da745c.jpg', 700.00, 1, 700.00),
(6, 1018, 'CONVERSE-001-40', 'Converse Chuck Taylor', '/uploads/images/2025/08/08/5e74116217b94c57b60c0b6e13da745e.jpg', 699.00, 1, 699.00),
(7, 1019, 'VANS-001-44', 'Vans Old Skool', '/uploads/images/2025/08/08/6e74116217b94c57b60c0b6e13da745f.jpg', 999.00, 1, 999.00);