package model.guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuestDAO {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
/*	public static Connection getConnection(){
		//Connection conn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","track2_12","1234");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
*/	
	private static Connection getConnection(){
		Connection conn=null;
		try {
			Context initContext=new InitialContext();
			Context envContext=(Context)initContext.lookup("java:/comp/env");
			//"jdbc/myoracle"이름의 객체를 찾아서 DataSource에 대입
			DataSource ds=(DataSource)envContext.lookup("jdbc/myoracle");
			
			conn=ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	//메소드 정의
	public void dbTest() {
		Connection conn=getConnection();
		System.out.println("conn:"+conn);
	}
	
	//전체 게시글 카운트 메소드
	public int guestCount() {
		//Connection conn=null;
		//PreparedStatement pstmt=null;
		//ResultSet rs=null;
		int row=0; //리턴타입
		String sql="select count(*) from tbl_guest";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				row=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//검색 게시글 카운트 메소드
	public int guestCount(String search, String key) {
		int row=0; //리턴타입
		//String sql="select count(*) from tbl_guest where ? like ?"; 필드명은 ?를 사용할 수 없음
		String sql="select count(*) from tbl_guest where "+search+" like ?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				row=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//전체 게시글 검색 반환(전체목록)
	public List<GuestDTO> guestList() {
		List<GuestDTO> list=new ArrayList<>(); //리턴타입
		String sql="select * from tbl_guest order by idx desc";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//전체 게시글 검색 반환(전체목록) - 페이징 처리
	public List<GuestDTO> guestList(int pagestart, int endpage) {
		List<GuestDTO> list=new ArrayList<>(); //리턴타입
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_guest order by idx desc) A\r\n"
				+ "                where rownum <=?) X where X.rm >=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, pagestart);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//전체 게시글 검색 반환(전체목록) - 검색기능+페이징 처리
	public List<GuestDTO> guestList(String search, String key, int pagestart, int endpage) {
		List<GuestDTO> list=new ArrayList<>(); //리턴타입
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_guest order by idx desc) A\r\n"
				+ "                where "+search+" like ? and rownum <=?) X where "+search+" like ? and X.rm >=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%"+key+"%");
			pstmt.setInt(4, pagestart);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//검색 게시글 검색 반환
	public List<GuestDTO> guestList(String search, String key) {
		List<GuestDTO> list=new ArrayList<>(); //리턴타입
		String sql="select * from tbl_guest where "+search+" like ? order by idx desc";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setContents(rs.getString("contents"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//조회수 증가 메소드
	public void guestHits(int idx) {
		String sql="update tbl_guest set readcnt=readcnt+1 where idx=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//특정게시글 검색
	public GuestDTO guestSelect(int idx) {
		GuestDTO dto=new GuestDTO(); //리턴타입
		String sql="select * from tbl_guest where idx=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	//게시글 등록
	public int guestWrite(GuestDTO dto) {
		int row=0; //리턴타입
		String sql="insert into tbl_guest(idx,name,subject,contents) "
				+"values(tbl_guest_seq_idx.nextval,?,?,?)";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//게시글 수정
	public int guestModify(GuestDTO dto) {
		int row=0; //리턴타입
		String sql="update tbl_guest set subject=?, contents=? where idx=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContents());
			pstmt.setInt(3, dto.getIdx());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//게시글 삭제
	public int guestDelete(GuestDTO dto) {
		int row=0; //리턴타입
		String sql="delete from tbl_guest where idx=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIdx());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
