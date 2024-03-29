package com.sgic.internal.product.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")

@Entity

@Table(schema = "productservice", name = "defectstatus")
public class DefectStatus implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // Defect Status Id
	@NotEmpty
	private String name; // Defect Status name
	@NotEmpty
	private String value; // Defect Status value
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = "Status";
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
