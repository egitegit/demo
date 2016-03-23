package xx;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

public class Monitor extends JFrame implements Runnable {

	private JPanel contentPane;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor frame = new Monitor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Monitor() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, BorderLayout.NORTH);
		
//		FileInputStream inputStream = null;
//		String path = "//home//you//workspace2//demo//src//forests2.jpg";
//		File file45 = new File(path);
//		if(!file45.exists())
//			file45.createNewFile();
//		try {
//			inputStream = new FileInputStream(file45);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// try {

				f();

				// }
			}
		});
	}

	public void f() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
//		// TODO Auto-generated method stub
		FileInputStream inputStream = null;
		try {
//			String path = "/media/you/新加卷/kankan/生活大爆炸.The.Big.Bang.Theory.S01E03.Chi_Eng.HR-HDTV.AAC.1024X576.x264-YYeTs人人影视.mkv";
			String path = "src" + File.separator + "forests2.jpg";
			File file = new File(path);
			inputStream = new FileInputStream(file);

			ProgressMonitorInputStream pm = new ProgressMonitorInputStream(this, "hi", inputStream);

			 byte[] bytes = new byte[1024]; 
	            while ( pm.read() != -1) {
				for (int i = 0; i < 100; i++)
					Math.sin(new Random().nextDouble());
			}

			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 try {
//	            File file = new File("/media/you/新加卷/kankan/生活大爆炸.The.Big.Bang.Theory.S01E03.Chi_Eng.HR-HDTV.AAC.1024X576.x264-YYeTs人人影视.mkv"); // 根据要复制的文件创建File对象
//	            File newFile = new File("/home/you/workspace2.mkv"); // 根据复制后文件的保存地址创建File对象
//	            FileOutputStream fop = new FileOutputStream(newFile); // 创建FileOutputStream对象
//	            InputStream in = new FileInputStream(file);
//	            // 读取文件，如果总耗时超过2秒，将会自动弹出一个进度监视窗口。
//	            ProgressMonitorInputStream pm = new ProgressMonitorInputStream(
//	                    this, "文件复制中，请稍后...", in);
//	            int c = 0;
//	            byte[] bytes = new byte[1024]; // 定义byte数组
//	            while ((c = pm.read(bytes)) != -1) { // 循环读取文件
//	                fop.write(bytes, 0, c); // 通过流写数据
//	            }
//	            fop.close(); // 关闭输出流
//	            pm.close(); // 关闭输入流
//	        } catch (Exception ex) {
//	            ex.printStackTrace();
//	        }
	}
}
