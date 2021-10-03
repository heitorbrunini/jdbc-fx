package model.dao;

import java.util.List;

import model.entities.Department;

public interface departmentDao {
	void create(Department d);
	void update(Department d);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
}
