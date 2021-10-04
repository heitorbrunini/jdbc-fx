package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM funcionarios WHERE Id = ?");
			st.setInt(1, id);
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
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM funcionarios WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				
				DepartmentDaoJDBC newdepartment = new DepartmentDaoJDBC(conn);
				obj.setDepartment( newdepartment.findById(rs.getInt("DepartmentId")) );
				
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM funcionarios ORDER BY Name");
			
			rs = st.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			if (rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				
				DepartmentDaoJDBC newdepartment = new DepartmentDaoJDBC(conn);
				obj.setDepartment( newdepartment.findById(rs.getInt("DepartmentId")) );
				
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
}

	@Override
	public List<Funcionario> findByDepartment(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM funcionarios WHERE DepartmentId = ?");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			
			if (rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				
				DepartmentDaoJDBC newdepartment = new DepartmentDaoJDBC(conn);
				obj.setDepartment( newdepartment.findById(rs.getInt("DepartmentId")) );
				
				list.add(obj);
			}
			
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	/*toda vez que um funcionario for adicionado é preciso adicionar o salario dele
	 * no balanco do departamento*/
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
