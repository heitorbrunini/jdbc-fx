package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class FuncionarioFormFindController {
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
	
	public void onRadioByidAction() {
		bydepartment.setSelected(false);
	}
	public void onRadioBydepartmentAction() {
		byid.setSelected(false);
	}
	
	public void onBtFindAction() {
		System.out.println("Find");
	}

}
