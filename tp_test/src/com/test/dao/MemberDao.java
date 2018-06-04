package com.test.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.test.domain.Member;

/*
 *  <context:component-scan base-package="com.test" />
 *  컴포넌트 스캐닝 방식
 *  
 * 	id = memberDao (클래스이름. 첫글자만 소문자)
 *  class = com.test.dao.MemberDao
 * */

//@Component
@Repository
public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("mymember");
	}
	
	public void add(Member member) {
		/*namedParameterJdbcTemplate.update("INSERT INTO mymember(id, passwd, name, reg_date, age, gender, email)"
				+ " VALUES(:id, :passwd, :name, :reg_date, :age, :gender, :email)"
				, new BeanPropertySqlParameterSource(member));	*/
		
		// SimpleJdbcInsert의 활용
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(member));
	} // add()의 끝
	
	public int update(Member member) {
		int affected = namedParameterJdbcTemplate.update(
				"UPDATE mymember SET name = :name, age = :age, gender = :gender, email = :email"
				+ " WHERE id = :id"
				, new BeanPropertySqlParameterSource(member));
		return affected;
	}
	
	public void delete(String id) {
		jdbcTemplate.update("DELETE FROM mymember WHERE id = ?", id);
	}
	
	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM mymember");
	}
	
	public Member get(String id) {
		try {
			Member m = jdbcTemplate.queryForObject("SELECT * FROM mymember WHERE id = ?"
					, new BeanPropertyRowMapper<Member>(Member.class), id);
			return m;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Member> search(String name) {
		List<Member> members = jdbcTemplate.query("SELECT * FROM mymember WHERE name LIKE ?"
				, new BeanPropertyRowMapper<Member>(Member.class), "%" + name + "%");
		return members;
	}
	
	public List<Member> getAll() {
		List<Member> members = jdbcTemplate.query("SELECT * FROM mymember ORDER BY name ASC"
				, new BeanPropertyRowMapper<Member>(Member.class));
		return members;
	}
	
	public long count() {
		long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM mymember", Long.class);
		return count;
	}
	
}
