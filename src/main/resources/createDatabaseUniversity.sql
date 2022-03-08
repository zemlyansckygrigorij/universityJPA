-- Database: university

-- DROP DATABASE university;

CREATE DATABASE university
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Type: gender

-- DROP TYPE public.gender;

CREATE TYPE public.gender AS ENUM
    ('MALE', 'FEMALE');

ALTER TYPE public.gender
    OWNER TO postgres;

-- Table: public.groups

-- DROP TABLE public.groups;

CREATE TABLE public.groups
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    specification text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.groups
    OWNER to postgres;


-- Table: public.subject

-- DROP TABLE public.subject;

CREATE TABLE public.subject
(
    id_subject integer NOT NULL,
    name_subject text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT subject_pkey PRIMARY KEY (id_subject)
)

    TABLESPACE pg_default;

ALTER TABLE public.subject
    OWNER to postgres;


-- Table: public.teacher

-- DROP TABLE public.teacher;

CREATE TABLE public.teacher
(
    id integer NOT NULL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    second_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    gender gender NOT NULL,
    category text COLLATE pg_catalog."default" NOT NULL,
    date_birth date NOT NULL,
    CONSTRAINT teacher_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.teacher
    OWNER to postgres;


-- Table: public.student

-- DROP TABLE public.student;

CREATE TABLE public.student
(
    id integer NOT NULL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    second_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    date_birth date NOT NULL,
    gender gender NOT NULL,
    id_group integer NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT fkbqmqupufmh1gnjjca1ld1ljt5 FOREIGN KEY (id_group)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;


-- Table: public.group_subject

-- DROP TABLE public.group_subject;

CREATE TABLE public.group_subject
(
    id integer NOT NULL,
    id_group integer NOT NULL,
    id_subject integer NOT NULL,
    CONSTRAINT group_subject_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.group_subject
    OWNER to postgres;

-- Table: public.teacher_subject

-- DROP TABLE public.teacher_subject;

CREATE TABLE public.teacher_subject
(
    id integer NOT NULL,
    id_subject integer NOT NULL,
    id_teacher integer NOT NULL,
    CONSTRAINT teacher_subject_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.teacher_subject
    OWNER to postgres;