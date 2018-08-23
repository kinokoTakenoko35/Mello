package model;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import dao.UserDAO;

public class AccountLogic {
	public void execute(User createuser) {
		UserDAO dao = new UserDAO();
		dao.create(createuser);
	}

	public void hashGenerator (User createuser){
	        //ハッシュを生成したい元の文字列
		    String source = createuser.getPass();
	        //ハッシュ生成前にバイト配列に置き換える際のCharset
	        Charset charset = StandardCharsets.UTF_8;
	        //ハッシュアルゴリズム
	        String algorithm = "SHA-512";

	        //ハッシュ生成処理
	        byte[] bytes = null;
			try {
				bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
			} catch (NoSuchAlgorithmException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
	        String pass = DatatypeConverter.printHexBinary(bytes);
	        //標準出力
	        System.out.format("生成結果=%1$s", pass);
	       createuser.setPass(pass);
	}

}
