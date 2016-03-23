import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarReader {
	private String jarFilePath = new File(System.getProperty("user.dir")).getParent() + File.separator + "c.jar";
	
	public List<String> read() {
		List<String> list = new ArrayList<String>();
		try {
			JarFile jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements()) {
				list.add(entries.nextElement().getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new JarReader().read());
	}

}
