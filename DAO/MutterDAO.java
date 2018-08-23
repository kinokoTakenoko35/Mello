package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lib.CommonLogic;
import model.DBDriver;
import model.Mutter;

public class MutterDAO implements DBDriver {

	//private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	//private final String JDBC_URL = "jdbc:mysql://localhost/mello?useUnicode=true&characterEncoding=utf8";
	//private final String DB_USER = "root";
	//private final String DB_PASS = "";

	public List<Mutter> findAll(){	// findAll()メソッド実行し、Mutterテーブルからレコードを取得する
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);	// JDBCドライバを読み込み
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SELECT文の準備(MySQLで内部結合したテーブルの情報を取得する
			String sql = "SELECT mutter.ID, NAME, TEXT, IMG FROM user INNER JOIN mutter ON mutter.USERID = user.id ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while(rs.next()) {
				Mutter mutter = new Mutter();
				int id = rs.getInt("mutter.ID");		//どのテーブルのIDを取得するか選択を追加
				String userUserId = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");
				// font とwordsの乱数の値をとってくる。
				CommonLogic commonLogic = new CommonLogic();
				mutter.setFont(commonLogic.fontGenerater());
				mutter.setWords(commonLogic.wordsGenerater());

				mutter = new Mutter(id, userUserId, text, img, mutter.getFont(), mutter.getWords());
				if(img == null) {
					mutter.setImg("20170722213834.jpg");
				}

				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return mutterList;
	}

	public boolean create(Mutter mutter) {
		Connection conn = null;
		try {
			// MySQLへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// INSERT文の準備(IDは自動連番なので指定しなくていい)
			String sql = "INSERT INTO MUTTER(USERID, TEXT) VALUES(?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setInt(1,  mutter.getUserId());
			pStmt.setString(2, mutter.getText());

			// INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// データベース切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}


	// お気に入り関連のメソッド

	// お気に入り一覧の表示メソッド
	public List<Mutter>getAll(int userId){	// FindAllでLOVESテーブルに結合した情報を取得する
		Connection conn = null;
		List<Mutter> MutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SELECT文の準備(MySQLで内部結合したテーブルの情報を取得する　＊WHEREでLOVESテーブルに入っているUserIDを選択するようにする！！
			//String sql = "SELECT USER.ID, NAME,TEXT, IMG FROM LOVES INNER JOIN USER ON LOVES.user = USER.id INNER JOIN MUTTER ON LOVES.mutter = MUTTER.id WHERE LOVES.user = ? ORDER BY ID DESC;";
			String sql = "SELECT MUTTER.id, NAME, TEXT, IMG FROM MUTTER INNER JOIN LOVES ON LOVES.MUTTER = MUTTER.id INNER JOIN USER ON MUTTER.USERID = USER.ID WHERE LOVES.USER = ?  ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,  userId);
			// SELECT 実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while(rs.next()) {
				Mutter mutter = new Mutter();
				int id = rs.getInt("MUTTER.id");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");

				mutter = new Mutter(id, userName, text, img, "", "");
				MutterList.add(mutter);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return MutterList;
	}

	// お気に入り検索のメソッド
	public List<Mutter>searchAll(String searchText, int userId){
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT文の準備(MySQLで内部結合したテーブルの情報を取得する
			String sql = "SELECT mutter.ID, NAME, TEXT, IMG FROM mutter INNER JOIN user ON mutter.USERID = user.id WHERE TEXT LIKE ? AND mutter.id NOT IN(select mutter from loves where user = ?) ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1,  "%" + searchText + "%");
			pStmt.setInt(2, userId);
			// SELECT 実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("mutter.ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");

				Mutter mutter = new Mutter(id, userName, text, img, "", "");
				mutterList.add(mutter);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return mutterList;
	}


}
