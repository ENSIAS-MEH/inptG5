package ECM;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import ECM.Home;
import ECM.Map.Map;

/************************************************************
 * Window is used to Initialize the frame Window<BR>
 * 
 * @author SouhailMaraoui
 *****************************/
class Window
{
	static JFrame frame;
	static JPanel map;

	public static JPanel panel;
	public static CardLayout cl;
	public static JSplitPane splitPane;
	public static Timer timer;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int splitX=(int) screenSize.getWidth() / 2;
	static int dividerLocation=splitX;

	/************************************************************
	 * Method.<BR>
	 * 
	 * Initialize the frame window that would contain all the panels and cards.
	 * 
	 * 	@param username 	the username of the current connected user.
	 *****************************/
	public static void init(String username)
	{
		float scalex = Login.scalex;
		float scaley = Login.scaley;
		frame = new JFrame();
		
		panel = new JPanel();
		cl = new CardLayout();
		panel.setLayout(cl);

		panel.add(Home.Init(username), "0");

		cl.show(panel, "0");

		map = Map.newMap();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, map, panel);
		splitPane.setEnabled(false);
		splitPane.setDividerLocation(splitX);
		
		/*timer = new Timer(5, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(dividerLocation<splitX)
				{
					int diff=(splitX-dividerLocation)/50;
					if (diff<1) diff=1;
					dividerLocation+=diff;
				}
				else if(dividerLocation>splitX)
				{
					int diff=(dividerLocation-splitX)/50;
					if (diff<1) diff=1;
					dividerLocation-=diff;
				}
				splitPane.setDividerLocation(dividerLocation);
			}
		});*/
		
		timer = new Timer(200, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				splitPane.setDividerLocation(splitX);
			}
		});
		
		frame.add(splitPane);
		
		frame.setUndecorated(true);
		frame.setUndecorated(true);
		frame.setSize((int) (1920 * scalex), (int) (1080 * scaley));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		timer.start();
	}
}

	