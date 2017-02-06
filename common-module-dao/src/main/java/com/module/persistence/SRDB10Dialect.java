package com.module.persistence;

import org.hibernate.dialect.PostgreSQL82Dialect;

public class SRDB10Dialect extends PostgreSQL82Dialect {
	@Override
	public String getQuerySequencesString() {
		return "select relname from sr_class where relkind='S'";
	}
}
