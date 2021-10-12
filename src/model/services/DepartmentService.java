package model.services;

import java.util.List;

import model.dao.departmentDao;
import model.entities.Department;

public class DepartmentService {
	private departmentDao dao = DaoFactory.createDepartmentDao();
	public List<Department> findAll(){
		
		return dao.findAll();
	}

}
