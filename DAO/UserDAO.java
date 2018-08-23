package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBDriver;
import model.User;

public class UserDAO implements DBDriver {
	// 各種設定
	//private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	//private final String JDBC_URL = "jdbc:mysql://localhost/mello?useUnicode=true&characterEncoding=utf8";
	//private final String DB_USER = "root";
	//private final String DB_PASS = "";

	public User create(User createuser) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// INSERT文でユーザーを登録の準備(IDは自動追加なので指定しなくていい）
			String sql = "INSERT INTO USER (NAME, PASS) VALUES (?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「？」に追加する値を設定しSQLを完成
			pStmt.setString(1, createuser.getName());
			pStmt.setString(2, createuser.getPass());

			// INSERT文を実行
			int result = pStmt.executeUpdate();	//UPDATEした件数が返ってくる
			if(result == 1) {
				//ResultSet rs = pStmt.executeQuery();

				String rs2 = "SELECT LAST_INSERT_ID() AS ID;";
				PreparedStatement pStmt2 = conn.prepareStatement(rs2);
				ResultSet  rs3= pStmt2.executeQuery();
				 //一致したユーザーがいた場合ID取得してくる
				while(rs3.next()) {
					createuser.setId(rs3.getInt("ID"));
			}

			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return createuser;
	}


	public User findUser(int id, String pass){
		Connection conn = null;
		User user = null;

		try {
			// JDBCドライバ読込
			Class.forName(DRIVER_NAME);
			// MySQL接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SELECT文でUserTABLE実行の準備
			String sql = "SELECT NAME, PASS, ID, IMG FROM USER WHERE ID= ? AND PASS = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,  id);
			pStmt.setString(2, pass);
			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("NAME");
				String img = rs.getString("IMG");
				user = new User(id, name, pass, img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return user;
	}

	public User updateUser(User profUser) {
		Connection conn = null;
		try {
			// JDBCドライバ読込
			Class.forName(DRIVER_NAME);
			// MySQL接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// UPDATE文でUserTABLEに画像を追加する実行の準備 IDは主キーとしてセレクトするためにWHEREで選択している
			String sql = "UPDATE USER SET USER.IMG = ? WHERE USER.ID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「？」に追加する値を設定しSQLを完成
			pStmt.setString(1, profUser.getImg());
			pStmt.setInt(2, profUser.getId());
			// INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result == 1) {
				String rs2 = "SELECT NAME, PASS, ID, IMG FROM USER WHERE ID= ? ;";
				PreparedStatement pStmt2 = conn.prepareStatement(rs2);
				//  WHEREで指定しているIDを以下で記述
				pStmt2.setInt(1, profUser.getId());
				ResultSet  rs3= pStmt2.executeQuery();

				while(rs3.next()) {
					//一致したユーザーがいた場合NAME取得してくる
					String name = rs3.getString("NAME");
					String pass = rs3.getString("PASS");
					profUser = new User(rs3.getInt("ID"), name, pass, rs3.getString("IMG"));
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return profUser;
	}


}
