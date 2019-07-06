//Import the express and url modules
var express = require('express');
var url = require("url");
var cors = require('cors')

//Status codes defined in external file
require('./http_status.js');

//The express module is a function. When it is executed it returns an app object
var app = express();

//Import the mysql module
var mysql = require('mysql');

//Create a connection object with the user details
var connectionPool = mysql.createPool({
    connectionLimit: 1,
    host: "localhost",
    port: "3306",
    user: "root",
    password: "",
    database: "bookstore",
    debug: false
});

app.use(cors());
//Set up the application to handle GET requests sent to the user path
app.get('/search/*', handleGetSearch);//Subfolders
app.get('/search', handleGetSearch);
app.get('/getdetails/*',handleGetDetails)
app.get('/getdetails',handleGetDetails)

//Start the app listening on port 8080
app.listen(8000);

/**
 * Handles GET requests sent to web service to get detilas of  a book and its varoius prices
 */
function handleGetDetails(request,response){
      //Parse the URL
      var urlObj = url.parse(request.url, true);

      //Extract object containing queries from URL object.
      var queries = urlObj.query;

      //get book id
      var bookId = queries['bookId']

      var sql = "SELECT * FROM prices WHERE book_id ="+bookId+";";
      connectionPool.query(sql,(err,result)=>{
                  //Check for errors
                  if (err){
                    //Not an ideal error code, but we don't know what has gone wrong.
                    response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
                    console.log(err)
                    response.json({'error': true, 'message': + err});
                  }else{
                    //Output results in JSON format
                    response.json(result);
                }
    });

}


/* Handles GET requests sent to web service to search for books.
  */
function handleGetSearch(request, response){
    //Parse the URL
    var urlObj = url.parse(request.url, true);

    //Extract object containing queries from URL object.
    var queries = urlObj.query;

    //Get the pagination properties if they have been set. Will be  undefined if not set.
    var searchQuery = queries['query'];
    
    //search for book in database
    getSearchDetails(response,searchQuery);

}

/*
*queries the database with the given isbn or book name
*/
function getSearchDetails(response,searchQuery){
   console.log(searchQuery) 
   //if name not set use isbn 
   var sql = "SELECT books.id,books.name,books.author,books.image,books.isbn,publishers.name as pname FROM (books LEFT JOIN publishers ON books.id = publishers.book_id) WHERE books.name LIKE '%"+searchQuery+"%' or books.author LIKE '%"
       +searchQuery +"%' or books.isbn LIKE '%"+searchQuery+"%' ;";
 
    connectionPool.query(sql,(err,result)=>{
                //Check for errors
        if (err){
            //Not an ideal error code, but we don't know what has gone wrong.
            response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
            console.log(err)
            response.json({'error': true, 'message': + err});
        }else{
            //Output results in JSON format

            response.json(result);
        }
               
    })           
}
