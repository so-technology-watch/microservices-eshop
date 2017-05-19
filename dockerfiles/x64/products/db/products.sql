
--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 16407)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE category (
    name character varying(30),
    description character varying(100),
    id integer NOT NULL
);


ALTER TABLE category OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16424)
-- Name: category_id1_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE category_id1_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_id1_seq OWNER TO postgres;

--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 188
-- Name: category_id1_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE category_id1_seq OWNED BY category.id;


--
-- TOC entry 185 (class 1259 OID 16395)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product (
    reference character varying(10),
    designation character varying(20),
    description character varying(100),
    price double precision,
    id_category integer,
    id_supplier integer NOT NULL,
    image character varying(200),
    id integer NOT NULL
);


ALTER TABLE product OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16431)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 189
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE product_id_seq OWNED BY product.id;


--
-- TOC entry 187 (class 1259 OID 16418)
-- Name: supplier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE supplier (
    company_name character varying(50),
    email character varying(50),
    phone_number character varying(20),
    id integer NOT NULL
);


ALTER TABLE supplier OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16438)
-- Name: supplier_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE supplier_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE supplier_id_seq OWNER TO postgres;

--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 190
-- Name: supplier_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE supplier_id_seq OWNED BY supplier.id;


--
-- TOC entry 2014 (class 2604 OID 16426)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY category ALTER COLUMN id SET DEFAULT nextval('category_id1_seq'::regclass);


--
-- TOC entry 2013 (class 2604 OID 16433)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- TOC entry 2015 (class 2604 OID 16440)
-- Name: supplier id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY supplier ALTER COLUMN id SET DEFAULT nextval('supplier_id_seq'::regclass);


--
-- TOC entry 2135 (class 0 OID 16407)
-- Dependencies: 186
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 188
-- Name: category_id1_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('category_id1_seq', 28, true);

--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 189
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('product_id_seq', 34, true);


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 190
-- Name: supplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('supplier_id_seq', 23, true);


--
-- TOC entry 2016 (class 1259 OID 16423)
-- Name: index_supplier; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index_supplier ON product USING btree (id_supplier);


-- Completed on 2017-04-19 10:13:49

--
-- PostgreSQL database dump complete
--

