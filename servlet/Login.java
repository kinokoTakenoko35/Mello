package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.CommonLogic;
import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Request Parameter
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		// LoginLogicでログイン情報をするためにインスタンスを作成し、入力値をLogicのexecuteメソッドへもっていく
		User user = new User(id, "", pass);

		if (user.getMsg().equals("")){
		CommonLogic HashGenerator = new CommonLogic();
		user.setPass(HashGenerator.hashGenerator(user.getPass()));	// userのpassを引数として送る

		LoginLogic LoginLogic = new LoginLogic();
		user = LoginLogic.execute(user.getId(), user.getPass());
		//} else {
			// ログイン成功時の処理
			if(user != null && user.getMsg().equals("")) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
			}
		} else {
			// ログイン失敗の場合、エラー文言をリクエストスコープに詰める
			request.setAttribute("msg", user.getMsg());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

}
