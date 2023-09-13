package servlet.boardreply;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardreply.BoardReplyDAO;
import model.boardreply.BoardReplyDTO;

/**
 * Servlet implementation class BoardReplyViewServlet
 */
@WebServlet("/BoardReply/board_view.do")
public class BoardReplyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyViewServlet() {
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
		
		boolean bool=false;
		Cookie info=null;
		Cookie[] cookies=request.getCookies();
		for(int i=0;i<cookies.length;i++) {
			info=cookies[i];
			if(info.getName().equals("Board"+idx)) {
				bool=true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis();
		if(!bool) {
			dao.readcntUpdate(idx);
			info=new Cookie("Board"+idx,newValue);
			info.setMaxAge(60*60);
			response.addCookie(info);
		}
		
		BoardReplyDTO dto=dao.boardSelect(idx);
		
		dto.setContents(dto.getContents().replace("\n", "<br>"));
		
		request.setAttribute("dto", dto);
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_view.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
