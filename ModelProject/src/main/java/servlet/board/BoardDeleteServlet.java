package servlet.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/Board/board_delete.do")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("idx", idx);
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_delete.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		String pass=request.getParameter("pass");
		
		BoardDAO dao=BoardDAO.getInstance();
		int row=dao.boardDelete(idx,pass);
		
/*		RequestDispatcher rd=request.getRequestDispatcher("board_delete_pro.jsp");
		rd.forward(request, response);
*/
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('삭제되었습니다');");
			out.print("window.opener.location.href='/Board/board_list.do?page="+nowpage+"';");
			out.print("self.close();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('비밀번호가 맞지 않습니다');");
			out.print("history.back()");
			out.print("</script>");
		}
	}

}
