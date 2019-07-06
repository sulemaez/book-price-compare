package com.khaled.bookscomp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 
 * @author khal
 *The class that fetches books that will will be scrapped 
 */
public class ListBooks {
	
	/**
	 * s
	 * @return List list of books isbn to search for
	 * @throws IOException if network error
	 */
	public List<String> books() throws IOException {
		//store the books isbn that have been gotten
		List<String> listbook=new ArrayList<String>();
	
		String isbn="";
		//goes through 100 pages of the wordery fiction catalogue change this number to increase pages visited
		for(int i=1;i<100;i++) {
			//fecthes  a specified page
			Document doc= Jsoup.connect("https://wordery.com/fiction?page="+i).get();
			//get the book title
			Elements list= doc.select(".c-book__title");
			
			//gets a book isbn and adds to list
			for(int j=0;j<4;j++) {
				isbn=list.select("a").get(j).attr("href").replaceAll("[^\\d]", "");
				listbook.add(isbn);
			}
		}

		return listbook;

	}

}
