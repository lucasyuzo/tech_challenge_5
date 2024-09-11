INSERT INTO payment_table
    (id, method, card_name, card_number, creation_date_time, order_id)
VALUES
    ('45ff08b5-4ebe-47f1-8ac7-fc1ce3d0b447', 'CREDIT', 'Lucas Pereira Silvestre', '12340001', '2024-09-10 14:30:00', 'ad9c60f6-0f20-4806-95aa-51a8f2f05753');

INSERT INTO order_table
    (id, status, user_id, payment_id)
VALUES
    ('ad9c60f6-0f20-4806-95aa-51a8f2f05753', 'IN_PREPARATION', 'cda0bb5f-7d3e-4411-ad15-00533ccd9555', '45ff08b5-4ebe-47f1-8ac7-fc1ce3d0b447'),
    ('08447b8f-18f2-4fab-907b-b6f3e74051e9', 'WAITING_PAYMENT', 'cda0bb5f-7d3e-4411-ad15-00533ccd9555', null);

INSERT INTO item_table
    (id, product_id, quantity, price, order_id)
VALUES
    ('95aacc23-ed7b-49ea-b045-8d5b334b9994', 'c4697228-b76b-4603-a73d-ac2abff19aaf', 1, 159.90, 'ad9c60f6-0f20-4806-95aa-51a8f2f05753'),
    ('32963d49-06e5-4c59-a46f-d416bdd55042', 'd463aead-1f15-43cf-8ba5-d8c6855cea46', 1, 75.99, 'ad9c60f6-0f20-4806-95aa-51a8f2f05753'),
    ('25d2ddc6-610d-4dab-ac5a-493d09f33fac', '5c6fb1bc-141c-4893-897b-e3db07c8ac63', 2, 24.99, '08447b8f-18f2-4fab-907b-b6f3e74051e9');