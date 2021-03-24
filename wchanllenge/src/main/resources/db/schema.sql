-- Tables: Creation de tables

CREATE TABLE "albums" (
    id_album bigint NOT NULL,
    id bigint,
    title character varying(255),
    user_id bigint,
    CONSTRAINT album_pkey PRIMARY KEY (id)
);

ALTER TABLE "albums" OWNER to postgres;

CREATE TABLE "users" (
    id bigint NOT NULL,
    email character varying(255),
    id_user bigint,
    name character varying(255),
    phone character varying(255),
    username character varying(255),
    website character varying(255),
    address_id bigint,
    company_id bigint,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE "users" OWNER to postgres;

CREATE TABLE "posts" (
    id bigint NOT NULL,
    body character varying(255),
    id_post bigint,
    title character varying(255),
    user_id bigint NOT NULL,
    CONSTRAINT posts_pkey PRIMARY KEY (id)
);

ALTER TABLE "posts" OWNER to postgres;

CREATE TABLE "photos" (
    id bigint NOT NULL,
    id_photo bigint,
    thumbnail_url character varying(255),
    title character varying(255),
    url character varying(255),
    album_id bigint NOT NULL,
    CONSTRAINT photos_pkey PRIMARY KEY (id)
);

ALTER TABLE "photos" OWNER to postgres;

CREATE TABLE "comments"(
     id bigint NOT NULL,
    body character varying(255),
    email character varying(255),
    id_comment bigint,
    name character varying(255),
    post_id bigint NOT NULL,
    CONSTRAINT comments_pkey PRIMARY KEY (id)
);

ALTER TABLE "comments" OWNER to postgres;

CREATE TABLE "album_user" (
    id bigint NOT NULL,
    album_id bigint NOT NULL,
    user_id bigint NOT NULL,
    type_of_access character varying(255),
    CONSTRAINT album_user_pkey PRIMARY KEY (id)
);

ALTER TABLE "album_user" OWNER to postgres;

CREATE TABLE "address" (
    id bigserial NOT NULL,
    geo_id bigint,
    street character varying(255),
    suite character varying(255),
    city character varying(255),
    zipcode character varying(255),
    CONSTRAINT address_pkey PRIMARY KEY (id)
);

ALTER TABLE "address" OWNER to postgres;

CREATE TABLE "geo" (
    id bigint NOT NULL,
    latitud character varying(255),
    longitud character varying(255),
    CONSTRAINT geo_pkey PRIMARY KEY (id)
);

ALTER TABLE "geo" OWNER to postgres;

CREATE TABLE "company" (
    id bigint NOT NULL,
    bs character varying(255),
    catch_phrase character varying(255),
    name character varying(255),
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

ALTER TABLE "company" OWNER to postgres;

-- ----------------------------
-- Foreign Keys
-- ----------------------------
ALTER TABLE "posts" ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "users" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "photos" ADD CONSTRAINT fk_album FOREIGN KEY (album_id) REFERENCES "albums" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "comments" ADD CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES "posts" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "album_user" ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "users" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "album_user" ADD CONSTRAINT fk_album FOREIGN KEY (album_id) REFERENCES "albums" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;


-- ----------------------------
-- Foreign Keys
-- ----------------------------
ALTER TABLE "address" ADD CONSTRAINT fk_geo FOREIGN KEY (geo_id) REFERENCES "geo" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "users" ADD CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES "address" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "users" ADD CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES "company" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

