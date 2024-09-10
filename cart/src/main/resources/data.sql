INSERT INTO cart_table
    (id, user_id)
VALUES
    ('6c55d72a-e752-4726-ab1d-d815ce5aad21', 1);

INSERT INTO item_table
    (id, product_id, quantity, price, cart_id)
VALUES
    ('4f00a62f-7b88-4547-829a-f0067a3be0e7', 1, 2, 49.99, '6c55d72a-e752-4726-ab1d-d815ce5aad21');