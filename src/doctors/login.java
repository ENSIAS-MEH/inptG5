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



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.*;



public class login {

	private JFrame frame;
	
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
	 * Initialize the contents of the frame.
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
			if(connect(User.getText(),Pass.getText()))
			{
				;
				frame.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton exit=Swing.NewButton("x",20,75,20 ,525,0);	frame.add(exit);
		JButton login=Swing.NewButton("Connect",20,180,40,300,175);	frame.add(login);
		
		login.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if(connect(User.getText(),Pass.getText()))
				{
					
					frame.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
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
	
	public static boolean connect(String username, String password)
	{
		
		try 
		{
			String query="Select Password from HumanResource where Username='"+username+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= ((java.sql.Statement) statement).executeQuery(query);
			if(resultSet.next())
			{
				return (resultSet.getString(1).equals(password));
			}
		}
		catch(Exception e) {e.printStackTrace();}
		return false;
}
		
	}

