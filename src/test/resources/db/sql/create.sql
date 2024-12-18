

DROP TABLE public.group_subject;
DROP TABLE public.teacher_subject;
DROP TABLE public.student;
DROP TABLE public.groups;
DROP TABLE public.teacher;
DROP TABLE public.subject;

CREATE TYPE public.gender AS ENUM
    ('MALE', 'FEMALE');

ALTER TYPE public.gender
    OWNER TO postgres;



CREATE TABLE public.teacher
(
    id SERIAL NOT NULL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    second_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    gender gender NOT NULL,
    category text COLLATE pg_catalog."default" NOT NULL,
    date_birth date NOT NULL,
    CONSTRAINT teacher_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE TABLE public.subject
(
    id SERIAL NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT subject_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.subject
    OWNER to postgres;

ALTER TABLE public.teacher
    OWNER to postgres;

CREATE TABLE public.teacher_subject
(
    id SERIAL NOT NULL,
    subject_id integer NOT NULL,
    teacher_id integer NOT NULL,
    CONSTRAINT teacher_subject_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.teacher_subject
    OWNER to postgres;



CREATE TABLE public.groups
(
    id  bigint NOT NULL ,
    name text COLLATE pg_catalog."default" NOT NULL,
    specification text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.groups
    OWNER to postgres;

-- Table: public.group_subject

-- DROP TABLE public.group_subject;

CREATE TABLE public.group_subject
(
    id SERIAL NOT NULL,
    group_id integer NOT NULL,
    subject_id integer NOT NULL,
    CONSTRAINT group_subject_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.group_subject
    OWNER to postgres;


-- Table: public.student

-- DROP TABLE public.student;

CREATE TABLE public.student
(
    id SERIAL NOT NULL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    second_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    date_birth date NOT NULL,
    gender gender NOT NULL,
    group_id integer NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT fkbqmqupufmh1gnjjca1ld1ljt5 FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;


