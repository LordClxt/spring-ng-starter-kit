CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    keycloack_id VARCHAR(255) NOT NULL,
    sexe VARCHAR(50)
);