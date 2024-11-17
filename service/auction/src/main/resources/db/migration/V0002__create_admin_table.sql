CREATE TABLE auction_administrators
(
    auction_id VARCHAR(255) NOT NULL,
    admin_id   VARCHAR(255) NOT NULL
);

ALTER TABLE auction_administrators
    ADD CONSTRAINT fk_auction_administrators_on_auction_entity FOREIGN KEY (auction_id) REFERENCES auction (id);

