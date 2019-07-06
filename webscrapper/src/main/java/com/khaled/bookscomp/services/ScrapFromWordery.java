package com.khaled.bookscomp.services;

import java.io.IOException;
import java.text.DecimalFormat;
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
 * scraps Wordery
 */
public class ScrapFromWordery extends WebScraping {

	public void scrapBook(String isbn, int id) throws IOException {
		//store bookprice to add to db
		List<BookPrice> libookprice =new ArrayList<BookPrice>();
		//store bookprice details
		BookPrice bookprice=new BookPrice();
		// request for a page
		Document doc = Jsoup.connect("https://wordery.com/" + isbn).get();
        //set url 
		String url = "https://wordery.com/" + isbn;
        //set image
		String img = "https://wordery.com"+ doc.select("img.c-prod-img__img.js-main-prod-img").first().attr("src");
		//get the price
		String pricep = doc.select("strong.u-fs--ex.u-t--lh1").first().text().replace("$","");
	    //set the retailer
		String retailer="Wordery";
		
		//set the bookprice details
		bookprice.setPrice(Double.parseDouble(pricep)*0.79);
		bookprice.setUrl(url);
		bookprice.setRetailer(retailer);
		bookprice.setBookid(id);
		//add book to db
		libookprice.add(bookprice);
		bookdao.addpricebook(libookprice);

	}

}
