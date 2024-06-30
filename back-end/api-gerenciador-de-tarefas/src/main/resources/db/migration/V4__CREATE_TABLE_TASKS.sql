CREATE TYPE "TASK_STATUS" AS ENUM('TODO', 'IN_PROGRESS', 'DONE');

CREATE TYPE "PRIORITY" AS ENUM('LOW', 'MEDIUM', 'HIGH', 'URGENT');

CREATE TABLE tasks(
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	title VARCHAR(50) NOT NULL,
	description VARCHAR(1000),
	status "TASK_STATUS" NOT NULL,
	priority "PRIORITY" NOT NULL,
	creation_timestamp BIGINT NOT NULL,
	expected_conclusion_timestamp BIGINT NOT NULL,
	creator_id UUID NOT NULL,
	worker_id UUID NOT NULL,
	project_id UUID NOT NULL,
	FOREIGN KEY (creator_id) REFERENCES users(id),
	FOREIGN KEY (worker_id) REFERENCES users(id),
	FOREIGN KEY (project_id) REFERENCES projects(id)
);