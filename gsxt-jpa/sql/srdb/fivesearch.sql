drop table IF EXISTS t_fivesearch CASCADE;

/*==============================================================*/
/* Table: t_fivesearch                                          */
/*==============================================================*/
create table t_fivesearch (
   id                   INT8                 not null,
   title                VARCHAR(255)         null,
   baiduContent         TEXT                 null,
   baiduUrlLink         TEXT                 null,
   bingContent          TEXT                 null,
   bingUrlLik           TEXT                 null,
   haosouContent        TEXT                 null,
   haosouUrlLink        TEXT                 null,
   sougouContent        TEXT                 null,
   sougouUrlLink        TEXT                 null,
   yahooContent         TEXT                 null,
   yahooUrlLink         TEXT                 null,
   constraint PK_T_FIVESEARCH primary key (id)
);

comment on column t_fivesearch.title is
'搜索标题';

comment on column t_fivesearch.baiduContent is
'百度搜索结果';

comment on column t_fivesearch.baiduUrlLink is
'百度搜索链接';

comment on column t_fivesearch.bingContent is
'bing搜索结果';

comment on column t_fivesearch.bingUrlLik is
'bing搜索链接';

comment on column t_fivesearch.haosouContent is
'好搜搜索结果';

comment on column t_fivesearch.haosouUrlLink is
'好搜搜索链接';

comment on column t_fivesearch.sougouContent is
'搜狗搜索结果';

comment on column t_fivesearch.sougouUrlLink is
'搜狗搜索链接';

comment on column t_fivesearch.yahooContent is
'雅虎搜索结果';

comment on column t_fivesearch.yahooUrlLink is
'雅虎搜索链接';
