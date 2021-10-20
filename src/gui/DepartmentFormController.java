package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	private DepartmentService dept_service = new DepartmentService();
	private List<DataChangeListener> listeners= new ArrayList<>();
	@FXML
	private TextField textId;
	@FXML
	private TextField textNome;
	@FXML
	private Label Labelerror;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	@FXML
	private ComboBox<String> operation;
	@FXML
	private ObservableList<String> auxlist;
	
	//ação de salvar no banco
	@FXML
	public void onBtsaveAction(ActionEvent event) {
		String Status = operation.getSelectionModel().getSelectedItem();
		try {
			if (dept_service != null) {
				if (Status == "Atualizar") {
					dept_service.update(new Department(Integer.parseInt(textId.getText()),textNome.getText()));
					notifyDataChangeListeners();
					Alerts.currentStage(event).close();
					Alerts.showAlert("Confirmação", "Atualização", "Você Atualizou um departamento!",
							AlertType.INFORMATION);
				} else if (Status == "Criar") {
					dept_service.create(new Department(textNome.getText()));
					System.out.println(textNome.getText());
					notifyDataChangeListeners();
					Alerts.currentStage(event).close();
					Alerts.showAlert("Confirmação", "Criação", "Você criou um novo departamento!",
							AlertType.INFORMATION);
				}
			}
			else {
				throw new IllegalStateException("falha ao tentar executar a operação");
			}
		} catch (IllegalStateException e) {
			Alerts.showAlert("ERROR", "Falha", e.getMessage(), AlertType.ERROR);
		}
	};
	
	public void onComboboxAction() {
		String Status = operation.getSelectionModel().getSelectedItem();
		if (Status == "Atualizar") {
			textId.setEditable(true);
		} else if (Status == "Criar") {
			textId.setText("");
			textId.setEditable(false);
		}
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Alerts.currentStage(event).close();
		Alerts.showAlert("Confirmação", "Cancelamento", "Você cancelou criar/alterar um departamento!",AlertType.INFORMATION);
	};

	//Atualizar view
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener: listeners) {
			listener.onDataChanged();
		}
	}
	public void subscripeDataChangeListener(DataChangeListener listener){
		listeners.add(listener);
	}
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> list = new ArrayList<>();
		list.add("Atualizar");
		list.add("Criar");
		auxlist = FXCollections.observableArrayList(list);
		operation.setItems(auxlist);
	}
	

}
