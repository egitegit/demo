import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteProperties {
	private Properties properties = new Properties();
	private String filePath = "TestLog.properties";
	
	public void writeProperties(String key, String value) {
		properties.setProperty(key, value);
		try {
			properties.store(new FileOutputStream(new File(filePath), true), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WriteProperties().writeProperties("5", "five");
	}

}
