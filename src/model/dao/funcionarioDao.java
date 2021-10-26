package model.dao;

import java.util.List;

import model.entities.Funcionario;

public interface funcionarioDao {
	void create(Funcionario func);
	public void update(Funcionario func, String Operator);
	void deleteById(Integer id);
	Funcionario findById(Integer id);
	List<Funcionario> findAll();
	List<Funcionario> findByDepartment(Integer department);
}
