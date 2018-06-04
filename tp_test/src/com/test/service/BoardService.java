package com.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.BoardDao;
import com.test.domain.Board;

@Service
public class BoardService {
	//@Resource 이름(id)기반으로 빈을 가져와서
	@Autowired //타입을 기준으로 빈을 가져와서 의존 관계 주입함
	private BoardDao boardDao;


	public void add(Board board) {
		// 글번호num 구하기
		Integer maxNum = boardDao.getMaxNum();
		if (maxNum == null) {
			board.setNum(1);
		} else {
			board.setNum(maxNum + 1);
		}

		board.setRe_ref(board.getNum());
		board.setRe_lev(0);
		board.setRe_seq(0);
		board.setReadcount(0);

		boardDao.add(board);
	}

	public int getBoardCount() {
		return boardDao.count();
	}

	public List<Board> getBoardList(int startRow, int endRow) {
		return boardDao.getBoardList(startRow, endRow);
	}

	public Board getBoard(int num) {
		return boardDao.getBoard(num);
	}

	public void updateReadcount(int num) {
		boardDao.updateReadcount(num);
	}

	public int updateBoard(Board board) {
		Board boardDb = boardDao.getBoard(board.getNum());
		// 패스워드비교 맞으면 수정. check=1
		// update num에 해당하는 name,subject,content 수정
		// 패스워드비교 틀리면 check=0
		int check = 0;
		if (board.getPasswd().equals(boardDb.getPasswd())) {
			boardDao.updateBoard(board);
			check = 1;
		} else {
			check = 0;
		}
		return check;
	}

	public int deleteBoard(int num, String passwd) {
		Board boardDb = boardDao.getBoard(num);
		// 패스워드비교 맞으면 check=1 글삭제
		// 패스워드비교 틀리면 check=0
		int check = 0;
		if (passwd.equals(boardDb.getPasswd())) {
			boardDao.deleteBoard(num);
			check = 1;
		} else {
			check = 0;
		}
		return check;
	}

	public void reInsertBoard(Board board) {
		// 같은글그룹 내에서 기존답변글 순서 재배치. update re_seq
		boardDao.updateReSeq(board.getRe_ref(), board.getRe_seq());
		// 글번호num 구하기
		Integer maxNum = boardDao.getMaxNum();
		if (maxNum == null) {
			board.setNum(1);
		} else {
			board.setNum(maxNum + 1);
		}
		// 나머지 답글 정보 셋팅
		board.setRe_ref(board.getRe_ref());
		board.setRe_lev(board.getRe_lev() + 1);
		board.setRe_seq(board.getRe_seq() + 1);
		board.setReadcount(0);
		// 답글 insert
		boardDao.add(board);
	}
}
