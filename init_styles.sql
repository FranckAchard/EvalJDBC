--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-03-21 09:00:48

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
-- TOC entry 2813 (class 0 OID 16811)
-- Dependencies: 203
-- Data for Name: Style; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Style" (pk_id, name) FROM stdin;
1	Blues
2	Folk
3	Rock & Roll
4	Pop
5	Reggae
6	Soul
7	Other
\.


--
-- TOC entry 2819 (class 0 OID 0)
-- Dependencies: 202
-- Name: Style_pk_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Style_pk_id_seq"', 7, true);


-- Completed on 2018-03-21 09:00:48

--
-- PostgreSQL database dump complete
--

