package ECM;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ECM.Home;
import ECM.Map.Map;

class Window
{
	static JFrame frame;
	static JPanel map;

	public static JPanel panel;
	public static CardLayout cl;
	public static JSplitPane splitPane;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
		splitPane.setDividerLocation((int) screenSize.getWidth() / 2);
		splitPane.setEnabled(false);

		frame.setUndecorated(true);
		frame.add(splitPane);
		
		frame.setUndecorated(true);
		frame.setSize((int) (1920 * scalex), (int) (1080 * scaley));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}

	