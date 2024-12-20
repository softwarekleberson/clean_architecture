CREATE TABLE cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    main BOOLEAN NOT NULL,
    printed_name VARCHAR(255) NOT NULL,
    code VARCHAR(50) NOT NULL,
    number_card VARCHAR(50) NOT NULL,
    flag VARCHAR(50) NOT NULL,
    expiration_date DATE NOT NULL,
    customer_entity_id VARCHAR(36) NOT NULL, 
    CONSTRAINT fk_customer_cards FOREIGN KEY (customer_entity_id) REFERENCES customers (id) ON DELETE CASCADE
);
