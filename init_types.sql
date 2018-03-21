--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-03-21 09:03:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2813 (class 0 OID 16798)
-- Dependencies: 201
-- Data for Name: Type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Type" (pk_id, name) FROM stdin;
1	Album
2	Single & EP
3	Compilation
4	Other
\.


--
-- TOC entry 2819 (class 0 OID 0)
-- Dependencies: 200
-- Name: Type_pk_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Type_pk_id_seq"', 4, true);


-- Completed on 2018-03-21 09:03:53

--
-- PostgreSQL database dump complete
--

