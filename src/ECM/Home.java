package ECM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import UserInterface.Swing;

/************************************************************
 * Home is used to Initialize the panel Home<BR>
 * 
 * @author SouhailMaraoui
 *****************************/
public class Home
{
	static JPanel Home;
	static JSplitPane splitPane;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/************************************************************
	 * Method.<BR>
	 * 
	 * Initialize the panel Home that would contain all the panels from buttons to labels.
	 * 
	 * 	@param username 	the username of the current connected user.
	 * 	 
	 *  @return Jpanel containing all the buttons, labels, etc... of the Home panel
	 *****************************/
	public static JPanel Init(String username)
	{
		Home = new JPanel();
		Home.add(Swing.NewLabel("Welcome " + username, Color.white, 30, 390, 200));

		JButton BNext = Swing.NewButton("Insert an emergency", 17, 250, 40, 375, 400);
		BNext.setIcon(new ImageIcon("res/ECM/Home/InsertIcon.png"));

		Home.add(BNext);
		JButton BManage = Swing.NewButton("Manage emergencies", 17, 250, 40, 375, 460);
		BManage.setIcon(new ImageIcon("res/ECM/Home/ManageIcon.png"));

		Home.add(BManage);

		JButton BExit = Swing.NewButton("Quit", Color.black, 17, 200, 40, 400, 850);
		BExit.setIcon(new ImageIcon("res/ECM/Home/ExitIcon.png"));

		Home.add(BExit);

		BNext.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Window.panel.add(Insert.Init(username), "1");
				Window.cl.show(Window.panel, "1");
			}
		});

		BManage.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Window.splitX=0;
				Window.timer.start();
				Window.panel.add(Manage.Init(username,1), "2");
				Window.cl.show(Window.panel, "2");
			}
		});

		BExit.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		
		Home.add(Swing.NewImage("res/ECM/Home/Background.png", 1920, 1080, 0, 0));
		
		Home.setLayout(null);
		return Home;
	}

}
