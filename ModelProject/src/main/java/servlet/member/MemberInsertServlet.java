package servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;
import model.util.UserSHA256;

/**
 * Servlet implementation class Member_Write
 */
@WebServlet("/Member/userinfo_insert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/User/user_insert.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao=MemberDAO.getInstance();
		MemberDTO dto=new MemberDTO();
		
		String pass=UserSHA256.getSHA256(request.getParameter("passwd"));
		String job=request.getParameter("job");
		String fa[]=request.getParameterValues("fa");
		
		if(job=="0") {
			job="";
		}
		
		String favorite="";
		if(fa!=null) {
			favorite=fa[0];
			for(int i=1;i<fa.length;i++) {
				favorite=favorite+","+fa[i];
			}
		}
		
		dto.setName(request.getParameter("name"));
		dto.setUserid(request.getParameter("userid"));
		dto.setPasswd(pass);
		dto.setGubun(request.getParameter("gubun"));
		dto.setZip(request.getParameter("zip"));
		dto.setAddr1(request.getParameter("addr1"));
		dto.setAddr2(request.getParameter("addr2"));
		dto.setTel(request.getParameter("tel"));
		dto.setEmail(request.getParameter("email"));
		dto.setIntro(request.getParameter("intro"));
		dto.setJob(job);
		dto.setFavorite(favorite);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		int row=dao.memberInsert(dto);
		if(row==1) {
			response.sendRedirect("/Member/userinfo_list.do");
		}else {
			out.print("<script>");
			out.print("alert('등록에 실패하였습니다');");
			out.print("history.back()");
			out.print("</script>");
		}
	}

}
