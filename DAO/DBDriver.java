package model;

public interface DBDriver {
	// 各種設定
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost/mello?useUnicode=true&characterEncoding=utf8";
	final String DB_USER = "root";
	final String DB_PASS = "";
}
