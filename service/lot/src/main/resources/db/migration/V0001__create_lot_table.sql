CREATE TABLE lot
(
    id            VARCHAR(255)                NOT NULL,
    create_time   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    auction_id VARCHAR(255) NOT NULL,
    title         VARCHAR(100)                NOT NULL,
    description   VARCHAR(255)                NOT NULL,
    image_url     VARCHAR(255)                NOT NULL,
    initial_price DECIMAL                     NOT NULL,
    CONSTRAINT pk_lot PRIMARY KEY (id)
);

