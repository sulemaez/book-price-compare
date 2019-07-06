package com.khaled.bookscomp.services;

import java.io.IOException;
import java.util.List;


import com.khaled.bookscomp.ListBooks;
import com.khaled.bookscomp.dao.BookDAO;
import com.khaled.bookscomp.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author khal
 *Abstract class that defines a webscrapper
 */
public abstract class WebScraping extends Thread{
    
	
    @Autowired
	BookDAO bookdao;
    
    //list of book isbn to seacrh
    private List<String> books;
    
    //time to sleep thread
	int sleepTime = 1000;

    /**
     * 
     * @param isbn to search for 
     * @param id the id to store in db
     * @throws IOException
     * searches for the book with given isbn
     */
	public abstract void scrapBook(String isbn, int id)throws IOException ;
	
	public void run(){
		try {
			int counter = 1;
			//for all isbn in out list
			for (String isbn : books) {
				try {
					//scraps the particular site for a book
					scrapBook(isbn,counter);
					counter++;
					//seep the thread
					sleep(sleepTime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BookDAO getBookdao() {
		return bookdao;
	}

	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}
	
	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}


	public  void settListBooks(List<String> books){
		this.books = books;
	}
}