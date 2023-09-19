package servlet.boardeditor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDTO;
import model.boardeditor.BoardEditorDAO;
import model.boardeditor.BoardEditorDTO;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/BoardEditor/board_write.do")
public class BoardEditorWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditorWriteServlet() {
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
		
		BoardEditorDAO dao=BoardEditorDAO.getInstance();
		BoardEditorDTO dto=new BoardEditorDTO();
		
		int nowpage=Integer.parseInt(request.getParameter("page"));
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		
		int row=dao.boardWrite(dto);
		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("board_write_pro.jsp");
		rd.forward(request, response);
	}

}
