package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AvailableBook implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private String author;
	private String genre;
	private String image;
	private Date date;

	public AvailableBook() {
	}
	
	public AvailableBook(Integer id, String title, String author, String genre, String image, Date date) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.image = image;
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	@Override
	public int hashCode() {
		return Objects.hash(author, date, genre, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvailableBook other = (AvailableBook) obj;
		return Objects.equals(author, other.author) && Objects.equals(date, other.date)
				&& Objects.equals(genre, other.genre) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", genre=" + genre + ", image=" + image + ", date="
				+ date + "]";
	}
	
	

}
