package doctors;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.itextpdf.text.DocumentException;

import Database.PatientHistory;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
/***********************************************************************************************
 * cette classe permet de créer l'interface du traitement du patient et donne la main au docteur pour entrer la maladie 
 * du patient et le traitement qu'il doit avoir et enregistre ces informations dans la base de donnée (historique du patient)
 * afin de d'inmprimer un reçu de traitement et l'imprimer pour le donner au patient 
 * @author User
 *
 */
public class treat {

	public JFrame frame;
	private static Connection connection;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public treat(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		DateFormat format1=new SimpleDateFormat("yyyy/MM/dd");
		frame = new JFrame();
		frame.setBounds(100, 100, 1366,768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		label_3.setBounds(1090, 29, 250, 76);
		
		label_3.setText(format.format(date));
		frame.getContentPane().add(label_3);
		
		JLabel lblId = new JLabel("id Doctor:");
		lblId.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblId.setBounds(38, 109, 123, 42);
		frame.getContentPane().add(lblId);
		
		JLabel lblDisease = new JLabel("Disease:");
		lblDisease.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblDisease.setBounds(333, 109, 123, 42);
		frame.getContentPane().add(lblDisease);
		
		JLabel lblTreatement = new JLabel("Treatement:");
		lblTreatement.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblTreatement.setBounds(599, 109, 151, 42);
		frame.getContentPane().add(lblTreatement);
		
		JLabel lblVisitDate = new JLabel("Visit Date:");
		lblVisitDate.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblVisitDate.setBounds(874, 109, 123, 42);
		frame.getContentPane().add(lblVisitDate);
		
		JLabel lblA = new JLabel("Amount:");
		lblA.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblA.setBounds(1118, 116, 123, 42);
		frame.getContentPane().add(lblA);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 161, 180, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 160, 215, 104);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(558, 162, 213, 102);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(826, 162, 191, 42);
		textField_1.setText(label_3.getText());
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(1070, 161, 180, 42);
		frame.getContentPane().add(textField_2);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(274, 338, 692, 238);
		frame.getContentPane().add(scrollPane1);
	
		JTable table = new JTable();
		
		table.setFont(new Font("Simplified Arabic", Font.PLAIN, 20));
		scrollPane1.setViewportView(table);
		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JOptionPane.showMessageDialog(null,"traité avec succès");
				
				try {
					CreatePdf.createPdf("1",(String)(textField.getText()),(String)(textArea.getText()),(String)(textArea_1.getText()),(String)(textField_1.getText()),(String)(textField_2.getText()));
				} catch (DocumentException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					PatientHistory.insert(1,Integer.parseInt(((String)(textField.getText()))),(String)(textArea.getText()),(String)(textArea_1.getText()),date,Integer.parseInt((String)(textField_2.getText())));
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 PatientHistory.update(table,PatientHistory.getResultSetByPatient1(1));
				
				
			}
		});
		btnConfirmer.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnConfirmer.setBounds(1134, 647, 159, 49);
		frame.getContentPane().add(btnConfirmer);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PatientTreatment p=new PatientTreatment(id);
				frame.dispose();
				p.frame.setVisible(true);
				
				
			}
		});
		btnRetour.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnRetour.setBounds(57, 647, 159, 49);
		frame.getContentPane().add(btnRetour);
		
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1350, 730);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(0, 0, 1350, 730);
		frame.getContentPane().add(label);
		label.setIcon(new ImageIcon("pic/blue1.jpg"));
	}
}
