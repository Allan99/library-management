package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String number;
	private String password;
	private String image;
	
	public Student() {
	}

	public Student(Integer id, String number, String password, String image) {
		super();
		this.id = id;
		this.number = number;
		this.password = password;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id) && Objects.equals(number, other.number);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", number=" + number + ", password=" + password + ", image=" + image + "]";
	}

	
}
