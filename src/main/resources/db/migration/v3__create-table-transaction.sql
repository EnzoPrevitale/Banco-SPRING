CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    date_time TIMESTAMP NOT NULL,
    origin_account_id INTEGER NOT NULL,
    destiny_account_id INTEGER NOT NULL,
    CONSTRAINT fk_origin FOREIGN KEY (origin_account_id) REFERENCES account(id),
    CONSTRAINT fk_destiny FOREIGN KEY (destiny_account_id) REFERENCES account(id)
);
