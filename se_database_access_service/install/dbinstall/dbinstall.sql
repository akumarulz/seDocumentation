CREATE TABLE `topic_table` (
  `topic_id` bigint NOT NULL AUTO_INCREMENT,
  `topic_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `sub_topic` (
  `subtopic_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `topic_id` bigint NOT NULL,
  PRIMARY KEY (`subtopic_id`),
  KEY `FKcujli4ylns6a720u7nxi3wt5q` (`topic_id`),
  CONSTRAINT `FKcujli4ylns6a720u7nxi3wt5q` FOREIGN KEY (`topic_id`) REFERENCES `topic_table` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `archive_records` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `archive_entry` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci