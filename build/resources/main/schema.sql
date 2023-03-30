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


CREATE TABLE b2b2c.socialnetwork_busisness
(
    socialnetwork_busisness_id serial NOT NULL,
    busisness_id integer NOT NULL,
    "socialNetwork" integer NOT NULL,
    PRIMARY KEY (socialnetwork_busisness_id),
    CONSTRAINT "UK_busisness_id_socialNetwork" UNIQUE (busisness_id)
        INCLUDE("socialNetwork"),
    CONSTRAINT "Fk_busisness_id" FOREIGN KEY (busisness_id)
        REFERENCES b2b2c.busisness (busisness_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS b2b2c.socialnetwork_busisness
    OWNER to postgres;


CREATE TABLE b2b2c.client
(
    client_id serial NOT NULL,
    name character varying(150) NOT NULL,
    "lastName" character varying(150) NOT NULL,
    document_type integer NOT NULL,
    dni character varying(50) NOT NULL,
    civil_status integer,
    gender integer NOT NULL,
    birthdate date,
    PRIMARY KEY (client_id),
    CONSTRAINT "UK_document_type_dni" UNIQUE (document_type, dni)
);


