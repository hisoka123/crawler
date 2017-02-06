--
-- SRdbSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: orafce; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS orafce WITH SCHEMA sr_catalog;


--
-- Name: EXTENSION orafce; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION orafce IS 'Functions and operators that emulate a subset of functions and packages from the Oracle RDBMS';


--
-- Name: plsrsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plsrsql WITH SCHEMA sr_catalog;


--
-- Name: EXTENSION plsrsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plsrsql IS 'PL/srSQL procedural language';


SET search_path = public, sr_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: t_civil_judgment; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_civil_judgment (
    id bigint NOT NULL,
    cause character varying(255),
    closed_way character varying(255),
    decision_time character varying(255),
    filing_court character varying(255),
    filing_time character varying(255),
    judgment_result character varying(255),
    reference character varying(255),
    subject character varying(255),
    subject_amount character varying(255),
    json_id bigint
);


ALTER TABLE t_civil_judgment OWNER TO srdb;

--
-- Name: t_civil_judgment_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_civil_judgment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_civil_judgment_id_seq OWNER TO srdb;

--
-- Name: t_civil_judgment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_civil_judgment_id_seq OWNED BY t_civil_judgment.id;


--
-- Name: t_credit_card_detail; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_credit_card_detail (
    id bigint NOT NULL,
    abort_day character varying(25),
    account_status character varying(10),
    cancellation_day character varying(25),
    currency character varying(10),
    is_overdue character varying(10),
    is_quasi_credit_card character varying(5),
    issue_day character varying(25),
    f_limit character varying(15),
    overdue_amount character varying(15),
    overdue_for_no character varying(10),
    overdue_no character varying(3),
    used_limit character varying(15),
    json_id bigint
);


ALTER TABLE t_credit_card_detail OWNER TO srdb;

--
-- Name: t_credit_card_detail_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_credit_card_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_credit_card_detail_id_seq OWNER TO srdb;

--
-- Name: t_credit_card_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_credit_card_detail_id_seq OWNED BY t_credit_card_detail.id;


--
-- Name: t_credit_record_profile; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_credit_record_profile (
    id bigint NOT NULL,
    account_num integer,
    active_num integer,
    guarantee_num integer,
    overdue90_num integer,
    overdue_num integer,
    type character varying(50),
    json_id bigint
);


ALTER TABLE t_credit_record_profile OWNER TO srdb;

--
-- Name: t_credit_record_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_credit_record_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_credit_record_profile_id_seq OWNER TO srdb;

--
-- Name: t_credit_record_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_credit_record_profile_id_seq OWNED BY t_credit_record_profile.id;


--
-- Name: t_enforcement; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_enforcement (
    id bigint NOT NULL,
    apply_amount character varying(255),
    apply_implement character varying(255),
    case_status character varying(255),
    cause character varying(255),
    closed_time character varying(255),
    closed_way character varying(255),
    court character varying(255),
    filing_time character varying(255),
    implemented character varying(255),
    implemented_amount character varying(255),
    reference character varying(255),
    json_id bigint
);


ALTER TABLE t_enforcement OWNER TO srdb;

--
-- Name: t_enforcement_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_enforcement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_enforcement_id_seq OWNER TO srdb;

--
-- Name: t_enforcement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_enforcement_id_seq OWNED BY t_enforcement.id;


--
-- Name: t_guaranty; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_guaranty (
    id bigint NOT NULL,
    actual_day character varying(25),
    currency character varying(50),
    guaranteed_person_id_num character varying(25),
    other_guarantee_amount character varying(25),
    real_principal character varying(25),
    json_id bigint
);


ALTER TABLE t_guaranty OWNER TO srdb;

--
-- Name: t_guaranty_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_guaranty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_guaranty_id_seq OWNER TO srdb;

--
-- Name: t_guaranty_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_guaranty_id_seq OWNED BY t_guaranty.id;


--
-- Name: t_loan_detail; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_loan_detail (
    id bigint NOT NULL,
    abort_day character varying(25),
    account_status character varying(25),
    actual_day character varying(25),
    conteact_amount character varying(25),
    currency character varying(25),
    is_overdue character varying(25),
    issue_day character varying(25),
    loan_balance character varying(25),
    overdue_amount character varying(25),
    overdue_for_no character varying(25),
    overdue_no character varying(25),
    settle_day character varying(25),
    json_id bigint
);


ALTER TABLE t_loan_detail OWNER TO srdb;

--
-- Name: t_loan_detail_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_loan_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_loan_detail_id_seq OWNER TO srdb;

--
-- Name: t_loan_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_loan_detail_id_seq OWNED BY t_loan_detail.id;


--
-- Name: t_pbccrc_account; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_pbccrc_account (
    id bigint NOT NULL,
    password character varying(300),
    tradecode character varying(50),
    username character varying(50)
);


ALTER TABLE t_pbccrc_account OWNER TO srdb;

--
-- Name: t_pbccrc_account_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_pbccrc_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_pbccrc_account_id_seq OWNER TO srdb;

--
-- Name: t_pbccrc_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_pbccrc_account_id_seq OWNED BY t_pbccrc_account.id;


--
-- Name: t_penalty; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_penalty (
    id bigint NOT NULL,
    amount character varying(255),
    content character varying(255),
    deadline character varying(255),
    docum_number character varying(255),
    institution character varying(255),
    isreconsider character varying(255),
    result character varying(255),
    start_time character varying(255),
    json_id bigint
);


ALTER TABLE t_penalty OWNER TO srdb;

--
-- Name: t_penalty_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_penalty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_penalty_id_seq OWNER TO srdb;

--
-- Name: t_penalty_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_penalty_id_seq OWNED BY t_penalty.id;


--
-- Name: t_plain_pbccrc_json; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_plain_pbccrc_json (
    id bigint NOT NULL,
    create_time timestamp without time zone,
    plain_pbccrc_json text,
    account_id bigint
);


ALTER TABLE t_plain_pbccrc_json OWNER TO srdb;

--
-- Name: t_plain_pbccrc_json_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_plain_pbccrc_json_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_plain_pbccrc_json_id_seq OWNER TO srdb;

--
-- Name: t_plain_pbccrc_json_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_plain_pbccrc_json_id_seq OWNED BY t_plain_pbccrc_json.id;


--
-- Name: t_query_record; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_query_record (
    id bigint NOT NULL,
    num character varying(50),
    operator character varying(50),
    query_cause character varying(50),
    query_date character varying(25),
    query_type character varying(50),
    json_id bigint
);


ALTER TABLE t_query_record OWNER TO srdb;

--
-- Name: t_query_record_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_query_record_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_query_record_id_seq OWNER TO srdb;

--
-- Name: t_query_record_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_query_record_id_seq OWNED BY t_query_record.id;


--
-- Name: t_report_base; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_report_base (
    id bigint NOT NULL,
    certificate_num character varying(50),
    certificate_type character varying(50),
    marriage_status character varying(10),
    query_time character varying(25),
    real_name character varying(50),
    report_id character varying(50),
    report_time character varying(25),
    json_id bigint
);


ALTER TABLE t_report_base OWNER TO srdb;

--
-- Name: t_report_base_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_report_base_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_report_base_id_seq OWNER TO srdb;

--
-- Name: t_report_base_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_report_base_id_seq OWNED BY t_report_base.id;


--
-- Name: t_taxes_owed; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_taxes_owed (
    id bigint NOT NULL,
    identify_code character varying(255),
    moment character varying(255),
    tax_authority character varying(255),
    taxes_total character varying(255),
    account_id bigint
);


ALTER TABLE t_taxes_owed OWNER TO srdb;

--
-- Name: t_taxes_owed_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_taxes_owed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_taxes_owed_id_seq OWNER TO srdb;

--
-- Name: t_taxes_owed_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_taxes_owed_id_seq OWNED BY t_taxes_owed.id;


--
-- Name: t_telecomm_arrears; Type: TABLE; Schema: public; Owner: srdb; Tablespace: 
--

CREATE TABLE t_telecomm_arrears (
    id bigint NOT NULL,
    arrears_amount character varying(255),
    entry_date character varying(255),
    opening_time character varying(255),
    operators character varying(255),
    service_type character varying(255),
    json_id bigint
);


ALTER TABLE t_telecomm_arrears OWNER TO srdb;

--
-- Name: t_telecomm_arrears_id_seq; Type: SEQUENCE; Schema: public; Owner: srdb
--

CREATE SEQUENCE t_telecomm_arrears_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE t_telecomm_arrears_id_seq OWNER TO srdb;

--
-- Name: t_telecomm_arrears_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: srdb
--

ALTER SEQUENCE t_telecomm_arrears_id_seq OWNED BY t_telecomm_arrears.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_civil_judgment ALTER COLUMN id SET DEFAULT nextval('t_civil_judgment_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_credit_card_detail ALTER COLUMN id SET DEFAULT nextval('t_credit_card_detail_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_credit_record_profile ALTER COLUMN id SET DEFAULT nextval('t_credit_record_profile_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_enforcement ALTER COLUMN id SET DEFAULT nextval('t_enforcement_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_guaranty ALTER COLUMN id SET DEFAULT nextval('t_guaranty_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_loan_detail ALTER COLUMN id SET DEFAULT nextval('t_loan_detail_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_pbccrc_account ALTER COLUMN id SET DEFAULT nextval('t_pbccrc_account_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_penalty ALTER COLUMN id SET DEFAULT nextval('t_penalty_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_plain_pbccrc_json ALTER COLUMN id SET DEFAULT nextval('t_plain_pbccrc_json_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_query_record ALTER COLUMN id SET DEFAULT nextval('t_query_record_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_report_base ALTER COLUMN id SET DEFAULT nextval('t_report_base_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_taxes_owed ALTER COLUMN id SET DEFAULT nextval('t_taxes_owed_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_telecomm_arrears ALTER COLUMN id SET DEFAULT nextval('t_telecomm_arrears_id_seq'::regclass);


--
-- Name: t_civil_judgment_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_civil_judgment
    ADD CONSTRAINT t_civil_judgment_pkey PRIMARY KEY (id);


--
-- Name: t_credit_card_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_credit_card_detail
    ADD CONSTRAINT t_credit_card_detail_pkey PRIMARY KEY (id);


--
-- Name: t_credit_record_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_credit_record_profile
    ADD CONSTRAINT t_credit_record_profile_pkey PRIMARY KEY (id);


--
-- Name: t_enforcement_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_enforcement
    ADD CONSTRAINT t_enforcement_pkey PRIMARY KEY (id);


--
-- Name: t_guaranty_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_guaranty
    ADD CONSTRAINT t_guaranty_pkey PRIMARY KEY (id);


--
-- Name: t_loan_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_loan_detail
    ADD CONSTRAINT t_loan_detail_pkey PRIMARY KEY (id);


--
-- Name: t_pbccrc_account_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_pbccrc_account
    ADD CONSTRAINT t_pbccrc_account_pkey PRIMARY KEY (id);


--
-- Name: t_penalty_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_penalty
    ADD CONSTRAINT t_penalty_pkey PRIMARY KEY (id);


--
-- Name: t_plain_pbccrc_json_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_plain_pbccrc_json
    ADD CONSTRAINT t_plain_pbccrc_json_pkey PRIMARY KEY (id);


--
-- Name: t_query_record_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_query_record
    ADD CONSTRAINT t_query_record_pkey PRIMARY KEY (id);


--
-- Name: t_report_base_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_report_base
    ADD CONSTRAINT t_report_base_pkey PRIMARY KEY (id);


--
-- Name: t_taxes_owed_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_taxes_owed
    ADD CONSTRAINT t_taxes_owed_pkey PRIMARY KEY (id);


--
-- Name: t_telecomm_arrears_pkey; Type: CONSTRAINT; Schema: public; Owner: srdb; Tablespace: 
--

ALTER TABLE ONLY t_telecomm_arrears
    ADD CONSTRAINT t_telecomm_arrears_pkey PRIMARY KEY (id);


--
-- Name: fk_44ydglw489ol0hogjjspw9lym; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_loan_detail
    ADD CONSTRAINT fk_44ydglw489ol0hogjjspw9lym FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_53lgabg2ag4ep2e8n3uhq9lad; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_report_base
    ADD CONSTRAINT fk_53lgabg2ag4ep2e8n3uhq9lad FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_af0ak33hv73ovehikj5wqktic; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_query_record
    ADD CONSTRAINT fk_af0ak33hv73ovehikj5wqktic FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_awrj9kp1o589x2bbgjhl2aa3d; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_civil_judgment
    ADD CONSTRAINT fk_awrj9kp1o589x2bbgjhl2aa3d FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_eqfcu3hhdbc9qkavk00u2lfnv; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_credit_record_profile
    ADD CONSTRAINT fk_eqfcu3hhdbc9qkavk00u2lfnv FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_hc3d7v9mh17j02gfyemm7v4d2; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_taxes_owed
    ADD CONSTRAINT fk_hc3d7v9mh17j02gfyemm7v4d2 FOREIGN KEY (account_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_ioruq0i95pxw1422b8rolkuo; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_telecomm_arrears
    ADD CONSTRAINT fk_ioruq0i95pxw1422b8rolkuo FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_n7s5cpphdpra3oatl4fqml76e; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_guaranty
    ADD CONSTRAINT fk_n7s5cpphdpra3oatl4fqml76e FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_nd3cwsgq59ucohehtwbpt7upj; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_plain_pbccrc_json
    ADD CONSTRAINT fk_nd3cwsgq59ucohehtwbpt7upj FOREIGN KEY (account_id) REFERENCES t_pbccrc_account(id);


--
-- Name: fk_p1dcc61rnfdemot5a15aschjx; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_penalty
    ADD CONSTRAINT fk_p1dcc61rnfdemot5a15aschjx FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_qbot4y9pt1i1hov5umc3y0h4o; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_credit_card_detail
    ADD CONSTRAINT fk_qbot4y9pt1i1hov5umc3y0h4o FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: fk_qia3vmwiqh35wig2o70qu9qgm; Type: FK CONSTRAINT; Schema: public; Owner: srdb
--

ALTER TABLE ONLY t_enforcement
    ADD CONSTRAINT fk_qia3vmwiqh35wig2o70qu9qgm FOREIGN KEY (json_id) REFERENCES t_plain_pbccrc_json(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: srdb
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM srdb;
GRANT ALL ON SCHEMA public TO srdb;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- SRdbSQL database dump complete
--


--------------------------------------------------comment--------------------------------------------------------------------------------------

comment on table t_pbccrc_account is '用户表';
comment on table t_plain_pbccrc_json is '人行爬取信息json表';
comment on table t_report_base is '人行征信表头';
comment on table t_credit_record_profile is '信用卡信息概要';
comment on table t_credit_card_detail is '解析后的信用卡信息详情表';
comment on table t_loan_detail is '解析后的房产借贷和其他借贷表';
comment on table t_guaranty is '解析后的为他人担保信息表';
comment on table t_query_record is '查询记录表';

comment on table t_civil_judgment is '民事判决记录';
comment on table t_enforcement is '强制执行记录';
comment on table t_penalty is '行政处罚记录';
comment on table t_taxes_owed is '欠税记录';
comment on table t_telecomm_arrears is '电信欠费';


comment on column t_pbccrc_account.username is '用户名';
comment on column t_pbccrc_account.password is '加密密码';
comment on column t_pbccrc_account.tradecode is '授权码';

comment on column t_plain_pbccrc_json.create_time is '创建时间';
comment on column t_plain_pbccrc_json.plain_pbccrc_json is '页面json';
comment on column t_plain_pbccrc_json.account_id is '外键：用户ID';

comment on column t_report_base.report_id is '报告编号';
comment on column t_report_base.query_time is '查询时间';
comment on column t_report_base.report_time is '报告时间';
comment on column t_report_base.real_name is '姓名';
comment on column t_report_base.certificate_type is '证件类型';
comment on column t_report_base.certificate_num is '证件号码';
comment on column t_report_base.marriage_status is '婚否';
comment on column t_report_base.json_id is '外键：备份编号';


comment on column t_credit_record_profile.type is '概要类型';
comment on column t_credit_record_profile.account_num is '账户数';
comment on column t_credit_record_profile.active_num is '未结清/未销户账户数';
comment on column t_credit_record_profile.overdue_num is '发生过逾期的账户数';
comment on column t_credit_record_profile.overdue90_num is '发生过90天以上逾期的账户数';
comment on column t_credit_record_profile.guarantee_num is '为他人担保笔数';
comment on column t_credit_record_profile.json_id is '外键：备份编号';


comment on column t_credit_card_detail.is_quasi_credit_card is '是否';
comment on column t_credit_card_detail.abort_day is '中止日期';
comment on column t_credit_card_detail.account_status is '账户状态';
comment on column t_credit_card_detail.cancellation_day is '取消日期';
comment on column t_credit_card_detail.currency is '货币类型';
comment on column t_credit_card_detail.f_limit is '限制';
comment on column t_credit_card_detail.is_overdue is '是否逾期';
comment on column t_credit_card_detail.issue_day is '发生状况日期';
comment on column t_credit_card_detail.overdue_amount is '超出数额';
comment on column t_credit_card_detail.overdue_for_no is '超出数量原因';
comment on column t_credit_card_detail.overdue_no is '超出数量';
comment on column t_credit_card_detail.used_limit is '使用限制';
comment on column t_credit_card_detail.json_id is '外键：备份编号';

comment on column t_loan_detail.abort_day is '中止日期';
comment on column t_loan_detail.account_status is '账户状态';
comment on column t_loan_detail.currency is '资产';
comment on column t_loan_detail.is_overdue is '是否逾期';
comment on column t_loan_detail.issue_day is '发生状况日期';
comment on column t_loan_detail.overdue_amount is '超出数额';
comment on column t_loan_detail.overdue_for_no is '超出数量原因';
comment on column t_loan_detail.overdue_no is '超出数量';
comment on column t_loan_detail.actual_day is '实际日期';
comment on column t_loan_detail.conteact_amount is '数额';
comment on column t_loan_detail.loan_balance is '贷款余额';
comment on column t_loan_detail.settle_day is '结算日期';
comment on column t_loan_detail.json_id is '外键：备份编号';

comment on column t_guaranty.guaranteed_person_id_num is '他人帐号';
comment on column t_guaranty.currency is '资产';
comment on column t_guaranty.other_guarantee_amount is '担保数额';
comment on column t_guaranty.real_principal is '本金';
comment on column t_guaranty.actual_day is '实际日期';
comment on column t_guaranty.json_id is '外键：备份编号';

comment on column t_query_record.num is '编号';
comment on column t_query_record.operator is '操作员';
comment on column t_query_record.query_cause is '查询原因';
comment on column t_query_record.query_date is '查询日期';
comment on column t_query_record.query_type is '查询类型';
comment on column t_query_record.json_id is '外键：备份编号';



--------------------------------------------------comment--------------------------------------------------------------------------------------







----------------------------------------update-------------------------------------------------------------------------

--2016-06-24
alter table t_credit_card_detail alter COLUMN currency  type varchar(50);

--2016-06-27
alter table t_pbccrc_account add constraint unique_username unique(username);






























