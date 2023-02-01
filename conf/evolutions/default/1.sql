-- !Ups

CREATE TABLE IF NOT EXISTS feedback_form
(
    id         bigserial PRIMARY KEY,
    first_name varchar(30)  NOT NULL,
    last_name  varchar(30)  NOT NULL,
    email      varchar(30)  NOT NULL,
    phone      varchar(20)  NOT NULL,
    created_at timestamptz  NOT NULL,
    text       varchar(500) NOT NULL
);


-- !Downs

DROP TABLE IF EXISTS feedback_form;