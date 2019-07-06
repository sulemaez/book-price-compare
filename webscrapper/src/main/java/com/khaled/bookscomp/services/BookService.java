package com.khaled.bookscomp.services;

import com.khaled.bookscomp.ListBooks;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author khal
 * BookService Model
 */
public class BookService {

    /**
     * The list of websrappers
     */
	List<WebScraping> scraperList;

	/**
	 * starts the webscrappers 
	 * @throws IOException if netowrk error
	 */
	public void startScraping() throws IOException {
		//get the list of isbn's
		List<String> books = new ListBooks().books();
		//get first scrapper to start scrapping
		scraperList.get(0).settListBooks(books);
		scraperList.get(0).start();
		try{
			//wait for the firs to finish
			scraperList.get(0).join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		//start all other scrappers
		for (WebScraping webScraper : scraperList) {
			//starts all scrapers except the first as it already ran
			if(!webScraper.equals(scraperList.get(0))){
				webScraper.settListBooks(books);
				webScraper.start();
			}

		}


	}
	//Getters and setters
	
	//get the list of web scrapper
	public List<WebScraping> getScraperList() {
		return scraperList;
	}
	
	/**
	 * set the webscrapers to use
	 * @param scraperList
	 */
	public void setScraperList(List<WebScraping> scraperList) {
		this.scraperList = scraperList;
	}
    
   
    
}
