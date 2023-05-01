CREATE TABLE if not exists `users`
(
    `user_id`   bigint      NOT NULL AUTO_INCREMENT,
    `name`      varchar(50) DEFAULT NULL,
    `last_name` varchar(50) DEFAULT NULL,
    `username`  varchar(50) DEFAULT NULL,
    `age`       tinyint(3),
    `email`     varchar(70) NOT NULL,
    `password`  varchar(64) NOT NULL,
    `enabled`   boolean     DEFAULT TRUE,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username_UNIQUE` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE if not exists `roles`
(
    `role_id` bigint      NOT NULL AUTO_INCREMENT,
    `name`    varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `user_fk_idx` (`user_id`),
    KEY `role_fk_idx` (`role_id`),
    CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `users` (`name`, `last_name`, `username`, `age`, `email`, `password`, `enabled`)
VALUES ('Javi', 'Kendo', 'javi@mail.ru', 24, 'javi@mail.ru',
        '$2a$12$jMhStTpR0mgPec3Fe106xOdHcb1X.iFWhyXFBbGioN0pqZARkI2qK', 1), -- password: javi
       ('Lionel', 'Messi', 'lionel@mail.ru', 35, 'lionel@mail.ru',
        '$2a$12$4PhJi2Cg5Js0Aba0eNqWXORJjet6HxS1lPcM7szWEOb9XwZ2cBp9e', 1), -- password: lionel
       ('Elon', 'Musk', 'elon@mail.ru', 51, 'elon@mail.ru',
        '$2a$12$Y5pXHeKFUHdWhDJdTWp/.OU.Ak.CN5KYH.2dlQFd0Wv8CwuEhjCu6', 1), -- password: elon
       ('Mark', 'Zuckerberg', 'mark@mail.ru', 38, 'mark@mail.ru',
        '$2a$12$ni1/GMVXkOBA4cm9uLFCFOKLZCMkDouq2SDPjr/X6ssrDlhzX5xkC', 1), -- password: mark
       ('Ivan', 'Ivanov', 'ivan@mail.ru', 30, 'ivan@mail.ru',
        '$2a$12$YUXuyfebjaKaetZOBPAeYuvnBlKBnPzNnHem9EAoFsAmAXW5nLN0e', 1); -- password: ivan

INSERT INTO `roles` (`name`) VALUES ('ADMIN');
INSERT INTO `roles` (`name`) VALUES ('CREATOR');
INSERT INTO `roles` (`name`) VALUES ('EDITOR');
INSERT INTO `roles` (`name`) VALUES ('USER');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1); -- user JaviKendo has role ADMIN
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 2); -- user LionelMessi has role CREATOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 3); -- user ElonMusk has role EDITOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (4, 2); -- user MarkZuckerberg has role CREATOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (4, 3); -- user MarkZuckerberg has role EDITOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (5, 4); -- user IvanIvanov has role USER

SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM users_roles;

DROP TABLE if exists users cascade;
DROP TABLE if exists roles cascade;
DROP TABLE if exists users_roles cascade;

DELETE FROM users_roles WHERE user_id = 10;
DELETE FROM users WHERE user_id = 10;