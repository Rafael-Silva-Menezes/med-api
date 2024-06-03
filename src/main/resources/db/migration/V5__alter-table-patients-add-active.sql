ALTER TABLE patients ADD active tinyint NULL;
UPDATE patients set active = 1;
ALTER TABLE patients MODIFY active tinyint NOT NULL;