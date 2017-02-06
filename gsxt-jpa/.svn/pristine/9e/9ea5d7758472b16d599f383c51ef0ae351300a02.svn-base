/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     2016/4/13 16:53:23                           */
/*==============================================================*/


drop table IF EXISTS t_bgxx  CASCADE ;

drop table IF EXISTS t_bgxxxq  CASCADE ;

drop table IF EXISTS t_ccjcxx  CASCADE ;

drop table IF EXISTS t_dcdydjxx  CASCADE ;

drop table IF EXISTS t_dwtgbzdbxx  CASCADE ; 

drop table IF EXISTS t_dwtzxx  CASCADE ;

drop table IF EXISTS t_fzjgxx  CASCADE ;

drop table IF EXISTS t_gdbgxx  CASCADE ;

drop table IF EXISTS t_gdjczxx  CASCADE ;

drop table IF EXISTS t_gqbgxx  CASCADE ;

drop table IF EXISTS t_gqczdjxx  CASCADE ;

drop table IF EXISTS t_gqdjxx  CASCADE ;

drop table IF EXISTS t_gsxt_html  CASCADE ;

drop table IF EXISTS t_jyychyzwfxx  CASCADE ;

drop table IF EXISTS t_member  CASCADE ;

drop table IF EXISTS t_qsxx  CASCADE ;

drop table IF EXISTS t_qtbmgs_xzxkxx  CASCADE ;

drop table IF EXISTS t_qygs_gdjczxx  CASCADE ;

drop table IF EXISTS t_qyjbxx  CASCADE ;

drop table IF EXISTS t_qynb  CASCADE ;

drop table IF EXISTS t_rjhsjmx  CASCADE ;

drop table IF EXISTS t_wzhwdxx  CASCADE ;

drop table IF EXISTS t_xzcfxx  CASCADE ;

drop table IF EXISTS t_xzcfxxxq  CASCADE ;

drop table IF EXISTS t_xzxkxx  CASCADE ;

drop table IF EXISTS t_zscqczdjxx  CASCADE ;

drop table IF EXISTS t_zyryxx  CASCADE ;

drop table IF EXISTS t_cxxx  CASCADE ;

drop table  IF EXISTS t_dyrgk CASCADE;

drop table IF EXISTS t_dywgk CASCADE;

drop table IF EXISTS t_dcdydjxxbg CASCADE;

drop table IF EXISTS t_scjyqk CASCADE;

drop table IF EXISTS t_zgbmxx CASCADE;


/*==============================================================*/
/* Table: t_bgxx      变更信息                                           */
/*==============================================================*/
create table t_bgxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   bgItem               VARCHAR(300)         null,
   bgqContent           TEXT                 null,
   bghContent           TEXT                 null,
   bgDate               VARCHAR(100)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   qynb_id              INT8                 null,
   qtbmgs_xzxkxx_id     INT8                 null,
   qygs_gdjczxx_id      INT8                 null,
   xzxkxx_id            INT8                 null,
   constraint PK_T_BGXX primary key (id)
);

comment on column t_bgxx.infoType is
'信息类型';

comment on column t_bgxx.bgItem is
'变更事项';

comment on column t_bgxx.bgqContent is
'变更前内容';

comment on column t_bgxx.bghContent is
'变更后内容';

comment on column t_bgxx.bgDate is
'变更日期';

comment on column t_bgxx.html is
'html内容';

comment on column t_bgxx.qyjbxx_id is
'企业基本信息';

comment on column t_bgxx.qynb_id is
'企业年报id';

comment on column t_bgxx.qtbmgs_xzxkxx_id is
'其他部门公示行政许可信息id';

comment on column t_bgxx.qygs_gdjczxx_id is
'企业公示股东及出资信息';

comment on column t_bgxx.xzxkxx_id is
'行政许可信息id';

/*==============================================================*/
/* Table: t_bgxxxq   变更信息详情                                           */
/*==============================================================*/
create table t_bgxxxq (
   id                   INT8                 not null,
   status               INT4                 null,
   name                 VARCHAR(100)         null,
   typeOrPosition       VARCHAR(100)         null,
   bgxx_id              INT8                 null,
   constraint PK_T_BGXXXQ primary key (id)
);

comment on column t_bgxxxq.status is
'状态： 0变更前  1变更后';

comment on column t_bgxxxq.name is
'姓名';

comment on column t_bgxxxq.typeOrPosition is
'类型或职位';

comment on column t_bgxxxq.bgxx_id is
'变更信息id';

/*==============================================================*/
/* Table: t_ccjcxx     抽查检查信息                                           */
/*==============================================================*/
create table t_ccjcxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   jcssAuthority        VARCHAR(100)         null,
   type                 VARCHAR(100)         null,
   date                 VARCHAR(100)         null,
   result               VARCHAR(200)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_CCJCXX primary key (id)
);

comment on column t_ccjcxx.infoType is
'信息类型';

comment on column t_ccjcxx.jcssAuthority is
'检查实施机关';

comment on column t_ccjcxx.type is
'类型';

comment on column t_ccjcxx.date is
'日期';

comment on column t_ccjcxx.result is
'结果';

comment on column t_ccjcxx.html is
'html内容';

comment on column t_ccjcxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_dcdydjxx      动产抵押登记信息                                       */
/*==============================================================*/
create table t_dcdydjxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   regNum               VARCHAR(100)         null,
   regDate              VARCHAR(100)         null,
   regAuthority         VARCHAR(100)         null,
   bdbzqAmount          VARCHAR(100)         null,
   status               VARCHAR(100)         null,
   pubDate              VARCHAR(100)         null,
   detail               VARCHAR(300)         null,
   guaranteedDebtType   VARCHAR(30)          null,
   term                 VARCHAR(30)          null,
   guaranteedScope      VARCHAR(300)         null,
   note                 TEXT                 null,
   revokeDate           VARCHAR(30)          null,
   revokeReason         VARCHAR(500)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_DCDYDJXX primary key (id)
);

comment on column t_dcdydjxx.infoType is
'信息类型';

comment on column t_dcdydjxx.regNum is
'登记编号';

comment on column t_dcdydjxx.regDate is
'登记日期';

comment on column t_dcdydjxx.regAuthority is
'登记机关';

comment on column t_dcdydjxx.bdbzqAmount is
'被担保债权数额';

comment on column t_dcdydjxx.status is
'状态';

comment on column t_dcdydjxx.pubDate is
'公示时间';

comment on column t_dcdydjxx.detail is
'详情';

comment on column t_dcdydjxx.guaranteedDebtType is
'被担保债权种类';

comment on column t_dcdydjxx.term is
'债务人履行债务的期限';

comment on column t_dcdydjxx.guaranteedScope is
'担保范围';

comment on column t_dcdydjxx.note is
'备注';

comment on column t_dcdydjxx.revokeDate is
'注销日期';

comment on column t_dcdydjxx.revokeReason is
'注销原因';

comment on column t_dcdydjxx.html is
'html内容';

comment on column t_dcdydjxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_dwtgbzdbxx     对外提供保证担保信息                                     */
/*==============================================================*/
create table t_dwtgbzdbxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   creditor             VARCHAR(100)         null,
   debtor               VARCHAR(100)         null,
   priCredRightType     VARCHAR(100)         null,
   priCredRightAmount   VARCHAR(50)          null,
   exeDebtDeadline      VARCHAR(100)         null,
   guaranteePeriod      VARCHAR(100)         null,
   guaranteeMethod      VARCHAR(100)         null,
   guaranteeScope       VARCHAR(300)         null,
   html                 TEXT                 null,
   qynb_id              INT8                 null,
   constraint PK_T_DWTGBZDBXX primary key (id)
);

comment on column t_dwtgbzdbxx.infoType is
'信息类型';

comment on column t_dwtgbzdbxx.creditor is
'债权人';

comment on column t_dwtgbzdbxx.debtor is
'债务人';

comment on column t_dwtgbzdbxx.priCredRightType is
'主债权种类';

comment on column t_dwtgbzdbxx.priCredRightAmount is
'主债权数额';

comment on column t_dwtgbzdbxx.exeDebtDeadline is
'履行债务的期限';

comment on column t_dwtgbzdbxx.guaranteePeriod is
'保证的期间';

comment on column t_dwtgbzdbxx.guaranteeMethod is
'保证的方式';

comment on column t_dwtgbzdbxx.guaranteeScope is
'保证担保的范围';

comment on column t_dwtgbzdbxx.html is
'html内容';

comment on column t_dwtgbzdbxx.qynb_id is
'企业年报id';

/*==============================================================*/
/* Table: t_dwtzxx       对外投资信息                                        */
/*==============================================================*/
create table t_dwtzxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   tzslqyhgmgqqyName    VARCHAR(100)         null,
   regNum               VARCHAR(100)         null,
   html                 TEXT                 null,
   qynb_id              INT8                 null,
   constraint PK_T_DWTZXX primary key (id)
);

comment on column t_dwtzxx.infoType is
'信息类型';

comment on column t_dwtzxx.tzslqyhgmgqqyName is
'投资设立企业或购买股权企业名称';

comment on column t_dwtzxx.regNum is
'注册号';

comment on column t_dwtzxx.html is
'html内容';

comment on column t_dwtzxx.qynb_id is
'企业年报id';

/*==============================================================*/
/* Table: t_fzjgxx       分支机构信息                                        */
/*==============================================================*/
create table t_fzjgxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   num                  VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   regAuthority         VARCHAR(100)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_FZJGXX primary key (id)
);

comment on column t_fzjgxx.infoType is
'信息类型';

comment on column t_fzjgxx.num is
'统一社会信用代码/注册号';

comment on column t_fzjgxx.name is
'名称';

comment on column t_fzjgxx.regAuthority is
'登记机关';

comment on column t_fzjgxx.html is
'html内容';

comment on column t_fzjgxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_gdbgxx     股东变更信息                                            */
/*==============================================================*/
create table t_gdbgxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   bzxPerson            VARCHAR(100)         null,
   gqAmount             VARCHAR(50)          null,
   srPerson             VARCHAR(100)         null,
   exeCourt             VARCHAR(100)         null,
   detail               VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_GDBGXX primary key (id)
);

comment on column t_gdbgxx.infoType is
'信息类型';

comment on column t_gdbgxx.bzxPerson is
'被执行人';

comment on column t_gdbgxx.gqAmount is
'股权数额';

comment on column t_gdbgxx.srPerson is
'受让人';

comment on column t_gdbgxx.exeCourt is
'执行法院';

comment on column t_gdbgxx.detail is
'详情';

comment on column t_gdbgxx.html is
'html内容';

comment on column t_gdbgxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_gdjczxx    股东及出资信息                                                */
/*==============================================================*/
create table t_gdjczxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   stockholder          VARCHAR(100)         null,
   rjczAmount           VARCHAR(100)         null,
   rjczDate             VARCHAR(100)         null,
   rjczMethod           VARCHAR(100)         null,
   sjczAmount           VARCHAR(100)         null,
   sjczDate             VARCHAR(100)         null,
   sjczMethod           VARCHAR(100)         null,
   html                 TEXT                 null,
   qynb_id              INT8                 null,
   member_id            INT8                 null unique,
   qygs_gdjczxx_id      INT8                 null,
   constraint PK_T_GDJCZXX primary key (id)
);

comment on column t_gdjczxx.infoType is
'信息类型';

comment on column t_gdjczxx.stockholder is
'股东（发起人）';

comment on column t_gdjczxx.rjczAmount is
'认缴出资额（万元）';

comment on column t_gdjczxx.rjczDate is
'认缴出资时间';

comment on column t_gdjczxx.rjczMethod is
'认缴出资方式';

comment on column t_gdjczxx.sjczAmount is
'实缴出资额（万元）';

comment on column t_gdjczxx.sjczDate is
'实缴出资时间';

comment on column t_gdjczxx.sjczMethod is
'实缴出资方式';

comment on column t_gdjczxx.html is
'html内容';

comment on column t_gdjczxx.qynb_id is
'企业年报id';

comment on column t_gdjczxx.member_id is
'人员信息id';

comment on column t_gdjczxx.qygs_gdjczxx_id is
'企业公示股东及出资信息id';

/*==============================================================*/
/* Table: t_gqbgxx       股权变更信息                                         */
/*==============================================================*/
create table t_gqbgxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   stockholder          VARCHAR(100)         null,
   bgqOwnershipRatio    VARCHAR(50)          null,
   bghOwnershipRatio    VARCHAR(50)          null,
   bgDate               VARCHAR(100)         null,
   html                 TEXT                 null,
   qynb_id              INT8                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_GQBGXX primary key (id)
);

comment on column t_gqbgxx.infoType is
'信息类型';

comment on column t_gqbgxx.stockholder is
'股东（发起人）';

comment on column t_gqbgxx.bgqOwnershipRatio is
'变更前股权比例';

comment on column t_gqbgxx.bghOwnershipRatio is
'变更后股权比例';

comment on column t_gqbgxx.bgDate is
'股权变更日期';

comment on column t_gqbgxx.html is
'html内容';

comment on column t_gqbgxx.qynb_id is
'企业年报信息id';

comment on column t_gqbgxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_gqczdjxx      股权出质登记信息                                        */
/*==============================================================*/
create table t_gqczdjxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   regNum               VARCHAR(100)         null,
   czr                  VARCHAR(100)         null,
   czrIdNum             VARCHAR(100)         null,
   czgqAmount           VARCHAR(100)         null,
   zqr                  VARCHAR(100)         null,
   zqrIdNum             VARCHAR(100)         null,
   gqczsldjDate         VARCHAR(100)         null,
   status               VARCHAR(100)         null,
   pubDate              VARCHAR(100)         null,
   changeSitu           VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_GQCZDJXX primary key (id)
);

comment on column t_gqczdjxx.infoType is
'信息类型';

comment on column t_gqczdjxx.regNum is
'登记编号';

comment on column t_gqczdjxx.czr is
'出质人';

comment on column t_gqczdjxx.czrIdNum is
'证照/证件号码（出质人）';

comment on column t_gqczdjxx.czgqAmount is
'出质股权数额';

comment on column t_gqczdjxx.zqr is
'质权人';

comment on column t_gqczdjxx.zqrIdNum is
'证照/证件号码（质权人）';

comment on column t_gqczdjxx.gqczsldjDate is
'股权出质设立登记日期';

comment on column t_gqczdjxx.status is
'状态';

comment on column t_gqczdjxx.pubDate is
'公示时间';

comment on column t_gqczdjxx.changeSitu is
'变化情况';

comment on column t_gqczdjxx.html is
'html内容';

comment on column t_gqczdjxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_gqdjxx     股权冻结信息                                          */
/*==============================================================*/
create table t_gqdjxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   bzxPerson            VARCHAR(100)         null,
   gqAmount             VARCHAR(50)          null,
   exeCourt             VARCHAR(100)         null,
   xzgstzsNum           VARCHAR(100)         null,
   status               VARCHAR(100)         null,
   detail               VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_GQDJXX primary key (id)
);

comment on column t_gqdjxx.infoType is
'信息类型  1工商公示信息 2企业公示信息 3其他部门公示信息';

comment on column t_gqdjxx.bzxPerson is
'被执行人';

comment on column t_gqdjxx.gqAmount is
'股权数额';

comment on column t_gqdjxx.exeCourt is
'执行法院';

comment on column t_gqdjxx.xzgstzsNum is
'协助公示通知书文号';

comment on column t_gqdjxx.status is
'状态';

comment on column t_gqdjxx.detail is
'详情';

comment on column t_gqdjxx.html is
'html内容';

comment on column t_gqdjxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_gsxt_html        企业原始不固定HMTL数据                                     */
/*==============================================================*/
create table t_gsxt_html (
   id                   INT8                 not null,
   gsgsBaInfo           TEXT                 null,
   gsgsXzcfInfo         TEXT                 null,
   gsgsYzwfInfo         TEXT                 null,
   qygsQynbInfo         TEXT                 null,
   qygsGdjczInfo        TEXT                 null,
   qygsGqbgInfo         TEXT                 null,
   qygsXzxkInfo         TEXT                 null,
   qygsZscqczdjInfo     TEXT                 null,
   qygsXzcfInfo         TEXT                 null,
   qtbmgsXzxkXzxkInfo   TEXT                 null,
   qtbmgsXzcfXzcfInfo   TEXT                 null,
   sfxzgsGqdjInfo       TEXT                 null,
   sfxzgsGdbgInfo       TEXT                 null,
   qyjbxx_id            INT8                 null unique,
   constraint PK_T_GSXT_HTML primary key (id)
);

comment on column t_gsxt_html.gsgsBaInfo is
'工商公示-备案信息';

comment on column t_gsxt_html.gsgsXzcfInfo is
'工商公示-行政处罚信息';

comment on column t_gsxt_html.gsgsYzwfInfo is
'工商公示-严重违法信息';

comment on column t_gsxt_html.qygsQynbInfo is
'企业公示-企业年报信息';

comment on column t_gsxt_html.qygsGdjczInfo is
'企业公示-股东及出资信息';

comment on column t_gsxt_html.qygsGqbgInfo is
'企业公示-股权变更信息';

comment on column t_gsxt_html.qygsXzxkInfo is
'企业公示-行政许可信息';

comment on column t_gsxt_html.qygsZscqczdjInfo is
'企业公示-知识产权出质登记信息';

comment on column t_gsxt_html.qygsXzcfInfo is
'企业公示-行政处罚信息';

comment on column t_gsxt_html.qtbmgsXzxkXzxkInfo is
'其他部门公示-行政许可信息';

comment on column t_gsxt_html.qtbmgsXzcfXzcfInfo is
'其他部门公示-行政处罚信息';

comment on column t_gsxt_html.sfxzgsGqdjInfo is
'司法协助公示-股权冻结信息';

comment on column t_gsxt_html.sfxzgsGdbgInfo is
'司法协助公示-股东变更信息';

comment on column t_gsxt_html.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_jyychyzwfxx       经营异常或严重违法信息                                  */
/*==============================================================*/
create table t_jyychyzwfxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   isJyyc               BOOL                 null,
   lrCause              VARCHAR(200)         null,
   lrDate               VARCHAR(100)         null,
   lrzcjdAuthority      VARCHAR(100)         null,
   ycCause              VARCHAR(100)         null,
   ycDate               VARCHAR(100)         null,
   yczcjdAuthority      VARCHAR(100)         null,
   zcjdAuthority        VARCHAR(100)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_JYYCHYZWFXX primary key (id)
);

comment on column t_jyychyzwfxx.infoType is
'信息类型';

comment on column t_jyychyzwfxx.isJyyc is
'是否为经营异常';

comment on column t_jyychyzwfxx.lrCause is
'列入原因';

comment on column t_jyychyzwfxx.lrDate is
'列入日期';

comment on column t_jyychyzwfxx.lrzcjdAuthority is
'做出决定机关（列入）';

comment on column t_jyychyzwfxx.ycCause is
'移出原因';

comment on column t_jyychyzwfxx.ycDate is
'移出日期';

comment on column t_jyychyzwfxx.yczcjdAuthority is
'做出决定机关（移出）';

comment on column t_jyychyzwfxx.zcjdAuthority is
'做出决定机关（列入或移出）';

comment on column t_jyychyzwfxx.html is
'html内容';

comment on column t_jyychyzwfxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_member         人员信息（包括股东）                                     */
/*==============================================================*/
create table t_member (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   isGd                 VARCHAR(10)          null,
   gdType               VARCHAR(100)         null,
   idType               VARCHAR(100)         null,
   idNum                VARCHAR(100)         null,
   "position"           VARCHAR(100)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_MEMBER primary key (id)
);

comment on column t_member.infoType is
'信息类型';

comment on column t_member.name is
'名称';

comment on column t_member.isGd is
'是否是股东';

comment on column t_member.gdType is
'股东类型';

comment on column t_member.idType is
'证照/证件类型';

comment on column t_member.idNum is
'证照/证件号码';

comment on column t_member."position" is
'职务';

comment on column t_member.html is
'html内容';

comment on column t_member.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_qsxx           清算信息                                     */
/*==============================================================*/
create table t_qsxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   leader               VARCHAR(100)         null,
   members              VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null unique,
   constraint PK_T_QSXX primary key (id)
);

comment on column t_qsxx.infoType is
'信息类型';

comment on column t_qsxx.leader is
'清算组负责人';

comment on column t_qsxx.members is
'清算组成员';

comment on column t_qsxx.html is
'html内容';

comment on column t_qsxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_qtbmgs_xzxkxx    其他部门公示行政许可信息                                   */
/*==============================================================*/
create table t_qtbmgs_xzxkxx (
   id                   INT8                 not null,
   infoType             VARCHAR(255)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null unique,
   constraint PK_T_QTBMGS_XZXKXX primary key (id)
);

comment on column t_qtbmgs_xzxkxx.infoType is
'信息类型';

comment on column t_qtbmgs_xzxkxx.html is
'html内容';

comment on column t_qtbmgs_xzxkxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_qygs_gdjczxx      企业公示股东及出资信息                                  */
/*==============================================================*/
create table t_qygs_gdjczxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null unique,
   constraint PK_T_QYGS_GDJCZXX primary key (id)
);

comment on column t_qygs_gdjczxx.infoType is
'信息类型';

comment on column t_qygs_gdjczxx.html is
'html内容';

comment on column t_qygs_gdjczxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_qyjbxx            企业基本信息                                    */
/*==============================================================*/
create table t_qyjbxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   num                  VARCHAR(255)         null,
   name                 VARCHAR(255)         null,
   "type"               VARCHAR(255)         null,
   legalRepr            VARCHAR(100)         null,
   registeredCapital    VARCHAR(100)         null,
   registeredDate       VARCHAR(100)         null,
   formType				VARCHAR(100)         null,
   address              VARCHAR(255)         null,
   startDate            VARCHAR(100)         null,
   endDate              VARCHAR(100)         null,
   businessScope        TEXT                 null,
   regAuthority         VARCHAR(255)         null,
   approvalDate         VARCHAR(100)         null,
   regStatus            VARCHAR(100)         null,
   revokeDate           VARCHAR(100)         null,
   html                 TEXT                 null,
   constraint PK_T_QYJBXX primary key (id)
);

comment on column t_qyjbxx.infoType is
'信息的类型';

comment on column t_qyjbxx.num is
'注册号或信用代码';

comment on column t_qyjbxx.name is
'名称';

comment on column t_qyjbxx.type is
'类型';

comment on column t_qyjbxx.legalRepr is
'法定代表人/经营者';

comment on column t_qyjbxx.registeredCapital is
'注册资本';

comment on column t_qyjbxx.registeredDate is
'成立日期';

comment on column t_qyjbxx.formType is
'组成形式';

comment on column t_qyjbxx.address is
'经营场所/住所';

comment on column t_qyjbxx.startDate is
'营业期限自（即营业开始日期）';

comment on column t_qyjbxx.endDate is
'营业期限至（即营业结束日期）';

comment on column t_qyjbxx.businessScope is
'经营范围';

comment on column t_qyjbxx.regAuthority is
'登记机关';

comment on column t_qyjbxx.approvalDate is
'核准日期';

comment on column t_qyjbxx.regStatus is
'登记状态';

comment on column t_qyjbxx.revokeDate is
'吊销日期';

comment on column t_qyjbxx.html is
'html内容';

/*==============================================================*/
/* Table: t_qynb            企业年报                                    */
/*==============================================================*/
create table t_qynb (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   submitYear           VARCHAR(100)         null,
   pubDate              VARCHAR(100)         null,
   num                  VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   tel                  VARCHAR(100)         null,
   zipCode              VARCHAR(50)          null,
   address              VARCHAR(200)         null,
   email                VARCHAR(50)          null,
   isYxzrgsbndgdgqzr    VARCHAR(20)          null,
   operatingStatus		VARCHAR(100)         null,
   hasWzhwd             VARCHAR(20)          null,
   isTzxxhgmqtgsgq      VARCHAR(20)          null,
   empNum               VARCHAR(50)          null,
   html_jbxx            TEXT                 null,
   assetAmount          VARCHAR(50)          null,
   liabilityAmount      VARCHAR(50)          null,
   salesAmount          VARCHAR(50)          null,
   profitAmount         VARCHAR(50)          null,
   xszezzyywsr          VARCHAR(50)          null,
   netProfit            VARCHAR(50)          null,
   taxesAmount          VARCHAR(50)          null,
   syzqyhj              VARCHAR(50)          null,
   html_qyzczkxx        TEXT                 null,
   legalrepr			VARCHAR(50)          null,
   registeredcapital	VARCHAR(30)			 null,
   capitalamount		VARCHAR(30)			 null,
   affiliation			VARCHAR(50)			 null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_QYNB primary key (id)
);

comment on column t_qynb.infoType is
'信息类型';

comment on column t_qynb.submitYear is
'报送年度';

comment on column t_qynb.pubDate is
'发布日期';

comment on column t_qynb.num is
'企业注册号/统一社会信用代码';

comment on column t_qynb.name is
'企业名称';

comment on column t_qynb.tel is
'企业联系电话';

comment on column t_qynb.zipCode is
'企业邮政编码';

comment on column t_qynb.address is
'企业通信地址';

comment on column t_qynb.email is
'电子邮箱';

comment on column t_qynb.isYxzrgsbndgdgqzr is
'有限责任公司本年度是否发生股东股权转让';

comment on column t_qynb.hasWzhwd is
'是否有网站或网店';

comment on column t_qynb.isTzxxhgmqtgsgq is
'企业是否有投资信息或购买其他公司股权';

comment on column t_qynb.empNum is
'从业人数';

comment on column t_qynb.html_jbxx is
'html内容（基本信息）';

comment on column t_qynb.assetAmount is
'资产总额';

comment on column t_qynb.liabilityAmount is
'负债总额';

comment on column t_qynb.salesAmount is
'销售总额';

comment on column t_qynb.profitAmount is
'利润总额';

comment on column t_qynb.xszezzyywsr is
'销售总额中主营业务收入';

comment on column t_qynb.netProfit is
'净利润';

comment on column t_qynb.taxesAmount is
'纳税总额';

comment on column t_qynb.syzqyhj is
'所有者权益合计';

comment on column t_qynb.html_qyzczkxx is
'html内容（企业资产状况信息）';

comment on column t_qynb.legalrepr is
'法定代表人/经营者';

comment on column t_qynb.registeredcapital is
'注册资本';

comment on column t_qynb.capitalamount is
'资金数额';

comment on column t_qynb.affiliation is
'隶属关系';

comment on column t_qynb.html is
'html内容（企业年报）';

comment on column t_qynb.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_rjhsjmx      认缴和实缴明细                                        */
/*==============================================================*/
create table t_rjhsjmx (
   id                   INT8                 not null,
   type                 VARCHAR(10)          null,
   method               VARCHAR(100)         null,
   amount               VARCHAR(100)         null,
   czDate               VARCHAR(100)         null,
   gdjczxx_id           INT8                 null,
   constraint PK_T_RJHSJMX primary key (id)
);

comment on column t_rjhsjmx.type is
'类型：1认缴  2实缴';

comment on column t_rjhsjmx.method is
'出资方式';

comment on column t_rjhsjmx.amount is
'出资额（万元）';

comment on column t_rjhsjmx.czDate is
'czDate 出资日期';

comment on column t_rjhsjmx.gdjczxx_id is
'股东及出资信息id';

/*==============================================================*/
/* Table: t_wzhwdxx       网站或网店信息                                      */
/*==============================================================*/
create table t_wzhwdxx (
   id                   INT8                 not null,
   type                 VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   website              VARCHAR(500)         null,
   html                 TEXT                 null,
   qynb_id              INT8                 null,
   constraint PK_T_WZHWDXX primary key (id)
);

comment on column t_wzhwdxx.type is
'类型';

comment on column t_wzhwdxx.name is
'名称';

comment on column t_wzhwdxx.website is
'网址';

comment on column t_wzhwdxx.html is
'html内容';

comment on column t_wzhwdxx.qynb_id is
'企业年报id';

/*==============================================================*/
/* Table: t_xzcfxx     行政处罚信息                                         */
/*==============================================================*/
create table t_xzcfxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   xzcfjdsNum           VARCHAR(100)         null,
   wfxwType             VARCHAR(100)         null,
   xzcfContent          VARCHAR(200)         null,
   zcxzcfjdjgName       VARCHAR(100)         null,
   zcxzcfjdDate         VARCHAR(100)         null,
   xzcfjds              TEXT                 null,
   note                 VARCHAR(300)         null,
   detail               VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_XZCFXX primary key (id)
);

comment on column t_xzcfxx.infoType is
'信息类型';

comment on column t_xzcfxx.xzcfjdsNum is
'行政处罚决定书文号';

comment on column t_xzcfxx.wfxwType is
'违法行为类型';

comment on column t_xzcfxx.xzcfContent is
'行政处罚内容';

comment on column t_xzcfxx.zcxzcfjdjgName is
'作出行政处罚决定机关名称';

comment on column t_xzcfxx.zcxzcfjdDate is
'作出行政处罚决定日期';

comment on column t_xzcfxx.xzcfjds is
'行政处罚决定书';

comment on column t_xzcfxx.note is
'备注';

comment on column t_xzcfxx.detail is
'详情';

comment on column t_xzcfxx.html is
'html内容';

comment on column t_xzcfxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_xzcfxxxq       行政处罚信息详情                                      */
/*==============================================================*/
create table t_xzcfxxxq (
   id                   INT8                 not null,
   xzcfjdsNum           VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   regNum               VARCHAR(100)         null,
   legalReprName        VARCHAR(100)         null,
   wfxwType             VARCHAR(100)         null,
   xzcfContent          VARCHAR(200)         null,
   zcxzcfjdjgName       VARCHAR(100)         null,
   zcxzcfjdDate         VARCHAR(100)         null,
   xzcfjds              TEXT                 null,
   xzcfxx_id            INT8                 null unique,
   constraint PK_T_XZCFXXXQ primary key (id)
);

comment on column t_xzcfxxxq.xzcfjdsNum is
'行政处罚决定书文号';

comment on column t_xzcfxxxq.name is
'名称';

comment on column t_xzcfxxxq.regNum is
'注册号';

comment on column t_xzcfxxxq.legalReprName is
'法定代表人（负责人）姓名';

comment on column t_xzcfxxxq.wfxwType is
'违法行为类型';

comment on column t_xzcfxxxq.xzcfContent is
'行政处罚内容';

comment on column t_xzcfxxxq.zcxzcfjdjgName is
'作出行政处罚决定机关名称';

comment on column t_xzcfxxxq.zcxzcfjdDate is
'作出行政处罚决定日期';

comment on column t_xzcfxxxq.xzcfjds is
'行政处罚决定书';

comment on column t_xzcfxxxq.xzcfxx_id is
'行政处罚信息id';

/*==============================================================*/
/* Table: t_xzxkxx        行政许可信息                                      */
/*==============================================================*/
create table t_xzxkxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   xkwjNum              VARCHAR(100)         null,
   xkwjName             VARCHAR(100)         null,
   startDate            VARCHAR(100)         null,
   endDate              VARCHAR(100)         null,
   xkAuthority          VARCHAR(100)         null,
   xkContent            VARCHAR(300)         null,
   status               VARCHAR(100)         null,
   detail               VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   qtbmgs_xzxkxx_id     INT8                 null,
   qynb_id     			INT8                 null,
   constraint PK_T_XZXKXX primary key (id)
);

comment on column t_xzxkxx.infoType is
'信息类型';

comment on column t_xzxkxx.xkwjNum is
'许可文件编号';

comment on column t_xzxkxx.xkwjName is
'许可文件名称';

comment on column t_xzxkxx.startDate is
'有效期自';

comment on column t_xzxkxx.endDate is
'有效期至';

comment on column t_xzxkxx.xkAuthority is
'许可机关';

comment on column t_xzxkxx.xkContent is
'许可内容';

comment on column t_xzxkxx.status is
'状态';

comment on column t_xzxkxx.detail is
'详情';

comment on column t_xzxkxx.html is
'html内容';

comment on column t_xzxkxx.qyjbxx_id is
'企业基本信息id';

comment on column t_xzxkxx.qtbmgs_xzxkxx_id is
'其他部门公示行政许可信息id';

/*==============================================================*/
/* Table: t_zscqczdjxx     知识产权出质登记信息                                     */
/*==============================================================*/
create table t_zscqczdjxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   regNum               VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   type                 VARCHAR(100)         null,
   czrName              VARCHAR(100)         null,
   zqrName              VARCHAR(100)         null,
   zqdjDeadline         VARCHAR(100)         null,
   status               VARCHAR(100)         null,
   changeSitu           VARCHAR(300)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_ZSCQCZDJXX primary key (id)
);

comment on column t_zscqczdjxx.infoType is
'信息类型';

comment on column t_zscqczdjxx.regNum is
'注册号';

comment on column t_zscqczdjxx.name is
'名称';

comment on column t_zscqczdjxx.type is
'类型';

comment on column t_zscqczdjxx.czrName is
'出质人名称';

comment on column t_zscqczdjxx.zqrName is
'质权人名称';

comment on column t_zscqczdjxx.zqdjDeadline is
'质权登记期限';

comment on column t_zscqczdjxx.status is
'状态';

comment on column t_zscqczdjxx.changeSitu is
'变化情况';

comment on column t_zscqczdjxx.html is
'html内容';

comment on column t_zscqczdjxx.qyjbxx_id is
'企业基本信息id';

/*==============================================================*/
/* Table: t_zyryxx      主要人员信息                                        */
/*==============================================================*/
create table t_zyryxx (
   id                   INT8                 not null,
   infoType             VARCHAR(100)         null,
   name                 VARCHAR(100)         null,
   "position"           VARCHAR(100)         null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_ZYRYXX primary key (id)
);

comment on column t_zyryxx.infoType is
'信息类型';

comment on column t_zyryxx.name is
'姓名';

comment on column t_zyryxx."position" is
'职务';

comment on column t_zyryxx.html is
'html内容';

comment on column t_zyryxx.qyjbxx_id is
'企业基本信息id';


/*==============================================================*/
/* Table: t_cxxx                                                 */
/*==============================================================*/
create table t_cxxx (
   id                   INT8                 not null,
   infoType             VARCHAR(50)          null,
   revokeItem           VARCHAR(255)         null,
   revokePreContent     TEXT                 null,
   revokePostContent    TEXT                 null,
   revokeDateTime       VARCHAR(30)          null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_CXXX primary key (id)
);

comment on column t_cxxx.infoType is
'信息类型';

comment on column t_cxxx.revokeItem is
'撤销事项';

comment on column t_cxxx.revokePreContent is
'撤销前内容';

comment on column t_cxxx.revokePostContent is
'撤销后内容';

comment on column t_cxxx.revokeDateTime is
'撤销日期';

comment on column t_cxxx.html is
'html内容';

comment on column t_cxxx.qyjbxx_id is
'企业基本信息id';


/*==============================================================*/
/* Table: t_dyrgk                                               */
/*==============================================================*/
create table t_dyrgk (
   id                   INT8                 not null,
   infoType             VARCHAR(50)          null,
   name                 VARCHAR(50)          null,
   idType               VARCHAR(20)          null,
   idNum                VARCHAR(50)          null,
   html                 TEXT                 null,
   dcdydjxx_id          INT8                 null,
   constraint PK_T_DYRGK primary key (id)
);

comment on table t_dyrgk is
'抵押人概况';

comment on column t_dyrgk.infoType is
'信息类型';

comment on column t_dyrgk.name is
'抵押权人名称';

comment on column t_dyrgk.idType is
'抵押权人证照/证件类型';

comment on column t_dyrgk.idNum is
'证照/证件号码';

comment on column t_dyrgk.html is
'html内容';

comment on column t_dyrgk.dcdydjxx_id is
'动产抵押登记信息id';


/*==============================================================*/
/* Table: t_dywgk                                               */
/*==============================================================*/
create table t_dywgk (
   id                   INT8                 not null,
   infoType             VARCHAR(50)          null,
   name                 VARCHAR(50)          null,
   ownerShip            VARCHAR(50)          null,
   generalSituation     VARCHAR(500)         null,
   note                 TEXT                 null,
   html                 TEXT                 null,
   dcdydjxx_id          INT8                 null,
   constraint PK_T_DYWGK primary key (id)
);

comment on table t_dywgk is
'抵押物概况';

comment on column t_dywgk.infoType is
'信息类型';

comment on column t_dywgk.name is
'名称';

comment on column t_dywgk.ownerShip is
'所有权归属';

comment on column t_dywgk.generalSituation is
'数量、质量、状况、所在地等情况';

comment on column t_dywgk.note is
'备注';

comment on column t_dywgk.html is
'html内容';

comment on column t_dywgk.dcdydjxx_id is
'动产抵押登记信息id';


/*==============================================================*/
/* Table: t_dcdydjxxbg                                          */
/*==============================================================*/
create table t_dcdydjxxbg (
   id                   INT8                 not null,
   changeDateTime       VARCHAR(30)          null,
   changeContent        TEXT                 null,
   infoType             VARCHAR(50)          null,
   dcdydjxx_id          INT8                 null,
   constraint PK_T_DCDYDJXXBG primary key (id)
);

comment on table t_dcdydjxxbg is
'动产抵押登记信息变更';

comment on column t_dcdydjxxbg.changeDateTime is
'变更日期';

comment on column t_dcdydjxxbg.changeContent is
'变更内容';

comment on column t_dcdydjxxbg.infoType is
'信息类型';

comment on column t_dcdydjxxbg.dcdydjxx_id is
'动产抵押登记信息id';


/*==============================================================*/
/* Table: t_scjyqk                                              */
/*==============================================================*/
create table t_scjyqk (
   id                   INT8                 not null,
   infoType             VARCHAR(50)          null,
   saleSum              VARCHAR(30)          null,
   salarySum            VARCHAR(30)          null,
   netProfit            VARCHAR(30)          null,
   html                 TEXT                 null,
   qynb_id              INT8                 null,
   constraint PK_T_SCJYQK primary key (id)
);

comment on table t_scjyqk is
'生产经营情况';

comment on column t_scjyqk.infoType is
'信息类型';

comment on column t_scjyqk.saleSum is
'营业额或营业收入';

comment on column t_scjyqk.salarySum is
'纳税总额';

comment on column t_scjyqk.netProfit is
'净利润';

comment on column t_scjyqk.html is
'html内容';

comment on column t_scjyqk.qynb_id is
'企业年报id';


/*==============================================================*/
/* Table: t_zgbmxx                                              */
/*==============================================================*/
create table t_zgbmxx (
   id                   INT8                 not null,
   infoType             VARCHAR(50)          null,
   name                 VARCHAR(50)          null,
   idType               VARCHAR(30)          null,
   idNum                VARCHAR(50)          null,
   showDate             VARCHAR(30)          null,
   html                 TEXT                 null,
   qyjbxx_id            INT8                 null,
   constraint PK_T_ZGBMXX primary key (id)
);

comment on table t_zgbmxx is
'主管部门（出资人）信息';

comment on column t_zgbmxx.infoType is
'信息类型';

comment on column t_zgbmxx.name is
'出资人';

comment on column t_zgbmxx.idType is
'证照/证件类型';

comment on column t_zgbmxx.idNum is
'证照/证件号码';

comment on column t_zgbmxx.showDate is
'公示日期';

comment on column t_zgbmxx.html is
'html内容';

comment on column t_zgbmxx.qyjbxx_id is
'企业基本信息id';


alter table t_bgxx
   add constraint FK_T_BGXX_BGXX_QTBM_T_QTBMGS foreign key (qtbmgs_xzxkxx_id)
      references t_qtbmgs_xzxkxx (id)
      on delete restrict on update restrict;

alter table t_bgxx
   add constraint FK_T_BGXX_BGXX_QYGS_T_QYGS_G foreign key (qygs_gdjczxx_id)
      references t_qygs_gdjczxx (id)
      on delete restrict on update restrict;

alter table t_bgxx
   add constraint FK_T_BGXX_BGXX_QYJB_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_bgxx
   add constraint FK_T_BGXX_BGXX_QYNB_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;

alter table t_bgxxxq
   add constraint FK_T_BGXXXQ_BGXXXQ_BG_T_BGXX foreign key (bgxx_id)
      references t_bgxx (id)
      on delete restrict on update restrict;

alter table t_ccjcxx
   add constraint FK_T_CCJCXX_CCJCXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_dcdydjxx
   add constraint FK_T_DCDYDJ_DCDYDJXX__T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_dwtgbzdbxx
   add constraint FK_T_DWTGBZ_DWTGBZDBX_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;

alter table t_dwtzxx
   add constraint FK_T_DWTZXX_DWTZXX_QY_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;

alter table t_fzjgxx
   add constraint FK_T_FZJGXX_FZJGXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_gdbgxx
   add constraint FK_T_GDBGXX_GDBGXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_gdjczxx
   add constraint FK_T_GDJCZX_GDJCZXX_M_T_MEMBER foreign key (member_id)
      references t_member (id)
      on delete restrict on update restrict;

alter table t_gdjczxx
   add constraint FK_T_GDJCZX_GDJCZXX_Q_T_QYGS_G foreign key (qygs_gdjczxx_id)
      references t_qygs_gdjczxx (id)
      on delete restrict on update restrict;

alter table t_gdjczxx
   add constraint FK_T_GDJCZX_GDJCZXX_Q_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;

alter table t_gqbgxx
   add constraint FK_T_GQBGXX_GQBGXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_gqbgxx
   add constraint FK_T_GQBGXX_GQBGXX_QY_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;

alter table t_gqczdjxx
   add constraint FK_T_GQCZDJ_GQCZDJXX__T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_gqdjxx
   add constraint FK_T_GQDJXX_GQDJXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_gsxt_html
   add constraint FK_T_GSXT_H_GSXT_HTML_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_jyychyzwfxx
   add constraint FK_T_JYYCHY_JYYCHYZWF_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_member
   add constraint FK_T_MEMBER_MEMBER_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_qsxx
   add constraint FK_T_QSXX_QSXX_QYJB_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_qtbmgs_xzxkxx
   add constraint FK_T_QTBMGS_QTBMGS_XZ_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_qygs_gdjczxx
   add constraint FK_T_QYGS_G_QYGS_GDJC_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_qynb
   add constraint FK_T_QYNB_QYNB_QYJB_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_rjhsjmx
   add constraint FK_T_RJHSJM_RJHSJMX_G_T_GDJCZX foreign key (gdjczxx_id)
      references t_gdjczxx (id)
      on delete restrict on update restrict;

alter table t_wzhwdxx
   add constraint FK_T_WZHWDX_WZHWDXX_Q_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;

alter table t_xzcfxx
   add constraint FK_T_XZCFXX_XZCFXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_xzcfxxxq
   add constraint FK_T_XZCFXX_XZCFXXXQ__T_XZCFXX foreign key (xzcfxx_id)
      references t_xzcfxx (id)
      on delete restrict on update restrict;

alter table t_xzxkxx
   add constraint FK_T_XZXKXX_XZXKXX_QT_T_QTBMGS foreign key (qtbmgs_xzxkxx_id)
      references t_qtbmgs_xzxkxx (id)
      on delete restrict on update restrict;

alter table t_xzxkxx
   add constraint FK_T_XZXKXX_XZXKXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_zscqczdjxx
   add constraint FK_T_ZSCQCZ_ZSCQCZDJX_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_zyryxx
   add constraint FK_T_ZYRYXX_ZYRYXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;

alter table t_cxxx
   add constraint FK_T_CXXX_CXXX_QYJB_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;
      
alter table t_dyrgk
   add constraint FK_T_DYRGK_DYRGK_DCD_T_DCDYDJ foreign key (dcdydjxx_id)
      references t_dcdydjxx (id)
      on delete restrict on update restrict;
      
alter table t_dywgk
   add constraint FK_T_DYWGK_DYWGK_DCD_T_DCDYDJ foreign key (dcdydjxx_id)
      references t_dcdydjxx (id)
      on delete restrict on update restrict;
      
alter table t_dcdydjxxbg
   add constraint FK_T_DCDYDJ_DCDYDJXXB_T_DCDYDJ foreign key (dcdydjxx_id)
      references t_dcdydjxx (id)
      on delete restrict on update restrict;      
      
alter table t_xzxkxx
   add constraint FK_T_XZXKXX_XZXKXX_QY_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;     
  
alter table t_scjyqk
   add constraint FK_T_SCJYQK_SCJYQK_QY_T_QYNB foreign key (qynb_id)
      references t_qynb (id)
      on delete restrict on update restrict;
      
alter table t_bgxx
   add constraint FK_T_BGXX_BGXX_XZXK_T_XZXKXX foreign key (xzxkxx_id)
      references t_xzxkxx (id)
      on delete restrict on update restrict;
      
alter table t_zgbmxx
   add constraint FK_T_ZGBMXX_ZGBMXX_QY_T_QYJBXX foreign key (qyjbxx_id)
      references t_qyjbxx (id)
      on delete restrict on update restrict;
      
      
      
      
      
      
      
      
      
      
      
      
      