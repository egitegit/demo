package xx;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReadProperties extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textField;
	private JButton btnNewButton;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadProperties frame = new ReadProperties();
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
	public ReadProperties() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("选择属性文件");
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnNewButton.addActionListener(new SelectFileListener());
		
	}
	class SelectFileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setFileFilter(new FileNameExtensionFilter("properties file", "properties"));
			int re = fileChooser.showOpenDialog(ReadProperties.this);
			if(re == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				Properties properties = new Properties();
				FileReader reader = null;
				try {
					DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
					tableModel.setColumnIdentifiers(new String[] {"keys", "values"});
				
					reader = new FileReader(fileChooser.getSelectedFile());
					properties.load(reader);
					Enumeration<?> keys = properties.propertyNames();
					while(keys.hasMoreElements()) {
						String key = keys.nextElement().toString();
						String value = properties.getProperty(key);
						tableModel.addRow(new String[] {key, value});
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
}
