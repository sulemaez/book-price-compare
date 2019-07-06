package com.khaled.bookscomp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.khaled.bookscomp.dao.BookDAO;
import com.khaled.bookscomp.models.*;

/**
 * 
 * @author khal
 *Scraps from BlackWell
 */
public class ScrapFromBlackwell extends WebScraping {

	public void scrapBook(String isbn,int id) throws IOException {
		
		List<BookPrice> libookprice =new ArrayList<BookPrice>();
		BookPrice bookprice=new BookPrice();
		// request for a page
		Document doc = Jsoup.connect("https://blackwells.co.uk/bookshop/product/" + isbn).get();
        
		//set the prices url
		String url = "https://blackwells.co.uk/bookshop/product/" + isbn;
		//set the retailer
		String retailer="Blackwell";
		
		//check if there exists such a book
		if(!doc.select("li.product-price--current").isEmpty()){
			//get the price from the element
			String pricep = doc.select("li.product-price--current").first().text().replace("£","");
            
			//set the details to a bookprice object
			bookprice.setPrice(Double.parseDouble(pricep));
			bookprice.setUrl(url);
			bookprice.setRetailer(retailer);
            
			//add to the database
			libookprice.add(bookprice);
			bookdao.addpricebook(libookprice);
		}
		}

	}


