package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	private DepartmentService dept_service = new DepartmentService();
	private List<DataChangeListener> listeners = new ArrayList<>();
	private int operation=0;
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
	private RadioButton radio1;
	@FXML
	private RadioButton radio2;
	@FXML
	private RadioButton radio3;

	// ação de salvar no banco
	@FXML
	public void onBtsaveAction(ActionEvent event) {

		try {
			if (dept_service != null) {
				if /*ATUALIZAR*/ (operation==1) {
					//dept_service.update(new Department(Integer.parseInt(textId.getText()), textNome.getText()));
					Alerts.showAlert("Confirmação", "Atualização", "Você Atualizou um departamento!",AlertType.INFORMATION);
				} /*SALVAR*/ else if (operation==0) {
					if (textNome.getText() == null || textNome.getText().isEmpty()) {
						Labelerror.setText(" preencha o campo!");
					} else {
						// dept_service.create(new Department(textNome.getText()));
						Alerts.showAlert("Confirmação", "Criação", "Você criou um novo departamento!",AlertType.INFORMATION);
					}
				} /*DELETAR*/ else if(operation==2) {
					dept_service.delete(new Department(Integer.parseInt(textId.getText()), textNome.getText()));
					Alerts.showAlert("Confirmação", "Deletar", "Você deletou um departamento!",AlertType.INFORMATION);
				}
				
				notifyDataChangeListeners();
				Alerts.currentStage(event).close();
								
			} else {
				throw new IllegalStateException("falha ao tentar executar a operação");
			}
		} catch (IllegalStateException e) {
			Alerts.showAlert("ERROR", "Falha", e.getMessage(), AlertType.ERROR);
		}
	};

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Alerts.currentStage(event).close();
		Alerts.showAlert("Confirmação", "Cancelamento", "Você cancelou criar/alterar um departamento!",
				AlertType.INFORMATION);
	};

	// Atualizar view
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : listeners) {
			listener.onDataChanged();
		}
	}

	public void subscripeDataChangeListener(DataChangeListener listener) {
		listeners.add(listener);
	}
	//definir se o banco vai atualizar, criar ou deletar
	public void onRadio1Action() {
		textId.setEditable(false);
		radio2.setSelected(false);		
		radio3.setSelected(false);
		this.operation=0;
	}

	public void onRadio2Action() {
		textId.setEditable(true);
		radio1.setSelected(false);
		radio3.setSelected(false);
		this.operation=1;
	}
	public void onRadio3Action() {
		textId.setEditable(true);
		radio1.setSelected(false);
		radio2.setSelected(false);
		this.operation=2;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
