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
import model.AccountLogic;
import model.User;

@WebServlet("/UserCreate")
public class UserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		//int id = Integer.parseInt(ids);
		String pass = request.getParameter("pass");

		// リクエストスコープに保存するインスタンス作成
		User createuser = new User(name, pass);
		if (createuser.getMsg().equals("") ) {
		CommonLogic HashGenerator = new CommonLogic();
		createuser.setPass(HashGenerator.hashGenerator(createuser.getPass()));
		AccountLogic accountLogic = new AccountLogic();
		accountLogic.execute(createuser);

			// 入力チェック
			if(createuser != null)  {
				// リクエストスコープに保存されたユーザー情報を取得
				request.setAttribute("createUser", createuser);
				// session scope regiser info
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", createuser);
			}
		} else {
			request.setAttribute("msg", createuser.getMsg());
		}
		// メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createResult.jsp");
		dispatcher.forward(request, response);

	}

}
