package com.khaled.bookscomp.services;

import com.khaled.bookscomp.models.Book;
import com.khaled.bookscomp.models.BookPrice;
import com.khaled.bookscomp.models.Publisher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.text.NumberFormatter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author khal
 * scraps from BookDepository
 */
public class ScrapFromBookDepository extends WebScraping {

    @Override
    public void scrapBook(String isbn, int id) throws IOException {
        List<BookPrice> libookprice =new ArrayList<BookPrice>();
        BookPrice bookprice=new BookPrice();
        // request for a page
        Document doc = Jsoup.connect("https://www.bookdepository.com/product/" + isbn).get();         
        //set the prices url
        String url = "https://www.bookdepository.com/product/" + isbn;
        //set the prices retailer
        String retailer="BookDepository";
        
        //check if such a book exixts
        if(!doc.select("span.price").isEmpty()){
        	//get the price
            String pricep = doc.select("span.price").first().text().replace("US$","");
            //set the bookprice details
            bookprice.setPrice(Double.parseDouble(pricep)*0.79);
            bookprice.setUrl(url);
            bookprice.setRetailer(retailer);
            bookprice.setBookid(id);
            //add to the db
            libookprice.add(bookprice);
            bookdao.addpricebook(libookprice);
        }
    }
}
