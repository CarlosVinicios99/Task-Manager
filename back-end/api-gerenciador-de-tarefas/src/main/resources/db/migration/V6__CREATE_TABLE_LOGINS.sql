CREATE TABLE logins (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	email VARCHAR(120) UNIQUE NOT NULL,
	password VARCHAR(256) NOT NULL
);