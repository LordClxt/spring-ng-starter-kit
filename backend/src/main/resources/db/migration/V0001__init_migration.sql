CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    sexe VARCHAR(50),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255),
    data JSON
);

CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    data JSON,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users_roles (
    user_id UUID REFERENCES users(id),
    role_id UUID REFERENCES roles(id),
    data JSON,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name) VALUES ('user'), ('admin');