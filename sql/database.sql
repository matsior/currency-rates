DROP DATABASE kursy_walut_4k;

CREATE DATABASE IF NOT EXISTS kursy_walut_4k DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE KURSY_WALUT_4K;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(40) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE users_currencies (
    user_id INT,
    currency_code VARCHAR(3) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (user_id, currency_code)
);

INSERT INTO
    users (username, password, email)
VALUES
    ('test', 'test', 'test@test.com');

INSERT INTO
    users (username, password, email, role)
VALUES
    ('admin', 'admin', 'admin@admin.com', 'ADMIN');

INSERT INTO
    users_currencies (user_id, currency_code)
VALUES
    (1, 'USD'),
    (1, 'EUR'),
    (1, 'GBP');