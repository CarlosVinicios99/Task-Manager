CREATE TABLE projects (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    creation_timestamp BIGINT NOT NULL,
    workspace_id UUID NOT NULL,
    FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
);
