import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class test2
{

	static double ratio = 0.1;
	static double delta = ratio / 50;
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame("JSplitPane");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton p1=new JButton("ttt");
		JButton p2=new JButton("ttt");

		final JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p2);
		Timer timer = new Timer(200, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ratio += delta;
				if (ratio >= 1.0)
				{
					ratio = 1.0;
					delta = -delta;
				} else if (ratio <= 0)
				{
					delta = -delta;
					ratio = 0;
				}
				jsp.setDividerLocation(ratio);
			}
		});

		f.add(jsp);
		f.setSize(200,200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		timer.start();

	}
}