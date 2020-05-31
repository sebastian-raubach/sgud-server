DROP VIEW IF EXISTS `view_items`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_items` AS
SELECT
       	items.id AS `item_id`,
       	items.name AS `item_name`,
       	items.description AS `item_description`,
       	manufacturers.id AS `manufacturer_id`,
       	manufacturers.name AS `manufacturer_name`,
       	manufacturers.description AS `manufacturer_description`,
       	manufacturers.url AS `manufacturer_url`,
       	sources.id AS `source_id`,
        sources.name AS `source_name`,
        sources.description AS `source_description`,
        sources.url AS `source_url`,
       	categories.id AS `category_id`,
       	categories.name AS `category_name`,
       	categories.description AS `category_description`,
       	categories.icon AS `category_icon`,
       	types.id AS `type_id`,
       	types.name AS `type_name`,
       	types.description AS `type_description`,
       	types.icon AS `type_icon`,
       	AVG(item_ratings.rating) as `avg_item_rating`
       FROM
       	items
       LEFT JOIN manufacturers ON
       	manufacturers.id = items.manufacturer_id
       LEFT JOIN sources ON
        sources.id = items.source_id
       LEFT JOIN categories ON
       	categories.id = items.category_id
       LEFT JOIN item_ratings ON
       	item_ratings.item_id = items.id
       LEFT JOIN types ON types.id = items.type_id

       	GROUP BY items.id
       ;

DROP VIEW IF EXISTS `view_categories`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_categories` AS
SELECT `categories`.*, (SELECT COUNT(1) FROM `items` WHERE `items`.`category_id` = `categories`.`id`) AS `count` FROM `categories`;

DROP VIEW IF EXISTS `view_ratings`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_ratings` AS
SELECT
    items.id AS `item_id`,
    items.name AS `item_name`,
    items.description AS `item_description`,
    rating_categories.id AS `rating_category_id`,
    rating_categories.name AS `rating_category_name`,
    rating_categories.description AS `rating_category_description`,
    categories.id AS `category_id`,
    categories.name AS `category_name`,
    categories.description AS `category_description`,
    item_ratings.rating AS `rating`
    FROM
     item_ratings
    LEFT JOIN items ON
     item_ratings.item_id = items.id
    LEFT JOIN rating_categories ON
     rating_categories.id = item_ratings.ratingcategory_id
    LEFT JOIN categories ON
     categories.id = rating_categories.category_id;
