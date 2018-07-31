package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.exception.BoardException;
import board.model.dao.BoardDao;
import board.model.vo.Board;

import static common.JDBCTemplate.*;

public class BoardService {
	public BoardService() {
	}

	public int getListCount() throws BoardException {
		Connection con = getConnection();
		int listCount = new BoardDao().getListCount(con);
		close(con);
		return listCount;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) throws BoardException {
		Connection con = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}

	public void addReadCount(int boardNum) throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().addReadCount(con, boardNum);
		if (result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
	}

	public Board selectBoard(int boardNum) throws BoardException {
		Connection con = getConnection();
		Board board = new BoardDao().selectBoard(con, boardNum);
		close(con);
		return board;
	}

	public int insertBoard(Board board) 
			throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().insertBoard(con, board);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	//새 댓글 등록시 
	//기존 댓글들의 board_reply_seq (댓글순번) 의 값을
	//1증가 처리하는 메소드
	public void updateReplySeq(Board replyBoard) 
		throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().updateReplySeq(
				con, replyBoard);
		if(result > 0)
			commit(con);
		else
			rollback(con);
	}
	
	public int insertReply(Board originBoard, 
						Board replyBoard) 
			throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().insertReply(
				con, originBoard, replyBoard);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int updateBoard(Board board) 
			throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().updateBoard(
				con, board);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int updateReply(Board board) 
			throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().updateReply(con, board);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int deleteBoard(int boardNum) 
			throws BoardException {
		Connection con = getConnection();
		int result = new BoardDao().deleteBoard(
				con, boardNum);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

}
