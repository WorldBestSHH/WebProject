package servlet.boardreply;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardreply.BoardReplyDAO;
import model.boardreply.BoardReplyDTO;
import model.util.PageIndex;

/**
 * Servlet implementation class BoardReplyListServlet
 */
@WebServlet("/BoardReply/board_list.do")
public class BoardReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardReplyDAO dao=BoardReplyDAO.getinstance();
		
		String search="", key="", url="/BoardReply/board_list.do";
		int totcount=0;
		
		if(request.getParameter("key")!=null) {
			key=request.getParameter("key");
			search=request.getParameter("search");
			totcount=dao.boardCount(search, key);
		}else {
			totcount=dao.boardCount();
		}
		
		int nowpage=1;
		int maxlist=10;
		int totpage=1;
		
		if(totcount%maxlist==0) {
			totpage=totcount/maxlist;
		}else {
			totpage=totcount/maxlist+1;
		}
		
		if(request.getParameter("page")!=null) {
			nowpage=Integer.parseInt(request.getParameter("page"));
		}
		
		int startpage=(nowpage-1)*maxlist+1;
		int endpage=nowpage*maxlist;
		int listcount=totcount-((nowpage-1)*maxlist);
		
		List<BoardReplyDTO> list=null;
		String pageSkip="";
		if(key.equals("")) {
			list=dao.boardList(startpage, endpage);
			pageSkip=PageIndex.pageList(nowpage, totpage, url, "");
		}else {
			list=dao.boardList(search, key, startpage, endpage);
			pageSkip=PageIndex.pageListHan(nowpage, totpage, url, search, key);
		}
		
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
