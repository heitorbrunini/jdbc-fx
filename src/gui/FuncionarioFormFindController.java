package gui;

import java.util.ArrayList;
import java.util.List;

import gui.listeners.DataChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.entities.Funcionario;
import model.services.FuncionarioService;

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
	@FXML
	private Button findAll;
	private List<DataChangeListener> listeners = new ArrayList<>();
	private List<Funcionario> found = new ArrayList<>();
	private FuncionarioService service = new FuncionarioService();
	public void onfindAllAction() {
		
	};	
	public void onRadioByidAction() {
		bydepartment.setSelected(false);
	}
	public void onRadioBydepartmentAction() {
		byid.setSelected(false);
	}
	
	public void onBtFindAction() {
		if (bydepartment.isSelected()) {
			found = service.findbyDp(Integer.parseInt(textNome.getText()));
		} else if (byid.isSelected()) {
			found.add(service.findbyid(Integer.parseInt(textId.getText()))) ;
		}
		notifyFindChangeListeners();
	}
	
	// Atualizar view
		private void notifyFindChangeListeners() {
			for (DataChangeListener listener : listeners) {
				listener.onDataFind(found);
			}
		}

		public void subscripeDataChangeListener(DataChangeListener listener) {
			listeners.add(listener);
		}

}
