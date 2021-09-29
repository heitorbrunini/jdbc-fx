package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.entities.Department;

public class DepartmentListController implements Initializable{

	@FXML	
	private TableView<Department> tableDeparment;
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
		// TODO Auto-generated method stub
		
	}

}
