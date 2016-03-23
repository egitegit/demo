import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

public class InsertImage {
	static String driver;
	static String url;
	static String user;
	static String password;
	
	static void getConnParam() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("mysql.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
	}
	
	static void insertImages(String imagePath) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			java.sql.PreparedStatement preparedStatement = conn.prepareStatement("delete * from images");
			preparedStatement.execute();
			preparedStatement = conn.prepareStatement("insert into images values(?, ?)");

			File[] files = new File(imagePath).listFiles();
			for (File file : files) {
				preparedStatement.setString(1, file.getName());
				preparedStatement.setBlob(2, new FileInputStream(file));
				preparedStatement.execute();
				System.out.println("insert " + file.getName());
			}
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		getConnParam();
		insertImages("images");
	}
}
