--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-08-26 21:42:12

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
-- TOC entry 2873 (class 0 OID 0)
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
-- TOC entry 2860 (class 0 OID 16401)
-- Dependencies: 203
-- Data for Name: camiones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('kns645', 'gol', 100, 100, 20, '2008-12-12');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('AB123CD', 'Berlingo II', 200, 100, 25, '2020-12-12');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('LZZ151', 'Partner', 100, 100, 200, '2019-12-12');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('ABC125', 'RANGER', 152, 100, 200, '2020-12-10');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('OPS152', 'AMAROK', 400, 100, 150, '2020-12-12');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('abc124', 'asdasdasd', 401, 2, 5, '2020-12-11');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('lzi897', 'strada', 412, 3, 9, '2014-12-11');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('KZS752', 'LIFAN 12', 262, 20, 30, '2020-12-11');
INSERT INTO public.camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES ('JKP785', 'Fiat Palio II', 523, 25, 15, '2020-12-11');


--
-- TOC entry 2867 (class 0 OID 16469)
-- Dependencies: 210
-- Data for Name: detallesenvios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.detallesenvios (nropedido, patente, ruta, costo) VALUES (116, 'abc124', '[Cordoba, Buenos Aires]', 860);
INSERT INTO public.detallesenvios (nropedido, patente, ruta, costo) VALUES (24820, 'lzi897', '[Cordoba, Buenos Aires]', 1308);
INSERT INTO public.detallesenvios (nropedido, patente, ruta, costo) VALUES (718, 'KZS752', '[Buenos Aires, Santa Fe, Entre Rios]', 5210);
INSERT INTO public.detallesenvios (nropedido, patente, ruta, costo) VALUES (3621, 'JKP785', '[Cordoba, Santa Fe, Neuquen]', 12635);


--
-- TOC entry 2862 (class 0 OID 16425)
-- Dependencies: 205
-- Data for Name: insumos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('Arena', 'Kilo', 100, 1, NULL, 'GENERAL');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('arena', 'kilo', 100, 1, NULL, 'GENERAL');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('cal', 'kilo', 200, 1, NULL, 'GENERAL');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('oxigeno', 'm3', 1500, NULL, 1141, 'LIQUIDO');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('Piedra', 'Kilo', 300, 1, NULL, 'GENERAL');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('cemento', 'kilo', 100, 1, NULL, 'GENERAL');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('Ladrillo', 'Kilo', 120, 1, NULL, 'GENERAL');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('GNL', 'M3', 13, NULL, 12, 'LIQUIDO');
INSERT INTO public.insumos (descripcion, unidadmedida, costo, peso, densidad, tipo) VALUES ('CAL', 'Kilo', 10, 1, NULL, 'GENERAL');


--
-- TOC entry 2864 (class 0 OID 16445)
-- Dependencies: 207
-- Data for Name: insumosplantas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('cal', 3, 1, 'Cordoba');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('oxigeno', 3, 1, 'Cordoba');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('oxigeno', 3, 1, 'Buenos Aires');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('arena', 9, 6, 'Buenos Aires');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('cal', 5, 1, 'Santa Fe');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('cemento', 2, 5, 'Entre Rios');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('Piedra', 19, 5, 'Cordoba');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('cemento', 20, 5, 'Cordoba');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('Piedra', 30, 3, 'Buenos Aires');
INSERT INTO public.insumosplantas (insumo, cantidad, puntopedido, nombreplanta) VALUES ('Piedra', 20, 10, 'Entre Rios');


--
-- TOC entry 2866 (class 0 OID 16461)
-- Dependencies: 209
-- Data for Name: itemspedidos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Arena', 2, 200, NULL);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Piedra', 12, 3600, 116);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cemento', 14, 1400, 116);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cal', 1, 200, 120);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cal', 10, 2000, 602);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Piedra', 29, 8700, 718);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Piedra', 10, 3000, 24820);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cal', 20, 4000, 28493);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cemento', 30, 3000, 24);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('GNL', 16, 208, 24);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('GNL', 12, 156, 2482020);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Ladrillo', 14, 1680, 2482020);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Ladrillo', 600, 72000, 241258);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cemento', 5, 500, 3621);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cemento', 12, 1200, 1111);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('GNL', 1, 13, 1111);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('oxigeno', 16, 24000, 2222);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('GNL', 20, 260, 2222);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Arena', 22, 23, 123);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cal', 21, 23, 123);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('Arena', 22, 23, 124);
INSERT INTO public.itemspedidos (item, cantidad, costo, nropedido) VALUES ('cal', 21, 23, 124);


--
-- TOC entry 2865 (class 0 OID 16453)
-- Dependencies: 208
-- Data for Name: ordenespedidos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (112, 'Cordoba', 'Santa Fe', NULL, '2020-12-11', 'CREADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (120, NULL, 'Buenos Aires', NULL, '2020-12-11', 'CREADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (602, NULL, 'Buenos Aires', NULL, '2021-01-01', 'CANCELADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (28493, NULL, 'Neuquen', NULL, '2020-11-14', 'CANCELADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (241258, NULL, 'Neuquen', NULL, '2020-12-17', 'CANCELADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (24, NULL, 'La Rioja', NULL, '2020-11-02', 'CANCELADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (2482020, NULL, 'Entre Rios', NULL, '2020-12-11', 'ENTREGADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (24820, 'Cordoba', 'Buenos Aires', NULL, '2020-12-20', 'ENTREGADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (116, 'Cordoba', 'Buenos Aires', NULL, '2020-12-12', 'ENTREGADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (1111, NULL, 'Santa Fe', NULL, '2020-11-13', 'CANCELADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (2222, NULL, 'Santa Fe', NULL, '2020-12-11', 'CANCELADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (718, 'Buenos Aires', 'Entre Rios', NULL, '2020-12-12', 'ENTREGADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (3621, 'Cordoba', 'Neuquen', NULL, '2020-12-11', 'PROCESADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (123, NULL, 'Santa Fe', NULL, '2020-12-11', 'CREADA');
INSERT INTO public.ordenespedidos (nropedido, plantaorigen, plantadestino, fechasolicitud, fechaentrega, estado) VALUES (124, NULL, 'Santa Fe', NULL, '2020-12-11', 'CREADA');


--
-- TOC entry 2863 (class 0 OID 16433)
-- Dependencies: 206
-- Data for Name: plantas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.plantas (nombre) VALUES ('Cordoba');
INSERT INTO public.plantas (nombre) VALUES ('Santa Fe');
INSERT INTO public.plantas (nombre) VALUES ('Buenos Aires');
INSERT INTO public.plantas (nombre) VALUES ('Entre Rios');
INSERT INTO public.plantas (nombre) VALUES ('Neuquen');
INSERT INTO public.plantas (nombre) VALUES ('La Rioja');


--
-- TOC entry 2861 (class 0 OID 16417)
-- Dependencies: 204
-- Data for Name: rutas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Cordoba', 'Santa Fe', 300, 6, 12, 'RN16');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Santa Fe', 'Cordoba', 300, 4, 560, 'R43');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Cordoba', 'Buenos Aires', 400, 12, 500, 'R99');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Buenos Aires', 'Neuquen', 100, 3, 1000, 'RBAN');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Neuquen', 'Entre Rios', 200, 6, 1000, 'RNER');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Buenos Aires', 'Santa Fe', 200, 6, 1000, 'RBASF');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Santa Fe', 'Entre Rios', 50, 1, 1000, 'RSFER');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Buenos Aires', 'La Rioja', 200, 6, 2500, 'RBALR');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('La Rioja', 'Entre Rios', 50, 1, 2500, 'RLRER');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Santa Fe', 'Neuquen', 200, 3, 1500, 'RSFN');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Entre Rios', 'Buenos Aires', 150, 2, 1500, 'RERBA');
INSERT INTO public.rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES ('Cordoba', 'Neuquen', 600, 12, 3, 'RCBAN');


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


-- Completed on 2020-08-26 21:42:12

--
-- PostgreSQL database dump complete
--

