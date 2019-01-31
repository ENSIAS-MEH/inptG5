package ECM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.*;

import net.proteanit.sql.DbUtils;
import Database.Ambulance;
import Database.AmbulanceUse;
import Database.Emergency;
import Database.HumanResource;
import Database.InpatientCare;
import Database.Room;
import Database.StaffUse;
import UserInterface.Swing;

public class Manage
{
	public static JPanel Manage;
	static JSplitPane splitPane;
	static JTable table;
	static String status;
	static int SelectedId = 0;
	static JButton BOption,BProcess,BResolve;
	static boolean RowSelected = false;
	static float scalex = Login.scalex;
	static float scaley = Login.scaley;
	
	static JLabel ambText,driverText,roomText,physicianText,nurseText;
	static JComboBox<String> useAmb, useDriver, useRoom, usePhysician, useNurse;

	public static JPanel Init(String username)
	{
		Manage = new JPanel();
		BOption = Swing.NewButton("", 17, 200, 40, 400, 850);
		table = new JTable();
		status = "Waiting";
		RowSelected = false;

		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 14)));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((int) (scalex * 50), (int) (scaley * 300), (int) (scalex * 860), (int) (scaley * 400));
		Manage.add(scrollPane);
		scrollPane.setViewportView(table);
		ResultSet rs = Emergency.getResultSetByStatus(status);
		table.setModel(DbUtils.resultSetToTableModel(rs));

		//--------------Waiting------------------------------------------------------------------------------------------------------
		JButton BWaiting = Swing.NewButton("Waiting", Color.GRAY, 17, 200, 40, 180, 250);		Manage.add(BWaiting);
		
		int[] availableAmbId=Ambulance.getAvailble();
		String[] availableAmb=new String[availableAmbId.length];
		for(int i=0;i<availableAmbId.length;i++) availableAmb[i]=String.valueOf(availableAmbId[i]);
		
		int[] availableDriverId=HumanResource.getAvailbleStaffIdByType(4);
		String[] availableDriver=new String[availableDriverId.length];
		for(int i=0;i<availableDriverId.length;i++) availableDriver[i]=String.valueOf(availableDriverId[i]);
		
		ambText=Swing.NewLabel("Ambulance Id to use",Color.white, 20, 200, 725);		Manage.add(ambText);
		driverText=Swing.NewLabel("Driver Id to use",Color.white, 20, 550, 725);		Manage.add(driverText);
		
		useAmb =Swing.NewComboBox(availableAmb,50,30, 405, 725); Manage.add(useAmb);
		useDriver =Swing.NewComboBox(availableDriver,50,30, 700, 725); Manage.add(useDriver);
		
		BProcess = Swing.NewButton("Process", Color.GRAY, 17, 200, 40, 700, 800);
		
		//--------------InProgress---------------------------------------------------------------------------------------------------
		JButton BInProgress = Swing.NewButton("In progress", Color.GRAY, 17, 200, 40, 380, 250);Manage.add(BInProgress);
		
		int[] availableRoomId=Room.getAvailble();
		String[] availableRoom=new String[availableRoomId.length];
		for(int i=0;i<availableRoomId.length;i++) availableRoom[i]=String.valueOf(availableRoomId[i]);
		
		int[] availablePhysicianId=HumanResource.getAvailbleDoctorIdBySpecialization(12);
		String[] availablePhysician=new String[availablePhysicianId.length];
		for(int i=0;i<availablePhysicianId.length;i++) availablePhysician[i]=String.valueOf(availablePhysicianId[i]);
		
		int[] availableNurseId=HumanResource.getAvailbleStaffIdByType(3);
		String[] availableNurse=new String[availableNurseId.length];
		for(int i=0;i<availableNurseId.length;i++) availableNurse[i]=String.valueOf(availableNurseId[i]);
		
		roomText=Swing.NewLabel("Room Id",Color.white, 20, 200, 725);		Manage.add(roomText);
		physicianText=Swing.NewLabel("Physician Id",Color.white, 20, 400, 725);	Manage.add(physicianText);
		nurseText=Swing.NewLabel("Nurse Id",Color.white, 20, 600, 725);			Manage.add(nurseText);

		
		useRoom =Swing.NewComboBox(availableRoom,50,30, 300, 725); Manage.add(useRoom);
		usePhysician =Swing.NewComboBox(availablePhysician,50,30, 525, 725); Manage.add(usePhysician);
		useNurse =Swing.NewComboBox(availableNurse,50,30, 700, 725); Manage.add(useNurse);
		
		setInProgressElementVisibilty(false);

		BResolve = Swing.NewButton("Resolve", Color.GRAY, 17, 200, 40, 700, 800);
		
		//--------------Done---------------------------------------------------------------------------------------------------------
		JButton BDone = Swing.NewButton("Resolved", Color.GRAY, 17, 200, 40, 580, 250);			Manage.add(BDone);

		JPanel BOptions = new JPanel();
		CardLayout cl = new CardLayout();
		BOptions.setLayout(cl);
		BOptions.setBounds((int) (scalex * 400), (int) (scaley * 800), (int) (scalex * 200), (int) (scaley * 40));

		BOptions.add(BProcess, "1");
		BOptions.add(BResolve, "2");

		cl.show(BOptions, "1");
		Manage.add(BOptions);

		BWaiting.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				BOptions.setVisible(true);
				status = "Waiting";
				resetTable(status);
				
				cl.show(BOptions, "1");
				
				setWaitingElementVisibilty(true);
				setInProgressElementVisibilty(false);

			}
		});

		BProcess.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (RowSelected)
				{
					int idAmb=Integer.valueOf(useAmb.getSelectedItem().toString());
					Ambulance.setAvailablity(idAmb,false);
					AmbulanceUse.insert(SelectedId, idAmb);
					
					int idDriver=Integer.valueOf(useDriver.getSelectedItem().toString());
					HumanResource.setAvailablity(idDriver,false);
					StaffUse.insert(SelectedId, idDriver);
					
					Emergency.setStatus(SelectedId, "Processing");
					
					resetTable(status);
				}
			}
		});

		BInProgress.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				BOptions.setVisible(true);
				
				status = "Processing";
				resetTable(status);
				
				cl.show(BOptions, "2");
				
				setWaitingElementVisibilty(false);
				setInProgressElementVisibilty(true);

			}
		});

		BResolve.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (RowSelected)
				{
					int idAmb=AmbulanceUse.getAmbulanceUsed(SelectedId);
					Ambulance.setAvailablity(idAmb,true);
					
					int idDriver=StaffUse.getDriverUsed(SelectedId);
					HumanResource.setAvailablity(idDriver,true);
					
					int idRoom=Integer.valueOf(useRoom.getSelectedItem().toString());
					Room.setStatus(idRoom, false);
					
					int idPhysician=Integer.valueOf(usePhysician.getSelectedItem().toString());
					HumanResource.setAvailablity(idPhysician,false);
					
					int idNurse=Integer.valueOf(useNurse.getSelectedItem().toString());
					HumanResource.setAvailablity(idNurse,false);
					
					int idPatient=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 1).toString());
					InpatientCare.insert(idPatient, idPhysician, idRoom,idNurse);

					Emergency.setStatus(SelectedId, "Resolved");
					
					resetTable(status);
				}
			}
		});

		BDone.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				BOptions.setVisible(false);
				
				status = "Resolved";
				resetTable(status);
				
				setWaitingElementVisibilty(false);
				setInProgressElementVisibilty(false);

			}
		});

		JButton Back = Swing.NewButton("Return", Color.black, 17, 200, 40, 400, 850);
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

		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				SelectedId = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
				BProcess.setBackground(new Color(0, 200, 200));
				BResolve.setBackground(new Color(0, 200, 200));
				RowSelected = true;
			}
		});
		
		Manage.add(Swing.NewImage("res/ECM/Home/Manage.png", 960, 1080, 0, 0));
		Manage.setLayout(null);

		return Manage;
	}
	
	static void resetTable(String status)
	{
		ResultSet rs = Emergency.getResultSetByStatus(status);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		BProcess.setBackground(Color.gray);
		BResolve.setBackground(Color.gray);
	}
	
	static void setWaitingElementVisibilty(boolean bool)
	{
		ambText.setVisible(bool);
		driverText.setVisible(bool);
		useAmb.setVisible(bool);
		useDriver.setVisible(bool);
	}
	
	static void setInProgressElementVisibilty(boolean bool)
	{
		roomText.setVisible(bool);
		physicianText.setVisible(bool);
		nurseText.setVisible(bool);
		useRoom.setVisible(bool);
		usePhysician.setVisible(bool);
		useNurse.setVisible(bool);
	}
}
