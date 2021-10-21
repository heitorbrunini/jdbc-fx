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
	public void update(Funcionario f) {
		dao.update(f);
	}
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	
}
