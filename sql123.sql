use inventory;

INSERT INTO items(inventory_number, description_item, count_items, id_office)
VALUES (1137007, "Монитор TFT 17", 10, 1);

INSERT INTO offices(office_number)
VALUES (500);

select o.id, o.office_number, i.number, i.description, i.count_items 
from offices as o left join items as i on o.id_items = i.id 
where o.office_number = 308;

select * from items;
select * from offices;

DELETE FROM offices WHERE office_number = 400;

DELETE FROM items WHERE id in (18);

update items set id_office = 1 where id = 10;








CREATE TABLE user (
    id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
) engine=InnoDB;

CREATE TABLE role (
    id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
) engine=InnoDB;

CREATE TABLE user_roles (
    id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id bigint not null,
    role_id bigint not null,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
) engine=InnoDB;







CREATE TABLE offices (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    office_number INT NOT NULL UNIQUE
);

CREATE TABLE items (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    inventory_number INT NOT NULL,
    description_item VARCHAR(300),
    count_items INT NOT NULL,
    id_office INT NOT NULL,
    FOREIGN KEY (id_office) REFERENCES offices (id)
);
