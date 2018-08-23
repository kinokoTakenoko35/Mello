package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String name;
	private String pass;
	private String img = "";
	private String msg = "";	// 初期値を空宣言してあげることで、ERRORを追加したときにNULLが出てこなくなる。
	// 0 Idエラー、 1 パスエラー、 2 名前エラー
	public static final String[] ERROR = { "Not Found your ID.<br>", "Wrong password.<br>" , "nameError<br>" };

	public User() {}
	// CreateUserを作成するためのメソッド
	public User(String name, String pass) {
		try {
		this.setName(name);
		} catch (Exception e) {
			this.setMsg(User.ERROR[2]);
		}
		try {
		this.setPass(pass);
		} catch (Exception e) {
			this.msg += User.ERROR[1];
		}
	}
	// ユーザーの全情報管理
	public User(int id, String name, String pass, String img) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.img = img;
	}

	// loginユーザーが存在するかチェックするメソッド
	public User(String id, String name, String pass) {
		try {
			this.setId(id);
		} catch  (Exception e) {
			this.setMsg(User.ERROR[0 ]);
		}
		this.name = name;
		try {
			this.setPass(pass);
		} catch (Exception e) {
			// error msgを+でつないでいる
				this.msg  += User.ERROR[1];
		}
	}
	// ログイン時のメソッド
	public User(int id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public void setId(Integer id) {
		if(id == null) {
			throw new NullPointerException("Not Found your ID.");
		}
		this.id = id;
	}

	public void setId(String id) {
		// リクエストにそもそもname属性が指定されていない場合
		if(id == null) {
			throw new NullPointerException("Not Found your ID.");	//エラーの場合はロジックへ送り、その値を持ってくる
		}
		// 長さチェック
		if(id.length() <= 1 || id.length() >= 15 ) {
			throw new IndexOutOfBoundsException("too short‼ OR too long!!");
		}
		// そもそも数字で文字入力されてきたのか
	    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]+$");
	    java.util.regex.Matcher matcher = pattern.matcher(id);

		if( ! (matcher.find()) ) {
			throw new IllegalArgumentException("Input parameters are invalid.");
		}
		this.id = Integer.parseInt(id);
	}


	public void setName(String name) {
		if(name == null) {
			throw new NullPointerException("Not Found your name.");
		}
		if(name.length() <= 1 || name.length() >= 15 ) {
			throw new IndexOutOfBoundsException("too short‼ OR too long!!");
		}
		this.name = name;
	}

	public void setPass(String pass) {
		if(pass == null) {
			throw new IllegalArgumentException("Not Found your pass.");
		}
		if(pass.length() < 1 ) {
			throw new IndexOutOfBoundsException("too short !!");
		}
		this.pass = pass;
	}
	public int getId() { return id; }
	public String getName() { return name; }
	public String getPass() { return pass; }
	public String getMsg() {return msg;}
	public void setMsg(String msg) {this.msg = msg;}
	public String getImg() {return img;}
	public void setImg(String img) {this.img = img;}

}
