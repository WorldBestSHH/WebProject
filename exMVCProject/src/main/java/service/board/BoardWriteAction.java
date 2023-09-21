package service.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage=Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("/Board/board_write.jsp");
		rd.forward(request, response); 
	}

}
