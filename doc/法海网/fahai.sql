drop table IF EXISTS t_fahai CASCADE;

/*==============================================================*/
/* Table: t_fahai                                               */
/*==============================================================*/
create table t_fahai (
   id                   INT8                 not null,
   title                VARCHAR(255)         null,
   content              TEXT                 null,
   contentHtml          TEXT                 null,
   linkUrl              TEXT                 null,
   constraint PK_T_FAHAI primary key (id)
);

comment on column t_fahai.title is
'标题';

comment on column t_fahai.content is
'内容(纯文本)';

comment on column t_fahai.contentHtml is
'内容(html格式)';

comment on column t_fahai.linkUrl is
'结果链接';
