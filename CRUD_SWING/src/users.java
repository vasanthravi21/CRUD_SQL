import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;

public class users {

	private JFrame frmCrudOperation;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtCity;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					users window = new users();
					window.frmCrudOperation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public users() {
		initialize();
		Connect();
	}
	
	// Database Connection
	Connection con = null;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dbvasanth?characterEncoding=utf8";
			String username = "root";
			String password = "root";
			con = DriverManager.getConnection(url,username,password);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// Clear All
	public void clear() {
		txtID.setText("");
		txtName.setText("");
		txtAge.setText("");
		txtCity.setText("");
		txtName.requestFocus();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrudOperation = new JFrame();
		frmCrudOperation.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCrudOperation.setTitle("CRUD Operation Swing MySQL");
		frmCrudOperation.setFont(new Font("Dialog", Font.PLAIN, 14));
		frmCrudOperation.setBounds(100, 100, 1074, 570);
		frmCrudOperation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudOperation.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 297, 66);
		frmCrudOperation.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 72, 427, 310);
		frmCrudOperation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 45, 80, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 85, 80, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Age");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 125, 80, 30);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("City");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 166, 80, 30);
		panel.add(lblNewLabel_1_3);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(98, 45, 295, 31);
		panel.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(98, 85, 295, 31);
		panel.add(txtName);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(98, 125, 295, 31);
		panel.add(txtAge);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(98, 165, 295, 31);
		panel.add(txtCity);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText();
				String name = txtName.getText();
				String age = txtAge.getText();
				String city = txtCity.getText();
				
				if(name == null || name.isEmpty() || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Name");
					txtName.requestFocus();
					return;
				}
				if(name == null || name.isEmpty() || age.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Age");
					txtAge.requestFocus();
					return;
				}
				if(name == null || name.isEmpty() || city.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter City");
					txtCity.requestFocus();
					return;
				}
				
				if(txtID.getText().isEmpty()) {
					try {
					String sql = "insert into users (NAME,AGE,CITY) values (?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setString(1, name);
					pst.setString(2, age);
					pst.setString(3, city);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data insert Success");
					clear();
//					loadData();
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnSave.setBounds(98, 206, 92, 30);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(200, 206, 92, 30);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(301, 206, 92, 30);
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(486, 72, 560, 310);
		frmCrudOperation.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
	}
}
