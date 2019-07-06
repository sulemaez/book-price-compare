 // Define the HTML that will appear on each page
const home = { 
	template: ` <div class="container">
			        <link rel="stylesheet" href="styles/home.css" />
					<div class="row">
					    <div class="col-12"><h2>Search For Book</h2></div>
					    <div class="col-12">
				    	    <div id="custom-search-input">
				                <div class="input-group">
				                    <input type="text" class="search-query form-control" placeholder="Search" />
				                </div>
				            </div>
				            <button @click="search" class="btn btn-primary">Search</button>
				        </div>
					</div>
                 </div>` ,
     
     methods: {
		 //method to hanlde search button clicked
         search : function(){
			//get the query keyword from the textfield
			let q = document.querySelector(".search-query").value
			//transmit event to parent component
         	 bus.$emit('search', q);
         }
     } 
     };

