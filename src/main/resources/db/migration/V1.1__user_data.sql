INSERT INTO user (id, first_name, last_name, email, password) VALUES (1, 'Admin', 'Admin', 'admin@gmail.com', '$2a$04$jJXpmfs2Gxdu4dWfYLN6xujR03Ixj3BbnMjWqZuU8EhcpcUWGFaz2');

INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);