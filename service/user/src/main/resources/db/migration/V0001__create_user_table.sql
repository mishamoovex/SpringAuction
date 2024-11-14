CREATE TABLE "user_account"
(
    id          VARCHAR(255)                NOT NULL,
    create_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    first_name  VARCHAR(30)                 NOT NULL,
    last_name   VARCHAR(30)                 NOT NULL,
    email       VARCHAR(40),
    password    VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);
