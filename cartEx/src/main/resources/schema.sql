DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS item;

CREATE TABLE item(
    id serial primary key,
    name varchar not null,
    price real not null
);

CREATE TABLE cart(
    id serial primary key
);

CREATE TABLE cart_item(
    id serial primary key,
    quantity int not null,
    item_id int not null,
    cart_id int not null,
    constraint fk_item_id foreign key(item_id) references "item"(id),
    constraint fk_cart_id foreign key(cart_id) references "cart"(id) on delete cascade on update cascade
);