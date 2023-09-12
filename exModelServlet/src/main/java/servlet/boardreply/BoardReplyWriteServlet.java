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
 * Servlet implementation class BoardReplyWriteServlet
 */
@WebServlet("/BoardReply/board_write.do")
public class BoardReplyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage=Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BoardReplyDTO dto=new BoardReplyDTO();
		BoardReplyDAO dao=BoardReplyDAO.getinstance();
		
		int nowpage=Integer.parseInt(request.getParameter("page"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		dto.setPass(request.getParameter("pass"));
		dto.setParent(Integer.parseInt(request.getParameter("parent")));
		dto.setRealparent(Integer.parseInt(request.getParameter("realparent")));
		dto.setDepth(Integer.parseInt(request.getParameter("depth")));
		dto.setIndent(Integer.parseInt(request.getParameter("indent")));
		
		int row=dao.boardWrite(dto);
		request.setAttribute("row", row);
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_write_pro.jsp");
		rd.forward(request, response);
	}

}
