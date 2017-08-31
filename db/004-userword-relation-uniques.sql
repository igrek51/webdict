-- user - word many-to-many relation
CREATE TABLE `user_word` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `user_id` bigint(20) NOT NULL,
 `word_id` bigint(20) NOT NULL,
 PRIMARY KEY (`id`),
 KEY `FK70vcixbxt7t2lhgcng6ukg74x` (`user_id`),
 KEY `FKjqlk39web147dg7bn1efkbddk` (`word_id`),
 CONSTRAINT `FK70vcixbxt7t2lhgcng6ukg74x` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
 CONSTRAINT `FKjqlk39web147dg7bn1efkbddk` FOREIGN KEY (`word_id`) REFERENCES `word` (`id`),
 CONSTRAINT `unique_user_id_word_id` UNIQUE(`user_id`, `word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- copy binding to new table
INSERT INTO `user_word` (id, user_id, word_id)
SELECT id, user_id, id AS word_id
FROM `word`;

-- remove redundant column word: user_id and constraint
ALTER TABLE `word` DROP FOREIGN KEY `FKq6vj4o70ro4p1mdi1kiuv98iv`;
ALTER TABLE `word` DROP COLUMN user_id;


-- new column: rank: user_word_id
ALTER TABLE `rank`
ADD COLUMN `user_word_id` bigint(20) DEFAULT NULL;

-- bind ranks to user-word
UPDATE `rank`
SET user_word_id = word_id;

-- create constraint: rank: user_word_id
ALTER TABLE `rank`
ADD CONSTRAINT `FKlx47nee3aig7fd95yh6ft45fs` FOREIGN KEY (`user_word_id`) REFERENCES `user_word` (`id`);

-- remove redundant column rank: word_id and constraint
ALTER TABLE `rank` DROP FOREIGN KEY `FKmlpxkhycyxrxwn21ow3705iig`;
ALTER TABLE `rank` DROP COLUMN word_id;

-- ADD UNIQUE CONSTRAINTS
-- add unique: rank: user_word_id, reversed_dictionary
ALTER TABLE `rank`
ADD CONSTRAINT `unique_user_word_id_reversed_dictionary` UNIQUE (user_word_id, reversed_dictionary);

ALTER TABLE `language`
ADD CONSTRAINT `unique_code` UNIQUE (code);

ALTER TABLE `dictionary`
ADD CONSTRAINT `unique_source_target_language_id` UNIQUE (source_language_id, target_language_id);
