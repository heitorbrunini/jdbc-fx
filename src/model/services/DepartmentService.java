package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	public List<Department> findAll(){
		List<Department> list = new ArrayList<>();
		list.add(new Department(5, "Marketing",100.00));
		list.add(new Department(3, "Vendas",200.00));
		list.add(new Department(8, "Recursos Humanos",150.00));
		return list;
	}

}
