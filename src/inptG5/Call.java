package inptG5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Call {
	private int idCall;
	private String fname;
	private String lname;
	private String date;
	private int idEm;
	
	static Utility u = new Utility();
	static Connection con = DBconnect.get_connection();
	
	//Constructor 
	public Call(int idCall, String fname, String lname, String date, int idEm) {
		this.idCall=idCall;
		this.fname=fname;
		this.lname=lname;
		this.date=date;
		this.idEm=idEm;
	}
	
	public int getIdCall() {
		return idCall;
	}
	public void setIdCall(int idCall) {
		this.idCall = idCall;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdEm() {
		return idEm;
	}
	public void setIdEm(int idEm) {
		this.idEm = idEm;
	}
	
	public static void display(BorderPane bp, Stage stage) {
		ObservableList<Call> list= Call.getData();
		final TableView<Call> table = Call.RHtable(list);
		
		Label t = new Label("Calls");
		t.setStyle("-fx-font: 28 CenturyGothic; -fx-font-weight: Bold;");
				
		//Delete Button
		ImageView de = u.Imgr("del.png");
		Button del = u.Bwsh("Delete", de);
		del.setMinSize(70, 30);

		//VBox
		VBox b = new VBox();
		b.getChildren().addAll(t,table,del);
		b.setSpacing(15);
		
		//BorderPlan Layout
		bp.setRight(null);
		bp.setLeft(null);
		bp.setCenter(b);
		BorderPane.setMargin(b, new Insets(20,20,20,20));
		stage.setFullScreen(true);
		
		//Button Set Actions
		del.setOnAction(e->{
			try {
				Call hr = (Call)table.getSelectionModel().getSelectedItem();
				String query = "DELETE FROM Call WHERE IdCall=?;";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, Integer.toString(hr.getIdCall()));
				pst.executeUpdate();
				display(bp, stage);
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		});
		
	}
	
	
	
	//------------TableView

		public static ObservableList<Call> getData() {
			ObservableList<Call> list= FXCollections.observableArrayList();
			try {
				ResultSet rs = DBconnect.get_result(con, "Select * from Call;");
				while(rs.next()) {
					list.add(new Call(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
							));
				}
				}catch(Exception e) {
					e.printStackTrace();
			}
	 		finally {
	 		}
			return list;
		}

		public static TableView<Call> RHtable(ObservableList<Call> list) {
			
			
			TableColumn<Call, Integer> c1 = new TableColumn<>("ID Call");
			c1.setMinWidth(80);
			c1.setCellValueFactory(new PropertyValueFactory<>("idCall"));
			
			TableColumn<Call, String> c2=setscolumn("First Name", 170, "fname");
			TableColumn<Call, String> c3=setscolumn("Last Name", 170, "lname");
			TableColumn<Call, String> c4=setscolumn("Date", 170, "date");
			
			TableColumn<Call, Integer> c5 = new TableColumn<>("ID EMERGENCY");
			c1.setMinWidth(80);
			c1.setCellValueFactory(new PropertyValueFactory<>("idEm"));
			
			
			TableView<Call> lrh= new TableView<>();
			lrh.setItems(list);
			lrh.getColumns().addAll(c1,c2,c3,c4,c5);
			return lrh;
		}
		
		//---------Set Column
		public static TableColumn<Call, String> setscolumn(String name, int width, String cvf) {
			TableColumn<Call, String> col = new TableColumn<>(name);
			col.setMinWidth(width);
			col.setCellValueFactory(new PropertyValueFactory<>(cvf));
			return col;
		}

}
