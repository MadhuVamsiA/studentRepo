package com.model;



public class Student {

	private int id;
	private String name;
	private int age;
	private String branch;
	private String section;
	
	
	//constructor for insert
	public Student(String name, int age, String branch, String section) {
		this.name =name;
		this.age=age;
		this.branch=branch;
		this.section=section;	
	}
	
	
	//constructor for update
	public Student(int id,String name, int age, String branch, String section) {
		this.id=id;
		this.name =name;
		this.age=age;
		this.branch=branch;
		this.section=section;
		
	}
	
	//default constructor
	public Student() {
		// TODO Auto-generated constructor stub
	}

	
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", branch=" + branch + ", section=" + section
				+ "]";
	}
	
	
	
}
