package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable{

	@FXML	
	private TableView<Department> tableDepartment;
	@FXML
	private TableColumn<Department, Integer> IdColumn;
	@FXML
	private TableColumn<Department, String> NameColumn;
	@FXML
	private TableColumn<Department, Double> BalanceColumn;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNweAction() {
		System.out.println("acção");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNodes();
		btNew.getStyleClass().add("button");
	}
	
	private void initalizeNodes() {
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		BalanceColumn.setCellValueFactory(new PropertyValueFactory<>("Receita"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

}
