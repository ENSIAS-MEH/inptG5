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

public class Emergency {
	private int idEm;
	private int idPa;
	private String occd;
	private String resd;
	private String loc;
	private double lat;
	private double lon;
	private int prio;
	private String stat;
	
	static Utility u = new Utility();
	static Connection con = DBconnect.get_connection();
	
	public Emergency(int idEm, int idPa, String occd, String resd, String loc, double lat, double lon, int prio, String stat) {
		this.idEm = idEm;
		this.idPa = idPa;
		this.occd = occd;
		this.resd = resd;
		this.loc = loc;
		this.lat = lat;
		this.lon = lon;
		this.prio = prio;
		this.stat = stat;
	}
	
	public int getIdEm() {
		return idEm;
	}
	public void setIdEm(int idEm) {
		this.idEm = idEm;
	}
	public int getIdPa() {
		return idPa;
	}
	public void setIdPa(int idPa) {
		this.idPa = idPa;
	}
	public String getOccd() {
		return occd;
	}
	public void setOccd(String occd) {
		this.occd = occd;
	}
	public String getResd() {
		return resd;
	}
	public void setResd(String resd) {
		this.resd = resd;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public static void display(BorderPane bp, Stage stage) {
		ObservableList<Emergency> list= Emergency.getData();
		TableView<Emergency> table = Emergency.RHtable(list);
		
		Label t = new Label("Emergency");
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
				Emergency hr = (Emergency)table.getSelectionModel().getSelectedItem();
				String query = "DELETE FROM Emergency WHERE IdEmergency=?;";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, Integer.toString(hr.getIdEm()));
				pst.executeUpdate();
				display(bp, stage);
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		});
		
	}
	
	
	//------------TableView

			public static ObservableList<Emergency> getData() {
				ObservableList<Emergency> list= FXCollections.observableArrayList();
				try {
					ResultSet rs = DBconnect.get_result(con, "Select * from Emergency;");
					while(rs.next()) {
						list.add(new Emergency(
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getDouble(6),
								rs.getDouble(7),
								rs.getInt(8),
								rs.getString(9)
								));
					}
					}catch(Exception e) {
						e.printStackTrace();
				}
		 		finally {
		 		}
				return list;
			}

			public static TableView<Emergency> RHtable(ObservableList<Emergency> list) {
				
				
				TableColumn<Emergency, Integer> c1 = new TableColumn<>("ID EMERGENCY");
				c1.setMinWidth(60);
				c1.setCellValueFactory(new PropertyValueFactory<>("idEm"));
				
				TableColumn<Emergency, String> c3=setscolumn("Occurence Date", 170, "occd");
				TableColumn<Emergency, String> c4=setscolumn("Resolution Date", 170, "resd");
				TableColumn<Emergency, String> c5=setscolumn("Location", 170, "loc");
				TableColumn<Emergency, String> c9=setscolumn("Status", 170, "stat");

				TableColumn<Emergency, Integer> c2 = new TableColumn<>("ID Patient");
				c2.setMinWidth(60);
				c2.setCellValueFactory(new PropertyValueFactory<>("idPa"));
				
				TableColumn<Emergency, Integer> c8 = new TableColumn<>("Priority");
				c8.setMinWidth(60);
				c8.setCellValueFactory(new PropertyValueFactory<>("prio"));
				
				TableColumn<Emergency, Double> c6 = new TableColumn<>("Latitude");
				c6.setMinWidth(100);
				c6.setCellValueFactory(new PropertyValueFactory<>("lat"));
				
				TableColumn<Emergency, Double> c7 = new TableColumn<>("Longitude");
				c7.setMinWidth(100);
				c7.setCellValueFactory(new PropertyValueFactory<>("lon"));
				
				TableView<Emergency> lrh= new TableView<>();
				lrh.setItems(list);
				lrh.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9);
				return lrh;
			}
			
			//---------Set Column
			public static TableColumn<Emergency, String> setscolumn(String name, int width, String cvf) {
				TableColumn<Emergency, String> col = new TableColumn<>(name);
				col.setMinWidth(width);
				col.setCellValueFactory(new PropertyValueFactory<>(cvf));
				return col;
			}

}
