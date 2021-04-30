import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class NewMember extends JFrame {
	static NewMember frameN;
	JPanel contentPane;
	JTextField fnameField;
	JTextField lnameField;
	JTextField passField;
	JTextField rePassField;
	JTextField userNameField;
	JTextField phoneNoField;
	JTextField emailField;
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost/Registerr";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameN = new NewMember();
					frameN.setLocationRelativeTo(null);
					frameN.setResizable(false);
					frameN.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	private JTextField idField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public NewMember() {
		con = Connector.dbconnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 503);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("First name :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(0, 0, 51));
		label.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label.setBounds(10, 71, 108, 22);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Last name :");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(new Color(0, 0, 51));
		label_1.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_1.setBounds(10, 104, 108, 22);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Password :");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(new Color(0, 0, 51));
		label_2.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_2.setBounds(10, 137, 108, 22);
		contentPane.add(label_2);
		
		fnameField = new JTextField();
		fnameField.setSelectionColor(new Color(255, 215, 0));
		fnameField.setOpaque(false);
		fnameField.setForeground(Color.BLACK);
		fnameField.setFont(new Font("Rockwell", Font.BOLD, 13));
		fnameField.setDisabledTextColor(Color.BLACK);
		fnameField.setColumns(10);
		fnameField.setCaretColor(Color.WHITE);
		fnameField.setBorder(UIManager.getBorder("CheckBox.border"));
		fnameField.setBackground(Color.BLACK);
		fnameField.setBounds(128, 75, 213, 20);
		contentPane.add(fnameField);
		
		lnameField = new JTextField();
		lnameField.setSelectionColor(new Color(255, 215, 0));
		lnameField.setOpaque(false);
		lnameField.setForeground(Color.BLACK);
		lnameField.setFont(new Font("Rockwell", Font.BOLD, 13));
		lnameField.setDisabledTextColor(Color.BLACK);
		lnameField.setColumns(10);
		lnameField.setCaretColor(Color.LIGHT_GRAY);
		lnameField.setBorder(UIManager.getBorder("CheckBox.border"));
		lnameField.setBackground(Color.BLACK);
		lnameField.setBounds(128, 106, 213, 20);
		contentPane.add(lnameField);
		
		passField = new JTextField();
		passField.setSelectionColor(new Color(255, 215, 0));
		passField.setOpaque(false);
		passField.setForeground(Color.BLACK);
		passField.setFont(new Font("Rockwell", Font.BOLD, 13));
		passField.setDisabledTextColor(Color.BLACK);
		passField.setColumns(10);
		passField.setCaretColor(Color.LIGHT_GRAY);
		passField.setBorder(UIManager.getBorder("CheckBox.border"));
		passField.setBackground(Color.BLACK);
		passField.setBounds(128, 139, 213, 20);
		contentPane.add(passField);
		
		JLabel label_3 = new JLabel("Re-type password:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(new Color(0, 0, 51));
		label_3.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_3.setBounds(10, 170, 108, 22);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Year:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(new Color(0, 0, 51));
		label_4.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_4.setBounds(10, 305, 108, 22);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Department:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(new Color(0, 0, 51));
		label_5.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_5.setBounds(10, 203, 108, 22);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Username:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(new Color(0, 0, 51));
		label_6.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_6.setBounds(10, 368, 108, 22);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("Phone No:  +251");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(new Color(0, 0, 51));
		label_7.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_7.setBounds(10, 236, 108, 27);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Email:");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(new Color(0, 0, 51));
		label_8.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_8.setBounds(10, 269, 108, 22);
		contentPane.add(label_8);
		
		rePassField = new JTextField();
		rePassField.setSelectionColor(new Color(255, 215, 0));
		rePassField.setOpaque(false);
		rePassField.setForeground(Color.BLACK);
		rePassField.setFont(new Font("Rockwell", Font.BOLD, 13));
		rePassField.setDisabledTextColor(Color.BLACK);
		rePassField.setColumns(10);
		rePassField.setCaretColor(Color.LIGHT_GRAY);
		rePassField.setBorder(UIManager.getBorder("CheckBox.border"));
		rePassField.setBackground(Color.BLACK);
		rePassField.setBounds(128, 170, 213, 20);
		contentPane.add(rePassField);
		
		userNameField = new JTextField();
		userNameField.setSelectionColor(new Color(255, 215, 0));
		userNameField.setOpaque(false);
		userNameField.setForeground(Color.BLACK);
		userNameField.setFont(new Font("Rockwell", Font.BOLD, 13));
		userNameField.setDisabledTextColor(Color.BLACK);
		userNameField.setColumns(10);
		userNameField.setCaretColor(Color.LIGHT_GRAY);
		userNameField.setBorder(UIManager.getBorder("CheckBox.border"));
		userNameField.setBackground(Color.BLACK);
		userNameField.setBounds(128, 370, 213, 20);
		contentPane.add(userNameField);
		
		phoneNoField = new JTextField();
		phoneNoField.setSelectionColor(new Color(255, 215, 0));
		phoneNoField.setOpaque(false);
		phoneNoField.setForeground(Color.BLACK);
		phoneNoField.setFont(new Font("Rockwell", Font.BOLD, 13));
		phoneNoField.setDisabledTextColor(Color.BLACK);
		phoneNoField.setColumns(10);
		phoneNoField.setCaretColor(Color.LIGHT_GRAY);
		phoneNoField.setBorder(UIManager.getBorder("CheckBox.border"));
		phoneNoField.setBackground(Color.BLACK);
		phoneNoField.setBounds(128, 239, 213, 20);
		contentPane.add(phoneNoField);
		
		emailField = new JTextField();
		emailField.setSelectionColor(new Color(255, 215, 0));
		emailField.setOpaque(false);
		emailField.setForeground(Color.BLACK);
		emailField.setFont(new Font("Rockwell", Font.BOLD, 13));
		emailField.setDisabledTextColor(Color.BLACK);
		emailField.setColumns(10);
		emailField.setCaretColor(Color.LIGHT_GRAY);
		emailField.setBorder(UIManager.getBorder("CheckBox.border"));
		emailField.setBackground(Color.BLACK);
		emailField.setBounds(128, 272, 213, 20);
		contentPane.add(emailField);
		
		JLabel label_9 = new JLabel("Sex:");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setForeground(new Color(0, 0, 51));
		label_9.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_9.setBounds(10, 338, 108, 22);
		contentPane.add(label_9);
		
		JRadioButton radioMButton = new JRadioButton("Male");
		radioMButton.setOpaque(false);
		radioMButton.setForeground(new Color(0, 0, 51));
		buttonGroup.add(radioMButton);
		radioMButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioMButton.setBackground(Color.GRAY);
		radioMButton.setBounds(130, 340, 60, 23);
		contentPane.add(radioMButton);
		
		JRadioButton radioFButton = new JRadioButton("Female");
		radioFButton.setOpaque(false);
		radioFButton.setForeground(new Color(0, 0, 51));
		buttonGroup.add(radioFButton);
		radioFButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioFButton.setBackground(Color.GRAY);
		radioFButton.setBounds(235, 340, 70, 23);
		contentPane.add(radioFButton);
		
		JButton button = new JButton("Sign Up");
		button.setBorder(UIManager.getBorder("Button.border"));
		button.setForeground(new Color(0, 51, 51));
		button.setFont(new Font("Sitka Display", Font.BOLD, 14));
		button.setBackground(new Color(192, 192, 192));
		button.setBounds(269, 426, 92, 27);
		contentPane.add(button);
		
		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setBackground(Color.LIGHT_GRAY);
		yearComboBox.setFont(new Font("Sitka Display", Font.BOLD, 12));
		yearComboBox.setModel(new DefaultComboBoxModel(new String[] {"Freshman", "Second Year", "Third Year", "Fourth Year", "Fifth Year"}));
		yearComboBox.setBounds(128, 305, 213, 22);
		contentPane.add(yearComboBox);
		
		JComboBox deptComboBox = new JComboBox();
		deptComboBox.setFont(new Font("Sitka Display", Font.BOLD, 12));
		deptComboBox.setBackground(Color.LIGHT_GRAY);
		deptComboBox.setModel(new DefaultComboBoxModel(new String[] {"Computer Science and Engineering", "Architecture", "Civil Engineering", "Communication Engineering", "Mechanical Engineering", "Material Engineering", "Power and Control Engineering", "Water Engineering"}));
		deptComboBox.setBounds(128, 201, 213, 22);
		contentPane.add(deptComboBox);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = fnameField.getText();
				String lname = lnameField.getText();
				String email = emailField.getText();
				String pass = passField.getText();
				String rePass = rePassField.getText();
				String dept = deptComboBox.getSelectedItem().toString();
				String year = yearComboBox.getSelectedItem().toString();
				String phone = phoneNoField.getText();
				String sex = null;
				String user = userNameField.getText();
				String id = idField.getText();
				String admi = "no";
				if(radioMButton.isSelected())
					sex = "M";
				else if(radioFButton.isSelected())
					sex = "F";
				
				try {
					String query = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(query);
					if(fname.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your first name", "Warning", 1);
						return;
					}else {
						pst.setString(1, fname);
					}
					if(lname.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your last name", "Warning", 1);
						return;
					}else {
						pst.setString(2, lname);
					}if(pass.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your password", "Warning", 1);
						return;
					}else {
						pst.setString(4, pass);
					}
					if(pass.equals(rePass)) {
					}
					else {
						JOptionPane.showMessageDialog(null, "Your password didn't match", "Warning", 1);
						return;
					}
					pst.setString(5, dept);
					pst.setString(6, year);
				    String regexx = "[0-9]{9}";
				   	if(phone.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your phone number", "Warning", 1);
						return;
					}else if (!phone.matches(regexx)) {
						JOptionPane.showMessageDialog(null, "Please enter valid phone number", "Warning", 1);
						return;
					}
					else {
						pst.setString(7, phone);
					}
					String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
					 
					Pattern pattern = Pattern.compile(regex);
					 
					Matcher matcher = pattern.matcher(email);
					boolean ans = matcher.matches();
					if(email.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your email address", "Warning", 1);
						return;
					}else if (!ans) {
						JOptionPane.showMessageDialog(null, "Please enter valid email address", "Warning", 1);
						return;
					}
					else {
						pst.setString(3, email);
					}
					if(radioMButton.isSelected() || radioFButton.isSelected()) {
						pst.setString(8, sex);
					}else {
						JOptionPane.showMessageDialog(null, "Please select your gender", "Warning", 1);
						return;
					}
					if(user.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your username", "Warning", 1);
						return;
					}else {
						pst.setString(9, user);
					}
					if(id.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter your ID", "Warning", 1);
						return;
					}else {
						pst.setString(10, id);
					}
					pst.setString(11, admi);
					pst.setString(12, "Present");
					pst.execute();
					JOptionPane.showMessageDialog(null, String.format("Congrats %s, you have registered successfully!", fname),"Success", 1);
					pst.close();
					}
				catch(java.sql.SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(null, "This username belongs to another user. Please choose different username","Warning",1);
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		
		JLabel lblWelcomeToAstu = new JLabel("Welcome to ASTU Development Club");
		lblWelcomeToAstu.setOpaque(true);
		lblWelcomeToAstu.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToAstu.setForeground(new Color(255, 102, 0));
		lblWelcomeToAstu.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		lblWelcomeToAstu.setBorder(UIManager.getBorder("DesktopIcon.border"));
		lblWelcomeToAstu.setBackground(new Color(0, 51, 51));
		lblWelcomeToAstu.setBounds(10, 11, 357, 30);
		contentPane.add(lblWelcomeToAstu);
		
		JLabel label_11 = new JLabel("ID:");
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setForeground(new Color(0, 0, 51));
		label_11.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		label_11.setBounds(10, 400, 108, 22);
		contentPane.add(label_11);
		
		idField = new JTextField();
		idField.setSelectionColor(new Color(255, 215, 0));
		idField.setOpaque(false);
		idField.setForeground(Color.BLACK);
		idField.setFont(new Font("Rockwell", Font.BOLD, 13));
		idField.setDisabledTextColor(Color.BLACK);
		idField.setColumns(10);
		idField.setCaretColor(Color.LIGHT_GRAY);
		idField.setBorder(UIManager.getBorder("CheckBox.border"));
		idField.setBackground(Color.BLACK);
		idField.setBounds(128, 401, 213, 20);
		contentPane.add(idField);
	}
}
