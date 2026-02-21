CREATE TABLE orders (
    id UUID PRIMARY KEY NOT NULL DEFAULT GEN_RANDOM_UUID (),
    user_id UUID NOT NULL,
    total_amount NUMERIC(12, 2) NOT NULL,
    order_created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT  fk_orders_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE RESTRICT
);

CREATE TABLE order_items (
    id UUID PRIMARY KEY NOT NULL DEFAULT GEN_RANDOM_UUID (),
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    price_at_purchase NUMERIC(12, 2) NOT NULL,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_order_items_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_quantity_positive
        CHECK ( quantity > 0 )
);