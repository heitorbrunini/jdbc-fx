package model.entities;

import java.io.Serializable;

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Double Balance=0.0; 


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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id do Departamento:" + id + ", Nome:" + name + ", Receita:" + Balance;
	}
}