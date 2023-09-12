package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class MemberDAO {
	private MemberDAO() {}
	private static MemberDAO instance=new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public List<MemberDTO> memberList(){
		List<MemberDTO> list=new ArrayList<>();
		String sql="select * from tbl_member order by first_time";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto=new MemberDTO();
				dto.setName(rs.getString("name"));
				dto.setUserid(rs.getString("userid"));
				dto.setTel(rs.getString("tel"));
				dto.setFirst_time(rs.getString("first_time"));
				dto.setLast_time(rs.getString("last_time"));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<MemberDTO> memberList(String search, String key){
		List<MemberDTO> list=new ArrayList<>();
		String sql="select * from tbl_member where "+search+" like ? order by first_time";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			if(search.equals("tel")) {
				pstmt.setString(1, "%"+key);
			}else {
				pstmt.setString(1, "%"+key+"%");
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto=new MemberDTO();
				dto.setName(rs.getString("name"));
				dto.setUserid(rs.getString("userid"));
				dto.setTel(rs.getString("tel"));
				dto.setFirst_time(rs.getString("first_time"));
				dto.setLast_time(rs.getString("last_time"));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int useridSelect(String userid) {
		int count=0;
		String sql="select count(*) from tbl_member where userid=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public int memberInsert(MemberDTO dto) {
		int row=0;
		String sql="insert into tbl_member(name, userid, passwd, gubun,"
				+"zip, addr1, addr2, tel, email, favorite, job, intro) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getGubun());
			pstmt.setString(5, dto.getZip());
			pstmt.setString(6, dto.getAddr1());
			pstmt.setString(7, dto.getAddr2());
			pstmt.setString(8, dto.getTel());
			pstmt.setString(9, dto.getEmail());
			pstmt.setString(10, dto.getFavorite());
			pstmt.setString(11, dto.getJob());
			pstmt.setString(12, dto.getIntro());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	public MemberDTO memberSelect(String userid){
		MemberDTO dto=new MemberDTO();
		String sql="select * from tbl_member where userid=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setUserid(rs.getString("userid"));
				dto.setGubun(rs.getString("gubun"));
				dto.setZip(rs.getString("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setJob(rs.getString("job"));
				dto.setIntro(rs.getString("intro"));
				dto.setFavorite(rs.getString("favorite"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public int memberUpdate(MemberDTO dto) {
		int row=0;
		String sql="update tbl_member set passwd=?, gubun=?,"
				+"zip=?, addr1=?, addr2=?, tel=?, email=?, favorite=?, job=?, intro=? where userid=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getGubun());
			pstmt.setString(3, dto.getZip());
			pstmt.setString(4, dto.getAddr1());
			pstmt.setString(5, dto.getAddr2());
			pstmt.setString(6, dto.getTel());
			pstmt.setString(7, dto.getEmail());
			pstmt.setString(8, dto.getFavorite());
			pstmt.setString(9, dto.getJob());
			pstmt.setString(10, dto.getIntro());
			pstmt.setString(11, dto.getUserid());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
}
