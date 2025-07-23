CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    agency VARCHAR(100),
    account_number VARCHAR(100) UNIQUE,
    balance DOUBLE PRECISION,
    type VARCHAR(50),
    client_id INTEGER NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES users(id)
);
