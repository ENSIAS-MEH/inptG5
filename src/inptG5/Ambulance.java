package inptG5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ambulance {
	private int idAmbulance;
	private String isAvailable;
	
	
	static Utility u = new Utility();
	static Connection con = DBconnect.get_connection();
	
	public Ambulance(int idAmbulance, String isAvailable) {
		this.idAmbulance=idAmbulance;
		this.isAvailable=isAvailable;
	}
	
	public int getIdAmbulance() {
		return idAmbulance;
	}
	public void setIdAmbulance(int idAmbulance) {
		this.idAmbulance = idAmbulance;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public static void display(BorderPane bp, Stage stage) {
		ObservableList<Ambulance> list= Ambulance.getData();
		final TableView<Ambulance> table = Ambulance.RHtable(list);
		
		Label t = new Label("Ambulance");
		t.setStyle("-fx-font: 28 CenturyGothic; -fx-font-weight: Bold;");
		
		ChoiceBox av = new ChoiceBox(FXCollections.observableArrayList("true", "false"));
		
		//TextField
		String[] l = {""};
		ArrayList<TextField> tfset = Utility.tf(l);
		tfset.get(0).setVisible(false);
		tfset.get(0).setDisable(true);
		
		//Delete Button
		ImageView ad = u.Imgr("add.png");
		Button add = u.Bwsh("Add", ad);
		add.setMinSize(70, 30);
		
		ImageView de = u.Imgr("del.png");
		Button del = u.Bwsh("Delete", de);
		del.setMinSize(70, 30);
		
		ImageView mod = u.Imgr("mod.png");
		Button modif1 = u.Bwsh("Modify", mod);
		ImageView sav = u.Imgr("save.png");
		Button modif2 = u.Bwsh("Save", sav);
		modif1.setMinSize(70, 30);
		modif2.setMinSize(70, 30);
		
		//VBox
		VBox b = new VBox();
		b.getChildren().addAll(t, tfset.get(0), av);
		
		//HBox gathering Button set
		HBox h = new HBox();
		HBox h2 = new HBox();
		h.setSpacing(20);
		h2.getChildren().add(modif1);
		h.getChildren().addAll( h2, add, del);
		b.getChildren().add(h);
		b.setSpacing(40);
		VBox h3 = new VBox();
		h3.getChildren().addAll(table, b);
		h3.setAlignment(Pos.CENTER);
		h3.setSpacing(30);
		h3.setPadding(new Insets(20,20,20,20));
		//BorderPlan Layout
		bp.setRight(null);
		bp.setLeft(null);
		bp.setCenter(h3);
		BorderPane.setAlignment(h3, Pos.CENTER);
		BorderPane.setMargin(h3, new Insets(20,20,20,20));
//		av.setDisable(true);
		stage.setFullScreen(true);
		
		//Button Set Actions
		add.setOnAction(e->{
			try {
				insert(DBconnect.count("Ambulance", con)+1, (String)av.getValue(), con);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			av.setValue(null);	
			display(bp, stage);
		});
		
		del.setOnAction(e->{
			try {
				Ambulance hr = (Ambulance)table.getSelectionModel().getSelectedItem();
				String query = "DELETE FROM Ambulance WHERE IdAmbulance=?;";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, Integer.toString(hr.getIdAmbulance()));
				pst.executeUpdate();
				display(bp, stage);
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		});
		modif1.setOnAction(e->{
			av.setDisable(false);
			h2.getChildren().clear();
			h2.getChildren().add(modif2);
			try {
				Ambulance hr = (Ambulance)table.getSelectionModel().getSelectedItem();
				tfset.get(0).setText(Integer.toString(hr.getIdAmbulance()));
				av.setDisable(false);
				if (hr.getIsAvailable() == null) {
					av.setValue(null);
				}else if(hr.getIsAvailable().equals("true")) {
					av.getSelectionModel().select(0);
				}else {
					av.getSelectionModel().select(1);
				}
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		});
		modif2.setOnAction(e->{
			try {
				String query = "DELETE FROM Ambulance WHERE IdAmbulance=?;";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, tfset.get(0).getText());
				pst.executeUpdate();
				insert(Integer.parseInt(tfset.get(0).getText()),(String)av.getValue(), con);
				display(bp, stage);
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		});
		
		
	}
	
	
	
	//------------TableView

		public static ObservableList<Ambulance> getData() {
			ObservableList<Ambulance> list= FXCollections.observableArrayList();
			try {
				ResultSet rs = DBconnect.get_result(con, "Select * from Ambulance;");
				while(rs.next()) {
					list.add(new Ambulance(
							rs.getInt(1),
							rs.getString(2)
							));
				}
				}catch(Exception e) {
					e.printStackTrace();
			}
	 		finally {
	 		}
			return list;
		}

		public static TableView<Ambulance> RHtable(ObservableList<Ambulance> list) {
			
			
			TableColumn<Ambulance, Integer> c1 = new TableColumn<>("ID Ambulance");
			c1.setMinWidth(120);
			c1.setCellValueFactory(new PropertyValueFactory<>("idAmbulance"));
			
			TableColumn<Ambulance, String> c2=setscolumn("Availability", 230, "isAvailable");	
			
			TableView<Ambulance> lrh= new TableView<>();
			lrh.setItems(list);
			lrh.getColumns().addAll(c1,c2);
			return lrh;
		}
		
		//---------Set Column
		public static TableColumn<Ambulance, String> setscolumn(String name, int width, String cvf) {
			TableColumn<Ambulance, String> col = new TableColumn<>(name);
			col.setMinWidth(width);
			col.setCellValueFactory(new PropertyValueFactory<>(cvf));
			return col;
		}
		//----------Insert
		public static void insert(int id, String av , Connection con ) throws SQLException {
			String query="INSERT INTO Ambulance Values ('"+id+"','"+av+"');";
			Statement statement;
			statement =con.createStatement();
			statement.executeUpdate(query);
		}
		
		
		
}
