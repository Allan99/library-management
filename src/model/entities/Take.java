package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Take implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String studentNumber;
	private String firstName;
	private String lastName;
	private String gender;
	private String bookTitle;
	private String image;
	private Date date;
	private String checkReturn;
	
	public Take() {
	}

	public Take(String studentNumber, String firstName, String lastName, String gender, String bookTitle, String image,
			Date date, String checkReturn) {
		super();
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.bookTitle = bookTitle;
		this.image = image;
		this.date = date;
		this.checkReturn = checkReturn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCheckReturn() {
		return checkReturn;
	}

	public void setCheckReturn(String checkReturn) {
		this.checkReturn = checkReturn;
	}

	@Override
	public String toString() {
		return "Take [id=" + id + ", studentNumber=" + studentNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", bookTitle=" + bookTitle + ", image=" + image + ", date=" + date
				+ ", checkReturn=" + checkReturn + "]";
	}

	
	
}
