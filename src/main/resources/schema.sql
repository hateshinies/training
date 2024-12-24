CREATE TABLE customer
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

CREATE TABLE post
(
    id          SERIAL PRIMARY KEY,
    customer_id INTEGER,
    text        VARCHAR,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES public.customer (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX post_customer_id_idx ON post USING BTREE (customer_id)
;

CREATE TABLE comment
(
    id          SERIAL PRIMARY KEY,
    customer_id INTEGER,
    post_id     INTEGER,
    text        VARCHAR,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES PUBLIC.customer (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (post_id) REFERENCES PUBLIC.post (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX comment_customer_id_idx ON comment USING BTREE (customer_id);
CREATE INDEX comment_post_id_idx ON comment USING BTREE (post_id)
;

CREATE TABLE int_lock
(
    lock_key     VARCHAR(100) NOT NULL,
    region       VARCHAR(100) NOT NULL,
    client_id    VARCHAR(36),
    created_date TIMESTAMP    NOT NULL,
    PRIMARY KEY (lock_key, region));