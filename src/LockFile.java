//under Ubuntu, failed
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileLock;

public class LockFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream outputStream = new FileOutputStream(new File("index.txt"));
			FileLock lock = outputStream.getChannel().lock();
			Thread.sleep(60000);
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
