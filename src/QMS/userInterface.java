package QMS;

//public class userInterface

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class userInterface {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userInterface window = new userInterface();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public userInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 784, 462);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel welcome = new JPanel();
		layeredPane.add(welcome, "name_272415156203787");
		welcome.setLayout(null);
		
		JButton btnImANew = new JButton("I'm a new patient");
		btnImANew.setForeground(Color.DARK_GRAY);
		btnImANew.setBackground(Color.CYAN);
		btnImANew.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnImANew.setBounds(284, 207, 216, 48);
		welcome.add(btnImANew);
		
		JLabel lblWelcomeToOur = new JLabel("Welcome to our Hospital");
		lblWelcomeToOur.setForeground(Color.DARK_GRAY);
		lblWelcomeToOur.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToOur.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcomeToOur.setBounds(143, 90, 497, 40);
		welcome.add(lblWelcomeToOur);
		
		JPanel info = new JPanel();
		layeredPane.add(info, "name_272728495836524");
		info.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setForeground(Color.DARK_GRAY);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFirstName.setBounds(46, 93, 161, 37);
		info.add(lblFirstName);
		
		textField = new JTextField();
		textField.setBounds(217, 93, 190, 37);
		info.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setForeground(Color.DARK_GRAY);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastName.setBounds(46, 141, 161, 37);
		info.add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(217, 141, 190, 37);
		info.add(textField_1);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.DARK_GRAY);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAge.setBounds(46, 189, 161, 37);
		info.add(lblAge);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(217, 189, 190, 37);
		info.add(textField_2);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.DARK_GRAY);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGender.setBounds(46, 237, 161, 37);
		info.add(lblGender);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(217, 237, 190, 37);
		info.add(textField_3);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setForeground(Color.DARK_GRAY);
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHeight.setBounds(46, 285, 161, 37);
		info.add(lblHeight);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(217, 285, 190, 37);
		info.add(textField_4);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setForeground(Color.DARK_GRAY);
		lblWeight.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblWeight.setBounds(46, 333, 161, 37);
		info.add(lblWeight);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(217, 333, 190, 37);
		info.add(textField_5);
		
		JLabel lblOptional = new JLabel("Optional");
		lblOptional.setForeground(Color.GRAY);
		lblOptional.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptional.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblOptional.setBounds(417, 285, 97, 37);
		info.add(lblOptional);
		
		JLabel label = new JLabel("Optional");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(417, 333, 97, 37);
		info.add(label);
		
		JLabel lblNewLabel = new JLabel("Please accelerate the process with giving us your information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(95, 11, 594, 53);
		info.add(lblNewLabel);
		
		JPanel purpose = new JPanel();
		layeredPane.add(purpose, "name_273113949385671");
		purpose.setLayout(null);
		
		JButton btnNewButton = new JButton("Diagnostic");
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(152, 112, 164, 62);
		purpose.add(btnNewButton);
		
		JButton btnScan = new JButton("Scan");
		btnScan.setForeground(Color.DARK_GRAY);
		btnScan.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnScan.setBackground(new Color(135, 206, 250));
		btnScan.setBounds(468, 112, 164, 62);
		purpose.add(btnScan);
		
		JButton btnCertificate = new JButton("Certificate");
		btnCertificate.setForeground(Color.DARK_GRAY);
		btnCertificate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCertificate.setBackground(new Color(135, 206, 250));
		btnCertificate.setBounds(152, 286, 164, 62);
		purpose.add(btnCertificate);
		
		JButton btnVaccine = new JButton("Vaccine");
		btnVaccine.setForeground(Color.DARK_GRAY);
		btnVaccine.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnVaccine.setBackground(new Color(135, 206, 250));
		btnVaccine.setBounds(468, 286, 164, 62);
		purpose.add(btnVaccine);
		
		JLabel lblNewLabel_1 = new JLabel("Please tell us the purpose of your visit");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(120, 26, 544, 41);
		purpose.add(lblNewLabel_1);
		
		JButton btnConsultDoctors = new JButton("Consult Doctors");
		btnConsultDoctors.setForeground(Color.DARK_GRAY);
		btnConsultDoctors.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnConsultDoctors.setBackground(new Color(135, 206, 250));
		btnConsultDoctors.setBounds(281, 200, 221, 62);
		purpose.add(btnConsultDoctors);
		
		JPanel consultation = new JPanel();
		layeredPane.add(consultation, "name_273497060914089");
		consultation.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.ORANGE);
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		comboBox.setBounds(246, 172, 292, 36);
		consultation.add(comboBox);
		comboBox.addItem("Neuroscientist");
		comboBox.addItem("Psychologist");
		comboBox.addItem("Surgeon");
		comboBox.addItem("Doctor");
		
		JLabel lblChooseTheDoctors = new JLabel("Choose the Doctor's specialty that you're looking for !");
		lblChooseTheDoctors.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblChooseTheDoctors.setForeground(Color.DARK_GRAY);
		lblChooseTheDoctors.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseTheDoctors.setBounds(66, 27, 652, 36);
		consultation.add(lblChooseTheDoctors);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(307, 288, 170, 46);
		consultation.add(btnNewButton_1);
		
		JPanel printing = new JPanel();
		layeredPane.add(printing, "name_276050841952620");
		printing.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Thanks for submitting");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel_2.setBounds(41, 163, 701, 54);
		printing.add(lblNewLabel_2);
		
		JLabel lblWeHopeYoull = new JLabel("we hope you'll be feeling better soon.");
		lblWeHopeYoull.setForeground(Color.DARK_GRAY);
		lblWeHopeYoull.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeHopeYoull.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWeHopeYoull.setBounds(41, 211, 701, 54);
		printing.add(lblWeHopeYoull);

	}
}
