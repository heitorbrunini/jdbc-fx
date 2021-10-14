package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DepartmentFormController implements Initializable{

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
	public void onBtsaveAction() {
		Alerts.showAlert("Confirmação", "Criação", "Você criou um novo departamento!",AlertType.INFORMATION );
	};
	@FXML
	public void onBtCancelAction() {
		Alerts.showAlert("Confirmação", "Cancelamento", "Você cancelou adicionar um novo departamento!",AlertType.INFORMATION );
	};
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
