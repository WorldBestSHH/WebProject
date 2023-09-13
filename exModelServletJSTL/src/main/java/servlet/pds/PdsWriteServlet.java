package servlet.pds;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.pds.PdsDAO;
import model.pds.PdsDTO;

/**
 * Servlet implementation class PdsWriteServlet
 */
@WebServlet("/Pds/pds_write.do")
public class PdsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdsWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("pds_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//서버상의 실제 경로 찾기
		ServletContext context=getServletContext();
		String path=context.getRealPath("Pds/upload");
		//System.out.println("서버상 실제 경로 테스트:"+path);
		
		String encType="UTF-8";
		int sizeLimit=2*1024*1024; //최대용량 2M
		
		MultipartRequest multi=new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		//new DefaultFileRenamePolicy()-파일중복시처리(a.bmp중복 => a1.bmp)
		//file이 넘어올 때는 request를 사용하지 않음
		
		String name=multi.getParameter("name");
		String email=multi.getParameter("email");
		String subject=multi.getParameter("subject");
		String contents=multi.getParameter("contents");
		String pass=multi.getParameter("pass");
		String filename=multi.getFilesystemName("filename");
		
/*		System.out.println("이름:"+name);
		System.out.println("이메일:"+email);
		System.out.println("제목:"+subject);
		System.out.println("내용:"+contents);
		System.out.println("비밀번호:"+password);
		System.out.println("파일:"+filename);
*/
		//db 저장
		PdsDAO dao=PdsDAO.getInstance();
		PdsDTO dto=new PdsDTO();
		dto.setName(name);
		dto.setEmail(email);
		dto.setSubject(subject);
		dto.setContents(contents);
		dto.setFilename(filename);
		dto.setPass(pass);
		
		int row=dao.pdsWrite(dto);
		
		request.setAttribute("row", row);
		RequestDispatcher rd=request.getRequestDispatcher("pds_write_pro.jsp");
		rd.forward(request, response);
	}

}
