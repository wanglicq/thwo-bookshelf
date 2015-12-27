DROP TABLE IF EXISTS wo_book;
CREATE TABLE wo_book (
  isbn VARCHAR(30) PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  author VARCHAR(50) NOT NULL,
  price DOUBLE NOT NULL
);

INSERT INTO wo_book(isbn, name, author, price) VALUES('isbn', 'book name', 'author', 55.20);