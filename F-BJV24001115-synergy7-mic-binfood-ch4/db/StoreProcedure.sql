CREATE OR REPLACE PROCEDURE
create_user("id" UUID, "username" character varying, "email" character varying, "password" character varying, "role" character varying)
LANGUAGE sql
AS $$
INSERT INTO users (id, username, email, password, role, deleted)
VALUES ("id", "username", "email", "password", "role", false);
$$;

CREATE OR REPLACE PROCEDURE
update_username("id" UUID, "username" character varying)
LANGUAGE sql
AS $$
UPDATE users SET username = "username" WHERE id = "id"
$$;

CREATE OR REPLACE PROCEDURE
update_email("id" UUID, "email" character varying)
LANGUAGE sql
AS $$
UPDATE users SET email = "email" WHERE id = "id"
$$;

CREATE OR REPLACE PROCEDURE
update_password("id" UUID, "password" character varying)
LANGUAGE sql
AS $$
UPDATE users SET password = "password" WHERE id = "id"
$$;

CREATE OR REPLACE PROCEDURE
safe_delete_user("id" UUID)
LANGUAGE sql
AS $$
UPDATE users SET deleted = true WHERE id = "id"
$$;