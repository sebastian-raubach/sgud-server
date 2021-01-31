ALTER TABLE `rating_categories`
ADD COLUMN `include_in_average` tinyint(1) NOT NULL DEFAULT 1 AFTER `description`;