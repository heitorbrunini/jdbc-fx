package gui;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.entities.Funcionario;
import model.services.FuncionarioService;

public class FuncionarioFormController implements Initializable {
	private FuncionarioService service = new FuncionarioService();
	private List<DataChangeListener> listeners = new ArrayList<>();
	private int operation = 0;
	@FXML
	private ObservableList<String> oplist;
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
	private RadioButton radio2;
	@FXML
	private RadioButton radio3;
	@FXML
	private ComboBox<String> campo;
	@FXML
	private Label mensagem;

	// ação de salvar no banco
	@FXML
	public void onBtsaveAction(ActionEvent event) {

		try {
			if (service != null) {
				if /* ATUALIZAR */ (operation == 1) {
					Funcionario f = makeUpdateFunc(service.findbyid(Integer.parseInt(textId.getText())), campo.getSelectionModel().getSelectedItem(), textNome);
					service.update(f, campo.getSelectionModel().getSelectedItem());
					Alerts.showAlert("Confirmação", "Atualização", "Você Atualizou um funcionário!",
							AlertType.INFORMATION);
				} /* DELETAR */ else if (operation == 2) {
					service.delete(Integer.parseInt(textId.getText()));
					Alerts.showAlert("Confirmação", "Deletar", "Você deletou um funcionário!", AlertType.INFORMATION);
				}

				notifyDataChangeListeners();
				Alerts.currentStage(event).close();

			} else {
				throw new IllegalStateException("falha ao tentar executar a operação");
			}
		} catch (IllegalStateException e) {
			Alerts.showAlert("ERROR", "Falha", e.getMessage(), AlertType.ERROR);
		}
		catch (db.DbException e) {
			Alerts.showAlert("ERROR", "Falha", e.getMessage(), AlertType.ERROR);
		}
	};

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Alerts.currentStage(event).close();
		Alerts.showAlert("Confirmação", "Cancelamento", "Você cancelou deletar/alterar um funcionário!",
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

	// definir se o banco vai atualizar, criar ou deletar
	public void onRadio2Action() {
		mensagem.setVisible(true);
		campo.setVisible(true);
		textId.setEditable(true);
		radio3.setSelected(false);
		this.operation = 1;
	}

	public void onRadio3Action() {
		mensagem.setVisible(false);
		campo.setVisible(false);
		textId.setEditable(true);
		radio2.setSelected(false);
		this.operation = 2;
	}

	public Funcionario makeUpdateFunc(Funcionario func, String Campo, TextField text) {
		Funcionario f = func;
		if (Campo == "email") {
			f.setEmail(text.getText());
		} else if (Campo == "name") {
			f.setName(text.getText());
		} else if (Campo == "birthDate") {
			f.setBirthDate(Date.valueOf(text.getText()));
		} else if (Campo == "department") {
			f.setDepartment(Integer.parseInt(text.getText()));
		} else if (Campo == "baseSalary") {
			f.setBaseSalary(Double.parseDouble(text.getText()));
		}
		return f;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// cria lista de valores para combobox
		List<String> list = new ArrayList<>();

		list.add("email");
		list.add("name");
		list.add("birthDate");
		list.add("department");
		list.add("baseSalary");

		oplist = FXCollections.observableArrayList(list);
		campo.setItems(oplist);
	}

}
