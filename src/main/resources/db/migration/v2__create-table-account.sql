CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    agency VARCHAR(255),
    account_number VARCHAR(255) UNIQUE,
    balance DOUBLE PRECISION,
    type VARCHAR(255),
    client_id INTEGER,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES users(id)
);
