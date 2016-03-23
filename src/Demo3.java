import java.io.File;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "images";
		File[] files = new File(path).listFiles();
		for (File file : files) {
			String newName = null;
			if (file.getName().matches("forests\\d\\.jpg")) {
				newName = file.getName().replaceFirst("forests(\\d)\\.jpg", "forests_$1\\.jpg");
			} else if (file.getName().matches("forests_\\d\\.jpg")) {
				newName = file.getName().replaceFirst("forests_(\\d)\\.jpg", "forests$1\\.jpg");
			}
			if (newName != null) {
				File newFile = new File(file.getParent() + File.separator + newName);
				file.renameTo(newFile);
			}
		}
	}
}