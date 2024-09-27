package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import model.dao.BookDao;
import model.dao.DaoFactory;
import model.entities.AvailableBook;

public class BookDaoJDBCUnitTests {

	@Test
	public void testInsertBook() {
		BookDao bookdao = DaoFactory.createBookDao();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			AvailableBook book = new AvailableBook(1, "Harry Potter e a Pedra Filosofal"
					,"J.K. Rowling"
					, "Ficção científica e fantasia"
					, "C:\\Users\\allan\\Downloads\\harry.jpg"
					, sdf.parse("26/06/1997"));
			
			AvailableBook book2 = new AvailableBook(1, "O Senhor dos Anéis: A Sociedade do Anel"
					,"J.R.R. Tolkien"
					, "Literatura fantástica"
					, "C:\\Users\\allan\\Downloads\\content.jpg"
					, sdf.parse("29/07/1954"));
			
			bookdao.insert(book2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateBook() {
		BookDao bookDao = DaoFactory.createBookDao();
		
		AvailableBook book = bookDao.findById(2);
		
		book.setImage("C:\\java-new-workspace\\libraryManagement\\src\\images\\content.jpg");
		
		bookDao.update(book);
	}
	
	@Test
	public void testFindById() {
		BookDao bookDao = DaoFactory.createBookDao();
		
		AvailableBook book = bookDao.findById(2);
		
		System.out.println(book);
	}
	
	@Test
	public void testFindAllBooks() {
		BookDao bookDao = DaoFactory.createBookDao();
		
		List<AvailableBook> allBooks = bookDao.findAll();
		
		for(AvailableBook book: allBooks) {
			System.out.println(book);
		}
		
	}
}
