package com.khaled.bookscomp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.khaled.bookscomp.dao.BookDAO;
import com.khaled.bookscomp.models.Book;
import com.khaled.bookscomp.services.BookService;


@FixMethodOrder(MethodSorters.JVM)
public class AppTest {
	ApplicationContext context;
	
	@Before
	/**
	 * sets up the application config before running tests
	 */
	public void getConfig() {
		//Instruct Spring to create and wire beans using annotations.
		 context = new AnnotationConfigApplicationContext(AppConfig.class);
	}
	
	@Test
	/**
	 * tests if the hibernte session is set up
	 */
	public void testHibernateSession() {
		//get hibernate session from spring
		SessionFactory sf = (SessionFactory)context.getBean("sessionFactory");  
		
		//check if it is existung
		assertNotNull(sf);
	}
	
	@Test
	/**
	 * tests if the class book works properly
	 */
	public void testBook() {
		//create a new book
		Book book = new Book();
		book.setAuthor("tom");
		book.setIsbn("123456");
		book.setName("Tom clancy");
		
		//check validity of book
		assertEquals("tom",book.getAuthor());
		assertEquals("123456",book.getIsbn());
		assertEquals("Tom clancy",book.getName());
	}
	
	@Test
	/**
	 * tests if the listbooks class works properly
	 * @throws IOException
	 */
	public void testListBooks() throws IOException {
		//get lis of books frmo lisbook class
		ListBooks listBooks = new ListBooks();
	    List<String> list = listBooks.books();
	    
	    //test if it is not null and has content
	    assertNotNull(list);
	    assert(list.size() > 0);
	}
	
	@SuppressWarnings("resource")
	@Test
	/**
	 * tests if dao class works properly
	 */
	public void testDao() {
		 //get the book dao and test its exixtence
		 BookDAO bdao = (BookDAO)context.getBean("bdao");
		 assertNotNull(bdao);
		 
		 //get its sessionfactory
		 SessionFactory sf = bdao.getSessionFactory();
		 assertNotNull(sf);
	     
	}
	 
	
	@SuppressWarnings("resource")
	@Test
	/**
	 * tests the bookservice class if it works
	 */
	public void testAppconfigBookService() {

		  //Get the web scraper manager bean
		  BookService manager =(BookService)context.getBean("bservice");
		  
		  //test if it exists and has scrapers
		  assertNotNull(manager);
		  assert(manager.getScraperList().size() == 5);
	}
	
	
	
}
