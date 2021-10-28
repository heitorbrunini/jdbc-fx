package model.services;

import java.util.List;

import model.dao.funcionarioDao;
import model.entities.Funcionario;

public class FuncionarioService {
	private funcionarioDao dao = DaoFactory.createSellerDao();
	public List<Funcionario> findAll(){
		return dao.findAll();
	}
	public void create(Funcionario f) {
		dao.create(f);
	}
	public void update(Funcionario func,Funcionario old, String Operator){
		dao.update(func,old, Operator);
	}
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	public Funcionario findbyid(Integer id) {
		return dao.findById(id);
	}
	public List<Funcionario> findbyDp(Integer dp) {
		return dao.findByDepartment(dp);
	}
}
