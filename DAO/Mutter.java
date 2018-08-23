package model;

import java.io.Serializable;


public class Mutter implements Serializable {
	private int id;							// 主キーとしてのID
	private String userName;		// ユーザー名
	private String text;				// つぶやき内容
	private int userId;					// MySQLで追加したUserIdを取得するためにメソッド追加
	private String font;				// ランダムフォント作成に使用
	private  String words;			//	 ランダム文字サイズ作成に使用
	private String img;

	public Mutter() {}
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	public Mutter(int userId, String text) {
		// MySQLで追加したUserIdを取得するためにメソッド追加(doPost)で使用するメソッド
		this.userId = userId;
		this.text = text;

	}

	public Mutter(int id, String userName, String text, String img, String font, String words) {
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.img = img;
		this.font = font;
		this.words = words;
	}


	public int getId() { return id; }
	public String getUserName() { return userName; }
	public String getText() { return text; }
	public int getUserId() { return userId; }
	public String getImg() {return img;}

	public String getFont() {return font;}
	public void setFont(String font) {this.font = font;}
	public String getWords() {return words;}
	public void setWords(String words) {this.words = words;}
	public void setImg(String img) {this.img = img;}
}
