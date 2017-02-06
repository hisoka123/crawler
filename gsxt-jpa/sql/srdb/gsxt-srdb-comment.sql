
CREATE FUNCTION tg_a() RETURNS trigger
    LANGUAGE plsrsql
    AS $$
	declare
	begin
		case TG_OP
			when 'INSERT' then
				insert into b(id,name,time) values (NEW.aid, NEW.aname, NEW.time);
			when 'UPDATE' then
				update b set id=NEW.aid, name=NEW.aname, time=NEW.time where id=OLD.aid;                                     
			when 'DELETE' then 
				delete from b where id=OLD.aid;
			when 'TRUNCATE' then
				truncate b;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$;




--Generated SQL（version1.0）

/*=====================================================
 company
=====================================================*/

CREATE TABLE company (
    id bigint NOT NULL,
    city character varying(255),
    email character varying(255),
    name character varying(255),
    num integer,
    priority integer DEFAULT 0,
    state integer,
    webaddress character varying(255)
    total_num integer DEFAULT 0
)

comment on table public.company is '工商系统公司信息表';
comment on column public.company.id is '';
comment on column public.company.city is '所在地区';
comment on column public.company.email is '邮箱';
comment on column public.company.name is '公司名称';
comment on column public.company.num is '表示一次任务中该记录被爬取的次数';
comment on column public.company.priority is '数据爬取优先级别（数字越大，优先级别越高，目前支持的级别为 0 1';
comment on column public.company.state is '数据爬取状态代码';
comment on column public.company.webaddress is '网址';
comment on column public.company.total_num is '该记录被爬取的总次数';



/*=====================================================
 ownertask_all
=====================================================*/

CREATE TABLE ownertask_all (
    id bigint NOT NULL,
    loginname character varying(255),
    tasknum bigint NOT NULL,
    tasktype character varying(255)
)

comment on table public.ownertask_all is '我的任务全表';
comment on column public.ownertask_all.id is '';
comment on column public.ownertask_all.loginname is '登录名';
comment on column public.ownertask_all.tasknum is '任务总数';
comment on column public.ownertask_all.tasktype is '任务类型';



/*=====================================================
 ownertask_gsxt
=====================================================*/

CREATE TABLE ownertask_gsxt (
    id bigint NOT NULL,
    city character varying(255),
    company_id bigint NOT NULL,
    completetaskdate timestamp without time zone,
    createtaskdate timestamp without time zone,
    loginname character varying(255),
    name character varying(255),
    state integer NOT NULL
)

comment on table public.ownertask_gsxt is '工商网任务表';
comment on column public.ownertask_gsxt.id is '';
comment on column public.ownertask_gsxt.city is '公司所在地区';
comment on column public.ownertask_gsxt.company_id is '工商系统公司信息表外键';
comment on column public.ownertask_gsxt.completetaskdate is '任务完成时间';
comment on column public.ownertask_gsxt.createtaskdate is '任务创建时间';
comment on column public.ownertask_gsxt.loginname is '登录名';
comment on column public.ownertask_gsxt.name is '公司名称';
comment on column public.ownertask_gsxt.state is '任务状态';



/*=====================================================
 t_bgxx
=====================================================*/

CREATE TABLE t_bgxx (
    id bigint NOT NULL,
    bgdate character varying(255),
    bghcontent text,
    bgitem character varying(255),
    bgqcontent text,
    html text,
    infotype character varying(255),
    qtbmgs_xzxkxx_id bigint,
    qygs_gdjczxx_id bigint,
    qyjbxx_id bigint,
    qynb_id bigint,
    xzxkxx_id bigint
)

comment on table public.t_bgxx is '变更信息表';
comment on column public.t_bgxx.id is '';
comment on column public.t_bgxx.bgdate is '变更日期';
comment on column public.t_bgxx.bghcontent is '变更后内容';
comment on column public.t_bgxx.bgitem is '变更事项';
comment on column public.t_bgxx.bgqcontent is '变更前内容';
comment on column public.t_bgxx.html is 'html内容';
comment on column public.t_bgxx.infotype is '信息类型';
comment on column public.t_bgxx.qtbmgs_xzxkxx_id is '其他部门公示-行政许可表外键';
comment on column public.t_bgxx.qygs_gdjczxx_id is '企业公示-股东及出资信息表外键';
comment on column public.t_bgxx.qyjbxx_id is '企业基本信息表外键';
comment on column public.t_bgxx.qynb_id is '企业年报外键';
comment on column public.t_bgxx.xzxkxx_id is '行政许可表外键';



/*=====================================================
 t_bgxxxq
=====================================================*/

CREATE TABLE t_bgxxxq (
    id bigint NOT NULL,
    name character varying(255),
    status integer,
    typeorposition character varying(255),
    bgxx_id bigint
)

comment on table public.t_bgxxxq is '变更信息详情表';
comment on column public.t_bgxxxq.id is '';
comment on column public.t_bgxxxq.name is '姓名';
comment on column public.t_bgxxxq.status is '变更状态 0表示变更前 1表示变更后';
comment on column public.t_bgxxxq.typeorposition is '投资人类型/职位';
comment on column public.t_bgxxxq.bgxx_id is '变更信息表外键';



/*=====================================================
 t_ccjcxx
=====================================================*/

CREATE TABLE t_ccjcxx (
    id bigint NOT NULL,
    date character varying(255),
    html text,
    infotype character varying(255),
    jcssauthority character varying(255),
    result character varying(255),
    type character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_ccjcxx is '抽查检查信息表';
comment on column public.t_ccjcxx.id is '';
comment on column public.t_ccjcxx.date is '日期';
comment on column public.t_ccjcxx.html is 'html内容';
comment on column public.t_ccjcxx.infotype is '信息类型';
comment on column public.t_ccjcxx.jcssauthority is '检查实施机关';
comment on column public.t_ccjcxx.result is '结果';
comment on column public.t_ccjcxx.type is '类型';
comment on column public.t_ccjcxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_cxxx
=====================================================*/

CREATE TABLE t_cxxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    revokedatetime character varying(255),
    revokeitem character varying(255),
    revokepostcontent text,
    revokeprecontent text,
    qyjbxx_id bigint
)

comment on table public.t_cxxx is '撤销信息表';
comment on column public.t_cxxx.id is '';
comment on column public.t_cxxx.html is 'html内容';
comment on column public.t_cxxx.infotype is '信息类型';
comment on column public.t_cxxx.revokedatetime is '撤销日期';
comment on column public.t_cxxx.revokeitem is '撤销事项';
comment on column public.t_cxxx.revokepostcontent is '撤销后内容';
comment on column public.t_cxxx.revokeprecontent is '撤销前内容';
comment on column public.t_cxxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_dcdydjxx
=====================================================*/

CREATE TABLE t_dcdydjxx (
    id bigint NOT NULL,
    bdbzqamount character varying(255),
    detail character varying(255),
    guaranteeddebttype character varying(255),
    guaranteedscope character varying(255),
    html text,
    infotype character varying(255),
    note text,
    pubdate character varying(255),
    regauthority character varying(255),
    regdate character varying(255),
    regnum character varying(255),
    revokedate character varying(255),
    revokereason character varying(255),
    status character varying(255),
    term character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_dcdydjxx is '动产抵押登记信息表';
comment on column public.t_dcdydjxx.id is '';
comment on column public.t_dcdydjxx.bdbzqamount is '被担保债权数额';
comment on column public.t_dcdydjxx.detail is '详情';
comment on column public.t_dcdydjxx.guaranteeddebttype is '被担保债权种类';
comment on column public.t_dcdydjxx.guaranteedscope is '担保范围';
comment on column public.t_dcdydjxx.html is 'html内容';
comment on column public.t_dcdydjxx.infotype is '信息类型';
comment on column public.t_dcdydjxx.note is '备注';
comment on column public.t_dcdydjxx.pubdate is '公示时间';
comment on column public.t_dcdydjxx.regauthority is '登记机关';
comment on column public.t_dcdydjxx.regdate is '登记日期';
comment on column public.t_dcdydjxx.regnum is '登记编号';
comment on column public.t_dcdydjxx.revokedate is '注销日期';
comment on column public.t_dcdydjxx.revokereason is '注销原因';
comment on column public.t_dcdydjxx.status is '状态';
comment on column public.t_dcdydjxx.term is '债务人履行债务的期限';
comment on column public.t_dcdydjxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_dcdydjxxbg
=====================================================*/

CREATE TABLE t_dcdydjxxbg (
    id bigint NOT NULL,
    changecontent text,
    changedatetime character varying(255),
    html text,
    infotype character varying(255),
    dcdydjxx_id bigint
)

comment on table public.t_dcdydjxxbg is '动产抵押登记信息变更表';
comment on column public.t_dcdydjxxbg.id is '';
comment on column public.t_dcdydjxxbg.changecontent is '变更内容';
comment on column public.t_dcdydjxxbg.changedatetime is '变更日期';
comment on column public.t_dcdydjxxbg.html is 'html内容';
comment on column public.t_dcdydjxxbg.infotype is '信息类型';
comment on column public.t_dcdydjxxbg.dcdydjxx_id is '动产抵押登记信息表外键';



/*=====================================================
 t_dwtgbzdbxx
=====================================================*/

CREATE TABLE t_dwtgbzdbxx (
    id bigint NOT NULL,
    creditor character varying(255),
    debtor character varying(255),
    exedebtdeadline character varying(255),
    guaranteemethod character varying(255),
    guaranteeperiod character varying(255),
    guaranteescope character varying(255),
    html text,
    infotype character varying(255),
    pricredrightamount character varying(255),
    pricredrighttype character varying(255),
    qynb_id bigint
)

comment on table public.t_dwtgbzdbxx is '对外提供保证担保信息表';
comment on column public.t_dwtgbzdbxx.id is '';
comment on column public.t_dwtgbzdbxx.creditor is '债权人';
comment on column public.t_dwtgbzdbxx.debtor is '债务人';
comment on column public.t_dwtgbzdbxx.exedebtdeadline is '履行债务的期限';
comment on column public.t_dwtgbzdbxx.guaranteemethod is '保证的方式';
comment on column public.t_dwtgbzdbxx.guaranteeperiod is '保证的期间';
comment on column public.t_dwtgbzdbxx.guaranteescope is '';
comment on column public.t_dwtgbzdbxx.html is 'html内容';
comment on column public.t_dwtgbzdbxx.infotype is '信息的类型';
comment on column public.t_dwtgbzdbxx.pricredrightamount is '主债权数额';
comment on column public.t_dwtgbzdbxx.pricredrighttype is '主债权种类';
comment on column public.t_dwtgbzdbxx.qynb_id is '企业年报外键';



/*=====================================================
 t_dwtzxx
=====================================================*/

CREATE TABLE t_dwtzxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    regnum character varying(255),
    tzslqyhgmgqqyname character varying(255),
    qynb_id bigint
)

comment on table public.t_dwtzxx is '对外投资信息表';
comment on column public.t_dwtzxx.id is '';
comment on column public.t_dwtzxx.html is 'html内容';
comment on column public.t_dwtzxx.infotype is '信息类型';
comment on column public.t_dwtzxx.regnum is '注册号';
comment on column public.t_dwtzxx.tzslqyhgmgqqyname is '投资设立企业或购买股权企业名称';
comment on column public.t_dwtzxx.qynb_id is '企业年报外键';



/*=====================================================
 t_dyrgk
=====================================================*/

CREATE TABLE t_dyrgk (
    id bigint NOT NULL,
    html text,
    idnum character varying(255),
    idtype character varying(255),
    infotype character varying(255),
    name character varying(255),
    dcdydjxx_id bigint
)

comment on table public.t_dyrgk is '抵押权人概况表';
comment on column public.t_dyrgk.id is '';
comment on column public.t_dyrgk.html is 'html内容';
comment on column public.t_dyrgk.idnum is '证照/证件号码';
comment on column public.t_dyrgk.idtype is '抵押权人证照/证件类型';
comment on column public.t_dyrgk.infotype is '信息类型';
comment on column public.t_dyrgk.name is '抵押权人名称';
comment on column public.t_dyrgk.dcdydjxx_id is '动产抵押登记信息表外键';



/*=====================================================
 t_dywgk
=====================================================*/

CREATE TABLE t_dywgk (
    id bigint NOT NULL,
    generalsituation character varying(255),
    html text,
    infotype character varying(255),
    name character varying(255),
    note text,
    ownership character varying(255),
    dcdydjxx_id bigint
)

comment on table public.t_dywgk is '抵押物概况表';
comment on column public.t_dywgk.id is '';
comment on column public.t_dywgk.generalsituation is '数量、质量、状况、所在地等情况';
comment on column public.t_dywgk.html is 'html内容';
comment on column public.t_dywgk.infotype is '信息类型';
comment on column public.t_dywgk.name is '名称';
comment on column public.t_dywgk.note is '备注';
comment on column public.t_dywgk.ownership is '所有权归属';
comment on column public.t_dywgk.dcdydjxx_id is '动产抵押登记信息表外键';



/*=====================================================
 t_fzjgxx
=====================================================*/

CREATE TABLE t_fzjgxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    name character varying(255),
    num character varying(255),
    regauthority character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_fzjgxx is '分支机构信息表';
comment on column public.t_fzjgxx.id is '';
comment on column public.t_fzjgxx.html is 'html内容';
comment on column public.t_fzjgxx.infotype is '信息类型';
comment on column public.t_fzjgxx.name is '名称';
comment on column public.t_fzjgxx.num is '统一社会信用代码/注册号';
comment on column public.t_fzjgxx.regauthority is '登记机关';
comment on column public.t_fzjgxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_gdbgxx
=====================================================*/

CREATE TABLE t_gdbgxx (
    id bigint NOT NULL,
    bzxperson character varying(255),
    detail character varying(255),
    execourt character varying(255),
    gqamount character varying(255),
    html text,
    infotype character varying(255),
    srperson character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_gdbgxx is '股东变更信息表';
comment on column public.t_gdbgxx.id is '';
comment on column public.t_gdbgxx.bzxperson is '被执行人';
comment on column public.t_gdbgxx.detail is '详情';
comment on column public.t_gdbgxx.execourt is '执行法院';
comment on column public.t_gdbgxx.gqamount is '股权数额';
comment on column public.t_gdbgxx.html is 'html内容';
comment on column public.t_gdbgxx.infotype is '信息类型';
comment on column public.t_gdbgxx.srperson is '受让人';
comment on column public.t_gdbgxx.qyjbxx_id is '企业基本信息表';



/*=====================================================
 t_gdjczxx
=====================================================*/

CREATE TABLE t_gdjczxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    investorstype character varying(255),
    rjczamount character varying(255),
    rjczdate character varying(255),
    rjczmethod character varying(255),
    sjczamount character varying(255),
    sjczdate character varying(255),
    sjczmethod character varying(255),
    stocktype character varying(255),
    stockholder character varying(255),
    member_id bigint,
    qygs_gdjczxx_id bigint,
    qynb_id bigint
)

comment on table public.t_gdjczxx is '股东及出资信息表';
comment on column public.t_gdjczxx.id is '';
comment on column public.t_gdjczxx.html is 'html内容';
comment on column public.t_gdjczxx.infotype is '信息类型';
comment on column public.t_gdjczxx.investorstype is '投资人类型';
comment on column public.t_gdjczxx.rjczamount is '认缴出资额';
comment on column public.t_gdjczxx.rjczdate is '认缴出资时间';
comment on column public.t_gdjczxx.rjczmethod is '认缴出资方式';
comment on column public.t_gdjczxx.sjczamount is '实缴出资额';
comment on column public.t_gdjczxx.sjczdate is '实缴出资时间';
comment on column public.t_gdjczxx.sjczmethod is '实缴出资方式';
comment on column public.t_gdjczxx.stocktype is '发起人类型';
comment on column public.t_gdjczxx.stockholder is '股东';
comment on column public.t_gdjczxx.member_id is '股东信息表外键';
comment on column public.t_gdjczxx.qygs_gdjczxx_id is '企业公示-股东及出资信息表外键';
comment on column public.t_gdjczxx.qynb_id is '企业年报外键';



/*=====================================================
 t_gqbgxx
=====================================================*/

CREATE TABLE t_gqbgxx (
    id bigint NOT NULL,
    bgdate character varying(255),
    bghownershipratio character varying(255),
    bgqownershipratio character varying(255),
    html text,
    infotype character varying(255),
    stockholder character varying(255),
    qyjbxx_id bigint,
    qynb_id bigint
)

comment on table public.t_gqbgxx is '股权变更信息表';
comment on column public.t_gqbgxx.id is '';
comment on column public.t_gqbgxx.bgdate is '股权变更日期';
comment on column public.t_gqbgxx.bghownershipratio is '变更后股权比例';
comment on column public.t_gqbgxx.bgqownershipratio is '变更前股权比例';
comment on column public.t_gqbgxx.html is 'html内容';
comment on column public.t_gqbgxx.infotype is '信息类型';
comment on column public.t_gqbgxx.stockholder is '股东（发起人）';
comment on column public.t_gqbgxx.qyjbxx_id is '企业基本信息表外键';
comment on column public.t_gqbgxx.qynb_id is '企业年报表外键';



/*=====================================================
 t_gqczdjxx
=====================================================*/

CREATE TABLE t_gqczdjxx (
    id bigint NOT NULL,
    changesitu character varying(255),
    czgqamount character varying(255),
    czr character varying(255),
    czridnum character varying(255),
    gqczsldjdate character varying(255),
    html text,
    infotype character varying(255),
    pubdate character varying(255),
    regnum character varying(255),
    status character varying(255),
    zqr character varying(255),
    zqridnum character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_gqczdjxx is '股权出质登记信息表';
comment on column public.t_gqczdjxx.id is '';
comment on column public.t_gqczdjxx.changesitu is '变化情况';
comment on column public.t_gqczdjxx.czgqamount is '出质股权数额';
comment on column public.t_gqczdjxx.czr is '出质人';
comment on column public.t_gqczdjxx.czridnum is '证照/证件号码（出质人）';
comment on column public.t_gqczdjxx.gqczsldjdate is '股权出质设立登记日期';
comment on column public.t_gqczdjxx.html is 'html内容';
comment on column public.t_gqczdjxx.infotype is '信息类型';
comment on column public.t_gqczdjxx.pubdate is '公示时间';
comment on column public.t_gqczdjxx.regnum is '登记编号';
comment on column public.t_gqczdjxx.status is '状态';
comment on column public.t_gqczdjxx.zqr is '质权人';
comment on column public.t_gqczdjxx.zqridnum is '证照/证件号码（质权人）';
comment on column public.t_gqczdjxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_gqdjxx
=====================================================*/

CREATE TABLE t_gqdjxx (
    id bigint NOT NULL,
    bzxperson character varying(255),
    detail character varying(255),
    execourt character varying(255),
    gqamount character varying(255),
    html text,
    infotype character varying(255),
    status character varying(255),
    xzgstzsnum character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_gqdjxx is '股权冻结信息表';
comment on column public.t_gqdjxx.id is '';
comment on column public.t_gqdjxx.bzxperson is '被执行人';
comment on column public.t_gqdjxx.detail is '详情';
comment on column public.t_gqdjxx.execourt is '执行法院';
comment on column public.t_gqdjxx.gqamount is '股权数额';
comment on column public.t_gqdjxx.html is 'html内容';
comment on column public.t_gqdjxx.infotype is '信息类型';
comment on column public.t_gqdjxx.status is '状态';
comment on column public.t_gqdjxx.xzgstzsnum is '协助公示通知书文号';
comment on column public.t_gqdjxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_gsxt_html
=====================================================*/

CREATE TABLE t_gsxt_html (
    id bigint NOT NULL,
    gsgsbainfo text,
    gsgsccjcinfo text,
    gsgsdcdydjinfo text,
    gsgsgqczdjinfo text,
    gsgsjyycinfo text,
    gsgsxzcfinfo text,
    gsgsyzwfinfo text,
    qtbmgsxzcfxzcfinfo text,
    qtbmgsxzxkxzxkinfo text,
    qygsgdjczinfo text,
    qygsgqbginfo text,
    qygsqynbinfo text,
    qygsxzcfinfo text,
    qygsxzxkinfo text,
    qygszscqczdjinfo text,
    sfxzgsgdbginfo text,
    sfxzgsgqdjinfo text,
    qyjbxx_id bigint
)

comment on table public.t_gsxt_html is 'html内容表';
comment on column public.t_gsxt_html.id is '';
comment on column public.t_gsxt_html.gsgsbainfo is '工商公示-备案信息 html内容';
comment on column public.t_gsxt_html.gsgsccjcinfo is '工商公示-抽查检查信息 html内容';
comment on column public.t_gsxt_html.gsgsdcdydjinfo is '工商公示-动产抵押登记信息 html内容';
comment on column public.t_gsxt_html.gsgsgqczdjinfo is '工商公示-股权出质登记信息 html内容';
comment on column public.t_gsxt_html.gsgsjyycinfo is '工商公示-经营异常信息 html内容';
comment on column public.t_gsxt_html.gsgsxzcfinfo is '工商公示-行政处罚信息 html内容';
comment on column public.t_gsxt_html.gsgsyzwfinfo is '工商公示-严重违法信息 html内容';
comment on column public.t_gsxt_html.qtbmgsxzcfxzcfinfo is '其他部门公示-行政处罚信息 html内容';
comment on column public.t_gsxt_html.qtbmgsxzxkxzxkinfo is '其他部门公示-行政许可信息 html内容';
comment on column public.t_gsxt_html.qygsgdjczinfo is '企业公示-股东及出资信息 html内容';
comment on column public.t_gsxt_html.qygsgqbginfo is '企业公示-股权变更信息 html内容';
comment on column public.t_gsxt_html.qygsqynbinfo is '企业公示-企业年报信息 html内容';
comment on column public.t_gsxt_html.qygsxzcfinfo is '企业公示-行政处罚信息 html内容';
comment on column public.t_gsxt_html.qygsxzxkinfo is '企业公示-行政许可信息 html内容';
comment on column public.t_gsxt_html.qygszscqczdjinfo is '企业公示-知识产权出质登记信息 html内容';
comment on column public.t_gsxt_html.sfxzgsgdbginfo is '司法协助公示-股东变更信息 html内容';
comment on column public.t_gsxt_html.sfxzgsgqdjinfo is '司法协助公示-股权冻结信息 html内容';
comment on column public.t_gsxt_html.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_jyychyzwfxx
=====================================================*/

CREATE TABLE t_jyychyzwfxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    isjyyc boolean,
    lrcause character varying(255),
    lrcausedetail text,
    lrdate character varying(255),
    lrzcjdauthority character varying(255),
    serialnumber character varying(255),
    yccause character varying(255),
    ycdate character varying(255),
    yczcjdauthority character varying(255),
    zcjdauthority character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_jyychyzwfxx is '经营异常或严重违法信息表';
comment on column public.t_jyychyzwfxx.id is '';
comment on column public.t_jyychyzwfxx.html is 'html内容';
comment on column public.t_jyychyzwfxx.infotype is '信息类型';
comment on column public.t_jyychyzwfxx.isjyyc is '是否为经营异常';
comment on column public.t_jyychyzwfxx.lrcause is '列入经营异常或严重违法名单原因';
comment on column public.t_jyychyzwfxx.lrcausedetail is '原因详情';
comment on column public.t_jyychyzwfxx.lrdate is '列入日期';
comment on column public.t_jyychyzwfxx.lrzcjdauthority is '作出决定机关（列入）';
comment on column public.t_jyychyzwfxx.serialnumber is '文书编号';
comment on column public.t_jyychyzwfxx.yccause is '移出经营异常或严重违法名单原因';
comment on column public.t_jyychyzwfxx.ycdate is '移出日期';
comment on column public.t_jyychyzwfxx.yczcjdauthority is '作出决定机关（移出）';
comment on column public.t_jyychyzwfxx.zcjdauthority is '作出决定机关（列入和移出）';
comment on column public.t_jyychyzwfxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_member
=====================================================*/

CREATE TABLE t_member (
    id bigint NOT NULL,
    gdtype character varying(255),
    html text,
    idnum character varying(255),
    idtype character varying(255),
    infotype character varying(255),
    isgd character varying(255),
    name character varying(255),
    "position" character varying(255),
    sconform character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_member is '股东信息表';
comment on column public.t_member.id is '';
comment on column public.t_member.gdtype is '股东类型';
comment on column public.t_member.html is 'html内容';
comment on column public.t_member.idnum is '证照/证件号码';
comment on column public.t_member.idtype is '证照/证件类型';
comment on column public.t_member.infotype is '信息类型';
comment on column public.t_member.isgd is '是否是股东';
comment on column public.t_member.name is '名称';
comment on column public.t_member."position" is '';
comment on column public.t_member.sconform is '投资方式';
comment on column public.t_member.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_qsxx
=====================================================*/

CREATE TABLE t_qsxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    leader character varying(255),
    members character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_qsxx is '清算信息表';
comment on column public.t_qsxx.id is '';
comment on column public.t_qsxx.html is 'html内容';
comment on column public.t_qsxx.infotype is '信息类型';
comment on column public.t_qsxx.leader is '清算组负责人';
comment on column public.t_qsxx.members is '清算组成员';
comment on column public.t_qsxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_qtbmgs_xzxkxx
=====================================================*/

CREATE TABLE t_qtbmgs_xzxkxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_qtbmgs_xzxkxx is '其他部门公示-行政许可信息表';
comment on column public.t_qtbmgs_xzxkxx.id is '';
comment on column public.t_qtbmgs_xzxkxx.html is 'html内容';
comment on column public.t_qtbmgs_xzxkxx.infotype is '信息类型';
comment on column public.t_qtbmgs_xzxkxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_qygs_gdjczxx
=====================================================*/

CREATE TABLE t_qygs_gdjczxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_qygs_gdjczxx is '企业公示-股东及出资信息表';
comment on column public.t_qygs_gdjczxx.id is '';
comment on column public.t_qygs_gdjczxx.html is 'html内容';
comment on column public.t_qygs_gdjczxx.infotype is '信息类型';
comment on column public.t_qygs_gdjczxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_qyjbxx
=====================================================*/

CREATE TABLE t_qyjbxx (
    id bigint NOT NULL,
    address character varying(255),
    approvaldate character varying(255),
    businessscope text,
    enddate character varying(255),
    executetime timestamp without time zone,
    formtype character varying(255),
    html text,
    infotype character varying(255),
    legalrepr character varying(255),
    name character varying(255),
    num character varying(255),
    regauthority character varying(255),
    registeredcapital character varying(255),
    registereddate character varying(255),
    regstatus character varying(255),
    revokedate character varying(255),
    startdate character varying(255),
    type character varying(255),
    company_id bigint
)

comment on table public.t_qyjbxx is '企业基本信息表';
comment on column public.t_qyjbxx.id is '';
comment on column public.t_qyjbxx.address is '经营场所/住所';
comment on column public.t_qyjbxx.approvaldate is '核准日期';
comment on column public.t_qyjbxx.businessscope is '经营范围';
comment on column public.t_qyjbxx.enddate is '营业期限至（即营业结束日期）';
comment on column public.t_qyjbxx.executetime is '记录查询时间';
comment on column public.t_qyjbxx.formtype is '组成形式';
comment on column public.t_qyjbxx.html is 'html内容';
comment on column public.t_qyjbxx.infotype is '信息类型';
comment on column public.t_qyjbxx.legalrepr is '法定代表人/经营者';
comment on column public.t_qyjbxx.name is '名称';
comment on column public.t_qyjbxx.num is '注册号 或 信用代码';
comment on column public.t_qyjbxx.regauthority is '登记机关';
comment on column public.t_qyjbxx.registeredcapital is '注册资本';
comment on column public.t_qyjbxx.registereddate is '成立日期';
comment on column public.t_qyjbxx.regstatus is '登记状态';
comment on column public.t_qyjbxx.revokedate is '吊销日期';
comment on column public.t_qyjbxx.startdate is '营业期限自（即营业开始日期）';
comment on column public.t_qyjbxx.type is '类型';
comment on column public.t_qyjbxx.company_id is '工商系统公司信息表外键';



/*=====================================================
 t_qynb
=====================================================*/

CREATE TABLE t_qynb (
    id bigint NOT NULL,
    address character varying(255),
    affiliation character varying(255),
    assetamount character varying(255),
    capitalamount character varying(255),
    cooperativename character varying(255),
    email character varying(255),
    empnum character varying(255),
    financialloan character varying(255),
    firstdate character varying(255),
    governmentfunds character varying(255),
    haswzhwd character varying(255),
    html text,
    html_jbxx text,
    html_qyzczkxx text,
    infotype character varying(255),
    istzxxhgmqtgsgq character varying(255),
    isyxzrgsbndgdgqzr character varying(255),
    legalrepr character varying(255),
    liabilityamount character varying(255),
    membersnum character varying(255),
    name character varying(255),
    netprofit character varying(255),
    num character varying(255),
    operatingstatus character varying(255),
    profitamount character varying(255),
    pubdate character varying(255),
    registeredcapital character varying(255),
    salesamount character varying(255),
    submityear character varying(255),
    syzqyhj character varying(255),
    taxesamount character varying(255),
    tel character varying(255),
    xszezzyywsr character varying(255),
    zipcode character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_qynb is '企业年报表';
comment on column public.t_qynb.id is '';
comment on column public.t_qynb.address is '企业通信地址';
comment on column public.t_qynb.affiliation is '隶属关系';
comment on column public.t_qynb.assetamount is '资产总额';
comment on column public.t_qynb.capitalamount is '资金数额';
comment on column public.t_qynb.cooperativename is '合作社名称';
comment on column public.t_qynb.email is '电子邮箱';
comment on column public.t_qynb.empnum is '从业人数';
comment on column public.t_qynb.financialloan is '金融贷款';
comment on column public.t_qynb.firstdate is '首次发布日期';
comment on column public.t_qynb.governmentfunds is '获得政府扶持资金、补助';
comment on column public.t_qynb.haswzhwd is '是否有网站或网店';
comment on column public.t_qynb.html is 'html内容';
comment on column public.t_qynb.html_jbxx is '企业年报-企业基本信息 html内容';
comment on column public.t_qynb.html_qyzczkxx is '企业年报-企业资产状况 html内容';
comment on column public.t_qynb.infotype is '信息类型';
comment on column public.t_qynb.istzxxhgmqtgsgq is '企业是否有投资信息或购买其他公司股权';
comment on column public.t_qynb.isyxzrgsbndgdgqzr is '有限责任公司本年度是否发生股东股权转让';
comment on column public.t_qynb.legalrepr is '经营者姓名';
comment on column public.t_qynb.liabilityamount is '负债总额';
comment on column public.t_qynb.membersnum is '成员人数';
comment on column public.t_qynb.name is '企业名称';
comment on column public.t_qynb.netprofit is '净利润';
comment on column public.t_qynb.num is '注册号/统一社会信用代码';
comment on column public.t_qynb.operatingstatus is '企业经营状态';
comment on column public.t_qynb.profitamount is '利润总额';
comment on column public.t_qynb.pubdate is '公示日期';
comment on column public.t_qynb.registeredcapital is '注册资本';
comment on column public.t_qynb.salesamount is '销售总额';
comment on column public.t_qynb.submityear is '报送年度';
comment on column public.t_qynb.syzqyhj is '所有者权益合计';
comment on column public.t_qynb.taxesamount is '纳税总额';
comment on column public.t_qynb.tel is '企业联系电话';
comment on column public.t_qynb.xszezzyywsr is '销售总额中主营业务收入';
comment on column public.t_qynb.zipcode is '邮政编码';
comment on column public.t_qynb.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_result_json
=====================================================*/

CREATE TABLE t_result_json (
    id bigint NOT NULL,
    cname character varying(255),
    executetime timestamp without time zone,
    result text,
    company_id bigint
)

comment on table public.t_result_json is '查询结果（JSON格式）表';
comment on column public.t_result_json.id is '';
comment on column public.t_result_json.cname is '企业名称';
comment on column public.t_result_json.executetime is '查询时间';
comment on column public.t_result_json.result is '查询结果（JSON格式）';
comment on column public.t_result_json.company_id is '工商系统公司信息表外键';



/*=====================================================
 t_rjhsjmx
=====================================================*/

CREATE TABLE t_rjhsjmx (
    id bigint NOT NULL,
    amount character varying(255),
    czdate character varying(255),
    gsdate character varying(255),
    method character varying(255),
    type character varying(255),
    gdjczxx_id bigint
)

comment on table public.t_rjhsjmx is '认缴或实缴明细表';
comment on column public.t_rjhsjmx.id is '';
comment on column public.t_rjhsjmx.amount is '出资额';
comment on column public.t_rjhsjmx.czdate is '出资日期';
comment on column public.t_rjhsjmx.gsdate is '公示日期';
comment on column public.t_rjhsjmx.method is '出资方式';
comment on column public.t_rjhsjmx.type is '类型';
comment on column public.t_rjhsjmx.gdjczxx_id is '股东及出资信息外键';



/*=====================================================
 t_scjyqk
=====================================================*/

CREATE TABLE t_scjyqk (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    netprofit character varying(255),
    salarysum character varying(255),
    salesum character varying(255),
    qynb_id bigint
)

comment on table public.t_scjyqk is '生产经营情况表';
comment on column public.t_scjyqk.id is '';
comment on column public.t_scjyqk.html is 'html内容';
comment on column public.t_scjyqk.infotype is '信息类型';
comment on column public.t_scjyqk.netprofit is '净利润';
comment on column public.t_scjyqk.salarysum is '纳税总额';
comment on column public.t_scjyqk.salesum is '营业额或营业收入';
comment on column public.t_scjyqk.qynb_id is '企业年报表外键';



/*=====================================================
 t_wzhwdxx
=====================================================*/

CREATE TABLE t_wzhwdxx (
    id bigint NOT NULL,
    html text,
    name character varying(255),
    type character varying(255),
    website character varying(255),
    qynb_id bigint
)

comment on table public.t_wzhwdxx is '网站或网店信息';
comment on column public.t_wzhwdxx.id is '';
comment on column public.t_wzhwdxx.html is 'html内容';
comment on column public.t_wzhwdxx.name is '名称';
comment on column public.t_wzhwdxx.type is '类型';
comment on column public.t_wzhwdxx.website is '网址';
comment on column public.t_wzhwdxx.qynb_id is '企业年报表外键';



/*=====================================================
 t_xzcfxx
=====================================================*/

CREATE TABLE t_xzcfxx (
    id bigint NOT NULL,
    detail character varying(255),
    html text,
    infotype character varying(255),
    note character varying(255),
    showdatetime character varying(255),
    wfxwtype character varying(255),
    xzcfcontent character varying(255),
    xzcfjds text,
    xzcfjdsnum character varying(255),
    zcxzcfjddate character varying(255),
    zcxzcfjdjgname character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_xzcfxx is '行政处罚信息表';
comment on column public.t_xzcfxx.id is '';
comment on column public.t_xzcfxx.detail is '详情';
comment on column public.t_xzcfxx.html is 'html内容';
comment on column public.t_xzcfxx.infotype is '信息类型';
comment on column public.t_xzcfxx.note is '备注';
comment on column public.t_xzcfxx.showdatetime is '公示信息';
comment on column public.t_xzcfxx.wfxwtype is '违法行为类型';
comment on column public.t_xzcfxx.xzcfcontent is '行政处罚内容';
comment on column public.t_xzcfxx.xzcfjds is '行政处罚决定书';
comment on column public.t_xzcfxx.xzcfjdsnum is '行政处罚决定书文号';
comment on column public.t_xzcfxx.zcxzcfjddate is '作出行政处罚决定日期';
comment on column public.t_xzcfxx.zcxzcfjdjgname is '作出行政处罚决定机关名称';
comment on column public.t_xzcfxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_xzcfxxxq
=====================================================*/

CREATE TABLE t_xzcfxxxq (
    id bigint NOT NULL,
    legalreprname character varying(255),
    name character varying(255),
    regnum character varying(255),
    wfxwtype character varying(255),
    xzcfcontent character varying(255),
    xzcfjds text,
    xzcfjdsnum character varying(255),
    zcxzcfjddate character varying(255),
    zcxzcfjdjgname character varying(255),
    xzcfxx_id bigint
)

comment on table public.t_xzcfxxxq is '行政处罚信息详情表';
comment on column public.t_xzcfxxxq.id is '';
comment on column public.t_xzcfxxxq.legalreprname is '法定代表人（负责人）姓名';
comment on column public.t_xzcfxxxq.name is '名称';
comment on column public.t_xzcfxxxq.regnum is '注册号';
comment on column public.t_xzcfxxxq.wfxwtype is '违法行为类型';
comment on column public.t_xzcfxxxq.xzcfcontent is '行政处罚内容';
comment on column public.t_xzcfxxxq.xzcfjds is '行政处罚决定书';
comment on column public.t_xzcfxxxq.xzcfjdsnum is '行政处罚决定书文号';
comment on column public.t_xzcfxxxq.zcxzcfjddate is '作出行政处罚决定日期';
comment on column public.t_xzcfxxxq.zcxzcfjdjgname is '作出行政处罚决定机关名称';
comment on column public.t_xzcfxxxq.xzcfxx_id is '行政处罚信息表外键';



/*=====================================================
 t_xzxkxx
=====================================================*/

CREATE TABLE t_xzxkxx (
    id bigint NOT NULL,
    detail character varying(255),
    enddate character varying(255),
    expirydate character varying(255),
    html text,
    infotype character varying(255),
    pubdatetime character varying(255),
    startdate character varying(255),
    status character varying(255),
    xkauthority character varying(255),
    xkcontent character varying(1024),
    xkwjname character varying(255),
    xkwjnum character varying(255),
    qtbmgs_xzxkxx_id bigint,
    qyjbxx_id bigint,
    qynb_id bigint
)

comment on table public.t_xzxkxx is '行政许可信息表';
comment on column public.t_xzxkxx.id is '';
comment on column public.t_xzxkxx.detail is '详情';
comment on column public.t_xzxkxx.enddate is '有效期至';
comment on column public.t_xzxkxx.expirydate is '有效日期';
comment on column public.t_xzxkxx.html is 'html内容';
comment on column public.t_xzxkxx.infotype is '信息类型';
comment on column public.t_xzxkxx.pubdatetime is '公示时间';
comment on column public.t_xzxkxx.startdate is '有效期自';
comment on column public.t_xzxkxx.status is '状态';
comment on column public.t_xzxkxx.xkauthority is '许可机关';
comment on column public.t_xzxkxx.xkcontent is '许可内容';
comment on column public.t_xzxkxx.xkwjname is '许可文件名称';
comment on column public.t_xzxkxx.xkwjnum is '许可文件编号';
comment on column public.t_xzxkxx.qtbmgs_xzxkxx_id is '其他部门公示-行政许可信息表外键';
comment on column public.t_xzxkxx.qyjbxx_id is '企业基本信息表外键';
comment on column public.t_xzxkxx.qynb_id is '企业年报表外键';



/*=====================================================
 t_zgbmxx
=====================================================*/

CREATE TABLE t_zgbmxx (
    id bigint NOT NULL,
    html text,
    idnum character varying(255),
    idtype character varying(255),
    infotype character varying(255),
    name character varying(255),
    showdatetime character varying(255),
    type character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_zgbmxx is '主管部门（出资人）信息';
comment on column public.t_zgbmxx.id is '';
comment on column public.t_zgbmxx.html is 'html内容';
comment on column public.t_zgbmxx.idnum is '证照/证件号码';
comment on column public.t_zgbmxx.idtype is '证照/证件类型';
comment on column public.t_zgbmxx.infotype is '信息类型';
comment on column public.t_zgbmxx.name is '出资人';
comment on column public.t_zgbmxx.showdatetime is '公示日期';
comment on column public.t_zgbmxx.type is '出资人类型';
comment on column public.t_zgbmxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_zscqczdjxx
=====================================================*/

CREATE TABLE t_zscqczdjxx (
    id bigint NOT NULL,
    changesitu character varying(255),
    czrname character varying(255),
    html text,
    infotype character varying(255),
    name character varying(255),
    regnum character varying(255),
    status character varying(255),
    type character varying(255),
    zqdjdeadline character varying(255),
    zqrname character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_zscqczdjxx is '知识产权出质登记信息表';
comment on column public.t_zscqczdjxx.id is '';
comment on column public.t_zscqczdjxx.changesitu is '变化情况';
comment on column public.t_zscqczdjxx.czrname is '出质人名称';
comment on column public.t_zscqczdjxx.html is 'html内容';
comment on column public.t_zscqczdjxx.infotype is '信息类型';
comment on column public.t_zscqczdjxx.name is '名称';
comment on column public.t_zscqczdjxx.regnum is '注册号';
comment on column public.t_zscqczdjxx.status is '状态';
comment on column public.t_zscqczdjxx.type is '种类';
comment on column public.t_zscqczdjxx.zqdjdeadline is '质权登记期限';
comment on column public.t_zscqczdjxx.zqrname is '质权人名称';
comment on column public.t_zscqczdjxx.qyjbxx_id is '企业基本信息表外键';



/*=====================================================
 t_zyryxx
=====================================================*/

CREATE TABLE t_zyryxx (
    id bigint NOT NULL,
    html text,
    infotype character varying(255),
    name character varying(255),
    "position" character varying(255),
    qyjbxx_id bigint
)

comment on table public.t_zyryxx is '主要人员信息表';
comment on column public.t_zyryxx.id is '';
comment on column public.t_zyryxx.html is 'html内容';
comment on column public.t_zyryxx.infotype is '信息类型';
comment on column public.t_zyryxx.name is '姓名';
comment on column public.t_zyryxx."position" is '职务';
comment on column public.t_zyryxx.qyjbxx_id is '企业基本信息表外键';


