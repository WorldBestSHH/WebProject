package model.boardeditor;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class BoardEditorDAO {
	//싱글톤
	private BoardEditorDAO() {}
	private static BoardEditorDAO instance=new BoardEditorDAO(); //자신 객체 생성
	public static BoardEditorDAO getInstance() {
		return instance;
	}
	//DB관련
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	//메소드 정의
	//게시판 글 총 수 계산
	public int boardCount() {
		//리턴타입
		int row=0;
		//쿼리
		String sql="select count(*) from tbl_boardeditor";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				row=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	
	//검색 게시판 글 수 계산
	public int boardCount(String search, String key) {
		//리턴타입
		int row=0;
		//쿼리
		String sql="select count(*) from tbl_boardeditor where "+search+" like ?";
		//String sql="select count(*) from tbl_board where "+search+" like '%"+key+"%'"; ?사용 안하는 방법
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				row=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}
	
	//전체 게시글 검색(전체 목록)
	public List<BoardEditorDTO> boardList(){
		List<BoardEditorDTO> list=new ArrayList<>();
		String sql="select * from tbl_boardeditor order by idx desc";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardEditorDTO dto=new BoardEditorDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//검색 게시글 검색
	public List<BoardEditorDTO> boardList(String search, String key){
		List<BoardEditorDTO> list=new ArrayList<>();
		String sql="select * from tbl_boardeditor where "+search+" like ? order by idx desc";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardEditorDTO dto=new BoardEditorDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setContents(rs.getString("contents"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//페이징 처리
	public List<BoardEditorDTO> boardList(int pagestart, int endpage){
		List<BoardEditorDTO> list=new ArrayList<>();
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_boardeditor order by idx desc) A\r\n"
				+ "                where rownum <=?) X where X.rm >=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, pagestart);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardEditorDTO dto=new BoardEditorDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setContents(rs.getString("contents"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//페이징 처리+검색기능
	public List<BoardEditorDTO> boardList(String search, String key, int pagestart, int endpage){
		List<BoardEditorDTO> list=new ArrayList<>();
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_boardeditor order by idx desc) A\r\n"
				+ "                where "+search+" like ? and rownum <=?) X where "+search+" like ? and X.rm >=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%"+key+"%");
			pstmt.setInt(4, pagestart);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardEditorDTO dto=new BoardEditorDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setContents(rs.getString("contents"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//조회수 업테이트
	public void readcntUpdate(int idx) {
		String sql="update tbl_boardeditor set readcnt=readcnt+1 where idx=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//특정 게시글 검색
	public BoardEditorDTO boardView(int idx){
		BoardEditorDTO dto=new BoardEditorDTO();
		String sql="select * from tbl_boardeditor where idx=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setContents(rs.getString("contents"));
				dto.setPass(rs.getString("pass"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return dto;
	}
	
	//게시글 등록
	public int boardWrite(BoardEditorDTO dto) {
		int row=0;
		String sql="insert into tbl_boardeditor(idx,name,subject,contents,pass)"
				+"values(tbl_board_seq_idx.nextval,?,?,?,?)";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			StringReader sr=new StringReader(dto.getContents());
			pstmt.setCharacterStream(3, sr, dto.getContents().length());
			pstmt.setString(4, dto.getPass());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//게시글 수정
	public int boardModify(BoardEditorDTO dto) {
		int row=0;
		String sql="update tbl_boardeditor set subject=?, contents=? where idx=? and pass=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			StringReader sr=new StringReader(dto.getContents());
			pstmt.setCharacterStream(2, sr, dto.getContents().length());
			pstmt.setInt(3, dto.getIdx());
			pstmt.setString(4, dto.getPass());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//게시글 삭제
	public int boardDelete(int idx, String pass) {
		int row=0;
		String sql="delete from tbl_boardeditor where idx=? and pass=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
}
