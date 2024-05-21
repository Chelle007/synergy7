CREATE TABLE "users" (
    id UUID PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50),
    deleted BOOLEAN
);

CREATE TABLE "restaurant" (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255),
    open BOOLEAN,
    owner_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (owner_id) REFERENCES "users" (id)
);

CREATE TABLE "menu_item" (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    food_type VARCHAR(20),
    price_s INTEGER,
    price_m INTEGER,
    price_l INTEGER,
    restaurant_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (restaurant_id) REFERENCES "restaurant" (id)
);

CREATE TABLE "orders" (
    id UUID PRIMARY KEY,
    order_time TIMESTAMP,
    destination_address VARCHAR(255),
    notes TEXT,
    completed BOOLEAN,
    user_id UUID,
    restaurant_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES "users" (id),
    FOREIGN KEY (restaurant_id) REFERENCES "restaurant" (id)
);

CREATE TABLE "order_detail" (
    id UUID PRIMARY KEY,
    size VARCHAR(255),
    qty INTEGER,
    price INTEGER,
    menu_item_id UUID,
    order_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (menu_item_id) REFERENCES "menu_item" (id),
    FOREIGN KEY (order_id) REFERENCES "orders" (id)
);
