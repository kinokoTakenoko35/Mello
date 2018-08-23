package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// つぶやきリストを取得して、リクエストスコープに保存
		GetMutterLogic getMutterListLogic = new GetMutterLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
		// ログインしているか確認するため、セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		// ログインしていない場合はリダイレクト
		if(loginUser == null) {
			response.sendRedirect("/Mello/jsp");
		} else { //ログイン済みの場合フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパスポートの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		// 入力値チェック
		if(text != null && text.length() !=0 ) {

			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");

			// つぶやきを、つぶやきリストに追加
			Mutter mutter = new Mutter(loginUser.getId(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);

		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "An error occurred! No posting.");
		}
		// つぶやきリストを取得して、リクエストスコープに保存
		GetMutterLogic getMutterListLogic = new GetMutterLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		// メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
