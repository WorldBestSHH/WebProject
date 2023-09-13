package servlet.pds;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pds.PdsDAO;
import model.pds.PdsDTO;

/**
 * Servlet implementation class PdsViewServlet
 */
@WebServlet("/Pds/pds_view.do")
public class PdsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdsViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		PdsDAO dao=PdsDAO.getInstance();
		
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
			info=new Cookie("Board"+idx, newValue);
			info.setMaxAge(60*60);
			response.addCookie(info);
		}
		
		PdsDTO dto=dao.pdsView(idx);
		
		dto.setContents(dto.getContents().replace("\n", "<br>"));
		
		request.setAttribute("page", nowpage);
		request.setAttribute("dto", dto);
		
		RequestDispatcher rd=request.getRequestDispatcher("pds_view.jsp");
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
