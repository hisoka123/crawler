drop table IF EXISTS t_zhefawang_exposurequery CASCADE;
drop table IF EXISTS t_zhefawang_executecasequery CASCADE;

/*==============================================================*/
/* Table: t_zhefawang_exposurequery                             */
/*==============================================================*/
create table t_zhefawang_exposurequery (
   id                   INT8                 not null,
   name                 VARCHAR(100)         null,
   idNo                 VARCHAR(100)         null,
   address              VARCHAR(255)         null,
   enforceBasis         VARCHAR(255)         null,
   caseNo               VARCHAR(100)         null,
   executReason         VARCHAR(255)         null,
   court                VARCHAR(255)         null,
   amountNotExecuted    VARCHAR(100)         null,
   caseDate             VARCHAR(50)          null,
   targetAmount         VARCHAR(100)         null,
   creditDate           VARCHAR(50)          null,
   constraint PK_T_ZHEFAWANG_EXPOSUREQUERY primary key (id)
);

comment on column t_zhefawang_exposurequery.name is
'姓名';

comment on column t_zhefawang_exposurequery.idNo is
'证件号码';

comment on column t_zhefawang_exposurequery.address is
'地址';

comment on column t_zhefawang_exposurequery.enforceBasis is
'执行依据';

comment on column t_zhefawang_exposurequery.caseNo is
'案号';

comment on column t_zhefawang_exposurequery.executReason is
'执行案由';

comment on column t_zhefawang_exposurequery.court is
'执行法院';

comment on column t_zhefawang_exposurequery.amountNotExecuted is
'未执行金额';

comment on column t_zhefawang_exposurequery.caseDate is
'立案日期';

comment on column t_zhefawang_exposurequery.targetAmount is
'标的金额';

comment on column t_zhefawang_exposurequery.creditDate is
'曝光日期';


/*==============================================================*/
/* Table: t_zhefawang_executecasequery                          */
/*==============================================================*/
create table t_zhefawang_executecasequery (
   id                   INT8                 not null,
   caseNo               VARCHAR(100)         null,
   court                VARCHAR(255)         null,
   caseState            VARCHAR(255)         null,
   caseDate             VARCHAR(50)          null,
   principal            VARCHAR(255)         null,
   constraint PK_T_ZHEFAWANG_EXECUTECASEQUER primary key (id)
);

comment on column t_zhefawang_executecasequery.caseNo is
'案号';

comment on column t_zhefawang_executecasequery.court is
'法院';

comment on column t_zhefawang_executecasequery.caseState is
'案件状态';

comment on column t_zhefawang_executecasequery.caseDate is
'立案日期';

comment on column t_zhefawang_executecasequery.principal is
'当事人';