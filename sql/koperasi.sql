--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: loan_status; Type: TYPE; Schema: public; Owner: ilham
--

CREATE TYPE public.loan_status AS ENUM (
    'PAID',
    'NOT_PAID'
);


ALTER TYPE public.loan_status OWNER TO ilham;

--
-- Name: transaction_type; Type: TYPE; Schema: public; Owner: ilham
--

CREATE TYPE public.transaction_type AS ENUM (
    'LOAN',
    'INSTALLMENT',
    'SAVE',
    'WITHDRAW'
);


ALTER TYPE public.transaction_type OWNER TO ilham;

--
-- Name: installment_seq; Type: SEQUENCE; Schema: public; Owner: ilham
--

CREATE SEQUENCE public.installment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.installment_seq OWNER TO ilham;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: installment; Type: TABLE; Schema: public; Owner: ilham
--

CREATE TABLE public.installment (
    id integer DEFAULT nextval('public.installment_seq'::regclass) NOT NULL,
    loan_id integer,
    total integer,
    loan_remainder integer,
    created_time timestamp without time zone,
    transaction_id integer
);


ALTER TABLE public.installment OWNER TO ilham;

--
-- Name: loan_seq; Type: SEQUENCE; Schema: public; Owner: ilham
--

CREATE SEQUENCE public.loan_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_seq OWNER TO ilham;

--
-- Name: loan; Type: TABLE; Schema: public; Owner: ilham
--

CREATE TABLE public.loan (
    id integer DEFAULT nextval('public.loan_seq'::regclass) NOT NULL,
    transaction_id integer,
    member_id integer,
    total integer,
    loan_date date,
    tenor date,
    status public.loan_status,
    created_time timestamp without time zone
);


ALTER TABLE public.loan OWNER TO ilham;

--
-- Name: member_seq; Type: SEQUENCE; Schema: public; Owner: ilham
--

CREATE SEQUENCE public.member_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.member_seq OWNER TO ilham;

--
-- Name: member; Type: TABLE; Schema: public; Owner: ilham
--

CREATE TABLE public.member (
    id integer DEFAULT nextval('public.member_seq'::regclass) NOT NULL,
    name character varying(255),
    address character varying(255),
    created_time timestamp without time zone
);


ALTER TABLE public.member OWNER TO ilham;

--
-- Name: saving_seq; Type: SEQUENCE; Schema: public; Owner: ilham
--

CREATE SEQUENCE public.saving_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.saving_seq OWNER TO ilham;

--
-- Name: saving; Type: TABLE; Schema: public; Owner: ilham
--

CREATE TABLE public.saving (
    id integer DEFAULT nextval('public.saving_seq'::regclass) NOT NULL,
    member_id integer,
    transaction_id integer,
    type public.transaction_type,
    total integer,
    current_saving integer,
    created_time timestamp without time zone
);


ALTER TABLE public.saving OWNER TO ilham;

--
-- Name: transaction_seq; Type: SEQUENCE; Schema: public; Owner: ilham
--

CREATE SEQUENCE public.transaction_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_seq OWNER TO ilham;

--
-- Name: transaction; Type: TABLE; Schema: public; Owner: ilham
--

CREATE TABLE public.transaction (
    id integer DEFAULT nextval('public.transaction_seq'::regclass) NOT NULL,
    member_id integer,
    type public.transaction_type,
    total integer,
    created_time timestamp without time zone
);


ALTER TABLE public.transaction OWNER TO ilham;

--
-- Data for Name: installment; Type: TABLE DATA; Schema: public; Owner: ilham
--

COPY public.installment (id, loan_id, total, loan_remainder, created_time, transaction_id) FROM stdin;
1	1	200000	800000	2022-06-21 15:45:10.697624	2
\.


--
-- Data for Name: loan; Type: TABLE DATA; Schema: public; Owner: ilham
--

COPY public.loan (id, transaction_id, member_id, total, loan_date, tenor, status, created_time) FROM stdin;
1	1	1	1000000	2022-06-21	2022-06-25	NOT_PAID	2022-06-21 15:44:58.679628
\.


--
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: ilham
--

COPY public.member (id, name, address, created_time) FROM stdin;
1	John	Jakarta	2022-06-21 15:44:46.400525
\.


--
-- Data for Name: saving; Type: TABLE DATA; Schema: public; Owner: ilham
--

COPY public.saving (id, member_id, transaction_id, type, total, current_saving, created_time) FROM stdin;
1	1	3	SAVE	100000000	100000000	2022-06-21 15:45:13.849079
2	1	5	WITHDRAW	200000	99800000	2022-06-21 15:45:24.989348
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: ilham
--

COPY public.transaction (id, member_id, type, total, created_time) FROM stdin;
1	1	LOAN	1000000	2022-06-21 15:44:58.679628
2	1	INSTALLMENT	200000	2022-06-21 15:45:10.697624
3	1	SAVE	100000000	2022-06-21 15:45:13.849079
5	1	WITHDRAW	200000	2022-06-21 15:45:24.989348
4	1	WITHDRAW	200000	2022-06-21 15:45:16.591341
\.


--
-- Name: installment_seq; Type: SEQUENCE SET; Schema: public; Owner: ilham
--

SELECT pg_catalog.setval('public.installment_seq', 1, true);


--
-- Name: loan_seq; Type: SEQUENCE SET; Schema: public; Owner: ilham
--

SELECT pg_catalog.setval('public.loan_seq', 1, true);


--
-- Name: member_seq; Type: SEQUENCE SET; Schema: public; Owner: ilham
--

SELECT pg_catalog.setval('public.member_seq', 1, true);


--
-- Name: saving_seq; Type: SEQUENCE SET; Schema: public; Owner: ilham
--

SELECT pg_catalog.setval('public.saving_seq', 2, true);


--
-- Name: transaction_seq; Type: SEQUENCE SET; Schema: public; Owner: ilham
--

SELECT pg_catalog.setval('public.transaction_seq', 5, true);


--
-- Name: installment installment_pkey; Type: CONSTRAINT; Schema: public; Owner: ilham
--

ALTER TABLE ONLY public.installment
    ADD CONSTRAINT installment_pkey PRIMARY KEY (id);


--
-- Name: loan loan_pkey; Type: CONSTRAINT; Schema: public; Owner: ilham
--

ALTER TABLE ONLY public.loan
    ADD CONSTRAINT loan_pkey PRIMARY KEY (id);


--
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: ilham
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- Name: saving saving_pkey; Type: CONSTRAINT; Schema: public; Owner: ilham
--

ALTER TABLE ONLY public.saving
    ADD CONSTRAINT saving_pkey PRIMARY KEY (id);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: ilham
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

