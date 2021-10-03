package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Funcionario;

public interface funcionarioDao {
	void create(Funcionario func);
	void update(Funcionario func);
	void deleteById(Integer id);
	Funcionario findById(Integer id);
	List<Funcionario> findAll();
	List<Funcionario> findByDepartment(Department department);
}
