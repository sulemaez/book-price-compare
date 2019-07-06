package com.khaled.bookscomp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.khaled.bookscomp.services.*;

import java.io.IOException;

/**
 * 
 * @author khal
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		  //Instruct Spring to create and wire beans using annotations.
		  ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		  //Get the web scraper manager bean
		  BookService manager =(BookService)context.getBean("bservice");
		  //Start the web scraping
		  manager.startScraping();
	}

	
}
