CREATE TABLE platforms (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE developers (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE games (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       cover VARCHAR(255),
                       release_date DATE NOT NULL,
                       summary TEXT,
                       developer_id BIGINT NOT NULL REFERENCES developers (id) ON DELETE CASCADE
);

CREATE TABLE platforms_games (
                                 platform_id BIGINT NOT NULL REFERENCES platforms (id) ON DELETE CASCADE,
                                 game_id BIGINT NOT NULL REFERENCES games (id) ON DELETE CASCADE,
                                 PRIMARY KEY (platform_id, game_id)
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password CHAR(60) NOT NULL
);

CREATE TABLE userroles (
                           code VARCHAR(255) PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE users_userroles (
                                 user_id BIGINT NOT NULL REFERENCES users (id),
                                 userrole_code VARCHAR(255) NOT NULL REFERENCES userroles (code),
                                 PRIMARY KEY (user_id, userrole_code)
);

CREATE TABLE users_backlog (
                               id BIGSERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
                               game_id BIGINT NOT NULL REFERENCES games (id) ON DELETE CASCADE,
                               added_date DATE NOT NULL
);