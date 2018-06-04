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

import com.test.domain.Board;

//@Repository("bDao")
@Repository
public class BoardDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("board");
	}

	public Integer getMaxNum() {
		try {
			Integer maxNum = jdbcTemplate.queryForObject("SELECT MAX(num) FROM board", Integer.class);
			return maxNum;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int count() {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM board", Integer.class);
		return count;
	}

	public void add(Board board) {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(board));
	} // add()의 끝

	public List<Board> getBoardList(int startRow, int endRow) {
		// sql
		// select * from board order by re_ref desc, re_seq asc
		StringBuilder sb = new StringBuilder();
		sb.append("select * ");
		sb.append("from (select rownum as rnum, a.* ");
		sb.append("        from (select * from board order by re_ref desc, re_seq asc) a ");
		sb.append("       where rownum <= ? ");
		sb.append("        ) a ");
		sb.append("where rnum >= ? ");

		List<Board> list = jdbcTemplate.query(sb.toString(),
				new BeanPropertyRowMapper<Board>(Board.class),
				endRow, startRow);
		return list;
	}
	
	public Board getBoard(int num) {
		try {
			Board b = jdbcTemplate.queryForObject("SELECT * FROM board WHERE num = ?"
					, new BeanPropertyRowMapper<Board>(Board.class), num);
			return b;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void updateReadcount(int num) {
		jdbcTemplate.update("UPDATE board SET readcount=readcount+1 WHERE num=?", num);
	}
	
	public int updateBoard(Board board) {
		int affected = namedParameterJdbcTemplate.update(
				"UPDATE board SET name = :name, subject = :subject, content = :content "
				+ " WHERE num = :num"
				, new BeanPropertySqlParameterSource(board));
		return affected;
	}
	
	public void deleteBoard(int num) {
		jdbcTemplate.update("DELETE FROM board WHERE num = ?", num);
	}
	
	public void updateReSeq(int re_ref, int re_seq) {
		jdbcTemplate.update("UPDATE board SET re_seq=re_seq+1 WHERE re_ref=? AND re_seq>?", re_ref, re_seq);
	}

}
