import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;

public class LgIn extends JFrame {
	static LgIn frame;
	String admn;
	JPanel contentPane;
	JTextField epTextField;
	JButton button;
	JButton btnLogin;
	JLabel labelUser;
	JLabel labelPass;
	JLabel lblWelcomeToAstu;
	static String user_name;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LgIn();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;

	public LgIn() {
		conn = Connector.dbconnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		epTextField = new JTextField();
		epTextField.setSelectionColor(SystemColor.scrollbar);
		epTextField.setForeground(Color.BLACK);
		epTextField.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		epTextField.setDisabledTextColor(Color.WHITE);
		epTextField.setColumns(10);
		epTextField.setCaretColor(Color.WHITE);
		epTextField.setBorder(UIManager.getBorder("DesktopIcon.border"));
		epTextField.setBackground(Color.LIGHT_GRAY);
		epTextField.setBounds(144, 81, 206, 29);
		contentPane.add(epTextField);
		
		JPasswordField passTextField = new JPasswordField();
		passTextField.setToolTipText("Enter your password here");
		passTextField.setSelectionColor(SystemColor.scrollbar);
		passTextField.setForeground(Color.BLACK);
		passTextField.setFont(new Font("Nirmala UI", Font.BOLD, 11));
		passTextField.setCaretColor(Color.WHITE);
		passTextField.setBorder(UIManager.getBorder("DesktopIcon.border"));
		passTextField.setBackground(Color.LIGHT_GRAY);
		passTextField.setBounds(144, 120, 206, 29);
		contentPane.add(passTextField);
		
		button = new JButton("Register");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				NewMember n = new NewMember();
				n.setResizable(false);
				n.setVisible(true);
				n.setLocationRelativeTo(null);
			}
		});
		button.setToolTipText("Sign up for a new Account");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Bell MT", Font.BOLD, 14));
		button.setBackground(new Color(230, 230, 250));
		button.setBounds(30, 221, 91, 29);
		contentPane.add(button);
		
		btnLogin = new JButton("Login");
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				try {
					String query = "SELECT * FROM MEMBERS WHERE  User = ? and Password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					user_name = epTextField.getText();
					pst.setString(1, user_name);
					pst.setString(2, passTextField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()) {
						count++;
					}
					
					if(user_name == null)
						JOptionPane.showMessageDialog(null, "You haven't entered your username");
					else if(passTextField.getText() == null)
						JOptionPane.showMessageDialog(null, "You haven't entered your password");
					else if(count == 1){
						String q = "SELECT ADMIN FROM MEMBERS WHERE  User = ?";
						PreparedStatement p = conn.prepareStatement(q);
						p.setString(1, LgIn.user_name);
					
						ResultSet rset = pst.executeQuery();
						while (rset.next()) {
					        admn = rset.getString(11);
						}
						if (admn.equalsIgnoreCase("yes")) {
							frame.dispose();
							Adminstrator ad = new Adminstrator();
							ad.setLocationRelativeTo(null);
							ad.setResizable(false);
							ad.setVisible(true);
						}
						else {
							Home hom = new Home();
							frame.dispose();
							hom.setResizable(false);
							hom.setLocationRelativeTo(null);
							hom.setVisible(true);
							user_name = epTextField.getText();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect Username and password, Try Again!", "Warnning", 2);
					}
					rs.close();
					pst.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnLogin.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnLogin.setBackground(new Color(230, 230, 250));
		btnLogin.setBounds(271, 160, 79, 29);
		contentPane.add(btnLogin);
		
		labelUser = new JLabel("Username :");
		labelUser.setHorizontalAlignment(SwingConstants.CENTER);
		labelUser.setForeground(new Color(0, 0, 51));
		labelUser.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		labelUser.setBounds(34, 83, 100, 22);
		contentPane.add(labelUser);
		
		labelPass = new JLabel("Password :");
		labelPass.setHorizontalAlignment(SwingConstants.CENTER);
		labelPass.setForeground(new Color(0, 0, 51));
		labelPass.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		labelPass.setBounds(34, 128, 105, 22);
		contentPane.add(labelPass);
		
		lblWelcomeToAstu = new JLabel("Welcome to ASTU Development Club");
		lblWelcomeToAstu.setOpaque(true);
		lblWelcomeToAstu.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToAstu.setForeground(new Color(255, 102, 0));
		lblWelcomeToAstu.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		lblWelcomeToAstu.setBorder(UIManager.getBorder("DesktopIcon.border"));
		lblWelcomeToAstu.setBackground(new Color(0, 51, 51));
		lblWelcomeToAstu.setBounds(34, 11, 357, 30);
		contentPane.add(lblWelcomeToAstu);
		
		JButton btnGuest = new JButton("Guest");
		btnGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Guest g = new Guest();
				g.setVisible(true);
				g.setLocationRelativeTo(null);
			}
		});
		btnGuest.setToolTipText("Sign up for a new Account");
		btnGuest.setHorizontalAlignment(SwingConstants.LEFT);
		btnGuest.setForeground(Color.BLACK);
		btnGuest.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnGuest.setBackground(new Color(230, 230, 250));
		btnGuest.setAlignmentX(0.5f);
		btnGuest.setBounds(283, 221, 79, 29);
		contentPane.add(btnGuest);
	}
}
