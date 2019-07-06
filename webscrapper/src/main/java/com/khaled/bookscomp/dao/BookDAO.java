package com.khaled.bookscomp.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.khaled.bookscomp.models.Book;
import com.khaled.bookscomp.models.BookPrice;
import com.khaled.bookscomp.models.Publisher;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 
 * @author khal
 *Handles database transactions
 */
public class BookDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 
	 * @param book book to dd
	 * Handles database transactions
	 */
	public void addbook(List<Book> book) {
		Book bookdetails = new Book();
		//goes through all books in list
		for (Book b : book) {
			//get the current hibernate session
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			//adds the details
			bookdetails.setAuthor(b.getAuthor());
			bookdetails.setImage(b.getImage());
			bookdetails.setName(b.getName());
			bookdetails.setIsbn(b.getIsbn());
			//save book
			session.save(bookdetails);
			//commit changes to database an close
			session.getTransaction().commit();
			session.close();

		}

	}

	/**
	 * 
	 * @param pricebook price details to be added
	 * the method will add the details of the book in the bookprice table
	 */
	public void addpricebook(List<BookPrice> pricebook) {
        
		BookPrice bookprice = new BookPrice();
        //for all book prices in list
		for (BookPrice bprice : pricebook) {
			//get the hibernate sessionfactory
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			//set the bookprice details
			bookprice.setPrice(bprice.getPrice());
			bookprice.setUrl(bprice.getUrl());
			bookprice.setRetailer((bprice.getRetailer()));
			bookprice.setBookid((bprice.getBookid()));
			//save and transact to the databse
			session.save(bookprice);
			session.getTransaction().commit();
			//close the session
			session.close();
		}

	}
    
	/**
	 * 
	 * @param publisher details to be added
	 * the method will add the details of the book in the publisher table
	 */
	public void addpublisher(List<Publisher> publisher) {
		Publisher bookpublisher = new Publisher();
        //for all publisher's in list
		for (Publisher bp : publisher) {
			//get the hibernate session
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
            //set the publisher details
			bookpublisher.setName(bp.getName());
			bookpublisher.setBookid(bp.getBookid());
			//save the publisher and trasact to the db
			session.save(bookpublisher);
			session.getTransaction().commit();
			//close
			session.close();
		}

	}


    //get the daos session factory 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
    
	/**
	 * set the session factory
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
