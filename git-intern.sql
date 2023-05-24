
/* users table for both company and intern */
CREATE TABLE `users` (
                         `user_id` bigint NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `enabled` tinyint(1) NOT NULL,
                         `role` varchar(255) NOT NULL,
                         `first_name` varchar(100) DEFAULT NULL,
                         `last_name` varchar(100) DEFAULT NULL,
                         `company_name` varchar(100) DEFAULT NULL,
                         `user_type` varchar(100) NOT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


    /* user_token table */
CREATE TABLE `user_token` (
                              `token_id` bigint NOT NULL AUTO_INCREMENT,
                              `confirmation_token` varchar(255) NOT NULL,
                              `created_date` date NOT NULL,
                              `user_id` bigint NOT NULL,
                              PRIMARY KEY (`token_id`),
                              KEY `fk_token_user` (`user_id`),
                              CONSTRAINT `fk_token_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci




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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci