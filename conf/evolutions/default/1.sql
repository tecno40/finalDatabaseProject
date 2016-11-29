# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cities (
  id                            integer,
  city                          varchar(255),
  state                         varchar(255)
);

create table shop (
  id                            integer,
  name                          varchar(255),
  street                        varchar(255),
  cityid                        integer,
  phone_number                  varchar(255),
  menu_url                      varchar(255),
  varieties                     integer,
  overallrating                 float
);

create table user (
  id                            varchar(255),
  password                      varchar(255),
  first_name                    varchar(255),
  last_name                     varchar(255),
  cityid                        integer,
  city                          varchar(255),
  state                         varchar(255),
  email                         varchar(255)
);


# --- !Downs

drop table if exists cities cascade;

drop table if exists shop cascade;

drop table if exists user cascade;

