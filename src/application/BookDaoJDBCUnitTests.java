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
			
			AvailableBook book = new AvailableBook();
			book.setTitle("O senhor dos anéis: O retorno do rei");
			book.setAuthor("J.R.R. Tolkien");
			book.setGenre("Literatura fantástica");
			book.setDate(sdf.parse("20/10/1955"));
			book.setImage("C:\\java-new-workspace\\libraryManagement\\src\\images\\o_retorno_do_rei.jpg");
			
			bookdao.insert(book);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateBook() {
		BookDao bookDao = DaoFactory.createBookDao();
		
		AvailableBook book = bookDao.findById(2);
		
		book.setImage("C:\\java-new-workspace\\libraryManagement\\src\\images\\sociedade_do_anel.jpg");
		
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
