package servlet.boardreply;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardreply.BoardReplyDAO;
import model.boardreply.BoardReplyDTO;

/**
 * Servlet implementation class BoardReplyModifyServlet
 */
@WebServlet("/BoardReply/board_modify.do")
public class BoardReplyModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		BoardReplyDAO dao=BoardReplyDAO.getinstance();
		BoardReplyDTO dto=dao.boardSelect(idx);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("dto", dto);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BoardReplyDAO dao=BoardReplyDAO.getinstance();
		BoardReplyDTO dto=new BoardReplyDTO();
		
		int nowpage=Integer.parseInt(request.getParameter("page"));
		dto.setIdx(Integer.parseInt(request.getParameter("idx")));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		dto.setPass(request.getParameter("pass"));
		
		int row=dao.boardModify(dto);
		request.setAttribute("row", row);
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_modify_pro.jsp");
		rd.forward(request, response);
	}

}
