// Create the new VueRouter and pass an object specifying the paths
const myRouter = new VueRouter({
routes: [
      { path: '/',component :home },
      { path:'/home',component:home},
      { path: '/list',name : "list", component:list , props : true},
      { path: '/display',name:"display",component:display,props:true}
      ]
  });


 //url the nose server is on 
 const url = "http://localhost:8000/"
  //for cors setup
 axios.defaults.headers.common['Access-Control-Allow-Origin']= 'http://localhost/';
 // Create the Vue application with the router
 const app = new Vue({
 	el: '#app',
    router: myRouter,
    components:{
       home,list,display
    },
     created() {
     	 let vue = this;

       //search for book with given key
	     bus.$on('search', (key) => {
	        	axios.get(url+"search?query="+key,{
	     	       	headers: {
	                  'Access-Control-Allow-Origin': '*',
	              }
	        	})//Send GET request to cereals path
            .then(function (response) {//Request successful
                //mount the list component with the resulsts as props
                vue.$router.push({ name: 'list', params: {results:response.data}});
             })
             .catch(function (error) {
                //Handle error
                console.log(error);
             });
	   })

     //get the details of a specific book and the prices 
     bus.$on('view',(book)=>{
         axios.get(url+"getdetails?bookId="+book.id,{
                headers: {
                    'Access-Control-Allow-Origin': '*',
                }
            })//Send GET request to cereals path
            .then(function (response) {//Request successful
                //mount the book page with the details
                vue.$router.push({ name: 'display', params: { details:response.data,book:book}});
             })
             .catch(function (error) {
                //Handle error
                console.log(error);
             });
     })

    }
 });

