package servlet.pds;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pds.PdsDAO;

/**
 * Servlet implementation class PdsDeleteServlet
 */
@WebServlet("/Pds/pds_delete.do")
public class PdsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdsDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("idx", idx);
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd=request.getRequestDispatcher("pds_delete.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		String pass=request.getParameter("pass");
		
		PdsDAO dao=PdsDAO.getInstance();
		int row=dao.pdsDelete(idx, pass);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('삭제되었습니다');");
			out.print("window.opener.location.href='/Pds/pds_list.do?page="+nowpage+"';");
			out.print("self.close();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('비밀번호가 맞지 않습니다');");
			out.print("history.back()");
			out.print("</script>");
		}
	}

}
