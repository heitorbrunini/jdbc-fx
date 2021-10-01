package model.entities;

import java.io.Serializable;

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Double Balance; 


	public Department() {
	}

	public Department(Integer id, String name,Double Balance) {
		this.id = id;
		this.name = name;
		this.Balance= Balance;
	}
		
	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Id do Departamento:" + id + ", Nome:" + name + ", Receita:" + Balance;
	}
}