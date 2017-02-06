drop table IF EXISTS t_renfawang_searchresult CASCADE;
drop table IF EXISTS t_renfawang_detail CASCADE;

/*==============================================================*/
/* Table: t_renfawang_searchresult                              */
/*==============================================================*/
create table t_renfawang_searchresult (
   id                   INT8                 not null,
   pnum                 VARCHAR(100)         null,
   pname                VARCHAR(100)         null,
   zxDate               VARCHAR(50)          null,
   zxNo                 VARCHAR(100)         null,
   constraint PK_T_RENFAWANG_SEARCHRESULT primary key (id)
);

comment on column t_renfawang_searchresult.pnum is
'被执行人的详情id';

comment on column t_renfawang_searchresult.pname is
'被执行人姓名/名称';

comment on column t_renfawang_searchresult.zxDate is
'立案时间';

comment on column t_renfawang_searchresult.zxNo is
'案号';


/*==============================================================*/
/* Table: t_renfawang_detail                                    */
/*==============================================================*/
create table t_renfawang_detail (
   id                   INT8                 not null,
   pname                VARCHAR(100)         null,
   partyCardNum         VARCHAR(100)         null,
   execCourtName        VARCHAR(100)         null,
   caseCreateTime       VARCHAR(50)          null,
   caseCode             VARCHAR(100)         null,
   execMoney            VARCHAR(100)         null,
   constraint PK_T_RENFAWANG_DETAIL primary key (id)
);

comment on column t_renfawang_detail.pname is
'被执行人姓名/名称';

comment on column t_renfawang_detail.partyCardNum is
'身份证号/组织机构代码';

comment on column t_renfawang_detail.execCourtName is
'执行法院';

comment on column t_renfawang_detail.caseCreateTime is
'立案时间';

comment on column t_renfawang_detail.caseCode is
'案号';

comment on column t_renfawang_detail.execMoney is
'执行标的';