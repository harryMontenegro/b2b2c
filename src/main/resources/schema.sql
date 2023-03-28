CREATE DATABASE "b2b2c"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


-- SCHEMA: b2b2c
-- DROP SCHEMA IF EXISTS b2b2c ;

CREATE SCHEMA IF NOT EXISTS b2b2c
    AUTHORIZATION postgres;


-- Table: b2b2c.busisness

-- DROP TABLE IF EXISTS b2b2c.busisness;

CREATE TABLE IF NOT EXISTS b2b2c.busisness
(
    id integer NOT NULL DEFAULT nextval('b2b2c.busisness_busisness_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    ruc character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT busisness_pkey PRIMARY KEY (id),
    CONSTRAINT "UK_Ruc" UNIQUE (ruc)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS b2b2c.busisness
    OWNER to postgres;