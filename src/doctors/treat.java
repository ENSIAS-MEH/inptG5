package doctors;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class treat {

	private JFrame frame;
	private static Connection connection;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					treat window = new treat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try 
				{
					Class.forName("org.sqlite.JDBC");
					connection = DriverManager.getConnection("jdbc:sqlite:database.db");
				}
				catch(Exception e) {e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public treat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		frame = new JFrame();
		frame.setBounds(100, 100, 1366,768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("Stencil", Font.PLAIN, 23));
		label_3.setBounds(1090, 29, 250, 76);
		
		label_3.setText(format.format(date));
		frame.getContentPane().add(label_3);
		
		JLabel lblId = new JLabel("id History:");
		lblId.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblId.setBounds(38, 109, 123, 42);
		frame.getContentPane().add(lblId);
		
		JLabel lblDisease = new JLabel("Disease:");
		lblDisease.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDisease.setBounds(333, 109, 123, 42);
		frame.getContentPane().add(lblDisease);
		
		JLabel lblTreatement = new JLabel("Treatement:");
		lblTreatement.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblTreatement.setBounds(599, 109, 151, 42);
		frame.getContentPane().add(lblTreatement);
		
		JLabel lblVisitDate = new JLabel("Visit Date:");
		lblVisitDate.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblVisitDate.setBounds(874, 109, 123, 42);
		frame.getContentPane().add(lblVisitDate);
		
		JLabel lblA = new JLabel("Amount:");
		lblA.setFont(new Font("Stencil", Font.PLAIN, 20));
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
				
			}
		});
		btnConfirmer.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnConfirmer.setBounds(1134, 647, 159, 49);
		frame.getContentPane().add(btnConfirmer);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRetour.setFont(new Font("Stencil", Font.PLAIN, 20));
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
