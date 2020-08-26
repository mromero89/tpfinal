--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-08-26 19:36:00

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
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16401)
-- Name: camiones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.camiones (
    patente character varying NOT NULL,
    modelo character varying,
    kmrec integer,
    costokm integer,
    costohora integer,
    fechacompra date
);


ALTER TABLE public.camiones OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16469)
-- Name: detallesenvios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detallesenvios (
    nropedido integer NOT NULL,
    patente character varying,
    ruta character varying,
    costo integer
);


ALTER TABLE public.detallesenvios OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16425)
-- Name: insumos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.insumos (
    descripcion character varying NOT NULL,
    unidadmedida character varying,
    costo integer,
    peso integer,
    densidad integer,
    tipo character varying
);


ALTER TABLE public.insumos OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16445)
-- Name: insumosplantas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.insumosplantas (
    insumo character varying,
    cantidad integer,
    puntopedido integer,
    nombreplanta character varying
);


ALTER TABLE public.insumosplantas OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16461)
-- Name: itemspedidos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.itemspedidos (
    item character varying,
    cantidad integer,
    costo integer,
    nropedido integer
);


ALTER TABLE public.itemspedidos OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16453)
-- Name: ordenespedidos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordenespedidos (
    nropedido integer NOT NULL,
    plantaorigen character varying,
    plantadestino character varying,
    fechasolicitud date,
    fechaentrega date,
    estado character varying
);


ALTER TABLE public.ordenespedidos OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16433)
-- Name: plantas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plantas (
    nombre character varying NOT NULL
);


ALTER TABLE public.plantas OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16417)
-- Name: rutas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rutas (
    origen character varying,
    destino character varying,
    distancia integer,
    duracion integer,
    pesomax integer,
    nombre character varying NOT NULL
);


ALTER TABLE public.rutas OWNER TO postgres;

--
-- TOC entry 2723 (class 2606 OID 16408)
-- Name: camiones camiones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camiones
    ADD CONSTRAINT camiones_pkey PRIMARY KEY (patente);


--
-- TOC entry 2733 (class 2606 OID 16476)
-- Name: detallesenvios detallesenvios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detallesenvios
    ADD CONSTRAINT detallesenvios_pkey PRIMARY KEY (nropedido);


--
-- TOC entry 2727 (class 2606 OID 16444)
-- Name: insumos insumos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.insumos
    ADD CONSTRAINT insumos_pkey PRIMARY KEY (descripcion);


--
-- TOC entry 2731 (class 2606 OID 16460)
-- Name: ordenespedidos ordenespedidos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordenespedidos
    ADD CONSTRAINT ordenespedidos_pkey PRIMARY KEY (nropedido);


--
-- TOC entry 2729 (class 2606 OID 16440)
-- Name: plantas plantas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantas
    ADD CONSTRAINT plantas_pkey PRIMARY KEY (nombre);


--
-- TOC entry 2725 (class 2606 OID 16442)
-- Name: rutas ruta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rutas
    ADD CONSTRAINT ruta_pkey PRIMARY KEY (nombre);


-- Completed on 2020-08-26 19:36:01

--
-- PostgreSQL database dump complete
--

