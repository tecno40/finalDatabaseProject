# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table average_ratings (
  ratingtype                    varchar(255),
  average_val                   integer
);

create table cities (
  id                            integer,
  city                          varchar(255),
  state                         varchar(255)
);

create table feature (
  shopid                        integer,
  feature                       varchar(255)
);

create table friendsfavs (
  name                          varchar(255),
  street                        varchar(255),
  state                         varchar(255),
  city                          varchar(255),
  overallrating                 varchar(255),
  reviewtext                    varchar(255)
);

create table image (
  id                            integer,
  shopid                        integer,
  userid                        varchar(255),
  url                           varchar(255)
);

create table rating (
  shopid                        integer,
  feature                       varchar(255)
);

create table reviewsofshop (
  overallrating                 varchar(255),
  reviewtext                    varchar(255),
  userid                        varchar(255)
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

create table socialmedia (
  shopid                        integer,
  platform                      varchar(255),
  handle                        varchar(255)
);


# --- !Downs

drop table if exists average_ratings cascade;

drop table if exists cities cascade;

drop table if exists feature cascade;

drop table if exists friendsfavs cascade;

drop table if exists image cascade;

drop table if exists rating cascade;

drop table if exists reviewsofshop cascade;

drop table if exists shop cascade;

drop table if exists socialmedia cascade;

