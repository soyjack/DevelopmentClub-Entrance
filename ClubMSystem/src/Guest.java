import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Guest extends JFrame {
	static Guest frame;
	private JPanel contentPane;
	private JTextField txtIcpcDevelopmentClub;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Guest();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Guest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 477);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIcpcDevelopmentClub = new JTextField();
		txtIcpcDevelopmentClub.setEnabled(false);
		txtIcpcDevelopmentClub.setEditable(false);
		txtIcpcDevelopmentClub.setDisabledTextColor(Color.BLACK);
		txtIcpcDevelopmentClub.setFont(new Font("Jokerman", Font.BOLD, 16));
		txtIcpcDevelopmentClub.setHorizontalAlignment(SwingConstants.CENTER);
		txtIcpcDevelopmentClub.setText("ICPC Development Club");
		txtIcpcDevelopmentClub.setBackground(Color.LIGHT_GRAY);
		txtIcpcDevelopmentClub.setBounds(80, 27, 278, 40);
		contentPane.add(txtIcpcDevelopmentClub);
		txtIcpcDevelopmentClub.setColumns(10);
		
		JTextArea txtrDuringTheLast = new JTextArea();
		txtrDuringTheLast.setDisabledTextColor(Color.BLACK);
		txtrDuringTheLast.setEnabled(false);
		txtrDuringTheLast.setEditable(false);
		txtrDuringTheLast.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtrDuringTheLast.setText("During the last four decades, Kerala has attained worldwide acclaim for its achievements in the social sector, particularly in health and education system. But the performance of the industrial sector has not been keeping pace with the potential of the state. Government of Kerala has recognized this factor and has instituted bold and forward looking measures to tap the unique strengths of the state aimed at providing a suitable stimulus for industrial growth.  \r\n\r\nThe Department of Industries and Commerce, Government of Kerala, in association With Education Department formulated a scheme to set up \u201CEntrepreneurship Development Clubs\u201D in schools and colleges of the State to inculcate \u201CEntrepreneurship Culture\u201D, amongst youth and equip them with the skills, techniques and confidence to act as torch-bearers of \u201CEnterprise\u201D for the new generation. ");
		txtrDuringTheLast.setLineWrap(true);
		txtrDuringTheLast.setWrapStyleWord(true);
		txtrDuringTheLast.setBackground(Color.GRAY);
		txtrDuringTheLast.setBounds(10, 100, 414, 327);
		contentPane.add(txtrDuringTheLast);
	}
}
