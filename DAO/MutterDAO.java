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

	public List<Mutter> findAll(){	// findAll()���\�b�h���s���AMutter�e�[�u�����烌�R�[�h���擾����
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);	// JDBC�h���C�o��ǂݍ���
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SELECT���̏���(MySQL�œ������������e�[�u���̏����擾����
			String sql = "SELECT mutter.ID, NAME, TEXT, IMG FROM user INNER JOIN mutter ON mutter.USERID = user.id ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT�����s
			ResultSet rs = pStmt.executeQuery();
			// SELECT���̌��ʂ�ArrayList�Ɋi�[
			while(rs.next()) {
				Mutter mutter = new Mutter();
				int id = rs.getInt("mutter.ID");		//�ǂ̃e�[�u����ID���擾���邩�I����ǉ�
				String userUserId = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");
				// font ��words�̗����̒l���Ƃ��Ă���B
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
			// �f�[�^�x�[�X�ؒf
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
			// MySQL�֐ڑ�
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// INSERT���̏���(ID�͎����A�ԂȂ̂Ŏw�肵�Ȃ��Ă���)
			String sql = "INSERT INTO MUTTER(USERID, TEXT) VALUES(?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT�����́u�H�v�Ɏg�p����l��ݒ肵SQL������
			pStmt.setInt(1,  mutter.getUserId());
			pStmt.setString(2, mutter.getText());

			// INSERT�������s
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// �f�[�^�x�[�X�ؒf
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


	// ���C�ɓ���֘A�̃��\�b�h

	// ���C�ɓ���ꗗ�̕\�����\�b�h
	public List<Mutter>getAll(int userId){	// FindAll��LOVES�e�[�u���Ɍ������������擾����
		Connection conn = null;
		List<Mutter> MutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SELECT���̏���(MySQL�œ������������e�[�u���̏����擾����@��WHERE��LOVES�e�[�u���ɓ����Ă���UserID��I������悤�ɂ���I�I
			String sql = "SELECT MUTTER.id, NAME, TEXT, IMG FROM MUTTER INNER JOIN LOVES ON LOVES.MUTTER = MUTTER.id INNER JOIN USER ON MUTTER.USERID = USER.ID WHERE LOVES.USER = ?  ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,  userId);
			// SELECT ���s
			ResultSet rs = pStmt.executeQuery();
			// SELECT���̌��ʂ�ArrayList�Ɋi�[
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

	// ���C�ɓ��茟���̃��\�b�h
	public List<Mutter>searchAll(String searchText, int userId){
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT���̏���(MySQL�œ������������e�[�u���̏����擾����
			String sql = "SELECT mutter.ID, NAME, TEXT, IMG FROM mutter INNER JOIN user ON mutter.USERID = user.id WHERE TEXT LIKE ? AND mutter.id NOT IN(select mutter from loves where user = ?) ORDER BY ID DESC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT�����́u�H�v�Ɏg�p����l��ݒ肵SQL������
			pStmt.setString(1,  "%" + searchText + "%");
			pStmt.setInt(2, userId);
			// SELECT ���s
			ResultSet rs = pStmt.executeQuery();
			// SELECT���̌��ʂ�ArrayList�Ɋi�[
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
