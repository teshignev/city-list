CREATE SEQUENCE IF NOT EXISTS public.activity_seq;

CREATE TABLE IF NOT EXISTS public.city_list (
    id                  INTEGER DEFAULT nextval('activity_seq'::REGCLASS) NOT NULL,
    city_name           VARCHAR(4)                                        NOT NULL,
    photo_url           VARCHAR(3)                                        NOT NULL,
    PRIMARY KEY (id)
);