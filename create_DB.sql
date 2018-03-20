--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-03-20 16:20:11

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
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 16784)
-- Name: Artist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Artist" (
    pk_id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public."Artist" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16787)
-- Name: Artist_pk_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Artist_pk_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Artist_pk_id_seq" OWNER TO postgres;

--
-- TOC entry 2840 (class 0 OID 0)
-- Dependencies: 199
-- Name: Artist_pk_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Artist_pk_id_seq" OWNED BY public."Artist".pk_id;


--
-- TOC entry 197 (class 1259 OID 16775)
-- Name: Disc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Disc" (
    pk_id integer NOT NULL,
    title text NOT NULL,
    year text NOT NULL,
    rating numeric,
    fk_artist_id integer NOT NULL,
    fk_type_id integer NOT NULL,
    fk_style_id integer,
    CONSTRAINT "Disc_rating_check" CHECK (((rating >= 0.0) AND (rating <= 5.0)))
);


ALTER TABLE public."Disc" OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16773)
-- Name: Disc_pk_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Disc_pk_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Disc_pk_id_seq" OWNER TO postgres;

--
-- TOC entry 2841 (class 0 OID 0)
-- Dependencies: 196
-- Name: Disc_pk_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Disc_pk_id_seq" OWNED BY public."Disc".pk_id;


--
-- TOC entry 203 (class 1259 OID 16811)
-- Name: Style; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Style" (
    pk_id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public."Style" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16809)
-- Name: Style_pk_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Style_pk_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Style_pk_id_seq" OWNER TO postgres;

--
-- TOC entry 2842 (class 0 OID 0)
-- Dependencies: 202
-- Name: Style_pk_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Style_pk_id_seq" OWNED BY public."Style".pk_id;


--
-- TOC entry 201 (class 1259 OID 16798)
-- Name: Type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Type" (
    pk_id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public."Type" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16796)
-- Name: Type_pk_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Type_pk_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Type_pk_id_seq" OWNER TO postgres;

--
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 200
-- Name: Type_pk_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Type_pk_id_seq" OWNED BY public."Type".pk_id;


--
-- TOC entry 2694 (class 2604 OID 16789)
-- Name: Artist pk_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Artist" ALTER COLUMN pk_id SET DEFAULT nextval('public."Artist_pk_id_seq"'::regclass);


--
-- TOC entry 2692 (class 2604 OID 16778)
-- Name: Disc pk_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Disc" ALTER COLUMN pk_id SET DEFAULT nextval('public."Disc_pk_id_seq"'::regclass);


--
-- TOC entry 2696 (class 2604 OID 16814)
-- Name: Style pk_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Style" ALTER COLUMN pk_id SET DEFAULT nextval('public."Style_pk_id_seq"'::regclass);


--
-- TOC entry 2695 (class 2604 OID 16801)
-- Name: Type pk_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Type" ALTER COLUMN pk_id SET DEFAULT nextval('public."Type_pk_id_seq"'::regclass);


--
-- TOC entry 2703 (class 2606 OID 16808)
-- Name: Artist Artist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Artist"
    ADD CONSTRAINT "Artist_pkey" PRIMARY KEY (pk_id);


--
-- TOC entry 2698 (class 2606 OID 16783)
-- Name: Disc Disc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Disc"
    ADD CONSTRAINT "Disc_pkey" PRIMARY KEY (pk_id);


--
-- TOC entry 2707 (class 2606 OID 16819)
-- Name: Style Style_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Style"
    ADD CONSTRAINT "Style_pkey" PRIMARY KEY (pk_id);


--
-- TOC entry 2705 (class 2606 OID 16806)
-- Name: Type Type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Type"
    ADD CONSTRAINT "Type_pkey" PRIMARY KEY (pk_id);


--
-- TOC entry 2699 (class 1259 OID 16825)
-- Name: fki_Disc_fk_artist_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_Disc_fk_artist_id_fkey" ON public."Disc" USING btree (fk_artist_id);


--
-- TOC entry 2700 (class 1259 OID 16837)
-- Name: fki_Disc_fk_style_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_Disc_fk_style_id_fkey" ON public."Disc" USING btree (fk_style_id);


--
-- TOC entry 2701 (class 1259 OID 16831)
-- Name: fki_Disc_fk_type_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_Disc_fk_type_id_fkey" ON public."Disc" USING btree (fk_type_id);


--
-- TOC entry 2708 (class 2606 OID 16820)
-- Name: Disc Disc_fk_artist_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Disc"
    ADD CONSTRAINT "Disc_fk_artist_id_fkey" FOREIGN KEY (fk_artist_id) REFERENCES public."Artist"(pk_id);


--
-- TOC entry 2710 (class 2606 OID 16832)
-- Name: Disc Disc_fk_style_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Disc"
    ADD CONSTRAINT "Disc_fk_style_id_fkey" FOREIGN KEY (fk_style_id) REFERENCES public."Style"(pk_id);


--
-- TOC entry 2709 (class 2606 OID 16826)
-- Name: Disc Disc_fk_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Disc"
    ADD CONSTRAINT "Disc_fk_type_id_fkey" FOREIGN KEY (fk_type_id) REFERENCES public."Type"(pk_id);


-- Completed on 2018-03-20 16:20:11

--
-- PostgreSQL database dump complete
--

