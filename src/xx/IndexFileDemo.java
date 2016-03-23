package xx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

public class IndexFileDemo extends JFrame {

	private JPanel contentPane;
	private JPanel headerPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private JTextField textField = new JTextField();
	private JButton selectButton = new JButton("select file");
	private JList<String> list = new JList<String>();
	private JButton createButton = new JButton("create index file");
	private JProgressBar progressBar = new JProgressBar();
	private List<String> liststring = new ArrayList<String>();
	private final JTextField textField_1 = new JTextField();
	private final JButton btnNewButton = new JButton("select dir");
	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexFileDemo frame = new IndexFileDemo();
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
	public IndexFileDemo() {
		textField_1.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new FlowLayout());
		headerPanel.add(textField);
		headerPanel.add(selectButton);
		textField.setPreferredSize(new Dimension(100, 20));
		textField.setText("index.txt");

		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(list);

		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());

		bottomPanel.add(textField_1);
		textField_1.setText(new File("src").getAbsolutePath());

		bottomPanel.add(btnNewButton);
		bottomPanel.add(createButton);
		bottomPanel.add(progressBar);
		// progressBar.setIndeterminate(true);

		list.setModel(listModel);

		selectButton.addActionListener(new SelectButtonActionListener());
		btnNewButton.addActionListener(new SelectDirButtonActionListener());
		createButton.addActionListener(new CreateButtonActionListener());

	}

	// void f() {
	// if (progressBar.isIndeterminate())
	// progressBar.setIwriteListse
	// pwriteListStringe(true);
	// }

	class SelectButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int re = fileChooser.showOpenDialog(IndexFileDemo.this);
			if (re == JFileChooser.APPROVE_OPTION) {
				String filenameString = fileChooser.getSelectedFile().getAbsolutePath();
				textField.setText(filenameString);
			}
			// f();
		}

	}

	class SelectDirButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int re = fileChooser.showOpenDialog(IndexFileDemo.this);
			if (re == JFileChooser.APPROVE_OPTION) {
				String filenameString = fileChooser.getSelectedFile().getAbsolutePath();
				textField_1.setText(filenameString);
			}
		}

	}

	class CreateButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			progressBar.setIndeterminate(true);
			// new Thread() {
			// @Override
			// public void run() {
			try {
				listModel.clear();
				liststring.clear();

				File dir = new File(textField_1.getText().trim());
				File file2 = new File(textField.getText());
				// file2.delete();
				// if(!file2.exists()) {
				// file2 = new File(textField.getText().trim());
				// }

				FileWriter fileWriter = new FileWriter(file2);
				writeList(dir);
				StringBuilder stringBuilder = new StringBuilder();
				for (String string : liststring) {
					stringBuilder.append(string);
					stringBuilder.append("\r\n");
				}
				fileWriter.write(stringBuilder.toString());
				fileWriter.flush();
				fileWriter.close();

				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						progressBar.setIndeterminate(false);
					}
				}.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				progressBar.setIndeterminate(false);
			}
			// }
			// }.start();

		}

		void writeList(File dir) {
			if (dir.isFile()) {
				// fileWriter.write(dir.getAbsolutePath() + "\r\n");
				liststring.add(dir.getAbsolutePath());
				listModel.addElement(dir.getAbsolutePath());
				// if(liststring.size() == 10)
				// return;
			} else if (dir.isDirectory()) {
				File[] files = dir.listFiles();
				for (File file2 : files)
					writeList(file2);
			}
		}
	}
}
