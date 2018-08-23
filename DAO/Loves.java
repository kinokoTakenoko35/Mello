package model;

import java.io.Serializable;

public class Loves implements Serializable {
	private int id;							// 主キーとしてのID
	private String userName;		// ユーザー名
	private String text;				// つぶやき内容
	private String img;
	private int userId;					// MySQLで追加したUserIdを取得するためにメソッド追加
	private int mutterId;
	private String search;			// Searchの値

	public Loves() {}
	public Loves(int id, String userName, String text, String img) {	// Lovesリストとして表示させるコンストラクタ
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.img = img;
	}

	public Loves(String search) {
		this.search = search;
	}

	public Loves(int id, int mutterId, int userId) {
		this.id = id;
		this.mutterId = mutterId;
		this.userId = userId;
	}

	public Loves(int mutterId, int userId) {
		this.mutterId = mutterId;
		this.userId = userId;
	}

	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}
	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
	public String getImg() {return img;}
	public void setImg(String img) {this.img = img;}
	public int getUserId() {return userId;}
	public void setUserId(int userId) {this.userId = userId;}
	public int getMutterId() {return mutterId;}
	public void setMutterId(int mutterId) {this.mutterId = mutterId;}
}
