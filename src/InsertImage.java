import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertImage {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.100.102:3306/test";
		String user = "root";
		String password = "1219root";
		String imagePath = "src" + File.separator + "images";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			java.sql.PreparedStatement psPreparedStatement = conn.prepareStatement("insert into images values(?, ?)");

			File[] files = new File(imagePath).listFiles();
			for (File file : files) {
				psPreparedStatement.setString(1, file.getName());
				psPreparedStatement.setBlob(2, new FileInputStream(file));
				psPreparedStatement.execute();
				System.out.println("insert " + file.getName());
			}
			psPreparedStatement.close();
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

}
