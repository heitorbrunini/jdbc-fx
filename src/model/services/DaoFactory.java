package model.services;

import db.DB;
import model.dao.departmentDao;
import model.dao.funcionarioDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.FuncionarioDaoJDBC;

public class DaoFactory {

	public static funcionarioDao createSellerDao() {
		return new FuncionarioDaoJDBC(DB.getConnection());
	}
	
	public static departmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
