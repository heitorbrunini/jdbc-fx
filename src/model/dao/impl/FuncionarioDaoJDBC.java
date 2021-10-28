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
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements funcionarioDao {

	private Connection conn;

	public FuncionarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void create(Funcionario func) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO funcionarios "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES " + "(?,?,?,?,?)");
			st.setString(1, func.getName());
			st.setString(2, func.getEmail());
			st.setDate(3, func.getBirthDate());
			st.setDouble(4, func.getBaseSalary());
			st.setInt(5, func.getDepartment());
			st.executeUpdate();

			addBalance(func.getDepartment(), func.getBaseSalary());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Funcionario func,Funcionario old, String Operator) {
		PreparedStatement st = null;
		try {
			if (Operator=="email") {
				st = conn.prepareStatement("UPDATE `fx-jdbc`.`funcionarios` SET `Email` = ? WHERE (`Id` = ?)");
				st.setString(1, func.getEmail());
				st.setInt(2, func.getId());
			}
			else if(Operator=="baseSalary") {
				subBalance(func.getDepartment(), old.getBaseSalary());
				addBalance(func.getDepartment(), func.getBaseSalary());
				st = conn.prepareStatement("UPDATE `fx-jdbc`.`funcionarios` SET `BaseSalary` = ? WHERE (`Id` = ?)");
				st.setDouble(1, func.getBaseSalary());
				st.setInt(2, func.getId());				
			}else if(Operator=="name") {
				st = conn.prepareStatement("UPDATE `fx-jdbc`.`funcionarios` SET `Name` = ? WHERE (`Id` = ?)");
				st.setString(1, func.getName());
				st.setInt(2, func.getId());				
			}else if(Operator=="department") {
				subBalance(old.getDepartment(), old.getBaseSalary());
				addBalance(func.getDepartment(), func.getBaseSalary());
				st = conn.prepareStatement("UPDATE `fx-jdbc`.`funcionarios` SET `DepartmentId` = ? WHERE (`Id` = ?)");
				st.setInt(1, func.getDepartment());
				st.setInt(2, func.getId());				
			}else if(Operator=="birthDate") {
				st = conn.prepareStatement("UPDATE `fx-jdbc`.`funcionarios` SET `BirthDate` = ? WHERE (`Id` = ?)");
				st.setDate(1, func.getBirthDate());
				st.setInt(2, func.getId());				
			}
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			Funcionario f = findById(id);
			st = conn.prepareStatement("DELETE FROM funcionarios WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
			subBalance(f.getDepartment(),f.getBaseSalary());
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM funcionarios WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(rs.getInt("DepartmentId"));

				return obj;
			}else {
				throw new DbException("Id não encontrado");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM funcionarios");

			rs = st.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			while (rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(rs.getInt("DepartmentId"));

				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Funcionario> findByDepartment(Integer department) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM funcionarios WHERE DepartmentId = ?");
			st.setInt(1, department);
			rs = st.executeQuery();
			List<Funcionario> list = new ArrayList<>();
			
			while (rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(rs.getInt("DepartmentId"));

				list.add(obj);
			}
			if (list.size()==0){
				throw new DbException("Nenhum funcionario encontrado no departamento");
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/*
	 * toda vez que um funcionario for adicionado é preciso adicionar o salario dele
	 * no balanco do departamento
	 */
	private void addBalance(Integer integer, Double salary) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE department " + "SET Balance = Balance + ?" + "WHERE Id = ?");
			st.setDouble(1, salary);
			st.setInt(2, integer);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	private void subBalance(Integer integer, Double salary) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE department " + "SET Balance = Balance - ?" + "WHERE Id = ?");
			st.setDouble(1, salary);
			st.setInt(2, integer);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
}
