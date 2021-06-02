DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_follows;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE user_follows(
    user INT NOT NULL,
    user_followed INT NOT NULL,
    foreign key (user) references users(id),
    foreign key (user_followed) references users(id)
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    color VARCHAR(100),
    notes VARCHAR(100)
);

CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detail INT NOT NULL,
    date DATE NOT NULL,
    category INT NOT NULL,
    price DOUBLE NOT NULL,
    hasPromo BOOLEAN DEFAULT false,
    discount double,
    foreign key (detail) references products(id)
);