import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SplitMergeFile {
	private String orignalFilePath = "src" + File.separator + "images" + File.separator + "forests2.jpg";
	private String newFilePath = "src" + File.separator + "forests2.jpg";
	private String tempPath = "src";
	private int sizePerTemp = 512 * 1024;

	public void splitFile(File orignalFile, File tempPath) {
		if (orignalFile == null)
			return;
		if (!tempPath.isDirectory())
			tempPath.mkdir();

		int nums = (int) (orignalFile.length() / sizePerTemp);
		int leftSize = (int) (orignalFile.length() % sizePerTemp);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(orignalFile);
			for (int i = 0; i < nums + 1; i++) {
				byte[] buf;
				if (i == nums) {
					if (leftSize > 0)
						buf = new byte[leftSize];
					else
						break;
				} else {
					buf = new byte[sizePerTemp];
				}

				File tempFile = new File(tempPath.getAbsolutePath() + File.separator + (i + 1) + ".tmp");
				if (!tempFile.isFile())
					tempFile.createNewFile();
				FileOutputStream outputStream = new FileOutputStream(tempFile);

				inputStream.read(buf);
				outputStream.write(buf);
				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void mergeFile(File tempPath, File newFile) {
		if (!newFile.isFile())
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		File[] files = tempPath.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.getName().endsWith(".tmp"))
					return true;
				return false;
			}
		});
		Arrays.sort(files);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(newFile);
			for(File file : files) {
				FileInputStream inputStream = new FileInputStream(file);
				byte[] buf = new byte[(int) file.length()];
				inputStream.read(buf);
				outputStream.write(buf);
				inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitMergeFile splitMergeFile = new SplitMergeFile();
		splitMergeFile.splitFile(new File(splitMergeFile.orignalFilePath), new File(splitMergeFile.tempPath));
		splitMergeFile.mergeFile(new File(splitMergeFile.tempPath), new File(splitMergeFile.newFilePath));
	}

}
