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

ALTER TABLE IF EXISTS b2b2c.socialnetwork_busisness
    OWNER to postgres;


CREATE SEQUENCE b2b2c.client_seq;

CREATE TABLE IF NOT EXISTS b2b2c.client (
    client_id INT NOT NULL DEFAULT NEXTVAL ('b2b2c.client_seq'),
    name VARCHAR(150) NOT NULL,
    lastName VARCHAR(150) NOT NULL,
    document_type INT NOT NULL,
    dni VARCHAR(50) NOT NULL,
    civil_status INT NULL,
    gender INT NOT NULL,
    birthdate DATE NULL,
    PRIMARY KEY (client_id),
    CONSTRAINT "UK_document_type_dni" UNIQUE (document_type, dni));

DROP TABLE IF EXISTS b2b2c.socialnetwork_busisness;
CREATE TABLE IF NOT EXISTS b2b2c.socialnetwork_busisness (
    socialnetwork_busisness_id INT NOT NULL DEFAULT NEXTVAL ('b2b2c.socialnetwork_busisness_seq'),
    busisness_id INT NOT NULL,
    socialNetwork INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (socialnetwork_busisness_id),
    CONSTRAINT "UK_busisness_id_socialNetwork" UNIQUE (name, socialNetwork, busisness_id),
    CONSTRAINT FK_socialnetwork_busisness_busisness1
    FOREIGN KEY (busisness_id)
    REFERENCES b2b2c.busisness (busisness_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** dress`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE b2b2c.address_seq;

CREATE TABLE IF NOT EXISTS b2b2c.address (
    address_id INT NOT NULL DEFAULT NEXTVAL ('b2b2c.address_seq'),
    addres VARCHAR(45) NOT NULL,
    latitude VARCHAR(45) NOT NULL,
    longitude VARCHAR(45) NOT NULL,
    client_id INT NOT NULL,
    PRIMARY KEY (address_id),
    CONSTRAINT "client_id_UNIQUE" UNIQUE (client_id),
    CONSTRAINT fk_address_client1
    FOREIGN KEY (client_id)
    REFERENCES b2b2c.client (client_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);-- Este en pgadmin


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** sisness_client`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE b2b2c.busisness_client_seq;

CREATE TABLE IF NOT EXISTS b2b2c.busisness_client (
    busisness_client_id INT NOT NULL DEFAULT NEXTVAL ('b2b2c.busisness_client_seq'),
    busisness_id INT NOT NULL,
    client_id INT NOT NULL,
    PRIMARY KEY (busisness_client_id),
    CONSTRAINT "UK_busisness_id_client_id" UNIQUE (busisness_id, client_id),
    CONSTRAINT fk_busisness_client_busisness1
    FOREIGN KEY (busisness_id)
    REFERENCES b2b2c.busisness (busisness_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_busisness_client_client1
    FOREIGN KEY (client_id)
    REFERENCES b2b2c.client (client_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);-- Este en Pgadmin


