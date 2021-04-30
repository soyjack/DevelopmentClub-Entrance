import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Insets;

public class Home extends JFrame {

	private JPanel contentPane;
	String name, last, id, gender, dep, cYear, oldPass, npass, rpass, eOldPass, gen, ps, us, desc;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	private JTextField oldPasstextField;
	private JTextField newPassTextField;
	private JTextField retypeTextField;
	private JTextField textField;
	private JTable table;

	public Home() {
		setBackground(UIManager.getColor("Button.disabledForeground"));
		conn = Connector.dbconnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 469);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 643, 430);
		contentPane.add(tabbedPane);
		
		JPanel home = new JPanel();
		home.setName("");
		home.setLayout(null);
		home.setBackground(Color.GRAY);
		tabbedPane.addTab("Home", null, home, null);
		
		try {
			String query = "SELECT FirstName, LastName, ID, SEX, DEPT, YEAR FROM MEMBERS WHERE  User = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, LgIn.user_name);
		
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
		        name = rs.getString(1);
		        last = rs.getString(2);
		        id = rs.getString(3);
		        gender = rs.getString(4);
		        dep = rs.getString(5);
		        cYear = rs.getString(6);
			}
			pst.close();
			rs.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		
		JLabel UserFullName = new JLabel(name +" " + last);
		UserFullName.setHorizontalAlignment(SwingConstants.LEFT);
		UserFullName.setFont(new Font("Georgia", Font.PLAIN, 14));
		UserFullName.setBounds(110, 30, 186, 39);
		home.add(UserFullName);
		
		JLabel lblId = new JLabel(id);
		lblId.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setBounds(396, 30, 213, 39);
		home.add(lblId);
		
		JLabel nameLabel = new JLabel("Name :");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		nameLabel.setBounds(10, 30, 89, 39);
		home.add(nameLabel);
		
		JLabel lblId_1 = new JLabel("ID :");
		lblId_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblId_1.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblId_1.setBounds(301, 30, 61, 39);
		home.add(lblId_1);
		
		JLabel lblProgram = new JLabel("Department :");
		lblProgram.setHorizontalAlignment(SwingConstants.LEFT);
		lblProgram.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblProgram.setBounds(301, 80, 103, 39);
		home.add(lblProgram);
		
		JLabel depField = new JLabel(dep);
		depField.setHorizontalAlignment(SwingConstants.LEFT);
		depField.setFont(new Font("Georgia", Font.PLAIN, 14));
		depField.setBounds(396, 80, 232, 39);
		home.add(depField);
		
		JLabel lblClassYear = new JLabel("Class Year :");
		lblClassYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblClassYear.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblClassYear.setBounds(10, 130, 89, 39);
		home.add(lblClassYear);
		
		JLabel cYearField = new JLabel(cYear);
		cYearField.setHorizontalAlignment(SwingConstants.LEFT);
		cYearField.setFont(new Font("Georgia", Font.PLAIN, 14));
		cYearField.setBounds(109, 130, 187, 39);
		home.add(cYearField);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblGender.setBounds(10, 80, 89, 39);
		home.add(lblGender);
		if (gender.equals("M")) {
			gen = "Male";
		}
		else {
			gen = "Female";
		}
		JLabel genderField = new JLabel(gen);
		genderField.setHorizontalAlignment(SwingConstants.LEFT);
		genderField.setFont(new Font("Georgia", Font.PLAIN, 14));
		genderField.setBounds(110, 80, 186, 39);
		home.add(genderField);
		
		JPanel even = new JPanel();
		even.setLayout(null);
		even.setBackground(Color.GRAY);
		tabbedPane.addTab("Events", null, even, null);
		
		JLabel lblNewLabel = new JLabel("Upcoming events");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(205, 11, 205, 39);
		even.add(lblNewLabel);

		Statement stm;
		Vector<Vector<Object>> dt = new Vector<Vector<Object>>();
		try {
			stm = conn.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM EVENTS");
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
		columnNm.addElement("Date");
		columnNm.addElement("Title");
		columnNm.addElement("Descr");
		columnNm.addElement("Event Num");
		
		table = new JTable(dt, columnNm);
		table.setRowMargin(3);
		table.setGridColor(Color.WHITE);
		table.setForeground(Color.WHITE);
		table.setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Book Antiqua", Font.BOLD, 13));
		table.setFont(new Font("Century", Font.PLAIN, 13));
		table.setColumnSelectionAllowed(false);
		table.setBackground(Color.GRAY);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBounds(10, 49, 618, 140);
		scrollPane.setVisible(true);
		even.add(scrollPane);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(89, 200, 442, 31);
		even.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setDisabledTextColor(Color.BLACK);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setSelectionColor(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setEnabled(false);
		textArea.setBounds(10, 242, 618, 149);
		
		JScrollPane scrollArea = new JScrollPane(textArea);
		scrollArea.setFont(new Font("Georgia", Font.PLAIN, 11));
		scrollArea.setBounds(10, 242, 618, 149);
		scrollArea.setVisible(true);
		even.add(scrollArea);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				Statement stmt = null;
				String id = String.format("%d", table.getValueAt(index, 3));
				String query = "SELECT TITLE, DESCR FROM EVENTS WHERE  NUM = "+id;
				try {
					stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					rs.first();
					textField.setText(table.getValueAt(index, 1).toString());
					textArea.setText(table.getValueAt(index, 2).toString());
				}catch(SQLException e4) {
					System.out.println(e4);
				}finally {
					if(stmt != null) { try {
						stmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}}
				}
			}
		});
		
		JPanel cngPass = new JPanel();
		cngPass.setLayout(null);
		cngPass.setBackground(Color.GRAY);
		tabbedPane.addTab("Change Password", null, cngPass, null);
		
		JLabel lblOldPassword = new JLabel("Old Password :");
		lblOldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblOldPassword.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblOldPassword.setBounds(97, 41, 152, 40);
		cngPass.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password :");
		lblNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewPassword.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblNewPassword.setBounds(97, 104, 152, 40);
		cngPass.add(lblNewPassword);
		
		JLabel lblRetypePassword = new JLabel("Re-type Password :");
		lblRetypePassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblRetypePassword.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		lblRetypePassword.setBounds(97, 166, 152, 40);
		cngPass.add(lblRetypePassword);
		
		oldPasstextField = new JTextField();
		oldPasstextField.setSelectionColor(SystemColor.scrollbar);
		oldPasstextField.setForeground(Color.BLACK);
		oldPasstextField.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		oldPasstextField.setDisabledTextColor(Color.WHITE);
		oldPasstextField.setColumns(10);
		oldPasstextField.setCaretColor(Color.WHITE);
		oldPasstextField.setBorder(UIManager.getBorder("DesktopIcon.border"));
		oldPasstextField.setBackground(Color.LIGHT_GRAY);
		oldPasstextField.setBounds(265, 41, 293, 36);
		cngPass.add(oldPasstextField);
		
		newPassTextField = new JTextField();
		newPassTextField.setSelectionColor(SystemColor.scrollbar);
		newPassTextField.setForeground(Color.BLACK);
		newPassTextField.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		newPassTextField.setDisabledTextColor(Color.WHITE);
		newPassTextField.setColumns(10);
		newPassTextField.setCaretColor(Color.WHITE);
		newPassTextField.setBorder(UIManager.getBorder("DesktopIcon.border"));
		newPassTextField.setBackground(Color.LIGHT_GRAY);
		newPassTextField.setBounds(265, 104, 293, 36);
		cngPass.add(newPassTextField);
		
		retypeTextField = new JTextField();
		retypeTextField.setSelectionColor(SystemColor.scrollbar);
		retypeTextField.setForeground(Color.BLACK);
		retypeTextField.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		retypeTextField.setDisabledTextColor(Color.WHITE);
		retypeTextField.setColumns(10);
		retypeTextField.setCaretColor(Color.WHITE);
		retypeTextField.setBorder(UIManager.getBorder("DesktopIcon.border"));
		retypeTextField.setBackground(Color.LIGHT_GRAY);
		retypeTextField.setBounds(265, 170, 293, 36);
		cngPass.add(retypeTextField);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setActionCommand("");
		btnChangePassword.setToolTipText("");
		btnChangePassword.setHorizontalAlignment(SwingConstants.LEFT);
		btnChangePassword.setForeground(Color.BLACK);
		btnChangePassword.setFont(new Font("Bell MT", Font.BOLD, 13));
		btnChangePassword.setBackground(new Color(230, 230, 250));
		btnChangePassword.setBounds(423, 232, 135, 36);
		btnChangePassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					String query = "SELECT PASSWORD FROM MEMBERS WHERE  User = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, LgIn.user_name);
				
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
				        oldPass = rs.getString(1);
					}
					eOldPass = oldPasstextField.getText().toString();
					
					if(eOldPass.equals(null)){
						JOptionPane.showMessageDialog(null, "Enter your old password");
						return;
					}
					if(!oldPass.equals(eOldPass)) {
						JOptionPane.showMessageDialog(null, "Please enter the correct old password");
						return;
					}
					npass = newPassTextField.getText();
					rpass = retypeTextField.getText();
					if(npass.equals("")) {
						JOptionPane.showMessageDialog(null, "Enter the new password");
						return;
					}
					if(rpass.equals("")) {
						JOptionPane.showMessageDialog(null, "Confirm the new password");
						return;
					}
					if(npass.equals(eOldPass)) {
						JOptionPane.showMessageDialog(null, "You entered the old password. Choose different password");
						return;
					}
					if (!npass.equals(rpass)) {
						JOptionPane.showMessageDialog(null, "The password entered didn't match!");
						return;
					}
					rs.close();
					pst.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					if(!npass.equals(rpass) ) {
						JOptionPane.showMessageDialog(null, "Please enter same passwords");
						return;
					}else {
						String que = "UPDATE MEMBERS SET PASSWORD = ? WHERE  User = ?";
						PreparedStatement pstt;
						try {
							pstt = conn.prepareStatement(que);
							pstt.setString(1, npass);
							pstt.setString(2, LgIn.user_name);
							pstt.executeUpdate();
							JOptionPane.showMessageDialog(null, "You password has changed");
							return;
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
		cngPass.add(btnChangePassword);
		
		JPanel attendance = new JPanel();
		attendance.setLayout(null);
		attendance.setBackground(Color.GRAY);
		tabbedPane.addTab("Attendance", null, attendance, null);
		
		JLabel lblNewLabel1 = new JLabel("Only abscent and permission days are shown here");
		lblNewLabel1.setBackground(Color.GRAY);
		lblNewLabel1.setOpaque(true);
		lblNewLabel1.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(60, 11, 459, 34);
		attendance.add(lblNewLabel1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		textArea_1.setEditable(false);
		textArea_1.setMargin(new Insets(2, 4, 2, 2));
		textArea_1.setBorder(new LineBorder(Color.GRAY, 1, true));
		textArea_1.setDisabledTextColor(Color.BLACK);
		textArea_1.setFont(new Font("MingLiU-ExtB", Font.BOLD, 17));
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setBackground(Color.LIGHT_GRAY);
		textArea_1.setBounds(111, 96, 191, 261);		
		
		JScrollPane stxt = new JScrollPane(textArea_1);
		stxt.setBounds(179, 101, 116, 261);
		stxt.setVisible(true);
		attendance.add(stxt);
		try {
			String quer = "SELECT EVENTDATE FROM ATTEN WHERE USERID = ?";
			PreparedStatement pst = conn.prepareStatement(quer);
			pst.setString(1, LgIn.user_name);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
				textArea_1.append(name+"   ");
			}
		}catch(Exception f) {
			System.out.print(f);
		}
		JLabel lblAbscent = new JLabel("Date");
		lblAbscent.setBackground(Color.GRAY);
		lblAbscent.setOpaque(true);
		lblAbscent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbscent.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblAbscent.setBounds(169, 56, 126, 34);
		attendance.add(lblAbscent);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBackground(Color.GRAY);
		lblStatus.setOpaque(true);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblStatus.setBounds(323, 56, 89, 34);
		attendance.add(lblStatus);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setMargin(new Insets(4, 6, 2, 2));
		textArea_2.setDisabledTextColor(Color.BLACK);
		textArea_2.setBorder(null);
		textArea_2.setEnabled(false);
		textArea_2.setEditable(false);
		textArea_2.setFont(new Font("MingLiU-ExtB", Font.BOLD, 17));
		textArea_2.setWrapStyleWord(true);
		textArea_2.setLineWrap(true);
		textArea_2.setBackground(Color.LIGHT_GRAY);
		textArea_2.setBounds(330, 96, 191, 261);
		
		JScrollPane stxt2 = new JScrollPane(textArea_2);
		stxt2.setBounds(323, 102, 101, 261);
		stxt2.setVisible(true);
		try {
			String quer = "SELECT STATUS FROM ATTEN WHERE USERID = ?";
			PreparedStatement pst = conn.prepareStatement(quer);
			pst.setString(1, LgIn.user_name);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
				textArea_2.append(name+" ");
			}
		}catch(Exception f) {
			System.out.print(f);
		}
		attendance.add(stxt2);
		
		JPanel about = new JPanel();
		about.setLayout(null);
		about.setBackground(Color.GRAY);
		tabbedPane.addTab("About", null, about, null);
		
		JLabel label_13 = new JLabel("HAMY Inc.");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		label_13.setBounds(155, 22, 285, 49);
		about.add(label_13);
		
		JLabel label_14 = new JLabel("V 1.0.0.0");
		label_14.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 12));
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(252, 98, 96, 21);
		about.add(label_14);
		
		JTextArea txtrCopyrightHamy = new JTextArea();
		txtrCopyrightHamy.setText("       Copyright \u00A9 HAMY Tech Group 2020, All rights reserved.\r\n\t\t\r\n                     ASTU Development Club Management.\r\n\r\n\tBuilding Date : - February 21, 2021\r\n\r\n You can report issues to me via e-mail: yakobassefa12@gmail.com\r\n");
		txtrCopyrightHamy.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtrCopyrightHamy.setEditable(false);
		txtrCopyrightHamy.setColumns(10);
		txtrCopyrightHamy.setBackground(Color.LIGHT_GRAY);
		txtrCopyrightHamy.setBounds(94, 168, 451, 173);
		about.add(txtrCopyrightHamy);		
	}
	String[][] myTable(){
		Statement stmt = null;
		String query = "SELECT * FROM EVENTS";
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
			}catch (SQLException e1) {
				e1.printStackTrace();
			}}
		}
		return null;
	}
}
