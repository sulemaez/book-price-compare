package com.khaled.bookscomp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;

import com.khaled.bookscomp.dao.BookDAO;
import com.khaled.bookscomp.services.*;

import org.springframework.context.annotation.Bean;
/**
 * 
 * @author khal
 *The Spring application configuration
 */
@Configuration
public class AppConfig {
	//the hibernate session
	SessionFactory sessionFactory =null;
    
	@Bean
	//generates a book dao 
	public BookDAO bdao() {
		BookDAO bdao = new BookDAO ();
		//set the dao hibernate session factory
		bdao.setSessionFactory(sessionFactory());
		return bdao;
	}

	@Bean
	//generate a book service instance
	public BookService bservice() {
		BookService bservice = new BookService();
		//add the list of webscrapersd
		List<WebScraping>scraperList = new ArrayList<WebScraping>();
		//add individual scrappers to list
		scraperList.add(waterstones());	
		scraperList.add(blackwell());	
		scraperList.add(thebookpeople());	
		scraperList.add(wordery());
		scraperList.add(bookDepository());
		//set the scraper list
		bservice.setScraperList(scraperList);

		return bservice;
	}

	@Bean
	//generates a book depository scraper
	public ScrapFromBookDepository bookDepository() {
		ScrapFromBookDepository bookDepository = new ScrapFromBookDepository();
		//set the book dao
		bookDepository.setBookdao(bdao());
		//set time for thread to sleep
		bookDepository.setSleepTime(1000);
		return bookDepository;
	}


	@Bean
	//generate blackwell scrapper
	public ScrapFromBlackwell blackwell() {
		ScrapFromBlackwell blackwell = new ScrapFromBlackwell();
		//set  the book dao
		blackwell.setBookdao(bdao());
		//set time for thread to sleep
		blackwell.setSleepTime(1000);
		return blackwell;
	}

	@Bean
	//generate the book people scrapper
	public ScrapFromTheBookPeople thebookpeople() {
		ScrapFromTheBookPeople thebookpeople = new ScrapFromTheBookPeople();
		//set the book dao
		thebookpeople.setBookdao(bdao());
		//set the time for thread to sleep
		thebookpeople.setSleepTime(1000);
		return thebookpeople;
	}

	@Bean
	//generate a waterstomes scrapper
	public ScrapFromWaterstones waterstones() {
		ScrapFromWaterstones waterstones = new ScrapFromWaterstones();
		//set book dao
		waterstones.setBookdao(bdao());
		//set thetime for thread to sleep
		waterstones.setSleepTime(1000);
		return waterstones;
	}

	@Bean
	//generate wordery scraper
	public ScrapFromWordery wordery() {
		ScrapFromWordery wordery = new ScrapFromWordery();
		//set the book dao
		wordery.setBookdao(bdao());
		//set the time for thread to sleep
		wordery.setSleepTime(1000);
		return wordery;
	}

	@Bean
	//generate a hibernate session factory
	public SessionFactory sessionFactory() {
		// Build session factory once only
		if (sessionFactory == null) {
			try {
				// Create a builder for the standard service registry
				StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

				// Load configuration from hibernate configuration file.
				// Here we are using a configuration file that specifies Java annotations.
				standardServiceRegistryBuilder.configure("hibernate-annotations.cfg.xml");

				// Create the registry that will be used to build the session factory
				StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
				try {
					// Create the session factory - this is the goal of the init method.
					sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
				} catch (Exception e) {
					/*
					 * The registry would be destroyed by the SessionFactory, but we had trouble
					 * building thes SessionFactory, so destroy it manually
					 */
					System.err.println("Session Factory build failed.");
					e.printStackTrace();
					StandardServiceRegistryBuilder.destroy(registry);
				}

				// Ouput result
				System.out.println("Session factory built.");

			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("SessionFactory creation failed. " + ex);
				ex.printStackTrace();
			}
		}

		return sessionFactory;
	}

}
