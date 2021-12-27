CREATE TABLE customer_individual
(
    customer_individual_id SERIAL PRIMARY KEY,
    first_name  TEXT NOT NULL,
    middle_name TEXT,
    last_name   TEXT NOT NULL,
    suffix      TEXT,
    email       TEXT,
    phone       TEXT
);
ALTER SEQUENCE customer_individual_customer_individual_id_seq RESTART 100000000;

CREATE TABLE customer_business
(
    customer_business_id SERIAL PRIMARY KEY,
    business_name  TEXT NOT NULL
);
ALTER SEQUENCE customer_business_customer_business_id_seq RESTART 200000000;