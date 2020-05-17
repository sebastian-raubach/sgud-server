SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of categories
-- ----------------------------

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of images
-- ----------------------------

-- ----------------------------
-- Table structure for item_images
-- ----------------------------
DROP TABLE IF EXISTS `item_images`;
CREATE TABLE `item_images`  (
  `item_id` int(11) NOT NULL,
  `image_id` int(11) NOT NULL,
  PRIMARY KEY (`item_id`, `image_id`) USING BTREE,
  INDEX `itemimages_ibfk_images`(`image_id`) USING BTREE,
  CONSTRAINT `item_images_ibfk_images` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_images_ibfk_items` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_images
-- ----------------------------

-- ----------------------------
-- Table structure for item_ratings
-- ----------------------------
DROP TABLE IF EXISTS `item_ratings`;
CREATE TABLE `item_ratings`  (
  `item_id` int(11) NOT NULL,
  `ratingcategory_id` int(11) NOT NULL,
  `rating` float UNSIGNED NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`item_id`, `ratingcategory_id`) USING BTREE,
  INDEX `item_ratings_ibfk_ratingcategory`(`ratingcategory_id`) USING BTREE,
  CONSTRAINT `item_ratings_ibfk_item` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_ratings_ibfk_ratingcategory` FOREIGN KEY (`ratingcategory_id`) REFERENCES `rating_categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_ratings
-- ----------------------------

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `manufacturer_id` int(11) NOT NULL,
  `source_id` int(11) NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `items_ibfk_categories`(`category_id`) USING BTREE,
  INDEX `items_ibfk_type`(`type_id`) USING BTREE,
  INDEX `items_ibfk_source`(`source_id`) USING BTREE,
  INDEX `items_ibfk_manufacturer`(`manufacturer_id`) USING BTREE,
  CONSTRAINT `items_ibfk_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `items_ibfk_source` FOREIGN KEY (`source_id`) REFERENCES `sources` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `items_ibfk_type` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `items_ibfk_manufacturer` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of items
-- ----------------------------

-- ----------------------------
-- Table structure for manufacturers
-- ----------------------------
DROP TABLE IF EXISTS `manufacturers`;
CREATE TABLE `manufacturers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manufacturers
-- ----------------------------

-- ----------------------------
-- Table structure for rating_categories
-- ----------------------------
DROP TABLE IF EXISTS `rating_categories`;
CREATE TABLE `rating_categories`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ratingcategories_ibfk_category`(`category_id`) USING BTREE,
  CONSTRAINT `rating_categories_ibfk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rating_categories
-- ----------------------------

-- ----------------------------
-- Table structure for sources
-- ----------------------------
DROP TABLE IF EXISTS `sources`;
CREATE TABLE `sources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sources
-- ----------------------------

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `type_ibfk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of types
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;