package model.services;

import java.util.List;

import model.dao.departmentDao;
import model.entities.Department;
 //PROBLEMA AQUI!
public class DepartmentService {
	
	private departmentDao dao = DaoFactory.createDepartmentDao();
	public DepartmentService() {}
	public DepartmentService (departmentDao dao)
	{
		this.dao=dao;
	}
	public List<Department> findAll(){
		return dao.findAll();
	}
	public void update(Department d) {
		dao.update(d);
	}
	public void create(Department d) {
		dao.create(d);
	}
	public void delete(Department d) {
		dao.deleteById(d.getId());
	}
	public Department findbyId(Integer I) {
		return dao.findById(I);
	}
}
