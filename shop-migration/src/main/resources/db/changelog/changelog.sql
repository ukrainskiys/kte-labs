--liquibase formatted sql

--changeset liquibase:1
-- comment: create tables
CREATE TABLE public.clients (
    id                         BIGSERIAL PRIMARY KEY NOT NULL,
    "name"                     VARCHAR(255),
    individual_discount_first  INTEGER,
    individual_discount_second INTEGER
);

CREATE TABLE public.products (
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    "name"      VARCHAR(255) NOT NULL,
    price       NUMERIC NOT NULL,
    description TEXT
);

CREATE TABLE public.product_discount (
    product_id       BIGINT NOT NULL,
    percent_discount INTEGER NOT NULL
);

CREATE TABLE public.product_ratings (
     id          BIGSERIAL PRIMARY KEY NOT NULL,
     rating      INTEGER NOT NULL,
     client_id   BIGINT NOT NULL REFERENCES public.clients,
     product_id BIGINT NOT NULL REFERENCES public.products
);

CREATE TABLE public.sale_facts (
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    client_id    BIGINT NOT NULL REFERENCES public.clients,
    sale_date    TIMESTAMP(6),
    check_number VARCHAR(6)
);

CREATE TABLE public.sale_fact_positions (
    id                     BIGSERIAL PRIMARY KEY NOT NULL,
    sale_fact_id           BIGINT NOT NULL REFERENCES public.sale_facts,
    product_id             BIGINT NOT NULL,
    count                  INTEGER NOT NULL,
    price                  NUMERIC NOT NULL,
    final_price            NUMERIC NOT NULL,
    final_discount_percent INTEGER NOT NULL
);

CREATE TABLE public.statistics (
    id              BIGSERIAL PRIMARY KEY NOT NULL,
    last_update     TIMESTAMP NOT NULL,
    client_id       BIGINT,
    product_id      BIGINT,
    count_checks    INTEGER NOT NULL,
    total_costs     NUMERIC NOT NULL,
    total_discounts NUMERIC NOT NULL
);


--changeset liquibase:2
-- comment: init clients table
INSERT INTO public.clients ("name", individual_discount_first, individual_discount_second) VALUES ('client-1', null, null);
INSERT INTO public.clients ("name", individual_discount_first, individual_discount_second) VALUES ('client-2', null, 7);
INSERT INTO public.clients ("name", individual_discount_first, individual_discount_second) VALUES ('client-3', 5, null);
INSERT INTO public.clients ("name", individual_discount_first, individual_discount_second) VALUES ('client-4', 5, 10);
INSERT INTO public.clients ("name", individual_discount_first, individual_discount_second) VALUES ('client-5', 20, 40);


--changeset liquibase:3
-- comment: init products table
INSERT INTO public.products ("name", description, price) VALUES ('product-1', 'dec-1', 100);
INSERT INTO public.products ("name", description, price) VALUES ('product-2', 'dec-2', 200);
INSERT INTO public.products ("name", description, price) VALUES ('product-3', 'dec-3', 300);
INSERT INTO public.products ("name", description, price) VALUES ('product-4', 'dec-4', 400);
INSERT INTO public.products ("name", description, price) VALUES ('product-5', 'dec-5', 500);
INSERT INTO public.products ("name", description, price) VALUES ('product-6', 'dec-6', 600);
INSERT INTO public.products ("name", description, price) VALUES ('product-7', 'dec-7', 700);
INSERT INTO public.products ("name", description, price) VALUES ('product-8', 'dec-8', 800);
INSERT INTO public.products ("name", description, price) VALUES ('product-9', 'dec-9', 900);
INSERT INTO public.products ("name", description, price) VALUES ('product-10', 'dec-10', 1000.99);
