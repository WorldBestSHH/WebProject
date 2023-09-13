package servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberDTO;
import model.util.UserSHA256;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/Member/userinfo_login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/User/user_login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao=MemberDAO.getInstance();
		
		String userid=request.getParameter("userid");
		String passwd=UserSHA256.getSHA256(request.getParameter("passwd"));
		
		int row=dao.memberLogin(userid, passwd);
		
		if(row==1) { //로그인 성공
			MemberDTO dto=dao.memberSelect(userid);
			HttpSession session=request.getSession(); //세션객체 생성 => 브라우저가 꺼지기 전까지 세션이 살아 있음
			//session.setAttribute("user", userid); //request는 전 단계만 
			session.setAttribute("user", dto);
			session.setMaxInactiveInterval(1800); //30분
		}
		
		request.setAttribute("row", row);
		
		RequestDispatcher rd=request.getRequestDispatcher("userlogin_ok.jsp");
		rd.forward(request, response);
	}

}
