CREATE TABLE charges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    main BOOLEAN DEFAULT FALSE,
    receiver VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    observation TEXT NOT NULL,
    street_type VARCHAR(50) NOT NULL,
    type_residence VARCHAR(50) NOT NULL,
    city VARCHAR(100) NOT NULL,
    customer_entity_id VARCHAR(36) NOT NULL, 
    CONSTRAINT fk_customer_charges FOREIGN KEY (customer_entity_id) REFERENCES customers (id) ON DELETE CASCADE
);
