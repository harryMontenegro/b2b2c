INSERT INTO b2b2c.busisness ("name",ruc) VALUES
	 ('La estrella 4','78522125-7'),
	 ('DistriFalca','78522125822'),
	 ('Cavify','85421255212'),
	 ('Didi','85421225855236');

-- Clave 1234
ALTER TABLE IF EXISTS b2b2c.busisness
    ADD COLUMN pass_busisness character varying(200) NOT NULL DEFAULT '$2a$10$Ci43RbTUGgpzZjERS0ytyOVVoS8jSqDlIOWhHOFtvL.eeL4rjz1Za';


