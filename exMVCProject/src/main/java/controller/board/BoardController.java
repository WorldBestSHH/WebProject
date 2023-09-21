package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.board.BoardDeleteAction;
import service.board.BoardDeleteProAction;
import service.board.BoardListAction;
import service.board.BoardModifyAction;
import service.board.BoardModifyProAction;
import service.board.BoardViewAction;
import service.board.BoardWriteAction;
import service.board.BoardWriteProAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		System.out.println("BoardController에서 요청을 받음:"+cmd);
		
		Action action=null;
		if(cmd.equals("board_list.do")) {
			action=new BoardListAction();
		}else if(cmd.equals("board_write.do")) { //입력폼
			action=new BoardWriteAction();
		}else if(cmd.equals("board_write_pro.do")) { //입력 처리
			action=new BoardWriteProAction();
		}else if(cmd.equals("board_view.do")) {
			action=new BoardViewAction();
		}else if(cmd.equals("board_modify.do")) {
			action=new BoardModifyAction();
		}else if(cmd.equals("board_modify_pro.do")) {
			action=new BoardModifyProAction();
		}else if(cmd.equals("board_delete.do")) {
			action=new BoardDeleteAction();
		}else if(cmd.equals("board_delete_pro.do")) {
			action=new BoardDeleteProAction();
		}
		
		action.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
