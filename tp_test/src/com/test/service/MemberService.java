package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.MemberDao;
import com.test.domain.Member;

@Service
@Transactional
public class MemberService {
	
	@Autowired //타입을 기준으로 빈을 가져와서 의존 관계 주입함
	private MemberDao memberDao;
	
	public void add(Member member) {
		memberDao.add(member);
	}
	
	public int userCheck(String id, String passwd) {
		Member member = memberDao.get(id);
		if (member != null) { // id 일치
			if (member.getPasswd().equals(passwd)) { // passwd 일치
				return Member.ID_OK_PASSWD_OK;
			} else { // passwd 불일치
				return Member.ID_OK_PASSWD_FAIL;
			}
		} else {
			return Member.ID_FAIL_PASSWD_FAIL;
		}
	}
	@Transactional(readOnly=true)
	public Member get(String id) {
		return memberDao.get(id);
	}
	
	public void update(Member member) {
		memberDao.update(member);
	}
	
	public void delete(String id) {
		memberDao.delete(id);
	}
	
	public List<Member> getAll() {
		return memberDao.getAll();
	}
	
}
