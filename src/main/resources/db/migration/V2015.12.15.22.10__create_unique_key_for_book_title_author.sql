ALTER TABLE wo_book
ADD UNIQUE KEY uk_title_author(title, author);