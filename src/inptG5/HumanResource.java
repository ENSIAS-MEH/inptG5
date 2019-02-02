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


public class HumanResource {
	
	private int id;
	private String fn;
	private String ln;
	private String un;
	private String pw;
	private String av;
	private String tp;
	private String sp;
	
	static Utility u = new Utility();
	static Connection con = DBconnect.get_connection();

	//Constructor
	public HumanResource(int id, String fn, String ln, String un, String pw, String av, String tp, String sp) {
		this.id=id;
		this.fn=fn;
		this.ln=ln;
		this.un=un;
		this.pw=pw;
		this.av=av;
		this.tp=tp;
		this.sp=sp;
	}
	
	//Getters & setters --------------------------------------------------
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFn() {
        return fn;
    }
    public void setFn(String fn) {
        this.fn = fn;
    }

	public String getLn() {
		return ln;
	}
	public void setLn(String ln) {
		this.ln=ln;
	}

	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un=un;
	}

	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw=pw;
	}

	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp=tp;
	}
	
	public String getAv() {
		return av;
	}
	public void setAv(String av) {
		this.av=av;
	}
	
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp=sp;
	}
	
	//Display..
	
		public static void display(BorderPane bp, Stage stage) {
			
			ObservableList<HumanResource> list= HumanResource.getData();
			final TableView<HumanResource> table = HumanResource.RHtable(list);
						
			Label t = new Label("Informations");
			t.setStyle("-fx-font: 26 CenturyGothic; -fx-font-weight: Bold;");
			
			//VBox
			VBox b = new VBox();
			b.getChildren().add(t);
			
			//Text Fields
			String[] l = {"First Name","Last Name","UserName","Password",""};
			ArrayList<TextField> tfset = Utility.tf(l);
			tfset.get(4).setVisible(false);
			tfset.get(4).setDisable(true);
			b = Utility.vb(b, tfset);
			
		
			ChoiceBox tp = new ChoiceBox(TypeRH.getAlltp(con));
			ChoiceBox sp = new ChoiceBox( Specialization.getAllsp(con));
			ChoiceBox av = new ChoiceBox(FXCollections.observableArrayList("true", "false"));
			
			ChoiceBox types = tp;
			ChoiceBox specs = sp;

			b.getChildren().addAll(av, types, specs);
			
			//Button set
			ImageView mod = u.Imgr("mod.png");
			Button modif1 = u.Bwsh("Modify", mod);
			ImageView sav = u.Imgr("save.png");
			Button modif2 = u.Bwsh("Save", sav);
			modif1.setMinSize(70, 30);
			modif2.setMinSize(70, 30);

			
			ImageView ad = u.Imgr("add.png");
			Button add = u.Bwsh("Add", ad);
			add.setMinSize(70, 30);
			
			ImageView de = u.Imgr("del.png");
			Button del = u.Bwsh("Delete", de);
			del.setMinSize(70, 30);
			
			ImageView re = u.Imgr("ref.png");
			Button ref = u.Bwsh("Refresh", re);
			ref.setMinSize(70, 30);
			
			
			//HBox gathering Button set
			HBox h = new HBox();
			HBox h2 = new HBox();
			h.setSpacing(20);
			h2.getChildren().add(modif1);
			h.getChildren().addAll( h2, add, del, ref);
			h.setAlignment(Pos.CENTER);			
			b.getChildren().add(h);
			
			//BorderPlan Layout
			bp.setRight(null);
			bp.setLeft(null);
			bp.setCenter(b);
			bp.setRight(table);
			av.getSelectionModel().select(0);
			av.setDisable(true);
			
			BorderPane.setAlignment(table, Pos.CENTER_RIGHT);
			BorderPane.setMargin(table, new Insets(10,10,10,0));
			BorderPane.setMargin(b, new Insets(10,10,10,0));
			stage.setFullScreen(true);
			
			//Button Set Actions
			ref.setOnAction(e->{
				display(bp,stage);
			});
			
			del.setOnAction(e->{
				try {
					HumanResource hr = (HumanResource)table.getSelectionModel().getSelectedItem();
					String query = "DELETE FROM HumanResource WHERE IdStaff=?;";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, Integer.toString(hr.getId()));
					pst.executeUpdate();
					display(bp, stage);
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			});
			
			add.setOnAction(e->{
				
				try {
					insert(DBconnect.count("HumanResource", con)+1,tfset.get(0).getText(), tfset.get(1).getText(), tfset.get(2).getText(), tfset.get(3).getText(),
							"true",
							TypeRH.getIdsq((String)types.getValue(), con), Specialization.getIdsq((String)specs.getValue(), con), con);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tfset.get(0).clear();
				tfset.get(1).clear();
				tfset.get(2).clear();
				tfset.get(3).clear();
				av.setValue(null);
				types.setValue(null);
				specs.setValue(null);
				
			});
			modif1.setOnAction(e->{
				h2.getChildren().clear();
				h2.getChildren().add(modif2);
				try {
					HumanResource hr = (HumanResource)table.getSelectionModel().getSelectedItem();
					tfset.get(0).setText(hr.getFn());
					tfset.get(1).setText(hr.getLn());
					tfset.get(2).setText(hr.getUn());
					tfset.get(3).setText(hr.getPw());
					tfset.get(4).setText(Integer.toString(hr.getId()));
					av.setDisable(false);
					if (hr.getAv() == null) {
						av.setValue(null);
					}else if(hr.getAv().equals("true")) {
						av.getSelectionModel().select(0);
					}else {
						av.getSelectionModel().select(1);
					}
					types.getSelectionModel().select(TypeRH.getIdsq(hr.getTp(), con));
					specs.getSelectionModel().select(Specialization.getIdsq(hr.getSp(), con));
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			});
			modif2.setOnAction(e->{
				try {
					String query = "DELETE FROM HumanResource WHERE IdStaff=?;";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, tfset.get(4).getText());
					pst.executeUpdate();
					insert(Integer.parseInt(tfset.get(4).getText()),tfset.get(0).getText(), tfset.get(1).getText(), tfset.get(2).getText(), tfset.get(3).getText(),
							(String)av.getValue(),
							TypeRH.getIdsq((String)types.getValue(), con), Specialization.getIdsq((String)specs.getValue(), con), con);
					display(bp, stage);
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			});
			
		}
	
	
	//------------TableView

	public static ObservableList<HumanResource> getData() {
		ObservableList<HumanResource> list= FXCollections.observableArrayList();
		try {
			ResultSet rs = DBconnect.get_result(con, "Select * from HumanResource;");
			while(rs.next()) {
				list.add(new HumanResource(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						TypeRH.getNamesq(rs.getInt(7), con),
						Specialization.getNamesq(rs.getInt(8), con)
						));
			}
			}catch(Exception e) {
				e.printStackTrace();
		}
 		finally {
 		}
		return list;
	}

	public static TableView<HumanResource> RHtable(ObservableList<HumanResource> list) {
		
		
		TableColumn<HumanResource, Integer> c1 = new TableColumn<>("ID");
		c1.setMinWidth(30);
		c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<HumanResource, String> c2=setscolumn("First Name", 140, "fn");
		TableColumn<HumanResource, String> c3=setscolumn("Last Name", 140, "ln");
		TableColumn<HumanResource, String> c4=setscolumn("UserName", 140, "un");
		TableColumn<HumanResource, String> c5=setscolumn("Password", 140, "pw");
		TableColumn<HumanResource, String> c6=setscolumn("Availability", 90, "av");
		TableColumn<HumanResource, String> c7=setscolumn("Type", 140, "tp");
		TableColumn<HumanResource, String> c8=setscolumn("Specialization", 140, "sp");
		
		
		TableView<HumanResource> lrh= new TableView<>();
		lrh.setItems(list);
		lrh.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8);
		return lrh;
	}
	
	//----------Insert
	public static void insert(int id, String firstname, String lastname,String username,String password,String av , int idType,int IdSpecialization, Connection con ) throws SQLException {
		String query="INSERT INTO HumanResource Values ('"+id+"','"+firstname+"','"+lastname+"','"+username+"','"+password+"','"+av+"','"+idType+"','"+IdSpecialization+"');";
		Statement statement;
		statement =con.createStatement();
		statement.executeUpdate(query);
	}
	
	//---------getname
	public static String getName(int IdStaff, Connection connection) {
		String R ="";
		try	{
			ResultSet resultSet = DBconnect.get_result(connection, "SELECT FirstName,LastName FROM HumanResource where IdStaff='"+IdStaff+"';");
			while(resultSet.next()){
				R=(resultSet.getString(1)+" "+resultSet.getString(2));
			}
		}catch(Exception e) {System.out.println(e);}
		return R;
	}	
	
	//---------Set Column
	public static TableColumn<HumanResource, String> setscolumn(String name, int width, String cvf) {
		TableColumn<HumanResource, String> col = new TableColumn<>(name);
		col.setMinWidth(width);
		col.setCellValueFactory(new PropertyValueFactory<>(cvf));
		return col;
	}
}