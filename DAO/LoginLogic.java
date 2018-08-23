package model;

import dao.UserDAO;

public class LoginLogic {
	public User  execute(int id, String pass) {
		UserDAO dao = new UserDAO();

		//if(user.getPass().equals("userList")) {
		//	return true;
		//}
		return  dao.findUser(id,pass);
	}
}
