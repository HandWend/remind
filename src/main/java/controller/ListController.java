package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import service.ListServiceImpl;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/list")
public class ListController extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //받는다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글자깨짐방지
		request.setCharacterEncoding("UTF-8");
		
		//mapper 갔다 와야 함.
		ListServiceImpl service = new ListServiceImpl();
		
		//이제 list.jsp에 있는 값들을 읽어와야 한다. 읽다 = read, 무엇이 service가
		//parameter값 가져갈 것이 없으므로 read()로 작성
		//이해부족(1) - collection
		Collection<BoardVO> list = service.read();
		
		//뽑아낸 값들을 list에 setAttribute()로 넣어준다.
		request.setAttribute("list", list);
		
		//.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
