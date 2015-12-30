DROP TABLE IF EXISTS wo_category;
CREATE TABLE wo_category (
  code VARCHAR(10) PRIMARY KEY NOT NULL,
  name VARCHAR(30) NOT NULL UNIQUE,
  description VARCHAR(255) NULL
);

INSERT INTO wo_category(code, name) VALUES('G623.58', 'IT'), ('G623.5', 'Mathematics'), ('B01', 'Philosophy');