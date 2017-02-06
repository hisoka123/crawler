comment on table public.c_iautos_keyword is '第一车网查询关键字信息表';
comment on column public.c_iautos_keyword.id is '主键';
comment on column public.c_iautos_keyword.city is '城市（全国即quanguo,其他城市为城市全拼如北京为beijing,上海为shanghai）';
comment on column public.c_iautos_keyword.name is '要查询的车的品牌或者型号名称';
comment on column public.c_iautos_keyword.num is '数据爬取次数';
comment on column public.c_iautos_keyword.priority is '数据爬取优先级别（数字越大，优先级别越高，目前支持的级别为 0 1）';
comment on column public.c_iautos_keyword.state is '数据爬取状态代';
comment on column public.c_iautos_keyword.total_num is '总爬取次数';

comment on table public.c_iautos_result is '第一车网查询结果信息表';
comment on column public.c_iautos_result.id is '主键';
comment on column public.c_iautos_result.certificate is '执行时间';
comment on column public.c_iautos_result.executetime is '要查询的车的品牌或者型号名称';
comment on column public.c_iautos_result.name is '车的品牌或者型号';
comment on column public.c_iautos_result.price is '价格';
comment on column public.c_iautos_result.profile_image is '图片地址';
comment on column public.c_iautos_result.timekm is '车程公里数';
comment on column public.c_iautos_result.url is '详细地址';
comment on column public.c_iautos_result.iautokeyword_id is '外键,来自c_iautos_keyword表的主';