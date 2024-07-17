CREATE VIEW view_users_workspaces AS
    SELECT 
        u.id AS user_id, 
        u.name AS user_name, 
        l.email, 
        w.id AS workspace_id, 
        w.title, 
        w.creation_timestamp, 
        w.description, 
        uw.role AS user_role,
        uw.id AS user_workspace_id
    FROM 
        users AS u
    JOIN 
        logins AS l ON u.login_id = l.id
    JOIN 
        users_workspaces AS uw ON uw.user_id = u.id
    JOIN 
        workspaces AS w ON uw.workspace_id = w.id;
