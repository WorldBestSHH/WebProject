package model.pds;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class PdsDAO {
	//싱글톤
	private PdsDAO() {}
	private static PdsDAO instance=new PdsDAO(); 
	public static PdsDAO getInstance() {
		return instance;
	}

	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	public int pdsCount() {
		int row=0;
		String sql="select count(*) from tbl_pds";
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
	
	public int pdsCount(String search, String key) {
		int row=0;
		String sql="select count(*) from tbl_pds where "+search+" like ?";
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
	
	public int pdsWrite(PdsDTO dto) {
		int row=0;
		String sql="insert into tbl_pds(idx,name,email,subject,contents,pass,filename)"
				+" values(tbl_pds_seq_idx.nextval,?,?,?,?,?,?)";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContents());
			pstmt.setString(5, dto.getPass());
			pstmt.setString(6, dto.getFilename());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	public List<PdsDTO> pdsList(){
		List<PdsDTO> list=new ArrayList<>();
		String sql="select * from tbl_pds order by idx desc";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PdsDTO dto=new PdsDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setFilename(rs.getString("filename"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	public List<PdsDTO> pdsList(String search, String key){
		List<PdsDTO> list=new ArrayList<>();
		String sql="select * from tbl_pds where "+search+" like ? order by idx desc";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PdsDTO dto=new PdsDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setFilename(rs.getString("filename"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	public List<PdsDTO> pdsList(int pagestart, int endpage){
		List<PdsDTO> list=new ArrayList<>();
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_pds order by idx desc) A\r\n"
				+ "                where rownum <=?) X where X.rm >=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, pagestart);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PdsDTO dto=new PdsDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setFilename(rs.getString("filename"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	public List<PdsDTO> pdsList(String search, String key, int pagestart, int endpage){
		List<PdsDTO> list=new ArrayList<>();
		String sql="select X.* from\r\n"
				+ "	    (select rownum as rm, A.* from\r\n"
				+ "            (select * from tbl_pds order by idx desc) A\r\n"
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
				PdsDTO dto=new PdsDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setFilename(rs.getString("filename"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}
	
	public void readcntUpdate(int idx) {
		String sql="update tbl_pds set readcnt=readcnt+1 where idx=?";
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
	
	public PdsDTO pdsView(int idx) {
		PdsDTO dto=new PdsDTO();
		String sql="select * from tbl_pds where idx=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setFilename(rs.getString("filename"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public int pdsDelete(int idx, String pass) {
		int row=0;
		String saveDir="Pds/upload/";
		String sql="";
		try {
			conn=DBManager.getConnection();
			sql="select filename from tbl_pds where idx=? and pass=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String filename=rs.getString("filename");
				File a=new File(saveDir+filename);
				System.out.println(saveDir+filename);
				if(a.isFile()) {
					System.out.println(saveDir+filename);
					a.delete();
				}
			}
			sql="delete from tbl_pds where idx=? and pass=?";
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
	
	public int pdsUpdate(PdsDTO dto) {
		int row=0;
		String sql="update tbl_pds set email=?, subject=?, contents=?, filename=? where idx=? and pass=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			pstmt.setString(4, dto.getFilename());
			pstmt.setInt(5, dto.getIdx());
			pstmt.setString(6, dto.getPass());
			row=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//파일이름 검색
	public String searchFile(int idx) {
		String sql="select filename from tbl_pds where idx=?";
		String filename=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				filename=rs.getString("filename");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return filename;
	}
}
