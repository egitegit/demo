//win7 success
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFile {

	public static void zip() {
		String filePath = "中文乱码.txt";
		String zipFilePath = "index.zip";
		try {
			FileInputStream inputStream = new FileInputStream(new File(filePath));
			File file = new File(zipFilePath);
			if (!file.exists())
				file.createNewFile();
			ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(file));
			outputStream.putNextEntry(new ZipEntry("a/" + filePath));
			byte[] buf = new byte[1024];
			int re;
			while ((re = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, re);
			}
			outputStream.closeEntry();
			outputStream.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void unzip() {
		String zipFilePath = "index.zip";

		try {
			java.util.zip.ZipFile file = new java.util.zip.ZipFile(zipFilePath);
			Enumeration<? extends ZipEntry> entries = file.entries();
			// ZipInputStream inputStream = new ZipInputStream(new
			// FileInputStream(new File(zipFilePath)));
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				String filePath = zipEntry.getName();
				File file2 = new File(filePath);
				if (!file2.getParentFile().exists())
					file2.getParentFile().mkdirs();
				if (!file2.exists())
					file2.createNewFile();
				FileOutputStream outputStream = new FileOutputStream(file2);
				// inputStream.getNextEntry();
				InputStream inputStream = file.getInputStream(zipEntry);
				byte[] buf = new byte[1024];
				int re;
				while ((re = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, re);
				}
				inputStream.close();
				outputStream.close();
			}
			// inputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void zipApache() {
		String filePath = "中文乱码.txt";
		String zipFilePath = "index.zip";

		try {
			FileInputStream inputStream = new FileInputStream(new File(filePath));
			org.apache.tools.zip.ZipOutputStream outputStream = new org.apache.tools.zip.ZipOutputStream(
					new FileOutputStream(new File(zipFilePath)));
			outputStream.putNextEntry(new org.apache.tools.zip.ZipEntry(filePath));
			byte[] buf = new byte[1024];
			int re;
			while((re = inputStream.read(buf)) != -1)
				outputStream.write(buf, 0, re);
			
			inputStream.close();
			outputStream.closeEntry();
			outputStream.close();
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
		zipApache();

	}

}
