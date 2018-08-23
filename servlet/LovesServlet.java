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

import model.GetLovesListLogic;
import model.GetMutterLogic;
import model.Loves;
import model.Mutter;
import model.User;

/**
 * Servlet implementation class Loves
 */
@WebServlet("/Loves")
public class LovesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LovesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");

		// お気に入り記事取得条件SELECT
		GetMutterLogic getF = new GetMutterLogic();
		List<Mutter> lovesList = getF.get(loginUser.getId());
		request.setAttribute("lovesList", lovesList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loves.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//検索条件操作 MUTTERのDB
		// 画面で入力したキーワードの取得
		String search = request.getParameter("text");
		String mutterIds = request.getParameter("mutter_id");
		String lovesId = request.getParameter("del_loves_id");

		// 入力値チェック
		if(search != null) {
			// キーワードをもとに、対象つぶやきのIDを取得MUTTERテーブルにLIKEで取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");
			// MUTTERDAOメソッド
			GetMutterLogic searchF = new GetMutterLogic();
			List<Mutter> mutterList = searchF.mutterList(search, loginUser.getId());

			request.setAttribute ("mutterList", mutterList);


			GetMutterLogic getF = new GetMutterLogic();
			List<Mutter> lovesList = getF.get(loginUser.getId());
			request.setAttribute("lovesList", lovesList);

		} else if(mutterIds != null){
			int Id = Integer.parseInt(mutterIds);
			// お気に入り登録 LOVESのDB
			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");
			// つぶやきIDとログインIDをもとに、お気に入りの追加
			GetLovesListLogic regF = new GetLovesListLogic();
			Loves loves = new Loves(Id, loginUser.getId());
			loves= regF.regLoves(loves);

			// リダイレクト
			response.sendRedirect("/Mello/Loves");
			return;
		} else {
			// お気に入りを削除
			if(lovesId != null) {
				int mutterId = Integer.parseInt(lovesId);
				// セッションスコープに保存されたユーザー情報を取得
				HttpSession session = request.getSession();
				User loginUser = (User)session.getAttribute("loginUser");
				GetLovesListLogic deleteF =new GetLovesListLogic();
				deleteF.delete(mutterId, loginUser.getId());
			}
			response.sendRedirect("/Mello/Loves");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loves.jsp");
		dispatcher.forward(request, response);
	}
}
