package gui.listeners;

import java.util.List;

import model.entities.Funcionario;

public interface DataChangeListener {
	void onDataChanged();
	void onDataFind(List<Funcionario> funcionarios);
}
