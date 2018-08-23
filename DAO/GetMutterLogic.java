package model;

import java.util.List;

import dao.MutterDAO;

public class GetMutterLogic {
	public List<Mutter> execute(){
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		return mutterList;
	}

	public List<Mutter>get(int userId){
		MutterDAO dao = new MutterDAO();
		List<Mutter> MutterList = dao.getAll(userId);
		return MutterList;
	}

	public List<Mutter> mutterList(String searchText, int userId){
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.searchAll(searchText, userId);
		// dao	からmutterListを取得
		return mutterList;
	}
}
