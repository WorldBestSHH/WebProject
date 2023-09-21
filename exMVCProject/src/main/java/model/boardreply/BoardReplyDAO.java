package model.boardreply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.board.BoardDTO;
import model.util.DBManager;

public class BoardReplyDAO {
	private BoardReplyDAO() {}
	private static BoardReplyDAO instance=new BoardReplyDAO();
	public static BoardReplyDAO getinstance() {
		return instance;
	}
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	//글등록
	public int boardWrite(BoardReplyDTO dto) {
		int parent=dto.getParent();
		int indent=dto.getIndent();
		int depth=dto.getDepth();
		int idx=0;
		int row=0;
		String sql="select tbl_boardreply_seq_idx.nextval from dual";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				idx=rs.getInt(1);
			}
			if(parent!=0) { //답변글일 경우
				sql="update tbl_boardreply set depth=depth+1 where parent=? and depth>?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, parent);
				pstmt.setInt(2, depth);
				pstmt.executeUpdate();
				dto.setDepth(depth+1);
				dto.setIndent(indent+1);				
			}else { //새 글일 경우
				dto.setParent(idx);
				depth=0;
				indent=0;
			}
			dto.setIdx(idx);
			sql="insert into tbl_boardreply(idx,name,email,subject,contents,pass,"
					+ "parent,realparent,depth,indent) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIdx());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContents());
			pstmt.setString(6, dto.getPass());
			pstmt.setInt(7, dto.getParent());
			pstmt.setInt(8, dto.getRealparent());
			pstmt.setInt(9, dto.getDepth());
			pstmt.setInt(10, dto.getIndent());
			row=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//게시판 글 수
	public int boardCount() {
		int row=0;
		String sql="select count(*) from tbl_boardreply";
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
	
	//검색 게시판 글 수
	public int boardCount(String search, String key) {
		int row=0;
		String sql="select count(*) from tbl_boardreply where "+search+" like ?";
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
	public List<BoardReplyDTO> boardList(){
		List<BoardReplyDTO> list=new ArrayList<>();
		String sql="select * from tbl_boardreply order by parent desc, depth asc";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardReplyDTO dto=new BoardReplyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setIndent(rs.getInt("indent"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//검색 게시글 검색
	public List<BoardReplyDTO> boardList(String search, String key){
		List<BoardReplyDTO> list=new ArrayList<>();
		String sql="select * from tbl_boardreply where "+search+" like ? order by parent desc, depth asc";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardReplyDTO dto=new BoardReplyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setIndent(rs.getInt("indent"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//페이징 처리
	public List<BoardReplyDTO> boardList(int pagestart, int endpage){
		List<BoardReplyDTO> list=new ArrayList<>();
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_boardreply order by parent desc, depth asc) A\r\n"
				+ "                where rownum <=?) X where X.rm >=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, pagestart);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardReplyDTO dto=new BoardReplyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setIndent(rs.getInt("indent"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//페이징 처리+검색기능
	public List<BoardReplyDTO> boardList(String search, String key, int pagestart, int endpage){
		List<BoardReplyDTO> list=new ArrayList<>();
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_boardreply order by parent desc, depth asc) A\r\n"
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
				BoardReplyDTO dto=new BoardReplyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setIndent(rs.getInt("indent"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	//조회수 업데이트
	public void readcntUpdate(int idx) {
		String sql="update tbl_boardreply set readcnt=readcnt+1 where idx=?";
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
	
	//view
	public BoardReplyDTO boardSelect(int idx){
		BoardReplyDTO dto=new BoardReplyDTO();
		String sql="select * from tbl_boardreply where idx=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setParent(rs.getInt("parent"));
				dto.setRealparent(rs.getInt("realparent"));
				dto.setDepth(rs.getInt("depth"));
				dto.setIndent(rs.getInt("indent"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	//게시글 수정
	public int boardModify(BoardReplyDTO dto) {
		int row=0;
		String sql="update tbl_boardreply set email=?, subject=?, contents=? where idx=? and pass=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			pstmt.setInt(4, dto.getIdx());
			pstmt.setString(5, dto.getPass());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	// 답변글 유무 체크 
	public int replySearch(int idx) {
		int row=0;
		String query = "select count(realparent) as num from tbl_boardreply where realparent = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}

	// 특정 글 삭제 처리
	public int boardDelete(int idx, String pass) {
		int row=0;
		String query="delete from tbl_boardreply where idx = ? and pass = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
}
