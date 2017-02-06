
/*=====================================
 * 	工商系统 company表 
 * 		  ownertask_gsxt 触发器
 * ===================================*/
create or replace function tg_company() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_gsxt set completetaskdate=now(), state=NEW.state where company_id=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_company after UPDATE ON company for each row execute procedure tg_company();
comment on function tg_company() is '工商系统company表触发器：当company表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到 ownertask_gsxt表 的对应记录上，同时更新ownertask_gsxt表的completetaskdate字段为最新时间';



/*=====================================
 * 	法海网 f_root表 
 * 		ownertask_fahaicc 触发器
 * ===================================*/
create or replace function tg_f_root() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_fahaicc set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_f_root after UPDATE ON f_root for each row execute procedure tg_f_root();
comment on function tg_f_root() is '法海风控网f_root表触发器：当f_root表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到 ownertask_fahaicc表 的对应记录上，同时更新ownertask_fahaicc表的completetaskdate字段为最新时间';



/*=====================================
 * 	人法网 r_company_or_id 
 * 		ownertask_people_court 触发器
 * ===================================*/
create or replace function tg_r_company_or_id() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_people_court set completetaskdate=now(), state=NEW.state where tasktimeid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_r_company_or_id after UPDATE ON r_company_or_id for each row execute procedure tg_r_company_or_id();
comment on function tg_r_company_or_id() is '人法网r_company_or_id表触发器：当r_company_or_id表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到 ownertask_people_court表 的对应记录上，同时更新ownertask_people_court表的completetaskdate字段为最新时间';


/*=====================================
 * 	贷联盟 d_company_or_identity 
 * 		 ownertask_creditunion  触发器
 * ===================================*/
create or replace function tg_d_company_or_identity() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_creditunion set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_d_company_or_identity after UPDATE ON d_company_or_identity for each row execute procedure tg_d_company_or_identity();
comment on function tg_d_company_or_identity() is '贷联盟d_company_or_identity表触发器：当d_company_or_identity表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_creditunion表 的对应记录上，同时更新ownertask_creditunion表的completetaskdate字段为最新时间';




/*=====================================
 * 	失信网 s_dishonesty_keyword 
 * 		 ownertask_dishonesty  触发器
 * ===================================*/
create or replace function tg_s_dishonesty_keyword() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_dishonesty set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_s_dishonesty_keyword after UPDATE ON s_dishonesty_keyword for each row execute procedure tg_s_dishonesty_keyword();
comment on function tg_s_dishonesty_keyword() is '失信网s_dishonesty_keyword表触发器：当s_dishonesty_keyword表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_dishonesty表 的对应记录上，同时更新ownertask_dishonesty表的completetaskdate字段为最新时间';




/*=====================================
 * 	第一车网 c_iautos_keyword 
 * 		 ownertask_iautos  触发器
 * ===================================*/
create or replace function tg_c_iautos_keyword() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_iautos set completetaskdate=now(), state=NEW.state where tasktimeid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_c_iautos_keyword after UPDATE ON c_iautos_keyword for each row execute procedure tg_c_iautos_keyword();
comment on function tg_c_iautos_keyword() is '第一车网c_iautos_keyword表触发器：当c_iautos_keyword表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_iautos表 的对应记录上，同时更新ownertask_iautos表的completetaskdate字段为最新时间';




/*=====================================
 * 	对外贸易经营者备案登记系统  m_iecms_company 
 * 		 ownertask_iecms  触发器
 * ===================================*/
create or replace function tg_m_iecms_company() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_iecms set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_m_iecms_company after UPDATE ON m_iecms_company for each row execute procedure tg_m_iecms_company();
comment on function tg_m_iecms_company() is '对外贸易经营者备案登记系统m_iecms_company表触发器：当m_iecms_company表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_iecms表 的对应记录上，同时更新ownertask_iecms表的completetaskdate字段为最新时间';




/*=====================================
 * 	海关网  customs_company 
 * 		 ownertask_customs  触发器
 * ===================================*/
create or replace function tg_customs_company() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_customs set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_customs_company after UPDATE ON customs_company for each row execute procedure tg_customs_company();
comment on function tg_customs_company() is '海关网customs_company表触发器：当customs_company表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_customs表 的对应记录上，同时更新ownertask_customs表的completetaskdate字段为最新时间';




/*=====================================
 * 	专利网  l_sipo_keyword 
 * 		 ownertask_sipo  触发器
 * ===================================*/
create or replace function tg_l_sipo_keyword() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_sipo set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_l_sipo_keyword after UPDATE ON l_sipo_keyword for each row execute procedure tg_l_sipo_keyword();
comment on function tg_l_sipo_keyword() is '专利网l_sipo_keyword表触发器：当l_sipo_keyword表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_sipo表 的对应记录上，同时更新ownertask_sipo表的completetaskdate字段为最新时间';




/*=====================================
 * 	认证网  certificate_company 
 * 		 ownertask_authenticate  触发器
 * ===================================*/
create or replace function tg_certificate_company() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_authenticate set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_certificate_company after UPDATE ON certificate_company for each row execute procedure tg_certificate_company();
comment on function tg_certificate_company() is '认证网certificate_company表触发器：当certificate_company表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_authenticate表 的对应记录上，同时更新ownertask_authenticate表的completetaskdate字段为最新时间';



/*=====================================
 * 	浙法网  g_zjsfgkw_keyword 
 * 		 ownertask_zjcourt  触发器
 * ===================================*/
create or replace function tg_g_zjsfgkw_keyword() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_zjcourt set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_g_zjsfgkw_keyword after UPDATE ON g_zjsfgkw_keyword for each row execute procedure tg_g_zjsfgkw_keyword();
comment on function tg_g_zjsfgkw_keyword() is '浙法网g_zjsfgkw_keyword表触发器：当g_zjsfgkw_keyword表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_zjcourt表 的对应记录上，同时更新ownertask_zjcourt表的completetaskdate字段为最新时间';




/*=====================================
 *	11315征信系统  k_enterprise_credit_keyword 
 * 		 ownertask_enterpcredit  触发器
 * ===================================*/
create or replace function tg_k_enterprise_credit_keyword() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_enterpcredit set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_k_enterprise_credit_keyword after UPDATE ON k_enterprise_credit_keyword for each row execute procedure tg_k_enterprise_credit_keyword();
comment on function tg_k_enterprise_credit_keyword() is '11315征信系统k_enterprise_credit_keyword表触发器：当k_enterprise_credit_keyword表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_enterpcredit表 的对应记录上，同时更新ownertask_enterpcredit表的completetaskdate字段为最新时间';




/*=====================================
 * 	信用中国  credit_company 
 * 		 ownertask_creditchina  触发器
 * ===================================*/
create or replace function tg_credit_company() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_creditchina set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_credit_company after UPDATE ON credit_company for each row execute procedure tg_credit_company();
comment on function tg_credit_company() is '信用中国credit_company表触发器：当credit_company表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_creditchina表 的对应记录上，同时更新ownertask_creditchina表的completetaskdate字段为最新时间';



/*=====================================
 * 	失信记录平台  j_crqp_company 
 * 		 ownertask_crqp  触发器
 * ===================================*/
create or replace function tg_j_crqp_company() returns trigger as $$
	declare
	begin
		case TG_OP
			when 'UPDATE' then
				if (NEW.num>=3 or NEW.state not in(2,5,100004,100013,100014,100015,100017)) then
					update ownertask_crqp set completetaskdate=now(), state=NEW.state where timetaskid=OLD.id;
				end if;
			else
				return NULL;
		end case;
		return NULL;
	end;
$$ language plsrsql;
create trigger tg_j_crqp_company after UPDATE ON j_crqp_company for each row execute procedure tg_j_crqp_company();
comment on function tg_j_crqp_company() is '失信记录平台j_crqp_company表触发器：当j_crqp_company表的某条记录更新时，在一定的条件下，将其对应的state字段更新同步到ownertask_crqp表 的对应记录上，同时更新ownertask_crqp表的completetaskdate字段为最新时间';















