package com.test.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.test.domain.Member;

@Repository
public class MemberProcDao {

	private SimpleJdbcCall simpleJdbcCall;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}
	
	public void add(Member member) {
		SimpleJdbcCall call = this.simpleJdbcCall.withProcedureName("pro_member_insert");
		int count = call.executeObject(Integer.class, new BeanPropertySqlParameterSource(member));
		
	}
	
	public int update (Member member) {
		SimpleJdbcCall call = this.simpleJdbcCall.withProcedureName("pro_member_update");
		int count = call.executeObject(Integer.class, new BeanPropertySqlParameterSource(member));
		
		return count;
	}
	
	public int deleteMember(String id, String passwd) {
		SimpleJdbcCall call = this.simpleJdbcCall.withProcedureName("pro_member_delete");
		int count = call.executeObject(Integer.class, id,passwd);
		return count;
	}
	
	public Member getMember(String id) {
		SimpleJdbcCall call = this.simpleJdbcCall.withProcedureName("pro_member_findById");
		Map<String, Object> map= call.execute(id);
		
		int count =(Integer)map.get("p_count");
		if(count == 0) {
			return null;
		}
		
		Member member = new Member();
		member.setId((String)map.get("p_id"));
		member.setPasswd((String)map.get("p_passwd"));
		member.setName((String)map.get("p_name"));
		member.setReg_date((Timestamp)map.get("p_reg_date"));
		member.setAge((Integer)map.get("p_age"));
		member.setGender((String)map.get("p_gender"));
		member.setEmail((String)map.get("p_email"));
		
		return member;
	}
	
	public List<Member> getMembers() {
		SimpleJdbcCall call = this.simpleJdbcCall
				.withProcedureName("pro_member_findAll")
				.returningResultSet("members", 
						new BeanPropertyRowMapper<>(Member.class));
		Map<String, Object> map = call.execute();
		List<Member> list= (List)map.get("members");
		return list;
	}
}
