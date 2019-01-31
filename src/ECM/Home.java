package ECM;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import UserInterface.Swing;

public class Home
{
	public static JPanel Home;
	static JSplitPane splitPane;

	public static JPanel Init(String username)
	{
		Home = new JPanel();
		Home.add(Swing.NewLabel("Welcome " + username, Color.white, 30, 190, 200));

		JButton BNext = Swing.NewButton("Insert an emergency", 17, 250, 40, 375, 400);
		Home.add(BNext);
		JButton BManage = Swing.NewButton("Manage emergencies", 17, 250, 40, 375, 460);
		Home.add(BManage);

		JButton BDisconnect = Swing.NewButton("Disconnect", Color.black, 17, 200, 40, 400, 800);
		JButton BExit = Swing.NewButton("Quit", Color.black, 17, 200, 40, 400, 850);

		Home.add(BDisconnect);
		Home.add(BExit);

		BNext.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Window.panel.add(Insertion.Init(username), "1");
				Window.cl.show(Window.panel, "1");
			}
		});

		BManage.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
			}
		});

		BDisconnect.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				Window.frame.dispose();
				Login.main(null);
			}
		});
		BExit.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		
		Home.add(Swing.NewImage("res/ECM/Home/Background.png", 960, 1080, 0, 0));
		
		Home.setLayout(null);
		return Home;
	}

}
