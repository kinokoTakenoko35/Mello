package model;

import dao.UserDAO;

public class LoginLogic {
	public User  execute(int id, String pass) {
		UserDAO dao = new UserDAO();
		return  dao.findUser(id,pass);
	}
}
