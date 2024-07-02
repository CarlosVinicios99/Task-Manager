CREATE TABLE users_workspaces(
	id UUID PRIMARY KEY NOT NULL DEFAULT uuid_generate_v4(),
	user_id UUID NOT NULL,
	workspace_id UUID NOT NULL,
	role VARCHAR(20) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
);