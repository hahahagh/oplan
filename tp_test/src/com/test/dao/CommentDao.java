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
import org.springframework.stereotype.Repository;

import com.test.domain.Comment;
import com.test.domain.Restaurant;

@Repository
public class CommentDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("restaurant_comment");

	}
	
	public Integer getMaxNum() {
		try {
			Integer maxNum = jdbcTemplate.queryForObject("SELECT MAX(comment_num) FROM restaurant_comment", Integer.class);
			System.out.println("maxNum: "+maxNum);
			if(maxNum == null) {
				maxNum = 0;
			}
			System.out.println("maxNum: "+maxNum);
			return maxNum;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int count() {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM restaurant_comment", Integer.class);
	
		return count;
	}
	
	public void addComment(Comment comment) {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(comment));
	}
	
	public List<Comment> getCommentList(int restaurant_num){
		StringBuilder sb = new StringBuilder();
		sb.append("select * ");
		sb.append("from (select rownum as rnum, a.* ");
		sb.append("from (select * from restaurant_comment where restaurant_num = ? order by reg_date desc) a ");
		sb.append("where rownum <= ? ");
		sb.append(") a where rnum >= ? ");
		
		String sql = "select * from restaurant_comment where restaurant_num = ? order by reg_date desc";
		List<Comment> comments = jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<Comment>(Comment.class),restaurant_num);
		
		System.out.println("commentDao호출 getCommentList");
		
		return comments;
	}
}
