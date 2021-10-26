package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.entities.Funcionario;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable, DataChangeListener{

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
	public void onBtNweAction() throws IOException {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		createDialogForm("/gui/departmentForm.fxml", stage);
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.dept_service = service;
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNodes();
		btNew.getStyleClass().add("button");
		
		setDepartmentService(new DepartmentService());
		updateTableView();	
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
	
	public void createDialogForm(String absoluteName,Stage parentStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		Pane pane = loader.load();
		//aponta para o controlador dos formulários
		DepartmentFormController controller = loader.getController();
		//adiciona o metodo como listener para a lista de listeners
		controller.subscripeDataChangeListener(this);
		
		Stage dialogobox = new Stage();
		//setar titulo da janela
		dialogobox.setTitle("Criar Departamento");
		//cria uma nova scena, pois é um novo palco
		dialogobox.setScene(new Scene(pane));
		//janela não responsiva 
		dialogobox.setResizable(false);
		//define o pai da nova scena
		dialogobox.initOwner(parentStage);
		//define a modalidade do diálogo (obrigatório)
		dialogobox.initModality(Modality.WINDOW_MODAL);
		dialogobox.showAndWait();
	}

	@Override
	public void onDataChanged() {
		updateTableView();		
	}

	@Override
	public void onDataFind(List<Funcionario> funcionarios) {
		// TODO Auto-generated method stub
	}
}
