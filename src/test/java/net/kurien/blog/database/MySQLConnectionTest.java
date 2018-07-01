package net.kurien.blog.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectionTest {
	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		
		String url = "jdbc:mysql://127.0.0.1:3306/kreBlog?useTimezone=true&serverTimezone=UTC&useSSL=false";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, "kreBlog", "kreBlog");
			stmt = c.createStatement();

			String sql = "insert into test values('한글')";
			stmt.execute(sql);
			
			
			sql = "select * from test";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
