CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    author TEXT,
    launch_date TIMESTAMP(6) NOT NULL,
    price DECIMAL(62, 2) NOT NULL,
    title TEXT
);