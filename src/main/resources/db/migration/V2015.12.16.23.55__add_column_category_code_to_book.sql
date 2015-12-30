ALTER TABLE wo_book
ADD COLUMN category_code VARCHAR(10) NULL;
ALTER TABLE wo_book
ADD CONSTRAINT fk_category_code FOREIGN KEY(category_code) REFERENCES wo_category(code);