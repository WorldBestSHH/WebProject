package servlet.boardeditor;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardeditor.BoardEditorDAO;
import model.boardeditor.BoardEditorDTO;
import model.util.PageIndex;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/BoardEditor/board_list.do")
public class BoardEditorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditorListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardEditorDAO dao=BoardEditorDAO.getInstance();
		
		String search="", key="", url="/Board/board_list.do";
		int totcount=0; //게시글 총 수
		
		//검색 판단
		if(request.getParameter("key")!=null) {
			key=request.getParameter("key");
			search=request.getParameter("search");
			totcount=dao.boardCount(search, key);
		}else {
			totcount=dao.boardCount();
		}
		
		int nowpage=1; //현재페이지 초기화
		int maxlist=10; //페이지당 글 수
		int totpage= 1; //전체페이지
		
		//총 페이지수 계산
		if(totcount%maxlist==0) {
			totpage=totcount/maxlist;
		}else {
			totpage=totcount/maxlist+1;
		}
		
		//페이지번호가 넘어온 경우
		if(request.getParameter("page")!=null) 
			nowpage=Integer.parseInt(request.getParameter("page"));
		
		//페이지별 출력될 시작, 끝번호
		int startpage=(nowpage-1)*maxlist+1;
		int endpage=nowpage*maxlist;
		int listcount=totcount-((nowpage-1)*maxlist); //웹에서 번호 출력용
		
		List<BoardEditorDTO> list=null;
		if(key.equals("")) {
			list=dao.boardList(startpage, endpage);
		}else {
			list=dao.boardList(search, key, startpage, endpage);
		}
		
		//페이지 처리
		String pageSkip="";
		if(key.equals("")) {
			pageSkip=PageIndex.pageList(nowpage, totpage, url, "");
		}else {
			pageSkip=PageIndex.pageListHan(nowpage, totpage, url, search, key);
		}
		
		//jsp에서 사용될 값을 request 내장객체에 담기
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		
		RequestDispatcher rd=request.getRequestDispatcher("board_list.jsp");
		rd.forward(request, response); //forward는 url이 보안이 됨
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
