package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.FBoardDAO;
import model.dao.MemberDAO;
import model.vo.FBoardVO;
import model.vo.MemberVO;

@WebServlet("/fboard")
public class FBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// search, delete
		FBoardDAO dao = new FBoardDAO();
		String action = request.getParameter("action");
		String writer = request.getParameter("writer");
		
		int page=0;
		if(request.getParameter("action")!=null) {
			page = dao.boardNum2(writer);
		} else if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("list", dao.listAll(page));
		request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// insert, update
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String action = request.getParameter("action");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MemberDAO mdao = new MemberDAO();
		FBoardDAO fdao = new FBoardDAO();
		MemberVO mvo = null;
		FBoardVO fvo = null;
		boolean result = true;
		HttpSession session;
		if(action.equals("insert")) {
			fvo = new FBoardVO();
			fvo.setTitle(title);
			fvo.setContent(content);
			session = request.getSession();
			result = fdao.insert(fvo, session);
			if(result) {
				request.setAttribute("imsg", session.getAttribute("id")+"의 게시글 작성 완료");
			} else {
				request.setAttribute("imsg", "작성실패 !!");
			}
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);
			
		} else if(action.equals("login")) {
			//로그인 
			result = mdao.checkID(id, pwd);
			if(result) {
				request.setAttribute("lmsg", "로그인 성공 !! ");
				session = request.getSession();
				session.setAttribute("id", id);
			} else {
				request.setAttribute("lfail", "로그인 실패 !! ");
			}
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);
		} else if(action.equals("signup")){
			//회원가입 
			mvo=new MemberVO();
			mvo.setId(id);
			mvo.setName(name);
			mvo.setPhone(phone);
			mvo.setPwd(pwd);
			result = mdao.insert(mvo);
			if(result) {
				request.setAttribute("msg", "회원가입 성공 !!");
			}else {
				request.setAttribute("msg", "회원가입 실패 !! ");
			}
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);
		} else if(action.equals("update")) {
			//update board
			int bid = Integer.parseInt(request.getParameter("bid"));
			fvo = new FBoardVO();
			fvo.setId(bid);
			fvo.setContent(content);
			fvo.setTitle(title);
			result = fdao.update(fvo);
			if(result) {
				request.setAttribute("umsg", " 수정 완료 !! ");
				request.setAttribute("bid", bid);
			} else {
				request.setAttribute("umsg", " 수정 실패 !! ");
			}
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);
		} else if(action.equals("delete")) {
			int bid = Integer.parseInt(request.getParameter("bid"));
			result = fdao.delete(bid);
			if(result) {
				request.setAttribute("dmsg", " 삭제 완료 !! ");
			} else {
				request.setAttribute("dmsg", " 삭제 실패 !! ");
			}
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);
		} else if(action.equals("search")) {
			String combo = request.getParameter("comboBox");
			String keyIn = (String)request.getParameter("keyIn");
			List<FBoardVO> list = null;
			if(combo.equals("sTitle")) {
				list = fdao.searchTitle(keyIn);
				request.setAttribute("list", list);
			} else if (combo.equals("sWriter")) {
				list = fdao.searchWriter(keyIn);
				request.setAttribute("list", list);
			} else {
				list = fdao.searchContent(keyIn);
				request.setAttribute("list", list);
			}
			
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);

		} else if(action.equals("searchWriter")) {
			String writer = request.getParameter("writer");
			int page = fdao.boardNum2(writer);
			request.setAttribute("list", fdao.listAll(page));
			request.setAttribute("pageS", page);
			request.getRequestDispatcher("/jspsrc/fBoardView.jsp").forward(request, response);
		}
		
	}
}
