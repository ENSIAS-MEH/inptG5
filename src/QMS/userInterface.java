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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import Database.HumanResource;
import Database.Queue;
import Database.Specialization;

public class userInterface {

	public static JPanel printing;
	public static JPanel consultation;
	public static JPanel welcome;
	public static JPanel purpose;
	public static JPanel info;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	patient p;


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
		
		Main.Main.main(null);
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 784, 462);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		welcome = new JPanel();
		layeredPane.add(welcome, "name_272415156203787");
		welcome.setLayout(null);
		
		JButton btnImANew = new JButton("I'm a new patient");
		btnImANew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				layeredPane.removeAll();
				layeredPane.add(info);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
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
		
		info = new JPanel();
		layeredPane.add(info, "name_272728495836524");
		info.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setForeground(Color.DARK_GRAY);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFirstName.setBounds(46, 93, 161, 37);
		info.add(lblFirstName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(217, 93, 190, 37);
		info.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setForeground(Color.DARK_GRAY);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastName.setBounds(46, 141, 161, 37);
		info.add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(217, 141, 190, 37);
		info.add(textField_1);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.DARK_GRAY);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAge.setBounds(46, 189, 161, 37);
		info.add(lblAge);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(217, 189, 190, 37);
		info.add(textField_2);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.DARK_GRAY);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGender.setBounds(46, 237, 161, 37);
		info.add(lblGender);
		
		
		JLabel lblHeight = new JLabel("Height (m)");
		lblHeight.setForeground(Color.DARK_GRAY);
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHeight.setBounds(46, 285, 161, 37);
		info.add(lblHeight);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(217, 285, 190, 37);
		info.add(textField_4);
		
		JLabel lblWeight = new JLabel("Weight (Kg)");
		lblWeight.setForeground(Color.DARK_GRAY);
		lblWeight.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblWeight.setBounds(46, 333, 161, 37);
		info.add(lblWeight);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 15));
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
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!(textField.getText().equals("") || textField_1.getText().equals("") ||
						textField_2.getText().equals(""))) {
				p = new patient(
						textField.getText(),  //first_name
						textField_1.getText(),  //last_name
						Integer.parseInt(textField_2.getText()), //age
						comboBox_1.getSelectedItem().toString(),  //gender
						Double.parseDouble(textField_4.getText()),  //height
						Double.parseDouble(textField_5.getText()),   //weight
						textField_6.getText());  //blood_type
				
				layeredPane.removeAll();
				layeredPane.add(purpose);
				layeredPane.repaint();
				layeredPane.revalidate();
				}else {
					textField.setBackground(new Color(248, 110, 110, 255));
					textField_1.setBackground(new Color(248, 110, 110, 255));
					textField_2.setBackground(new Color(248, 110, 110, 255));
				}
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_2.setBounds(601, 395, 132, 37);
		info.add(btnNewButton_2);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(217, 381, 190, 37);
		info.add(textField_6);
		
		JLabel lblBloodType = new JLabel("Blood Type");
		lblBloodType.setForeground(Color.DARK_GRAY);
		lblBloodType.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBloodType.setBounds(46, 381, 161, 37);
		info.add(lblBloodType);
		
		JLabel label_1 = new JLabel("Optional");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label_1.setBounds(417, 381, 97, 37);
		info.add(label_1);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox_1.setBackground(Color.LIGHT_GRAY);
		comboBox_1.setBounds(217, 237, 190, 37);
		info.add(comboBox_1);
		comboBox_1.addItem("male");
		comboBox_1.addItem("female");
		
		purpose = new JPanel();
		layeredPane.add(purpose, "name_273113949385671");
		purpose.setLayout(null);
		
		JButton btnNewButton = new JButton("Diagnostic");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(printing);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(152, 112, 164, 62);
		purpose.add(btnNewButton);
		
		JButton btnScan = new JButton("Scan");
		btnScan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(printing);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnScan.setForeground(Color.DARK_GRAY);
		btnScan.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnScan.setBackground(new Color(135, 206, 250));
		btnScan.setBounds(468, 112, 164, 62);
		purpose.add(btnScan);
		
		JButton btnCertificate = new JButton("Certificate");
		btnCertificate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(printing);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnCertificate.setForeground(Color.DARK_GRAY);
		btnCertificate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCertificate.setBackground(new Color(135, 206, 250));
		btnCertificate.setBounds(152, 286, 164, 62);
		purpose.add(btnCertificate);
		
		JButton btnVaccine = new JButton("Vaccine");
		btnVaccine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(printing);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
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
		btnConsultDoctors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(consultation);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnConsultDoctors.setForeground(Color.DARK_GRAY);
		btnConsultDoctors.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnConsultDoctors.setBackground(new Color(135, 206, 250));
		btnConsultDoctors.setBounds(281, 200, 221, 62);
		purpose.add(btnConsultDoctors);
		
		consultation = new JPanel();
		layeredPane.add(consultation, "name_273497060914089");
		consultation.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.ORANGE);
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		comboBox.setBounds(246, 172, 292, 36);
		consultation.add(comboBox);
		for(int i = 0; i < Specialization.count(); i++) {
			comboBox.addItem(Specialization.getName(i));
		}
		
		JLabel lblChooseTheDoctors = new JLabel("Choose the Doctor's specialty that you're looking for !");
		lblChooseTheDoctors.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblChooseTheDoctors.setForeground(Color.DARK_GRAY);
		lblChooseTheDoctors.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseTheDoctors.setBounds(66, 27, 652, 36);
		consultation.add(lblChooseTheDoctors);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(Arrays.toString(HumanResource.getDoctorId(comboBox.getSelectedIndex())));
				int[] waitingpatients = new int[HumanResource.getDoctorId(comboBox.getSelectedIndex()).length];
				for (int i : HumanResource.getDoctorId(comboBox.getSelectedIndex())) {
					for (int j = 0 ; j < waitingpatients.length ; j++) {
					waitingpatients[j] = Queue.getWaitingPatientCount(i);
					}
				}
				
				System.out.println(Arrays.toString(waitingpatients));
				
			}
		});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(307, 288, 170, 46);
		consultation.add(btnNewButton_1);
		
		printing = new JPanel();
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
		
		JButton btnNewButton_3 = new JButton("Return to the main page");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(welcome);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_3.setBounds(242, 286, 299, 48);
		printing.add(btnNewButton_3);

	}
}
