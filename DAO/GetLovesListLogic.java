package model;

import dao.LovesDAO;

public class GetLovesListLogic {
	public Loves regLoves(Loves loves){
		LovesDAO dao = new LovesDAO();
		 ((LovesDAO) dao).register(loves);
		return loves;
	}

	public boolean delete(int mutterId, int userId) {
		LovesDAO dao = new LovesDAO();
		return dao.delete(mutterId, userId);
	}

}
