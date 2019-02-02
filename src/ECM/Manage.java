package ECM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
import ECM.Map.Map;
import UserInterface.Swing;

/************************************************************
 * Manage is used to Initialize the panel Manage<BR>
 * 
 * @author SouhailMaraoui
 *****************************/
public class Manage
{
	public static JPanel Manage;
	static JSplitPane splitPane;
	static JTable table;
	static String status;
	static int SelectedId = 0;
	static JButton BOption,BWaiting,BProcess,BInProgress,BResolve,BDone;
	static boolean RowSelected = false;
	static float scalex = Login.scalex;
	static float scaley = Login.scaley;
	
	static JLabel ambText,driverText,roomText,physicianText,nurseText;
	static JComboBox<String> useAmb, useDriver, useRoom, usePhysician, useNurse;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/************************************************************
	 * Method.<BR>
	 * 
	 * Initialize the panel Manage where we can manage already entered emergencies.
	 * 
	 * 	@param username 	the username of the current connected user.
	 * 	@param stage 		the tab we want to view in the manage panel.
	 * 
	 *  @return Jpanel containing all the buttons, labels .. of the Manage panel
	 *****************************/
	public static JPanel Init(String username, int stage)
	{
		Manage = new JPanel();
		BOption = Swing.NewButton("", 17, 200, 40, 400, 850);
		table = new JTable();
		if(stage==1)	status = "Waiting";
		else if(stage==2)	status = "Processing";
		else	status = "Resolved";

		RowSelected = false;

		table.setDefaultEditor(Object.class, null);
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Century Gothic", Font.PLAIN, (int) (scalex * 14)));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((int) (scalex * 150), (int) (scaley * 150), (int) (scalex * 1625), (int) (scaley * 675));
		Manage.add(scrollPane);
		scrollPane.setViewportView(table);
		ResultSet rs = Emergency.getResultSetByStatus(status);
		table.setModel(DbUtils.resultSetToTableModel(rs));

		//--------------Waiting------------------------------------------------------------------------------------------------------
		BWaiting = Swing.NewButton("Waiting", new Color(0,200,200), 17, 200, 40, 150, 110);		Manage.add(BWaiting);
		BWaiting.setIcon(new ImageIcon("res/ECM/Manage/WaitingIcon.png"));

		int[] availableAmbId=Ambulance.getAvailble();
		String[] availableAmb=new String[availableAmbId.length];
		for(int i=0;i<availableAmbId.length;i++) availableAmb[i]=String.valueOf(availableAmbId[i]);
		
		int[] availableDriverId=HumanResource.getAvailbleStaffIdByType(4);
		String[] availableDriver=new String[availableDriverId.length];
		for(int i=0;i<availableDriverId.length;i++) availableDriver[i]=String.valueOf(availableDriverId[i]);
		
		ambText=Swing.NewLabel("Ambulance Id to use",Color.white, 20, 200,850);		Manage.add(ambText);
		driverText=Swing.NewLabel("Driver Id to use",Color.white, 20, 600,850);		Manage.add(driverText);
		
		useAmb =Swing.NewComboBox(availableAmb,50,30, 410, 850); Manage.add(useAmb);
		useDriver =Swing.NewComboBox(availableDriver,50,30, 780, 850); Manage.add(useDriver);
		
		BProcess = Swing.NewButton("Process", Color.GRAY, 17, 200, 40, 500, 850);
		if(stage!=1)setWaitingElementVisibilty(false);

		//--------------InProgress---------------------------------------------------------------------------------------------------
		BInProgress = Swing.NewButton("In progress", Color.GRAY, 17, 200, 40, 350, 110);Manage.add(BInProgress);
		BInProgress.setIcon(new ImageIcon("res/ECM/Manage/ProgressIcon.png"));

		int[] availableRoomId=Room.getAvailble();
		String[] availableRoom=new String[availableRoomId.length];
		for(int i=0;i<availableRoomId.length;i++) availableRoom[i]=String.valueOf(availableRoomId[i]);
		
		int[] availablePhysicianId=HumanResource.getAvailbleDoctorIdBySpecialization(12);
		String[] availablePhysician=new String[availablePhysicianId.length];
		for(int i=0;i<availablePhysicianId.length;i++) availablePhysician[i]=String.valueOf(availablePhysicianId[i]);
		
		int[] availableNurseId=HumanResource.getAvailbleStaffIdByType(3);
		String[] availableNurse=new String[availableNurseId.length];
		for(int i=0;i<availableNurseId.length;i++) availableNurse[i]=String.valueOf(availableNurseId[i]);
		
		roomText=Swing.NewLabel("Room Id",Color.white, 20, 200, 850);		Manage.add(roomText);
		physicianText=Swing.NewLabel("Physician Id",Color.white, 20, 600, 850);	Manage.add(physicianText);
		nurseText=Swing.NewLabel("Nurse Id",Color.white, 20, 1000, 850);			Manage.add(nurseText);

		
		useRoom =Swing.NewComboBox(availableRoom,50,30, 305, 850); Manage.add(useRoom);
		usePhysician =Swing.NewComboBox(availablePhysician,50,30, 755, 850); Manage.add(usePhysician);
		useNurse =Swing.NewComboBox(availableNurse,50,30, 1105, 850); Manage.add(useNurse);
		
		if(stage!=2)setInProgressElementVisibilty(false);

		BResolve = Swing.NewButton("Resolve", Color.GRAY, 17, 200, 40, 700, 850);
		
		//--------------Done---------------------------------------------------------------------------------------------------------
		BDone = Swing.NewButton("Resolved", Color.GRAY, 17, 200, 40, 550, 110);			Manage.add(BDone);
		BDone.setIcon(new ImageIcon("res/ECM/Manage/ResolvedIcon.png"));

		JPanel BOptions = new JPanel();
		CardLayout cl = new CardLayout();
		BOptions.setLayout(cl);
		BOptions.setBounds((int) (scalex * 1420), (int) (scaley * 850), (int) (scalex * 200), (int) (scaley * 40));

		BOptions.add(BProcess, "1");
		BOptions.add(BResolve, "2");

		cl.show(BOptions, "1");
		Manage.add(BOptions);

		BWaiting.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				resetColor();
				BWaiting.setBackground(new Color(0,200,200));
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
					
					Manage.removeAll();
					Window.panel.add(Init(username,1), "1");
					Window.cl.show(Window.panel, "1");
					Map.deleteWaypoint();
				}
			}
		});

		BInProgress.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				resetColor();
				BInProgress.setBackground(new Color(0,200,200));

				BOptions.setVisible(true);
				
				status = "Processing";
				resetTable(status);
				
				cl.show(BOptions, "2");
				Map.deleteWaypoint();

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
					Room.setAvailibility(idRoom, false);
					
					int idPhysician=Integer.valueOf(usePhysician.getSelectedItem().toString());
					HumanResource.setAvailablity(idPhysician,false);
					
					int idNurse=Integer.valueOf(useNurse.getSelectedItem().toString());
					HumanResource.setAvailablity(idNurse,false);
					
					int idPatient=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 1).toString());
					InpatientCare.insert(idPatient, idPhysician, idRoom,idNurse);

					Emergency.setStatus(SelectedId, "Resolved");
					
					Manage.removeAll();
					Window.panel.add(Init(username,2), "1");
					Window.cl.show(Window.panel, "1");
					Map.deleteWaypoint();
				}
			}
		});

		BDone.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				resetColor();
				BDone.setBackground(new Color(0,200,200));
				BOptions.setVisible(false);
				
				status = "Resolved";
				resetTable(status);
				Map.deleteWaypoint();

				setWaitingElementVisibilty(false);
				setInProgressElementVisibilty(false);

			}
		});

		JButton Back = Swing.NewButton("Return", Color.black, 17, 200, 40, 1420,  923);
		Back.setIcon(new ImageIcon("res/ECM/Manage/ReturnIcon.png"));
		Manage.add(Back);

		Back.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				Window.splitX=(int)screenSize.getWidth()/2;

				Map.deleteWaypoint();
				Insert.Insertion.removeAll();
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
				double lat=Double.valueOf(table.getValueAt(table.getSelectedRow(), 5).toString());
				double lon=Double.valueOf(table.getValueAt(table.getSelectedRow(), 6).toString());
				double[] info= {lat,lon,15};
				Map.goTo(info);
				BProcess.setBackground(new Color(0, 200, 200));
				BResolve.setBackground(new Color(0, 200, 200));
				RowSelected = true;
			}
		});
		
		Manage.add(Swing.NewImage("res/ECM/Manage/Background.png", 1920, 1080, 0, 0));
		Manage.setLayout(null);

		return Manage;
	}
	
	/************************************************************
	 * Method.<BR>
	 * 
	 * Reset the table contents depending on the status we want to view.
	 * 
	 * 	@param status 	the emergency status.
	 *****************************/
	static void resetTable(String status)
	{
		ResultSet rs = Emergency.getResultSetByStatus(status);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		BProcess.setBackground(Color.gray);
		BResolve.setBackground(Color.gray);
	}
	
	/************************************************************
	 * Method.<BR>
	 * 
	 * To modify the visibility of the element in the "Waiting" tab
	 * 
	 * 	@param bool 	the visibility.
	 *****************************/
	static void setWaitingElementVisibilty(boolean bool)
	{
		ambText.setVisible(bool);
		driverText.setVisible(bool);
		useAmb.setVisible(bool);
		useDriver.setVisible(bool);
	}
	
	/************************************************************
	 * Method.<BR>
	 * 
	 * To modify the visibility of the element in the "Progress" tab
	 * 
	 * 	@param bool 	the visibility.
	 *****************************/
	static void setInProgressElementVisibilty(boolean bool)
	{
		roomText.setVisible(bool);
		physicianText.setVisible(bool);
		nurseText.setVisible(bool);
		useRoom.setVisible(bool);
		usePhysician.setVisible(bool);
		useNurse.setVisible(bool);
	}
	
	static void resetColor()
	{
		BWaiting.setBackground(Color.gray);
		BInProgress.setBackground(Color.gray);
		BDone.setBackground(Color.gray);
	}
}