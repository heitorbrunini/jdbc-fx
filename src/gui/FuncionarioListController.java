package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
	@FXML
	private Button btCreate;
	
	private ObservableList<Funcionario> obslist;
	private FuncionarioService service = new FuncionarioService();	
	
	public void onbtOkAction() throws IOException {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		createDialogForm("/gui/funcionarioForm.fxml", stage,"FuncionarioFormController");
	}
	public void onbtBuscarAction() throws IOException  {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		createDialogForm("/gui/funcionarioFind.fxml", stage,"FuncionarioFormFindController");
	}
	public void onbtCreateAction() throws IOException  {
		Stage stage = (Stage) Main.getMainScene().getWindow();
		createDialogForm("/gui/funcionarioCreateForm.fxml", stage,"FuncionarioFormCreateController");
	}
	private void initalizeNodes() {
		initializeComboBox();
		updateTableView();
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableFuncionarios.prefHeightProperty().bind(stage.heightProperty());
	}
	// Metodos para tabela de funcionarios
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("servico nulo");
		}
		
		List<Funcionario> list = service.findAll();
		obslist = FXCollections.observableArrayList(list);
		tableFuncionarios.setItems(obslist);
	}
	
	//criar formulario para funcionario	
	public void createDialogForm(String absoluteName,Stage parentStage, String type) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		Pane pane = loader.load();
		Stage dialogobox = new Stage();
		//aponta para o controlador dos formulários
		//adiciona o metodo como listener para a lista de listeners
		if (type=="FuncionarioFormController") {
			//setar titulo da janela
			dialogobox.setTitle("Alterar Funcionario");
			FuncionarioFormController controller = loader.getController();
			controller.subscripeDataChangeListener(this);
		}else if(type=="FuncionarioFormFindController") {
			//setar titulo da janela
			dialogobox.setTitle("Buscar Funcionario");
			FuncionarioFormFindController controller = loader.getController();
			controller.subscripeDataChangeListener(this);
		}else if(type=="FuncionarioFormCreateController") {
			//setar titulo da janela
			dialogobox.setTitle("Cadastrar Funcionario");
			FuncionarioFormCreate controller = loader.getController();
			controller.subscripeDataChangeListener(this);
		};		
				
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
	
	public void initializeComboBox() {
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		IdDept.setCellValueFactory(new PropertyValueFactory<>("department"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
		SalaryColumn.setCellValueFactory(new PropertyValueFactory<>("BaseSalary"));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initalizeNodes();
	}
	@Override
	public void onDataChanged() {
		updateTableView();			
	}
	@Override
	public void onDataFind(List<Funcionario> funcionarios) {
		if (service == null) {
			throw new IllegalStateException("servico nulo");
		}
		obslist = FXCollections.observableArrayList(funcionarios);
		tableFuncionarios.setItems(obslist);
	}
	
		
}
