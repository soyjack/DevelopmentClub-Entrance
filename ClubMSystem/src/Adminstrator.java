import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Adminstrator extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String fname, lname, admi, email, pass, year, phone;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminstrator frame = new Adminstrator();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTextField usrNm;
	private String name;
	private JTextField fName;
	private JTextField eField;
	private JTextField pField;
	private JTextField phoField;
	private JTextField lName;
	private JTextField title;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup2 = new ButtonGroup();
	private JTextField textField;
	public Adminstrator() {
		conn = Connector.dbconnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 378);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 740, 338);
		contentPane.add(tabbedPane);
		
		JPanel home = new JPanel();
		home.setName("");
		home.setLayout(null);
		home.setBackground(Color.GRAY);
		tabbedPane.addTab("All User's Status", null, home, null);
		
		JLabel lblNewLabel = new JLabel("All Users and Admins");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(261, 11, 219, 39);
		home.add(lblNewLabel);
		
		Statement stm;
		Vector<Vector<Object>> dt = new Vector<Vector<Object>>();
		try {
			stm = conn.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM MEMBERS");
			ResultSetMetaData metaData = res.getMetaData();
			int columns = metaData.getColumnCount();
			while(res.next()) {
				Vector<Object> row = new Vector<Object>(columns);
				for(int i = 1; i <= columns; i++) {
					row.addElement(res.getObject(i));
				}
				dt.addElement(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		Vector<String> columnNm = new Vector<String>();
		columnNm.addElement("FirstName");
		columnNm.addElement("LastName");
		columnNm.addElement("Email");
		columnNm.addElement("Password");
		columnNm.addElement("Department");
		columnNm.addElement("Academic Year");
		columnNm.addElement("Phone No");
		columnNm.addElement("Gender");
		columnNm.addElement("Username");
		columnNm.addElement("ID");
		columnNm.addElement("Administrator");
		
		table = new JTable(dt, columnNm);
		table.setRowMargin(3);
		table.setGridColor(Color.WHITE);
		table.setForeground(Color.WHITE);
		table.setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Book Antiqua", Font.BOLD, 13));
		table.setFont(new Font("Century", Font.PLAIN, 13));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setColumnSelectionAllowed(false);
		table.setBackground(Color.GRAY);
		
		JScrollPane scrollPanee = new JScrollPane(table);
		scrollPanee.setBounds(10, 49, 715, 250);
		scrollPanee.setVisible(true);
		scrollPanee.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		home.add(scrollPanee);
		
		JPanel Adminis = new JPanel();
		Adminis.setLayout(null);
		Adminis.setName("");
		Adminis.setBackground(Color.GRAY);
		tabbedPane.addTab("Administration", null, Adminis, null);
		
		JLabel lblNewLabel_1 = new JLabel("Grant administration privilage : ");
		lblNewLabel_1.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 52, 209, 27);
		Adminis.add(lblNewLabel_1);
		
		usrNm = new JTextField();
		usrNm.setBackground(Color.LIGHT_GRAY);
		usrNm.setBounds(125, 9, 179, 27);
		Adminis.add(usrNm);
		usrNm.setColumns(10);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setOpaque(false);
		rdbtnYes.setForeground(new Color(0, 0, 51));
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnYes.setBackground(Color.GRAY);
		rdbtnYes.setBounds(227, 52, 60, 23);
		Adminis.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setOpaque(false);
		rdbtnNo.setForeground(new Color(0, 0, 51));
		rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBackground(Color.GRAY);
		rdbtnNo.setBounds(289, 52, 60, 23);
		Adminis.add(rdbtnNo);
		
		JLabel lblEnterUsername = new JLabel("Enter Username :");
		lblEnterUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterUsername.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblEnterUsername.setBounds(10, 11, 116, 27);
		Adminis.add(lblEnterUsername);
		
		JLabel lblChangePassword = new JLabel("Change Password to :");
		lblChangePassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblChangePassword.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblChangePassword.setBounds(10, 166, 141, 27);
		Adminis.add(lblChangePassword);
		
		JLabel lblChangeAcademicYear = new JLabel("Change Academic Year to :");
		lblChangeAcademicYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblChangeAcademicYear.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblChangeAcademicYear.setBounds(10, 204, 167, 27);
		Adminis.add(lblChangeAcademicYear);
		
		JLabel lblChangeFirstnameAnd = new JLabel("Change name to :");
		lblChangeFirstnameAnd.setHorizontalAlignment(SwingConstants.LEFT);
		lblChangeFirstnameAnd.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblChangeFirstnameAnd.setBounds(10, 90, 141, 27);
		Adminis.add(lblChangeFirstnameAnd);
		
		JLabel lblChangeDepartment = new JLabel("Change email to :");
		lblChangeDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		lblChangeDepartment.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblChangeDepartment.setBounds(10, 128, 141, 27);
		Adminis.add(lblChangeDepartment);
		
		JLabel lblChangePhoneNo = new JLabel("Change Phone No to :");
		lblChangePhoneNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblChangePhoneNo.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblChangePhoneNo.setBounds(10, 242, 122, 27);
		Adminis.add(lblChangePhoneNo);
		
		JButton btnNewButton = new JButton("Delete User");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(352, 278, 106, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (usrNm.getText().equals("")) {
						throw new java.sql.SQLIntegrityConstraintViolationException();
					}
					String del = "DELETE FROM MEMBERS WHERE USER = ?";
					PreparedStatement prst = conn.prepareStatement(del);
					prst.setString(1, usrNm.getText());
					prst.executeUpdate();
					JOptionPane.showMessageDialog(null, String.format("%s is deleted", usrNm.getText()));
				}catch(java.sql.SQLIntegrityConstraintViolationException k) {
					JOptionPane.showMessageDialog(null, "Enter the username");
				}
				catch(Exception d) {
					JOptionPane.showMessageDialog(null, "There is no user with this username");
				}
			}
		});
		Adminis.add(btnNewButton);
		
		fName = new JTextField();
		fName.setColumns(10);
		fName.setBackground(Color.LIGHT_GRAY);
		fName.setBounds(187, 88, 179, 27);
		Adminis.add(fName);
		
		eField = new JTextField();
		eField.setColumns(10);
		eField.setBackground(Color.LIGHT_GRAY);
		eField.setBounds(187, 126, 179, 27);
		Adminis.add(eField);
		
		pField = new JTextField();
		pField.setColumns(10);
		pField.setBackground(Color.LIGHT_GRAY);
		pField.setBounds(187, 164, 179, 27);
		Adminis.add(pField);
		
		phoField = new JTextField();
		phoField.setColumns(10);
		phoField.setBackground(Color.LIGHT_GRAY);
		phoField.setBounds(187, 240, 179, 27);
		Adminis.add(phoField);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBackground(Color.LIGHT_GRAY);
		lName.setBounds(430, 88, 167, 27);
		Adminis.add(lName);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Freshman", "Second Year", "Third Year", "Fourth Year", "Fifth Year"}));
		comboBox.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(187, 204, 100, 22);
		Adminis.add(comboBox);
		
		JRadioButton rdbtnPresent = new JRadioButton("Abscent");
		rdbtnPresent.setOpaque(false);
		rdbtnPresent.setForeground(new Color(0, 0, 51));
		buttonGroup2.add(rdbtnPresent);
		rdbtnPresent.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnPresent.setBackground(Color.GRAY);
		rdbtnPresent.setBounds(516, 164, 81, 23);
		Adminis.add(rdbtnPresent);
		
		JRadioButton rdbtnPermission = new JRadioButton("Permission");
		rdbtnPermission.setOpaque(false);
		rdbtnPermission.setForeground(new Color(0, 0, 51));
		rdbtnPermission.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup2.add(rdbtnPermission);
		rdbtnPermission.setBackground(Color.GRAY);
		rdbtnPermission.setBounds(600, 164, 100, 23);
		Adminis.add(rdbtnPermission);
		
		JComboBox datecomboBox = new JComboBox();
		datecomboBox.setFont(new Font("Sitka Heading", Font.PLAIN, 15));
		datecomboBox.setBackground(Color.LIGHT_GRAY);
		datecomboBox.setBounds(516, 128, 122, 22);
		Adminis.add(datecomboBox);
		
		JButton btnSubmit = new JButton("Update");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBounds(187, 278, 106, 27);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q = "SELECT USER FROM MEMBERS WHERE USER = ?";
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, usrNm.getText());
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()) {
				        name = rs.getString(1);
					}
					if (!name.equals("")) {
						String admi = null;
						String fname = fName.getText();
						String lname = lName.getText();
						String email = eField.getText();
						String pass = pField.getText();
						String phone = phoField.getText();
						String year = comboBox.getSelectedItem().toString();
						String attend = datecomboBox.getSelectedItem().toString();
						if(rdbtnYes.isSelected())
							admi = "yes";
						else if(rdbtnNo.isSelected())
							admi = "no";
						if(rdbtnPresent.isSelected())
							attend = "Abscent";
						else if(rdbtnPermission.isSelected())
							attend = "Permission";
						else {
							attend = "Present";
						}
						try {
							String query = "UPDATE MEMBERS SET FirstName = ?, LastName = ?, email = ?, password = ?, Admin = ?, phone = ?, year = ?, attendance = ? WHERE user = ?";
							PreparedStatement pst = conn.prepareStatement(query);
							if(fname.isBlank()) {
								JOptionPane.showMessageDialog(null, "First name is blank", "Warning", 1);
								return;
							}else {
								pst.setString(1, fname);
							}
							if(lname.isBlank()) {
								JOptionPane.showMessageDialog(null, "Last name is blank", "Warning", 1);
								return;
							}else {
								pst.setString(2, lname);
							}if(email.isBlank()) {
								JOptionPane.showMessageDialog(null, "Email address is blank", "Warning", 1);
								return;
							}else {
								pst.setString(3, email);
							}if(pass.isBlank()) {
								JOptionPane.showMessageDialog(null, "Password is blank", "Warning", 1);
								return;
							}else {
								pst.setString(4, pass);
							}
							pst.setString(5, admi);
							if(phone.isBlank()) {
								JOptionPane.showMessageDialog(null, "Phone number is blank", "Warning", 1);
								return;
							}else {
								pst.setString(6, phone);
							}
							pst.setString(7, year);
							pst.setString(8, attend);
							pst.setString(9, usrNm.getText());
							pst.execute();
							try {
								String queryy = "INSERT INTO ATTEN VALUES(?, ?, ?)";
								PreparedStatement pstat = conn.prepareStatement(queryy);
								pstat.setString(1, usrNm.getText());
								pstat.setString(2, datecomboBox.getSelectedItem().toString());
								pstat.setString(3, attend);
								pstat.execute();
								pstat.close();
							}catch(Exception s) {
								System.out.println(s);
							}
							JOptionPane.showMessageDialog(null, String.format("%s's status is updated!", fname),"Success", 1);
							pst.close();
							}catch(java.sql.SQLIntegrityConstraintViolationException e2) {
							JOptionPane.showMessageDialog(null, "This username belongs to another user. Please choose different username","Warning",1);
						}
					}
				}catch(java.lang.NullPointerException j) {
					JOptionPane.showMessageDialog(null, "Enter username");
				}
				catch(Exception e2) {
					System.out.println(e2);
				}
			}
		});
		Adminis.add(btnSubmit);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(314, 9, 73, 27);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q = "SELECT FIRSTNAME, LASTNAME, ADMIN, EMAIL, PASSWORD, YEAR, PHONE FROM MEMBERS WHERE USER = ?";
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, usrNm.getText());
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						fname = rs.getString(1);
						lname = rs.getString(2);
						admi = rs.getString(3);
						email = rs.getString(4);
						pass = rs.getString(5);
						year = rs.getString(6);
						phone = rs.getString(7);
					}
					fName.setText(fname);
					lName.setText(lname);
					eField.setText(email);
					pField.setText(pass);
					phoField.setText(phone);
					comboBox.setSelectedItem(year);
					if (admi.equals("yes")) {
						rdbtnYes.setSelected(true);
					}else {
						rdbtnNo.setSelected(true);
					}
					String daty = "SELECT COUNT(DATEE) FROM EVENTS", cNum = null;
					PreparedStatement prsty = conn.prepareStatement(daty);
					ResultSet rssy = prsty.executeQuery();
					while(rssy.next()) {
						cNum = rssy.getString(1);
					}
					String []date = new String[Integer.parseInt(cNum)];
					
					String dat = "SELECT DATEE FROM EVENTS";
					PreparedStatement prst = conn.prepareStatement(dat);
					ResultSet rss = prst.executeQuery();
					int in = 0;
					while(rss.next()) {
						date[in] = rss.getString(1);
						in++;
					}
					datecomboBox.setModel(new DefaultComboBoxModel(date));
				}catch(Exception es) {
					System.out.println(es);
				}
			}
			});
		Adminis.add(btnSearch);
		
		JLabel lblAttendance = new JLabel("Attendance :");
		lblAttendance.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttendance.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblAttendance.setBounds(430, 166, 80, 27);
		Adminis.add(lblAttendance);
		
		JLabel lblDate_1 = new JLabel("Date :");
		lblDate_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate_1.setFont(new Font("Sitka Heading", Font.BOLD, 13));
		lblDate_1.setBounds(430, 126, 80, 27);
		Adminis.add(lblDate_1);
		
		JPanel eventss = new JPanel();
		eventss.setLayout(null);
		eventss.setName("");
		eventss.setBackground(Color.GRAY);
		tabbedPane.addTab("Adding Events", null, eventss, null);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblTitle.setBounds(73, 23, 151, 27);
		eventss.add(lblTitle);
		
		JLabel lblEventDescription = new JLabel("Event Description :");
		lblEventDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEventDescription.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblEventDescription.setBounds(73, 104, 151, 27);
		eventss.add(lblEventDescription);
		
		title = new JTextField();
		title.setColumns(10);
		title.setBackground(Color.LIGHT_GRAY);
		title.setBounds(245, 22, 403, 27);
		eventss.add(title);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(245, 104, 403, 135);
		
		JLabel lblNotMoreThan = new JLabel("Not more than ~800 words");
		lblNotMoreThan.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotMoreThan.setFont(new Font("Sitka Heading", Font.PLAIN, 14));
		lblNotMoreThan.setBounds(427, 260, 221, 27);
		eventss.add(lblNotMoreThan);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(245, 103, 403, 146);
		scroll.setVisible(true);
		
		eventss.add(scroll);
		
		JButton button = new JButton("Submit");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(245, 260, 106, 27);
		eventss.add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(245, 60, 201, 27);
		eventss.add(textField);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		lblDate.setBounds(73, 61, 151, 27);
		eventss.add(lblDate);
		
		JLabel lblFormatYyyymmdd = new JLabel("Format: YYYY-MM-DD");
		lblFormatYyyymmdd.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormatYyyymmdd.setFont(new Font("Sitka Heading", Font.PLAIN, 14));
		lblFormatYyyymmdd.setBounds(467, 60, 151, 27);
		eventss.add(lblFormatYyyymmdd);
		table.setDefaultEditor(Object.class, null);
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					String query = "INSERT INTO EVENTS VALUES(?, ?, ?, DEFAULT)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, title.getText());
					pst.setString(3, textArea.getText());
					pst.executeUpdate();
					pst.close();
					JOptionPane.showMessageDialog(null, "Submitted!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
	}
		String[][] MyTable(){
			Statement stmt = null;
			String query = "SELECT * FROM MEMBERS";
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				rs.first();
				for(int row = 0; row < 10; row++) {
					for(int col = 1; col < 5; col++) {
						table.setValueAt(rs.getString(col+1), row, col-1);
					}
					rs.next();
				}
				}catch(SQLException e4) {
					e4.printStackTrace();
				}finally {
					if(stmt != null) { try {
						stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}
			}
			return null;
		}
}
