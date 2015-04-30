package com.yiting.push.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/4/22.
 */
public class AbstractBaseDao {
	@Resource
	protected JdbcTemplate nettyJdbcTemplate;
	@Resource
	protected SimpleJdbcTemplate nettySimpleJdbcTemplate;
	@Resource
	protected NamedParameterJdbcTemplate nettyNamedParameterJdbcTemplate;
	@Resource
	protected SimpleJdbcCall nettySimpleJdbcCall;

	public JdbcTemplate getJdbcTemplate() {
		return nettyJdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate nettyJdbcTemplate) {
		this.nettyJdbcTemplate = nettyJdbcTemplate;
	}

}
