ALTER TABLE deliveries
ADD main BOOLEAN DEFAULT FALSE;

UPDATE deliveries
SET main = FALSE;
