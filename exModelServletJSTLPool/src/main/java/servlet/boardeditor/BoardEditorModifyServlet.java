package servlet.boardeditor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardeditor.BoardEditorDAO;
import model.boardeditor.BoardEditorDTO;

/**
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/BoardEditor/board_modify.do")
public class BoardEditorModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditorModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		BoardEditorDAO dao=BoardEditorDAO.getInstance();
		BoardEditorDTO dto=dao.boardView(idx);
		
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
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		BoardEditorDAO dao=BoardEditorDAO.getInstance();
		
		BoardEditorDTO dto=new BoardEditorDTO();
		dto.setIdx(Integer.parseInt(request.getParameter("idx")));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		dto.setPass(request.getParameter("pass"));
		
		int row=dao.boardModify(dto);
		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_modify_pro.jsp");
		rd.forward(request, response);
	}

}
