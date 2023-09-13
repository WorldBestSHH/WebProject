package servlet.pds;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.pds.PdsDAO;
import model.pds.PdsDTO;

/**
 * Servlet implementation class PdsModifyServlet
 */
@WebServlet("/Pds/pds_modify.do")
public class PdsModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdsModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		PdsDAO dao=PdsDAO.getInstance();
		PdsDTO dto=dao.pdsView(idx);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("dto", dto);
		
		RequestDispatcher rd=request.getRequestDispatcher("pds_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ServletContext context=getServletContext();
		String path=context.getRealPath("Pds/upload/");
		String encType="UTF-8";
		int sizeLimit=2*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,encType, new DefaultFileRenamePolicy());
		
		String nowpage=multi.getParameter("page");
		int idx = Integer.parseInt(multi.getParameter("idx"));
		String name = multi.getParameter("name");
		String email = multi.getParameter("email");
		String pass = multi.getParameter("pass");
		String subject = multi.getParameter("subject");
		String contents = multi.getParameter("contents");
		String filename1 = multi.getParameter("filename1");
		String filename = multi.getFilesystemName("filename");
		
		PdsDTO dto=new PdsDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPass(pass);
		dto.setSubject(subject);
		dto.setContents(contents);
		dto.setFilename(filename);
		
		if(filename==null) { //예전 파일 이용시
			dto.setFilename(filename1);
		}else { //새 파일로 변경(구파일삭제)
			File a1=new File(path+filename1);
			if(a1.exists())
				a1.delete();
		}
		
		PdsDAO dao=PdsDAO.getInstance();
		int row=dao.pdsUpdate(dto);
		
		request.setAttribute("row", row);
		request.setAttribute("page", nowpage);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('수정되었습니다');");
			out.print("location.replace('/Pds/pds_list.do?page="+nowpage+"');");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('비밀번호가 맞지 않습니다');");
			out.print("history.back()");
			out.print("</script>");
		}
	}

}
