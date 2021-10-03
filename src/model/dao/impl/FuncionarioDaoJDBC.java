package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.funcionarioDao;
import model.entities.Department;
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements funcionarioDao {

	private Connection conn;
	
	public FuncionarioDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void create(Funcionario func) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO funcionarios " +
				"(Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES " +
				"(?,?,?,?,?)"
				);
			st.setString(1, func.getName());
			st.setString(1, func.getEmail());
			st.setDouble(1, func.getBaseSalary());
			st.setInt(1, func.getDepartment().getId());
			st.executeUpdate();
			
			addBalance(func.getDepartment(),func.getBaseSalary());
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Funcionario func) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE funcionarios " +
				"SET Name = ? " +
				"SET Email = ?"+
				"SET BaseSalary = ?"+
				"SET BirthDate = ?" +
				"SET DepartmentId = ?"+
				"WHERE Id = ?");

			st.setString(1, func.getName());
			st.setString(2, func.getEmail());
			st.setDouble(3, func.getBaseSalary());
			st.setDate(4, func.getBirthDate());
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Funcionario findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void addBalance(Department d, Double salary) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE department " +
					"SET Balance = Balance + ?"+
					"WHERE Id = ?");
				st.setDouble(1, salary);
				st.setInt(2, d.getId());
				st.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

}
