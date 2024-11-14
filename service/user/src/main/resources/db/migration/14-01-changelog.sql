-- liquibase formatted sql

-- changeset user:1731607385046-1
CREATE TABLE user_account
(
    id          VARCHAR(255)                NOT NULL,
    create_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    first_name  VARCHAR(30)                 NOT NULL,
    last_name   VARCHAR(30)                 NOT NULL,
    email       VARCHAR(50)                 NOT NULL,
    password    VARCHAR(50)                 NOT NULL,
    CONSTRAINT pk_user_account PRIMARY KEY (id)
);

-- changeset user:1731607385046-2
ALTER TABLE user_account
    ADD CONSTRAINT uc_user_account_email UNIQUE (email);

