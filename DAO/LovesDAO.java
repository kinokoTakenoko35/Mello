package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBDriver;
import model.Loves;

public class LovesDAO implements DBDriver {

	public boolean register(Loves loves){
		Connection conn = null;
		//List<Loves> lovesList = new ArrayList<Loves>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//INSERTでLOVESテーブルにお気に入り記事を追加する(MUTTER.IDを使用)
			String sql = "INSERT INTO LOVES (USER, MUTTER) VALUES (?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,loves.getUserId());
			pStmt.setInt(2, loves.getMutterId());		// mutterID
			// INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
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
		return true;		//trueで返す
	}

	// お気に入り削除メソッド　LOVESテーブルから値をDELETEで削除
	public boolean delete(int mutterId, int userId){
		Connection conn = null;
		//List<Loves> lovesList = new ArrayList<Loves>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//DELETEでLOVESテーブルのお気に入り記事を削除する(IDを使用)
			String sql = "DELETE FROM LOVES WHERE MUTTER = ? AND USER = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mutterId);		// Lovesリストの行を削除
			pStmt.setInt(2, userId);		// Lovesリストの行を削除
			// DELETE文を実行
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
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
		return true;		//trueで返す
	}

}
