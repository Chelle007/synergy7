CREATE TABLE "User" IF NOT EXISTS {
    id UUID,
    username TEXT,
    email TEXT,
    password TEXT,
    type TEXT,
    restaurant_id UUID,
    PRIMARY KEY(id),
    FOREIGN KEY(restaurant_id) REFERENCES "Restaurant"(id)
};

CREATE TABLE "Restaurant" IF NOT EXISTS {
    id UUID,
    name TEXT,
    location TEXT,
    open BOOLEAN,
    PRIMARY KEY(id)
};

CREATE TABLE "MenuItem" IF NOT EXISTS {
    id UUID,
    name TEXT,
    foodType TEXT,
    priceS INT,
    priceM INT,
    priceL INT,
    restaurant_id UUID,
    PRIMARY KEY(id),
    FOREIGN KEY(restaurant_id) REFERENCES "Restaurant"(id)
};

CREATE TABLE "Order" IF NOT EXISTS {
    id UUID,
    orderTime TIMESTAMP,
    destinationAddress TEXT,
    completed BOOLEAN,
    user_id UUID,
    restaurant_id UUID,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES "User"(id),
    FOREIGN KEY(restaurant_id) REFERENCES "Restaurant"(id)
};

CREATE TABLE "OrderDetail" IF NOT EXISTS {
    id UUID,
    size TEXT,
    qty INT,
    price INT,
    menuItem_id UUID,
    order_id UUID,
    PRIMARY KEY(id),
    FOREIGN KEY(menuItem_id) REFERENCES "MenuItem"(id),
    FOREIGN KEY(order_id) REFERENCES "Order"(id)
};