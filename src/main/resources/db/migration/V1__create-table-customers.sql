CREATE TABLE customers (
    id VARCHAR(36) PRIMARY KEY, 
    cpf VARCHAR(11) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT 0, 
    name VARCHAR(255) NOT NULL, 
    birth DATE NOT NULL, 
    password VARCHAR(255), 
    gender ENUM('MALE', 'FEMALE'),
    role ENUM('ROLE_CUSTOMER', 'ROLE_ADM'),
    ddd VARCHAR(2), 
    phone VARCHAR(9), 
    email VARCHAR(255) NOT NULL UNIQUE 
);