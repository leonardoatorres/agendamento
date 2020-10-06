create table cav(
  id int primary key auto_increment not null,
  name varchar(200)
);

insert into cav (name) VALUE ('Botafogo');
insert into cav (name) VALUE ('Barra da Tijuca');
insert into cav (name) VALUE ('Norte Shopping');

create table car(
    id int primary key auto_increment not null,
    brand varchar(200),
    model varchar(200),
    cav varchar(200)
);

insert into car (brand, model, cav) VALUES ('VW', 'Golf', 'Botafogo');
insert into car (brand, model, cav) VALUES ('Ford', 'Fiesta', 'Norte Shopping');
insert into car (brand, model, cav) VALUES ('GM', 'Cruze', 'Barra da Tijuca');
insert into car (brand, model, cav) VALUES ('GM', 'Cobalt', 'Barra da Tijuca');
insert into car (brand, model, cav) VALUES ('GM', 'Cobalt', 'Barra da Tijuca');
insert into car (brand, model, cav) VALUES ('Fiat', 'Palio', 'Barra da Tijuca');
insert into car (brand, model, cav) VALUES ('GVW', 'Up', 'Botafogo');

create table car(
    brand varchar(200),
    model varchar(200),
    cav varchar(200)
);

create table calendar(
    date date,
    cav varchar(200),
    tipo varchar(200),
    car int,
    hour int
);