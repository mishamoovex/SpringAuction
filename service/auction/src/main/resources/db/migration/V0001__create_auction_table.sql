CREATE TABLE auction
(
    id          VARCHAR(255)                NOT NULL,
    create_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    owner_id    VARCHAR(255)                NOT NULL,
    name        VARCHAR(100)                NOT NULL,
    status      VARCHAR(255)                NOT NULL,
    start_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_auction PRIMARY KEY (id)
);
