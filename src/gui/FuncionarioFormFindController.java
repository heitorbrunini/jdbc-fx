package gui;

import java.util.ArrayList;
import java.util.List;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.entities.Funcionario;
import model.services.FuncionarioService;

public class FuncionarioFormFindController {
	@FXML
	private Button cancel;
	@FXML
	private RadioButton byid;
	@FXML
	private RadioButton bydepartment;
	@FXML
	private TextField textId;
	@FXML
	private TextField textNome;
	@FXML
	private Button find;
	@FXML
	private Button findAll;
	private List<DataChangeListener> listeners = new ArrayList<>();
	private List<Funcionario> found = new ArrayList<>();
	private FuncionarioService service = new FuncionarioService();

	public void onRadioByidAction() {
		bydepartment.setSelected(false);
		textId.setEditable(true);
		textNome.setEditable(false);
		textNome.setText("");
	}

	public void onRadioBydepartmentAction() {
		byid.setSelected(false);
		textNome.setEditable(true);
		textId.setEditable(false);
		textId.setText("");
	}

	public void onBtFindAction(ActionEvent event) {
		try {
			if (bydepartment.isSelected()) {
				found = service.findbyDp(Integer.parseInt(textNome.getText()));
			} else if (byid.isSelected()) {
				found.add(service.findbyid(Integer.parseInt(textId.getText())));
			}
			notifyFindChangeListeners();
			Alerts.currentStage(event).close();
		} catch (db.DbException e) {
			Alerts.showAlert("ERROR", "Falha", e.getMessage(), AlertType.ERROR);
		}catch (java.lang.NumberFormatException e) {
			Alerts.showAlert("ERROR", "Falha", "Não foi possível reconhecer o valor inserido", AlertType.ERROR);
		}
	}

	public void onCancelbtAction(ActionEvent event){
		Alerts.currentStage(event).close();
	}
	
	// Atualizar view

	// para encontrar busca
	private void notifyFindChangeListeners() {
		for (DataChangeListener listener : listeners) {
			listener.onDataFind(found);
		}
		
	}

	// para desfazer busca
	public void onFindAllBt(ActionEvent event) {
		for (DataChangeListener listener : listeners) {
			listener.onDataChanged();
		}
		Alerts.currentStage(event).close();
	}
	

	public void subscripeDataChangeListener(DataChangeListener listener) {
		listeners.add(listener);
	}

}
