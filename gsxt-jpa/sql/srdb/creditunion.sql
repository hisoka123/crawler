drop table IF EXISTS t_creditunion_detail CASCADE;
drop table IF EXISTS t_creditunion_searchresult CASCADE;

/*==============================================================*/
/* Table: t_creditunion_detail                                  */
/*==============================================================*/
create table t_creditunion_detail (
   id                   INT8                 not null,
   name                 VARCHAR(100)         null,
   caseNum              VARCHAR(100)         null,
   age                  VARCHAR(10)          null,
   sex                  VARCHAR(10)          null,
   cardID               VARCHAR(100)         null,
   legalPerson          VARCHAR(100)         null,
   executeCourt         VARCHAR(255)         null,
   province             VARCHAR(100)         null,
   executeNum           VARCHAR(100)         null,
   caseDate             VARCHAR(50)          null,
   dependCourt          VARCHAR(255)         null,
   effectNum            TEXT                 null,
   executeSituation     VARCHAR(255)         null,
   alreadyExecute       VARCHAR(255)         null,
   noExecute            VARCHAR(255)         null,
   behaviorSituation    TEXT                 null,
   pubDate              VARCHAR(50)          null,
   updateDate           VARCHAR(50)          null,
   constraint PK_T_CREDITUNION_DETAIL primary key (id)
);

comment on column t_creditunion_detail.name is
'被执行人姓名/名称';

comment on column t_creditunion_detail.caseNum is
'案号';

comment on column t_creditunion_detail.age is
'年龄';

comment on column t_creditunion_detail.sex is
'性别';

comment on column t_creditunion_detail.cardID is
'身份证号/组织机构代码';

comment on column t_creditunion_detail.legalPerson is
'法定代表人或者负责人';

comment on column t_creditunion_detail.executeCourt is
'执行法院';

comment on column t_creditunion_detail.province is
'省份';

comment on column t_creditunion_detail.executeNum is
'执行依据文号';

comment on column t_creditunion_detail.caseDate is
'立案时间';

comment on column t_creditunion_detail.dependCourt is
'做出执行依据单位';

comment on column t_creditunion_detail.effectNum is
'生效法律文书确定的义务';

comment on column t_creditunion_detail.executeSituation is
'被执行人的履行情况';

comment on column t_creditunion_detail.alreadyExecute is
'已履行';

comment on column t_creditunion_detail.noExecute is
'未履行';

comment on column t_creditunion_detail.behaviorSituation is
'失信被执行人行为情形';

comment on column t_creditunion_detail.pubDate is
'发布时间';

comment on column t_creditunion_detail.updateDate is
'更新时间';

/*==============================================================*/
/* Table: t_creditunion_searchresult                            */
/*==============================================================*/
create table t_creditunion_searchresult (
   id                   INT8                 not null,
   name                 VARCHAR(100)         null,
   lenderMoney          VARCHAR(100)         null,
   lenderDate           VARCHAR(50)          null,
   lenderMethod         VARCHAR(255)         null,
   InfoSource           VARCHAR(255)         null,
   updateDate           VARCHAR(50)          null,
   detailUrl            VARCHAR(255)         null,
   constraint PK_T_CREDITUNION_SEARCHRESULT primary key (id)
);

comment on column t_creditunion_searchresult.name is
'姓名';

comment on column t_creditunion_searchresult.lenderMoney is
'金额';

comment on column t_creditunion_searchresult.lenderDate is
'借款日期';

comment on column t_creditunion_searchresult.lenderMethod is
'贷款方式';

comment on column t_creditunion_searchresult.InfoSource is
'信息来源';

comment on column t_creditunion_searchresult.updateDate is
'更新时间';

comment on column t_creditunion_searchresult.detailUrl is
'详情url';