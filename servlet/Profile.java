package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.UserDAO;
import model.FileLogic;
import model.User;


@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパスポートの取得
		request.setCharacterEncoding("UTF-8");
		// ログインされた情報をセッションスコープから取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		// IDにIMGを紐づけるためのインスタンスを作成
  		User user = new User();
		// 画像アップロードのためのインスタンス準備
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		Iterator<FileItem> iterator;
		String fileNameWithExt = null ;
		String newFileName = null;

			try {
				iterator = upload.parseRequest(request).iterator();
				// dirPathに保存先フォルダを指定
				String dirPath="C:\\work\\workspace_php\\photo";
					while (iterator.hasNext()) {
						FileItem item = iterator.next();
							if (!item.isFormField()) {
								// ファイル名をfileNameWithExtで取得している
								fileNameWithExt = new File(item.getName()).getName();
								File filePath = new File(dirPath);
								if (!filePath.exists()) {
									filePath.mkdirs();
								}
								// 拡張子判別
								FileLogic expanded = new FileLogic();
								String ext = expanded.getSuffix(fileNameWithExt);

								// ファイル名作成⇒ユーザースコープのID使用
								String id = String.valueOf(loginUser.getId());
								File uploadedFile = new File(dirPath + "/" + fileNameWithExt);

								newFileName = id +"." + ext;
								File newUploadedFile = new File(dirPath + "/"  + newFileName);

								if (newUploadedFile.exists()) {
									newUploadedFile.delete();
								}
								// 結合したファイル名を書き出し
								item.write(uploadedFile);
								// 書き出したファイルを移動
								uploadedFile.renameTo(newUploadedFile);
							}
					}

	      		user.setId(loginUser.getId());
	      		user.setImg(newFileName);
	      		// DAOにあるIMGをアップデートするメソッドにuserに格納した情報を渡し、loginUserに返す
	      		UserDAO profUser = new UserDAO();
	      		loginUser = profUser.updateUser(user);

				session.setAttribute("loginUser", loginUser);
	             } catch(Exception e) {
	            	 e.printStackTrace();
	             }
			// メイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
			dispatcher.forward(request, response);
	}

}
