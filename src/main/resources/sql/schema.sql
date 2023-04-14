DROP SEQUENCE if exists b2b2c.busisness_seq;
CREATE SEQUENCE b2b2c.busisness_seq;
DROP TABLE IF EXISTS b2b2c.busisness;
CREATE TABLE IF NOT EXISTS b2b2c.busisness
(
    busisness_id integer NOT NULL DEFAULT nextval('b2b2c.busisness_seq'),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    ruc character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT busisness_pkey PRIMARY KEY (busisness_id),
    CONSTRAINT "UK_Ruc" UNIQUE (ruc)
    );

-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** ient`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP SEQUENCE if exists b2b2c.client_seq;
CREATE SEQUENCE b2b2c.client_seq;

DROP TABLE IF EXISTS b2b2c.client;
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

DROP SEQUENCE if exists b2b2c.socialnetwork_busisness_seq;
CREATE SEQUENCE b2b2c.socialnetwork_busisness_seq;
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
DROP SEQUENCE if exists b2b2c.address_seq;
CREATE SEQUENCE b2b2c.address_seq;

DROP TABLE IF EXISTS b2b2c.address;
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
DROP SEQUENCE if exists b2b2c.busisness_client_seq;
CREATE SEQUENCE b2b2c.busisness_client_seq;

DROP TABLE IF EXISTS b2b2c.busisness_client;
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

DROP SEQUENCE if exists b2b2c.transactionsbusisnessclient_seq;
CREATE SEQUENCE b2b2c.transactionsbusisnessclient_seq;
DROP TABLE IF EXISTS b2b2c.transactionsbusisnessclient;
CREATE TABLE IF NOT EXISTS b2b2c.transactionsbusisnessclient (
    transactionsbusisnessclient_id INT NOT NULL DEFAULT NEXTVAL ('b2b2c.transactionsbusisnessclient_seq'),
    client_id INT NOT NULL,
    busisness_id INT NOT NULL,
    transactionDate DATE not NULL,
    PRIMARY KEY (transactionsbusisnessclient_id),
    CONSTRAINT fk_transactionsbusisnessclient_client_busisness1
    FOREIGN KEY (busisness_id)
    REFERENCES b2b2c.busisness (busisness_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_transactionsbusisnessclient_client1
    FOREIGN KEY (client_id)
    REFERENCES b2b2c.client (client_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE b2b2c.address
    ADD COLUMN city VARCHAR(45) NULL;

