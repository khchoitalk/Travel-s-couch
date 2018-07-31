package board.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import board.exception.BoardException;
import board.model.vo.Board;

public class BoardDao {
	public BoardDao(){}

	public int getListCount(Connection con) 
		throws BoardException {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from board";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
				listCount = rset.getInt(1);
			}else{
				throw new BoardException("게시글이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<Board> selectList(
			Connection con, int currentPage, 
			int limit) throws BoardException {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, board_num, "
				+ "board_title, board_writer, board_content, "
				+ "board_original_filename, board_rename_filename, "
				+ "board_date, board_readcount, board_ref, "
				+ "board_level, board_reply_ref, board_reply_seq "
				+ "from (select * from board "
				+ "order by board_ref desc, board_reply_ref desc, "
				+ "board_level asc, board_reply_seq asc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Board b = new Board();
				b.setBoardNum(rset.getInt("board_num"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardReadCount(rset.getInt("board_readcount"));
				b.setBoardOriginalFileName(rset.getString("board_original_filename"));
				b.setBoardRenameFileName(rset.getString("board_rename_filename"));
				b.setBoardLevel(rset.getInt("board_level"));
				b.setBoardRef(rset.getInt("board_ref"));
				b.setBoardReplyRef(rset.getInt("board_reply_ref"));
				b.setBoardReplySeq(rset.getInt("board_reply_seq"));
				
				list.add(b);
			}
			
			if(list.size() == 0)
				throw new BoardException(
						"게시글이 존재하지 않습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int addReadCount(Connection con, 
			int boardNum) throws BoardException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board "
				+ "set board_readcount = board_readcount + 1 "
				+ "where board_num = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new BoardException(
						boardNum + "번 게시글 조회수 증가 처리 실패!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public Board selectBoard(Connection con, 
			int boardNum) throws BoardException {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from board "
				+ "where board_num = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				board = new Board();
				
				board.setBoardNum(boardNum);
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardLevel(rset.getInt("board_level"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));				
			}else{
				throw new BoardException(
						boardNum + "번글 조회 실패!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	//원글 등록용 메소드
	public int insertBoard(Connection con, 
			Board board) throws BoardException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into board values "
				+ "((select max(board_num) + 1 from board), "
				+ "?, ?, ?, ?, ?, sysdate, 0, "
				+ "(select max(board_num) + 1 from board), "
				+ "null, default, default)";	
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardWriter());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getBoardOriginalFileName());
			pstmt.setString(5, board.getBoardRenameFileName());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new BoardException("새 원글 등록 실패!");			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(pstmt);
		}		
		
		return result;
	}

	public int deleteBoard(Connection con, 
			int boardNum) throws BoardException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from board "
				+ "where board_num = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new BoardException("게시글 삭제 실패!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection con, Board originBoard, Board replyBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateBoard(Connection con, Board board) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateReply(Connection con, Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateReplySeq(Connection con, Board replyBoard) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}










