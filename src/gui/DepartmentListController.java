package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{

	private DepartmentService dept_service;
	@FXML	
	private TableView<Department> tableDepartment;
	@FXML
	private TableColumn<Department, Integer> IdColumn;
	@FXML
	private TableColumn<Department, String> NameColumn;
	@FXML
	private TableColumn<Department, Double> BalanceColumn;
	
	private ObservableList<Department> obslist;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNweAction() {
		System.out.println("acção");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.dept_service = service;
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNodes();
		btNew.getStyleClass().add("button");
	}
	
	private void initalizeNodes() {
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		BalanceColumn.setCellValueFactory(new PropertyValueFactory<>("Balance"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (dept_service == null) {
			throw new IllegalStateException("servico nulo");
		}
		
		List<Department> list = dept_service.findAll();
		obslist = FXCollections.observableArrayList(list);
		tableDepartment.setItems(obslist);
	}
}
