create table users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(150) NOT NULL ,
    email VARCHAR(220) NOT NULL ,
    password VARCHAR(190) NOT NULL
);