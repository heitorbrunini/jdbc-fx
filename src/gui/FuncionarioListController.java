package gui;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Funcionario;

public class FuncionarioListController implements Initializable, DataChangeListener {
	/*	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;
	private Department department;*/
	
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
	
	private ObservableList<Funcionario> obslist;
	
	@FXML
	private Button btOk;
	
	private void initalizeNodes() {
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		IdDept.setCellValueFactory(new PropertyValueFactory<>("department"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		SalaryColumn.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableFuncionarios.prefHeightProperty().bind(stage.heightProperty());
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
