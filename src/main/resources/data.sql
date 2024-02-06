--Customers
INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf9c', 'alisher@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Alisher', 'Qodirov', '42d6a42c-40c8-424d-9e3c-6bef049aaf9c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf8c', 'javlon@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Javlon', 'Qochqorov', '42d6a42c-40c8-424d-9e3c-6bef049aaf8c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf7c', 'rustam@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Rustam', 'Orozov', '42d6a42c-40c8-424d-9e3c-6bef049aaf7c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf6c', 'temur@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Temur', 'Murtazaev', '42d6a42c-40c8-424d-9e3c-6bef049aaf6c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf5c', 'ulugbek@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Ulugbek', 'Shakirov', '42d6a42c-40c8-424d-9e3c-6bef049aaf5c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf4c', 'tom@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Tom', 'Cruise', '42d6a42c-40c8-424d-9e3c-6bef049aaf4c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf3c', 'emil@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Emil', 'Wcislo', '42d6a42c-40c8-424d-9e3c-6bef049aaf3c');

INSERT INTO customer (id, email) VALUES ('42d6a42c-40c8-424d-9e3c-6bef049aaf2c', 'mathew@emial.com');
INSERT INTO users(id, name, surname, customer_id) VALUES (uuid_generate_v4(), 'Mathew', 'Perry', '42d6a42c-40c8-424d-9e3c-6bef049aaf2c');

--Orders
INSERT INTO orders(id, order_date, order_state, customer_id) VALUES ('b8e45205-d417-45d7-a04f-303266954d87', TO_DATE('2024-01-16','YYYY-MM-DD'), 1,'42d6a42c-40c8-424d-9e3c-6bef049aaf6c');
INSERT INTO orders(id, order_date, order_state, customer_id) VALUES ('b8e45205-d417-45d7-a04f-303266954d86', TO_DATE('2024-01-17','YYYY-MM-DD'), 0,'42d6a42c-40c8-424d-9e3c-6bef049aaf6c');
INSERT INTO orders(id, order_date, order_state, customer_id) VALUES ('b8e45205-d417-45d7-a04f-303266954d85', TO_DATE('2024-01-13','YYYY-MM-DD'), 0,'42d6a42c-40c8-424d-9e3c-6bef049aaf6c');
INSERT INTO orders(id, order_date, order_state, customer_id) VALUES ('b8e45205-d417-45d7-a04f-303266954d84', TO_DATE('2024-01-19','YYYY-MM-DD'), 3,'42d6a42c-40c8-424d-9e3c-6bef049aaf6c');
INSERT INTO orders(id, order_date, order_state, customer_id) VALUES ('b8e45205-d417-45d7-a04f-303266954d83', TO_DATE('2024-01-20','YYYY-MM-DD'), 0,'42d6a42c-40c8-424d-9e3c-6bef049aaf6c');

--Create some book
INSERT INTO publisher (id, name, phone) VALUES ('bd90362e-fddb-457d-9a1e-e191def365d2', 'publisherName', '+48792682164');
INSERT INTO book(id, category, number_of_pages, price, publish_date, title, publisher_id) VALUES(
                                                                                                    '42d6a42c-40c8-424d-9e3c-6bef049aaf9b', 'Science', 375, 75, TO_DATE('2024-01-20','YYYY-MM-DD'), 'Cracking Coding Interview', 'bd90362e-fddb-457d-9a1e-e191def365d2');

--Order items
ALTER TABLE order_item DROP CONSTRAINT uk_f67xlaxtwr6wwab7u7mxndi5s;

INSERT INTO order_item(id, quantity, book_id, order_id) VALUES
    (uuid_generate_v4(), 20, '42d6a42c-40c8-424d-9e3c-6bef049aaf9b', 'b8e45205-d417-45d7-a04f-303266954d87');
INSERT INTO order_item(id, quantity, book_id, order_id) VALUES
    (uuid_generate_v4(), 10, '42d6a42c-40c8-424d-9e3c-6bef049aaf9b', 'b8e45205-d417-45d7-a04f-303266954d86');
INSERT INTO order_item(id, quantity, book_id, order_id) VALUES
    (uuid_generate_v4(), 20, '42d6a42c-40c8-424d-9e3c-6bef049aaf9b', 'b8e45205-d417-45d7-a04f-303266954d85');
INSERT INTO order_item(id, quantity, book_id, order_id) VALUES
    (uuid_generate_v4(), 10, '42d6a42c-40c8-424d-9e3c-6bef049aaf9b', 'b8e45205-d417-45d7-a04f-303266954d84');
INSERT INTO order_item(id, quantity, book_id, order_id) VALUES
    (uuid_generate_v4(), 20, '42d6a42c-40c8-424d-9e3c-6bef049aaf9b', 'b8e45205-d417-45d7-a04f-303266954d83');

--Creating invoice
INSERT INTO invoice(id, tax_rate_in_percentage, order_id) VALUES
    (uuid_generate_v4(), 24, 'b8e45205-d417-45d7-a04f-303266954d87');
INSERT INTO invoice(id, tax_rate_in_percentage, order_id) VALUES
    (uuid_generate_v4(), 21, 'b8e45205-d417-45d7-a04f-303266954d86');
INSERT INTO invoice(id, tax_rate_in_percentage, order_id) VALUES
    (uuid_generate_v4(), 24, 'b8e45205-d417-45d7-a04f-303266954d85');
INSERT INTO invoice(id, tax_rate_in_percentage, order_id) VALUES
    (uuid_generate_v4(), 21, 'b8e45205-d417-45d7-a04f-303266954d84');
INSERT INTO invoice(id, tax_rate_in_percentage, order_id) VALUES
    (uuid_generate_v4(), 24, 'b8e45205-d417-45d7-a04f-303266954d83');
