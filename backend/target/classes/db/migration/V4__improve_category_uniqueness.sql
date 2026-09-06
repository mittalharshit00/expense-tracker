ALTER TABLE categories
DROP CONSTRAINT category_unique;

ALTER TABLE categories
ADD CONSTRAINT category_unique UNIQUE (user_id, name);