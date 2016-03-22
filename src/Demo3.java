import java.io.File;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "src" + File.separator + "images" + File.separator;
		File[] files = new File(path).listFiles();
		for(File file : files) {
			if(file.getName().matches("forests\\d\\.jpg")) {
				String newPath = file.getAbsolutePath();
				String parent = file.getParent();
				String pString = file.getPath();
				
				newPath = newPath.replaceFirst("forests_(\\d)\\.jpg", "forests$1\\.jpg");
				File newFile = new File(newPath);
				file.renameTo(newFile);
			}
		}
	}

}
