package com.khaled.bookscomp.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import com.khaled.bookscomp.dao.BookDAO;
import com.khaled.bookscomp.models.Book;
import com.khaled.bookscomp.models.BookPrice;


/**
 * 
 * @author khal
 * scraps from BookDepository
 */
public class ScrapFromTheBookPeople extends WebScraping{

	public void scrapBook(String isbn,int id) throws IOException {
		//store the details in these
		List<BookPrice> libookprice =new ArrayList<BookPrice>();
		BookPrice bookprice=new BookPrice();
		
		// request for a page
		Document doc = Jsoup.connect("https://www.thebookpeople.co.uk/webapp/wcs/stores/servlet/qs_searchResult_tbp?searchTerm="+ isbn).get();
        
		//get all elements with a book
		Elements searchResults = doc.select(".col-xs-12");
		
		//if a elements is present
		if(searchResults.size()>=1) {
			//set the url
			String url = "https://www.thebookpeople.co.uk/webapp/wcs/stores/servlet/"+doc.select("a.url.js_productDetailLink").first().attr("href");
            //if a book exists
			if(!doc.select("span.price").isEmpty()){
				//get the price
				String pricep=doc.select("span.price").first().text().replace("£","");
				//set the retailer
				String retailer="TheBookPeople";
                //set the bookprice details
				bookprice.setPrice(Double.parseDouble(pricep));
				bookprice.setUrl(url);
				bookprice.setRetailer(retailer);
				bookprice.setBookid(id);
				//save the data to db
				libookprice.add(bookprice);
				bookdao.addpricebook(libookprice);
			}
		}

	}

}
