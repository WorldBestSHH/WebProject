package service.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardDTO;
import service.Action;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		int nowpage=Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao=BoardDAO.getInstance();
		
		//쿠키검사 및 설정
		boolean bool=false;
		Cookie info=null; //최대 300회까지 저장됨
		Cookie[] cookies=request.getCookies(); //클라이언트에 있는 쿠키 가져와서 배열에 저장
		for(int i=0;i<cookies.length;i++) {
			info=cookies[i];
			if(info.getName().equals("Board"+idx)) { //idx를 안넣으면 모든 게시물에 쿠키가 1개 밖에 안생김
				bool=true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis(); //쿠키값으로 사용
		if(!bool) { //쿠키가 존재하지 않으면
			dao.readcntUpdate(idx);
			info=new Cookie("Board"+idx, newValue); //쿠키생성
			info.setMaxAge(60*60); //쿠키유효시간-한시간
			response.addCookie(info);
		}
		
		BoardDTO dto=dao.boardView(idx);
		
		dto.setContents(dto.getContents().replace("\n", "<br>"));
		
		request.setAttribute("page", nowpage);
		request.setAttribute("dto", dto);
		
		RequestDispatcher rd=request.getRequestDispatcher("/Board/board_view.jsp");
		rd.forward(request, response);
	}

}
