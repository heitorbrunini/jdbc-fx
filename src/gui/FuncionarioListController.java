package gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Funcionario;
import model.services.FuncionarioService;

public class FuncionarioListController implements Initializable, DataChangeListener {
	
	@FXML	
	private TableView<Funcionario> tableFuncionarios;
	@FXML
	private TableColumn<Funcionario, Integer> IdColumn;
	@FXML
	private TableColumn<Funcionario, Integer> IdDept;
	@FXML
	private TableColumn<Funcionario, String> NameColumn;
	@FXML
	private TableColumn<Funcionario, String> EmailColumn;
	@FXML
	private TableColumn<Funcionario, Double> SalaryColumn;
	@FXML
	private TableColumn<Funcionario, Date> birthDateColumn;
	@FXML
	private Button btOk;
	@FXML
	private Button btBuscar;
	
	private ObservableList<Funcionario> obslist;
	private FuncionarioService service = new FuncionarioService();	
	
	public void onbtOkAction() {
		System.out.println("New");
	}
	public void onbtBuscarAction() {
		System.out.println("Buscar");
	}
	private void initalizeNodes() {
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		IdDept.setCellValueFactory(new PropertyValueFactory<>("department"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
		SalaryColumn.setCellValueFactory(new PropertyValueFactory<>("BaseSalary"));
		updateTableView();
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableFuncionarios.prefHeightProperty().bind(stage.heightProperty());
	}
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("servico nulo");
		}
		
		List<Funcionario> list = service.findAll();
		obslist = FXCollections.observableArrayList(list);
		tableFuncionarios.setItems(obslist);
	}
	@Override
	public void onDataChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNodes();		
	}
	
	
	
	
	
}
