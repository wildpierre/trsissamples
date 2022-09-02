CREATE USER debezium WITH LOGIN REPLICATION PASSWORD 'debezium';
 
create schema example;

set search_path='example';

DROP TABLE IF EXISTS person;

CREATE TABLE person (
  persid serial primary key,
  name text NOT NULL
);

DROP TABLE IF EXISTS userprofile;

CREATE TABLE userprofile (
  userid serial primary key,
  fk_persid integer not null, 
  orgname text NOT NULL
);


ALTER SCHEMA example OWNER TO debezium;