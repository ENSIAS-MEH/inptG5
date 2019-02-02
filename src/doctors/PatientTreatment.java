package doctors;

import java.awt.EventQueue;
import java.text.*;
import java.util.*;
import Database.*;
import Database.Queue;
import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.itextpdf.text.log.SysoCounter;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/******************************************************************************************
 * cette classe contient le frame dans lequel le docteur peut afficher les
 * informations du patient et son historique dans cet hopital et les symptomes
 * qu'il a et la queue des personnesen attente et donne la possibilié de traiter
 * ce patient d'aprés ce frame ou se deconnecter
 * 
 * @author User
 *
 */
public class PatientTreatment
{

	public JFrame frame;
	private JTable table;
	private JScrollPane scrollPane,scrollPane2;
	private JTextField textField;
	private static Connection connection;
	public JTable table2 = new JTable();;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PatientTreatment window = new PatientTreatment(0);
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();

				}
				try
				{
					Class.forName("org.sqlite.JDBC");
					connection = DriverManager.getConnection("jdbc:sqlite:database.db");
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public PatientTreatment(int id)
	{
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id)
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane2=new JScrollPane();
		
		scrollPane.setBounds(10, 719, 439, -707);
		scrollPane2.setBounds(510,500,800,150);
		
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(scrollPane2);
		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0f));
		panel.setBounds(489, 11, 851, 297);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblName.setBounds(33, 32, 89, 39);
		panel.add(lblName);

		JLabel lblAge = new JLabel("Age    :");
		lblAge.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblAge.setBounds(33, 82, 89, 39);
		panel.add(lblAge);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblGender.setBounds(33, 136, 89, 39);
		panel.add(lblGender);

		JLabel lblHeight = new JLabel("Height :");
		lblHeight.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblHeight.setBounds(33, 186, 89, 39);
		panel.add(lblHeight);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblWeight.setBounds(33, 236, 89, 39);
		panel.add(lblWeight);

		JTextField label_3 = new JTextField();
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_3.setBounds(279, 136, 89, 39);
		panel.add(label_3);
		label_3.setEnabled(false);

		JTextField label_2 = new JTextField();
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_2.setBounds(279, 69, 89, 39);
		panel.add(label_2);
		label_2.setEnabled(false);
		
		JTextField label_4 = new JTextField();
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_4.setBounds(279, 186, 89, 39);
		panel.add(label_4);
		label_4.setEnabled(false);

		JTextField label_5 = new JTextField();
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_5.setBounds(279, 236, 89, 39);
		panel.add(label_5);
		label_5.setEnabled(false);
		
		JTextField label_1 = new JTextField();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 32, 423, 635);
		frame.getContentPane().add(scrollPane);

		table2 = new JTable();
		ResultSet rs2 = Queue.getResultSet(123);
		table2.setModel(DbUtils.resultSetToTableModel(rs2));
		
		table2.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				int ligne = table2.getSelectedRow();
				int id=Integer.parseInt((table2.getModel().getValueAt(ligne, 2).toString()));
				String[] patientInfo=Patient.getResultSet3(id);
				System.out.println(patientInfo[1]);
				textField.setText(patientInfo[1]+" "+patientInfo[2]);
				label_2.setText(patientInfo[4]);
				label_3.setText(patientInfo[3]);
				label_4.setText(patientInfo[6]);
				label_5.setText(patientInfo[5]);
				label_1.setText(patientInfo[7]);
				ResultSet rs=PatientHistory.getResultSetByPatient1(id);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}
		});
		table2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		scrollPane.setViewportView(table2);

		
		scrollPane2.setViewportView(table);

		textField = new JTextField();

		textField.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		textField.setBounds(194, 32, 189, 30);
		panel.add(textField);
		textField.setEnabled(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(489, 319, 851, 120);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 0, 0, 0f));
		JLabel lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		lblSymptoms.setBounds(21, 11, 152, 39);
		panel_1.add(lblSymptoms);

		JLabel label_6 = new JLabel("");
		label_6.setBounds(31, 61, 799, 110);
		panel_1.add(label_6);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(489, 450, 852, 195);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(0, 0, 0, 0f));

		JLabel lblHistory = new JLabel("History:");
		lblHistory.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		lblHistory.setBounds(26, 11, 152, 39);
		panel_2.add(lblHistory);
		JLabel lblBloodType = new JLabel("Blood type:");
		lblBloodType.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblBloodType.setBounds(392, 236, 137, 39);
		panel.add(lblBloodType);

		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_1.setBounds(614, 236, 89, 39);
		panel.add(label_1);


		JButton btnDconnexion = new JButton("D\u00E9connexion");
		btnDconnexion.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				frame.dispose();
				login l = new login();
				l.frame.setVisible(true);
			}
		});
		btnDconnexion.setFont(new Font("Century Gothic", Font.ITALIC, 16));
		btnDconnexion.setBackground(Color.WHITE);
		btnDconnexion.setBounds(64, 673, 166, 33);
		frame.getContentPane().add(btnDconnexion);

		JButton btnNewButton = new JButton("Treat");
		btnNewButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				frame.dispose();
				treat t = new treat(id);
				t.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.ITALIC, 16));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(1160, 673, 166, 33);
		frame.getContentPane().add(btnNewButton);

		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(0, 0, 1350, 730);
		frame.getContentPane().add(label);
		label.setIcon(new ImageIcon("pic/blue1.jpg"));

	}
}
