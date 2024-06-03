ALTER TABLE doctors ADD active tinyint NULL;
UPDATE doctors set active = 1;
ALTER TABLE doctors MODIFY active tinyint NOT NULL;