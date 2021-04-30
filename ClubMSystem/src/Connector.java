import java.sql.*;
import javax.swing.*;


public class Connector {
	Connection conn = null;
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost/Registerr";
	public static Connection dbconnection() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DATABASE_URL,"root","letmeinside");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
