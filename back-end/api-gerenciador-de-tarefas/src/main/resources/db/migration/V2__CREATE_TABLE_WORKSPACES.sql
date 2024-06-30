CREATE TABLE workspaces(
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	title VARCHAR(50) NOT NULL,
	description VARCHAR(200),
	creation_timestamp BIGINT NOT NULL
);