package ECM;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Database.HumanResource;

import UserInterface.Swing;

/************************************************************
 * Login create the first frame we see and use to log in.<BR>
 * 
 * @author SouhailMaraoui
 *****************************/
public class Login
{
	
	static Dimension screenSize;
	/**
	 * The horizontal scale of the current screen compared to 1080p to adjust UI element depending on the screen resolution. 
	 */
	public static float scalex;
	/**
	 * The vertical scale of the current screen compared to 1080p to adjust UI element depending on the screen resolution. 
	 */
	public static float scaley;
	
	private String username;
	
	/************************************************************
	 * Method .<BR>
	 * 
	 *The main method of the program where we calculate scalex and scaley and initiate login.
	 * @param args an array of command-line arguments for the application
	 *****************************/
	public static void main(String[] args) 
	{
		Main.Main.main(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		scaley=(float)screenSize.getHeight()/1080;
		scalex=(float)screenSize.getWidth()/1920;
		
		new Login();
	}
	
	/************************************************************
	 * Constructor.<BR>
	 * 
	 * Build the login frame where a user can enter his username and password to connect.
	 *****************************/
	public Login()
	{
		JFrame frame = new JFrame();	
		
		frame.setSize((int)(600*scalex), (int)(250*scaley));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(true);

		frame.add(Swing.NewLabel("Username",20,250, 60));
		frame.add(Swing.NewLabel("Password",20,250, 110));
		frame.add(Swing.NewImage("res/ECM/Login/Logo.png", (int) (600 * 3 / 7), 250, 0, 0));
		
		JTextField User=Swing.NewTextField(175,32,400,60);			frame.add(User);
		JPasswordField Pass=Swing.NewPasswordField(175,32,400,110);	frame.add(Pass);
		
		User.addActionListener(ae -> 
		{
			Pass.grabFocus();
		});
		
		Pass.addActionListener(ae -> 
		{
			if(connect(User,Pass))
			{
				Window.init(username);
				frame.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "An error occured, please try again","Error",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton exit=Swing.NewButton("x",20,75,20 ,525,0);	frame.add(exit);
		JButton login=Swing.NewButton("Connect",20,180,40,300,175);	frame.add(login);
		login.setIcon(new ImageIcon("res/ECM/Login/LoginIcon.png"));
		
		login.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if(connect(User,Pass))
				{
					Window.init(username);
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
	
	/************************************************************
	 * Method.<BR>
	 * 
	 * To check if the entered username and password are correct or not.
	 * @param User The Username JTextfield
	 * @param Pass The Password JTextfield
	 * @return Boolean whether we could connect or not.
	 *****************************/
	protected boolean connect(JTextField User,JPasswordField Pass)
	{
		username=User.getText();
		String password=String.valueOf(Pass.getPassword());
		return HumanResource.connect(username, password);
	}
}