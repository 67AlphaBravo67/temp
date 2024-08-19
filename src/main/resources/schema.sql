create table if not exists Taco_Order (
    id serial primary key,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Taco (
                                    id serial primary key,
                                    name varchar(50) not null,
                                    taco_order bigint not null,
                                    taco_order_key bigint not null,
                                    created_at timestamp not null
);

create table if not exists Ingredient_Ref (
                                              ingredient varchar(4) primary key,
                                              taco bigint not null,
                                              taco_key bigint not null
);

create table if not exists Ingredient (
                                          id varchar(4) primary key,
                                          name varchar(25) not null,
                                          type varchar(10) not null
);

alter table Taco
    add foreign key (taco_order) references Taco_Order(id);

alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);

insert into Ingredient(id, name, type)
values ('GRAB', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');
insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');