CREATE TABLE deliveries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    receiver VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    observation TEXT,
    street_type VARCHAR(50) NOT NULL,
    type_residence VARCHAR(50) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    delivery_phrase TEXT NOT NULL,
    customer_entity_id VARCHAR(36) NOT NULL, 
    CONSTRAINT fk_customer FOREIGN KEY (customer_entity_id) REFERENCES customers (id) ON DELETE CASCADE
);
