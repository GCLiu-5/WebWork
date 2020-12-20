package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import vo.User;

public class SaveToSql {
	public boolean save(User user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/excise?useunicode=true&character=utf-8",
					"root", "root");
			String sql = "select * from t_user1 ";
			PreparedStatement pst = con.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				if (rs.getString("phone").equals(user.getPhone())) {
					return false;
				}
			}
			
			String insertSql = "insert into t_user1 values ('"+user.getPhone()+"','"+user.getPassWord()+"')";
			Statement sm = con.createStatement();
			sm.executeUpdate(insertSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
