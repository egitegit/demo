package xx;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Blob;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowImageFromDB extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private int nums;
	private String[] imageNames;
	private java.sql.Blob[] imageDatas;
	JLabel lblNewLabel = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowImageFromDB frame = new ShowImageFromDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowImageFromDB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		getImage();
		
		for(int i = 0; i < nums; i++) {
			JButton button = new JButton(imageNames[i]);
			button.setActionCommand(i + "");
			panel.add(button);
			button.addActionListener(new ImageActionListener());
		}
		
	}

	private void getImage() {
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

			java.sql.PreparedStatement psPreparedStatement = conn
					.prepareStatement("select count(*) as image_nums from images");
			ResultSet rSet = psPreparedStatement.executeQuery();
			if(rSet.first())
				nums = rSet.getInt(1);
			imageNames = new String[nums];
			imageDatas = new java.sql.Blob[nums];
			psPreparedStatement = conn.prepareStatement("select image_name, image_data from images");
			rSet = psPreparedStatement.executeQuery();
			int i = 0;
			while(rSet.next()) {
				imageNames[i] = rSet.getString("image_name");
				imageDatas[i++] = rSet.getBlob("image_data");
			}
			rSet.close();
			psPreparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ImageActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = Integer.parseInt(e.getActionCommand());
			try {
				lblNewLabel.setIcon(new ImageIcon(imageDatas[i].getBytes(1, (int) imageDatas[i].length())));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
