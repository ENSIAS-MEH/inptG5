package doctors;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Database.HumanResource;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.*;


/******************************************************************
 * creation d'une frame qui demande à l'utilisateur(Docteur) un nom d'utilisateur et un mot de passe pour se connecter à son compte
 * et avoir le différentes fonctionnalités
 * @author User
 *
 */
public class login {

	public JFrame frame;
	
	static Dimension screenSize;
	public static float scaley;
	public static float scalex;
    public static Connection connection;
    
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		scaley=(float)screenSize.getHeight()/1080;
		scalex=(float)screenSize.getWidth()/1920;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:database.db");
		}
		catch(Exception e) {e.printStackTrace();}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					login window = new login();
				 
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * l'initialisation du contenu du frame login.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize((int)(600*scalex), (int)(250*scaley));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(Swing.NewLabel("Username",20,250, 60));
		frame.getContentPane().add(Swing.NewLabel("Password",20,250, 110));
		frame.getContentPane().add(Swing.NewImage("pic/logo.png",257, 250, 0, 0));
		JTextField User=Swing.NewTextField(175,32,400,60);			frame.add(User);
		JPasswordField Pass=Swing.NewPasswordField(175,32,400,110);	frame.add(Pass);
		
		User.addActionListener(ae -> 
		{
			Pass.grabFocus();
		});
		
		Pass.addActionListener(ae -> 
		{
			if(((String)(User.getText())).equals("nabil") && ((String)(Pass.getText())).equals("nabil"))
			{
				
				frame.dispose();
				PatientTreatment p=new PatientTreatment();
				p.frame.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			/*
			if(HumanResource.connect((String)(User.getText()),(String)(Pass.getText())))
			{
				;
				frame.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
			}
			*/
		});
		
		JButton exit=Swing.NewButton("x",20,75,20 ,525,0);	frame.add(exit);
		JButton login=Swing.NewButton("Connect",20,180,40,300,175);	frame.add(login);
		
		login.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if(((String)(User.getText())).equals("nabil") && ((String)(Pass.getText())).equals("nabil"))
				{
					
					frame.dispose();
					PatientTreatment p=new PatientTreatment();
					p.frame.setVisible(true);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				/*if(HumanResource.connect(User.getText(),Pass.getText()))
				{
					
					frame.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
				*/
			}
		});

		exit.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		
		frame.setVisible(true);
	}
	
}
