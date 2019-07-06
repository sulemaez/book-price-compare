package com.khaled.bookscomp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import com.khaled.bookscomp.models.Book;
import com.khaled.bookscomp.models.BookPrice;
import com.khaled.bookscomp.models.Publisher;

/**
 * 
 * @author khal
 * scraps from Scrap from Waterstones
 */
public class ScrapFromWaterstones extends WebScraping{

	public void scrapBook(String isbn,int id) throws IOException {
        //list to add book 
		List<Book> libook =new ArrayList<Book>();
		//list to add bookprice
		List<BookPrice> libookprice =new ArrayList<BookPrice>();
		//list to add publisher
		List<Publisher> libookpublisher =new ArrayList<Publisher>();
		//store book details
		Book bookdetails=new Book();
		//storre price detils
		BookPrice bookprice=new BookPrice();
		//store publisher details
		Publisher bookpublisher=new Publisher();
		// request for a page
		Document doc = Jsoup.connect("https://www.waterstones.com/book/" + isbn).get();
        //set url
		String url = "https://www.waterstones.com/book/" + isbn;
        
		//if book exists
		if(!doc.select(".price").select("b").isEmpty()){
			//get the price
			String pricep = doc.select(".price").select("b").get(0).text().replace("£","");
			//set the retiler
			String retailer = "Waterstones";
			//get author details
			String author = doc.select(".text-gold").select("span").first().text();
			//get the book img
			String img=doc.getElementById("scope_book_image").select("img").first().attr("src");
			//get the books name
			String name=doc.select(".book-title").select("span").first().text();
			String bisbn=isbn;
			//get publisher name
			String publisher=doc.select(".spec").select("span").first().text();

            //set the book details
			bookdetails.setAuthor(author);
			bookdetails.setImage(img);
			bookdetails.setName(name);
			bookdetails.setIsbn(bisbn);
			//add book to db
			libook.add(bookdetails);
			bookdao.addbook(libook);
            
			//set the book[rice details
			bookprice.setPrice(Double.parseDouble(pricep));
			bookprice.setUrl(url);
			bookprice.setRetailer(retailer);
			bookprice.setBookid(id);
            //add bookprice to db
			libookprice.add(bookprice);
			bookdao.addpricebook(libookprice);
            
			//add publisher details
			bookpublisher.setName(publisher);
			bookpublisher.setBookid(id);
			//add publisher to db
			libookpublisher.add(bookpublisher);
			bookdao.addpublisher(libookpublisher);

		}
		

		

	}

}
