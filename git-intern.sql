
/* users table for both company and intern */
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `role` enum('INTERN','COMPANY') DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `user_type` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


    /* user_token table */
CREATE TABLE `user_token` (
  `token_id` bigint NOT NULL AUTO_INCREMENT,
  `confirmation_token` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`token_id`),
  KEY `fk_token_user` (`user_id`),
  CONSTRAINT `fk_token_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci




    /* company-profile table */
CREATE TABLE `company_profile` (
  `company_profile_id` bigint NOT NULL AUTO_INCREMENT,
  `company_id` bigint DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `company_link` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_profile_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `company_profile_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


    /* intern_profile table */
CREATE TABLE `intern_profile` (
  `intern_profile_id` bigint NOT NULL AUTO_INCREMENT,
  `intern_id` bigint NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`intern_profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


    /* resume table */
CREATE TABLE `resume` (
  `resume_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `data` blob,
  PRIMARY KEY (`resume_id`),
  KEY `resume_ibfk_1` (`user_id`),
  CONSTRAINT `resume_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


/* internships Table */
CREATE TABLE `internships` (
  `internship_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `description` text,
  `requirements` text,
  `responsibilities` text,
  `qualifications` text,
  `application_deadline` date DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `is_paid` tinyint(1) DEFAULT NULL,
  `number_of_positions` int DEFAULT NULL,
  `is_remote` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`internship_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `internships_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


/* internship application Table */

CREATE TABLE `internship_application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `intern_profile_id` bigint DEFAULT NULL,
  `resume_id` bigint DEFAULT NULL,
  `internship_id` bigint DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `status` enum('ACCEPTED','REJECTED','PENDING') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `intern_profile_id` (`intern_profile_id`),
  KEY `internship_id` (`internship_id`),
  KEY `FK_your_table_name_resume` (`resume_id`),
  CONSTRAINT `FK_your_table_name_resume` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`resume_id`),
  CONSTRAINT `internship_application_ibfk_1` FOREIGN KEY (`intern_profile_id`) REFERENCES `intern_profile` (`intern_profile_id`),
  CONSTRAINT `internship_application_ibfk_3` FOREIGN KEY (`internship_id`) REFERENCES `internships` (`internship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci