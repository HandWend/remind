package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import service.WriterServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/writer")
public class WriterController extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //받는다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("writer.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//post: 글 쓴 내용들을 받는 것.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글자 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		//DB에 저장된 값을 가져오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		//값이 어디있는데? 'BoardVO'
		BoardVO vo = new BoardVO();
		
		//무슨 값을 받을건데? - 위에 getParameter("")로 가져온 값
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		WriterServiceImpl service = new WriterServiceImpl();
		//mvc1에서 값 받을 때 쿼리문 뭐 썼는데? 'INSERT'
		//무슨 값을 받을 건데 'vo'
		service.insert(vo);
		
		//받아서 어쩔 건데 - 넘겨야지 - 어디로?
		response.sendRedirect("List");
	}

}
