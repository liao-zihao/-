package com.lzh;

import java.util.*;
import javax.persistence.*;
@Entity
@Table(name="person_inf")
public class Person {
	@Id @Column(name="person_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	private String name;
	private int age;
	
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name="school_inf",
			joinColumns=@JoinColumn(name="person_id",nullable=false))
	@Column(name="school_name")
	@OrderColumn(name="list_order")
	private List<String> schools=new ArrayList<>();

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getSchools() {
		return schools;
	}

	public void setSchools(List<String> schools) {
		this.schools = schools;
	}

	
}
