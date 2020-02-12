package org.as.devtechsolution.stock.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quotes")
public class Quote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="quote")
	private String qoute;
	
	public Quote() {
	}
	
	

	public Quote(String userName, String qoute) {
		super();
		this.userName = userName;
		this.qoute = qoute;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQoute() {
		return qoute;
	}

	public void setQoute(String qoute) {
		this.qoute = qoute;
	}
	
	
	

}
