package gui;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.entities.Funcionario;
import model.services.DepartmentService;

public class FuncionarioFormCreate {
	private DepartmentService service = new DepartmentService();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<Label> erros = new ArrayList<>();
	
	@FXML
	private TextField textNome;
	@FXML
	private Label Labelerrorname;
	@FXML
	private TextField textEmail;
	@FXML
	private Label LabelerrorMail;
	@FXML
	private TextField textDate;
	@FXML
	private Label LabelerrorDate;
	@FXML
	private TextField textSalary;
	@FXML
	private Label LabelerrorSalary;
	@FXML
	private TextField textDepartment;
	@FXML
	private Label LabelerrorDepartment;
	@FXML
	private Button save;
	@FXML
	private Button cancel;

	public void onBtSaveAction(ActionEvent event) throws ParseException {
		resetList();
		Funcionario novo = new Funcionario(getName(), getMail(), getDate(), getSalary(), getDepartment());
		checkAll();
	}

	public Integer getDepartment() {
		try {
			Department dp = service.findbyId(Integer.parseInt(textDepartment.getText()));
			if (dp == null) {
				erros.add(LabelerrorDepartment);
			}			
			return Integer.parseInt(textDepartment.getText());
		} catch (NumberFormatException e) {
			erros.add(LabelerrorDepartment);
		}
		return null;
	}

	public String getName() {
		if (textNome.getText().trim().isEmpty() || textNome.getText().trim().isBlank()) {
			erros.add(Labelerrorname);
		} else {
			return textNome.getText();
		}
		return null;
	};

	public String getMail() {
		if (textEmail.getText().trim().isEmpty() || textEmail.getText().trim().isBlank()) {
			erros.add(LabelerrorMail);
		} else {
			return textEmail.getText();
		}
		return null;
	};

	public Date getDate() {
		try {
			return Funcionario.dateof(textDate.getText());
		} catch (ParseException e) {
			erros.add(LabelerrorDate);
		}
		return null;
	};

	public Double getSalary() {
		try {
			return Double.parseDouble(textSalary.getText());
		} catch (NumberFormatException e) {
			erros.add(LabelerrorSalary);
		}
		return null;
	};

	public boolean checkAll() {
		if (erros.size() > 0) {
			for (Label erro : erros) {
				erro.setVisible(true);
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void resetList() {
		for (Label erro : erros) {
			erro.setVisible(false);
		}
		erros.removeAll(erros);
	}

}
