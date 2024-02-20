CREATE TABLE customer (
    id serial PRIMARY KEY,
    name text,
    address_line_1 text,
    address_line_2 text,
    town text,
    county text,
    country text,
    postcode varchar(10)
);