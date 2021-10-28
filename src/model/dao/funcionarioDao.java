package model.dao;

import java.util.List;

import model.entities.Funcionario;

public interface funcionarioDao {
	void create(Funcionario func);
	void update(Funcionario func,Funcionario old, String Operator);
	void deleteById(Integer id);
	Funcionario findById(Integer id);
	List<Funcionario> findAll();
	List<Funcionario> findByDepartment(Integer department);
}
