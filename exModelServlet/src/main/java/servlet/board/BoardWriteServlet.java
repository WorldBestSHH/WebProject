package servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardDTO;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/Board/board_write.do")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
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
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardDTO dto=new BoardDTO();
		
		int nowpage=Integer.parseInt(request.getParameter("page"));
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		
		int row=dao.boardWrite(dto);
		request.setAttribute("row", row);
		
		if(row==1) {
			response.sendRedirect("/Board/board_list.do?page="+nowpage); 
		}else {
			response.sendRedirect("/Board/board_write.do?page="+nowpage);
		}
	}

}
