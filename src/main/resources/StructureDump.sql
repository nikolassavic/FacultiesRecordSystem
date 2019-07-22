CREATE DATABASE  IF NOT EXISTS `faculties_record_system`;
USE `faculties_record_system`;

DROP TABLE IF EXISTS `faculties`;

CREATE TABLE `faculties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
);

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `ucid` varchar(13) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `dob` date NOT NULL,
  `address` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `created` timestamp NOT NULL,
  `updated` timestamp NOT NULL,
  PRIMARY KEY (`ucid`),
  UNIQUE KEY `ucid` (`ucid`),
  UNIQUE KEY `email` (`email`)
);

DROP TABLE IF EXISTS `faculties_students`;

CREATE TABLE `faculties_students` (
  `faculty_id` int(11) NOT NULL,
  `student_ucid` varchar(13) NOT NULL,
  PRIMARY KEY (`faculty_id`,`student_ucid`),
  KEY `student_ucid` (`student_ucid`),
  CONSTRAINT `faculties_students_ibfk_1` FOREIGN KEY (`faculty_id`) REFERENCES `faculties` (`id`),
  CONSTRAINT `faculties_students_ibfk_2` FOREIGN KEY (`student_ucid`) REFERENCES `students` (`ucid`)
);

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  UNIQUE KEY `username` (`username`)
);