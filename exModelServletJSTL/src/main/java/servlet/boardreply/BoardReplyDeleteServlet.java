package servlet.boardreply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardreply.BoardReplyDAO;

/**
 * Servlet implementation class BoardReplyDeleteServlet
 */
@WebServlet("/BoardReply/board_delete.do")
public class BoardReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyDeleteServlet() {
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
		
		BoardReplyDAO dao=BoardReplyDAO.getinstance();
		int reply=dao.replySearch(idx);
		int row=0;
		if(reply==0) {
			row=dao.boardDelete(idx,pass);
		}else {
			row=-1;
		}

		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_delete_pro.jsp");
		rd.forward(request, response);
	}

}
