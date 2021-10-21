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
	private Boolean New=false;
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

	// ação de salvar no banco
	@FXML
	public void onBtsaveAction(ActionEvent event) {

		try {
			if (dept_service != null) {
				if (New==false) {
					//dept_service.update(new Department(Integer.parseInt(textId.getText()), textNome.getText()));
					notifyDataChangeListeners();
					Alerts.currentStage(event).close();
					Alerts.showAlert("Confirmação", "Atualização", "Você Atualizou um departamento!",AlertType.INFORMATION);
				} else if (New) {
					if (textNome.getText() == null || textNome.getText().isEmpty()) {
						Labelerror.setText(" preencha o campo!");
					} else {
						// dept_service.create(new Department(textNome.getText()));
						notifyDataChangeListeners();
						Alerts.currentStage(event).close();
						Alerts.showAlert("Confirmação", "Criação", "Você criou um novo departamento!",
								AlertType.INFORMATION);
					}
				}
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
	//definir se o banco vai atualizar ou criar
	public void onRadio1Action() {
		textId.setEditable(false);
		radio2.setSelected(false);		
		this.New=true;
	}

	public void onRadio2Action() {
		textId.setEditable(true);
		radio1.setSelected(false);
		this.New=false;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
