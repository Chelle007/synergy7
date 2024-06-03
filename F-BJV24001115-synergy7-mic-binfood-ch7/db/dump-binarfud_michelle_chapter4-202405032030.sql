--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-05-03 20:30:31

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

DROP DATABASE binarfud_michelle_chapter4;
--
-- TOC entry 4877 (class 1262 OID 16672)
-- Name: binarfud_michelle_chapter4; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE binarfud_michelle_chapter4 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';


\connect binarfud_michelle_chapter4

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 4878 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 220 (class 1255 OID 16739)
-- Name: create_user(uuid, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.create_user(IN "Id" uuid, IN "Username" character varying, IN "Email" character varying, IN "Password" character varying, IN "Role" character varying)
    LANGUAGE sql
    AS $$
INSERT INTO users (id, username, email, password, role, deleted)
VALUES ("Id", "Username", "Email", "Password", "Role", false);
$$;


--
-- TOC entry 224 (class 1255 OID 16743)
-- Name: safe_delete_user(uuid); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.safe_delete_user(IN "Id" uuid)
    LANGUAGE sql
    AS $$
UPDATE users SET deleted = true WHERE id = "Id"
$$;


--
-- TOC entry 222 (class 1255 OID 16741)
-- Name: update_email(uuid, character varying); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.update_email(IN "Id" uuid, IN "Email" character varying)
    LANGUAGE sql
    AS $$
UPDATE users SET email = "Email" WHERE id = "Id"
$$;


--
-- TOC entry 223 (class 1255 OID 16742)
-- Name: update_password(uuid, character varying); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.update_password(IN "Id" uuid, IN "Password" character varying)
    LANGUAGE sql
    AS $$
UPDATE users SET password = "Password" WHERE id = "Id"
$$;


--
-- TOC entry 221 (class 1255 OID 16740)
-- Name: update_username(uuid, character varying); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.update_username(IN "Id" uuid, IN "Username" character varying)
    LANGUAGE sql
    AS $$
UPDATE users SET username = "Username" WHERE id = "Id"
$$;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16692)
-- Name: menu_item; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.menu_item (
    id uuid NOT NULL,
    name character varying(255),
    food_type character varying(20),
    price_s integer,
    price_m integer,
    price_l integer,
    restaurant_id uuid,
    deleted boolean
);


--
-- TOC entry 219 (class 1259 OID 16719)
-- Name: order_detail; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.order_detail (
    id uuid NOT NULL,
    size character varying(255),
    qty integer,
    price integer,
    menu_item_id uuid,
    order_id uuid,
    deleted boolean
);


--
-- TOC entry 218 (class 1259 OID 16702)
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    id uuid NOT NULL,
    order_time timestamp without time zone,
    destination_address character varying(255),
    notes text,
    completed boolean,
    user_id uuid,
    restaurant_id uuid,
    deleted boolean
);


--
-- TOC entry 216 (class 1259 OID 16680)
-- Name: restaurant; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.restaurant (
    id uuid NOT NULL,
    name character varying(255),
    location character varying(255),
    open boolean,
    owner_id uuid,
    deleted boolean
);


--
-- TOC entry 215 (class 1259 OID 16673)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    username character varying(255),
    email character varying(255),
    password character varying(255),
    role character varying(50),
    deleted boolean
);


--
-- TOC entry 4869 (class 0 OID 16692)
-- Dependencies: 217
-- Data for Name: menu_item; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.menu_item VALUES ('ee200faa-adb9-41f6-ab26-35c905d7bec2', 'Nasi Goreng', 'FOOD', 13000, 15000, 17000, '2bafe394-6415-4116-9d39-e620bb77f13c', false);
INSERT INTO public.menu_item VALUES ('1755e894-68d6-4d7c-875a-7a3681fb3388', 'Mie Goreng', 'FOOD', 11000, 13000, 15000, '2bafe394-6415-4116-9d39-e620bb77f13c', false);
INSERT INTO public.menu_item VALUES ('c5f46fe4-8e4f-438d-bc04-4e9915677efb', 'Nasi + Ayam', 'FOOD', 16000, 18000, 20000, '2bafe394-6415-4116-9d39-e620bb77f13c', false);
INSERT INTO public.menu_item VALUES ('004a6cd7-3c91-4908-8667-1e27e3abcf7d', 'Es Teh Manis', 'BEVERAGE', NULL, 3000, NULL, '2bafe394-6415-4116-9d39-e620bb77f13c', false);
INSERT INTO public.menu_item VALUES ('0b58833a-8de2-42f2-a28c-300715c53780', 'Es Jeruk', 'BEVERAGE', NULL, 5000, NULL, '2bafe394-6415-4116-9d39-e620bb77f13c', false);


--
-- TOC entry 4871 (class 0 OID 16719)
-- Dependencies: 219
-- Data for Name: order_detail; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 4870 (class 0 OID 16702)
-- Dependencies: 218
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 4868 (class 0 OID 16680)
-- Dependencies: 216
-- Data for Name: restaurant; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.restaurant VALUES ('2bafe394-6415-4116-9d39-e620bb77f13c', 'BinarResto', 'Singapore', true, '1316c841-2bff-4a9f-9153-5d56406719fc', false);


--
-- TOC entry 4867 (class 0 OID 16673)
-- Dependencies: 215
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users VALUES ('29633076-5c53-4a52-af24-20867d8f3d99', 'Customer', 'Customer@gmail.com', '12345678', 'CUSTOMER', false);
INSERT INTO public.users VALUES ('1316c841-2bff-4a9f-9153-5d56406719fc', 'Seller', 'Seller@gmail.com', '12345678', 'SELLER', false);


--
-- TOC entry 4713 (class 2606 OID 16696)
-- Name: menu_item menu_item_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menu_item
    ADD CONSTRAINT menu_item_pkey PRIMARY KEY (id);


--
-- TOC entry 4717 (class 2606 OID 16723)
-- Name: order_detail order_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (id);


--
-- TOC entry 4715 (class 2606 OID 16708)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 4711 (class 2606 OID 16686)
-- Name: restaurant restaurant_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);


--
-- TOC entry 4709 (class 2606 OID 16679)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4719 (class 2606 OID 16697)
-- Name: menu_item menu_item_restaurant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menu_item
    ADD CONSTRAINT menu_item_restaurant_id_fkey FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 4722 (class 2606 OID 16724)
-- Name: order_detail order_detail_menu_item_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_menu_item_id_fkey FOREIGN KEY (menu_item_id) REFERENCES public.menu_item(id);


--
-- TOC entry 4723 (class 2606 OID 16729)
-- Name: order_detail order_detail_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 4720 (class 2606 OID 16714)
-- Name: orders orders_restaurant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_restaurant_id_fkey FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 4721 (class 2606 OID 16709)
-- Name: orders orders_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4718 (class 2606 OID 16687)
-- Name: restaurant restaurant_owner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT restaurant_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES public.users(id);


-- Completed on 2024-05-03 20:30:32

--
-- PostgreSQL database dump complete
--

