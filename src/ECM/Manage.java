package ECM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.*;
import net.proteanit.sql.DbUtils;

import Database.Emergency;
import UserInterface.Swing;

public class Manage
{
	public static JPanel Manage;
	static JSplitPane splitPane;
	static JTable table;
	static String etat;
	static int SelectedIdIncident = 0;
	static JButton BOption;
	static boolean RowSelected = false;
	static float scalex = Login.scalex;
	static float scaley = Login.scaley;

	public static JPanel Init(String username)
	{
		Manage = new JPanel();
		BOption = Swing.NewButton("", 17, 200, 40, 400, 850);
		table = new JTable();
		etat = "En Attente";
		RowSelected = false;

		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 14)));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((int) (scalex * 50), (int) (scaley * 300), (int) (scalex * 860), (int) (scaley * 400));
		Manage.add(scrollPane);
		scrollPane.setViewportView(table);
		ResultSet rs = Emergency.getResultSetByStatus(etat);
		table.setModel(DbUtils.resultSetToTableModel(rs));

		JButton BWaiting = Swing.NewButton("Waiting", Color.GRAY, 17, 200, 40, 180, 250);		Manage.add(BWaiting);
			JButton BProcess = Swing.NewButton("Process", Color.GRAY, 17, 200, 40, 700, 750);
			
		JButton BInProgress = Swing.NewButton("In progress", Color.GRAY, 17, 200, 40, 380, 250);Manage.add(BInProgress);
			JButton BResolve = Swing.NewButton("Resolve", Color.GRAY, 17, 200, 40, 700, 750);
			
		JButton BDone = Swing.NewButton("Resolved", Color.GRAY, 17, 200, 40, 580, 250);			Manage.add(BDone);

		JPanel BOptions = new JPanel();
		CardLayout cl = new CardLayout();
		BOptions.setLayout(cl);
		BOptions.setBounds((int) (scalex * 400), (int) (scaley * 750), (int) (scalex * 200), (int) (scaley * 40));

		BOptions.add(BProcess, "1");
		BOptions.add(BResolve, "2");

		cl.show(BOptions, "1");
		Manage.add(BOptions);

		BWaiting.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				BOptions.setVisible(true);
				etat = "En Attente";
				ResultSet rs = Emergency.getResultSetByStatus(etat);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				cl.show(BOptions, "1");
				BProcess.setBackground(Color.gray);
				BResolve.setBackground(Color.gray);
			}
		});

		BProcess.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (RowSelected)
				{
				}
			}
		});

		BInProgress.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				BOptions.setVisible(true);
				etat = "En Cours";
				ResultSet rs = Emergency.getResultSetByStatus(etat);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				cl.show(BOptions, "2");
				BProcess.setBackground(Color.gray);
				BResolve.setBackground(Color.gray);
			}
		});

		BResolve.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (RowSelected)
				{
					
					BProcess.setBackground(Color.gray);
					BResolve.setBackground(Color.gray);

					ResultSet rs = Emergency.getResultSetByStatus(etat);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
			}
		});

		BDone.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				BOptions.setVisible(false);
				ResultSet rs = Emergency.getResultSetByStatus(etat);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				BProcess.setBackground(Color.gray);
				BResolve.setBackground(Color.gray);
			}
		});

		JButton Back = Swing.NewButton("Return", Color.black, 17, 200, 40, 400, 800);
		Manage.add(Back);

		Back.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				Insertion.Insertion.removeAll();
				Home.Home.removeAll();
				Manage.removeAll();
				Window.panel.add(Home.Init(username), "0");
				Window.cl.show(Window.panel, "0");
			}
		});

		Manage.add(Swing.NewImage("res/ECM/Home/Manage.png", 960, 1080, 0, 0));
		Manage.setLayout(null);

		return Manage;
	}
}
