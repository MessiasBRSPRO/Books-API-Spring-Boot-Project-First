create table books(
    id SERIAL PRIMARY KEY,
    book_name VARCHAR(100) not null,
    author VARCHAR(100) not null,
    price DOUBLE PRECISION not null,
    pages_number INT NOT NULL,
    genre_book VARCHAR(100) not null
);