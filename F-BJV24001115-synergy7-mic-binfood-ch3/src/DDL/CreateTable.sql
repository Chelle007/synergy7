CREATE TABLE User IF NOT EXIST {
    id INT,
    username VARCHAR(100),
    email VARCHAR(320),
    password VARCHAR(100),
    restaurantId INT,
    PRIMARY KEY(id),
    FOREIGN KEY(restaurantId) REFERENCES Restaurant(id)
};

CREATE TABLE Restaurant IF NOT EXIST {
    id INT,
    name VARCHAR(100),
    location VARCHAR(400),
    open BOOLEAN,
    PRIMARY KEY(id)
};

CREATE TABLE MenuItem IF NOT EXIST {
    id INT,
    name VARCHAR(100),
    foodType VARCHAR(100),
    priceS INT,
    priceM INT,
    priceL INT,
    restaurantId INT,
    PRIMARY KEY(id),
    FOREIGN KEY(restaurantId) REFERENCES Restaurant(id)
};

CREATE TABLE Order IF NOT EXIST {
    id INT,
    orderTime DATETIME,
    destinationAddress VARCHAR(400),
    completed BOOLEAN,
    userId INT,
    restaurantId INT,
    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES User(id),
    FOREIGN KEY(restaurantId) REFERENCES Restaurant(id)
};

CREATE TABLE OrderDetail IF NOT EXIST {
    id INT,
    size VARCHAR(50),
    qty INT,
    price INT,
    menuItemId INT,
    orderId INT,
    PRIMARY KEY(id),
    FOREIGN KEY(menuItemId) REFERENCES MenuItem(id),
    FOREIGN KEY(orderId) REFERENCES Order(id)
};