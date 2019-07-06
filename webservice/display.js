const display = { 
	template: `
    <div class="container" style="margin-top:30px;">
         <div v-if="details == undefined || details.length  == 0" class="text-center">
                 <h3>No Book FOUND</h3>
                 <button class="btn btn-light"><router-link to="/home">Search Again</router-link></button>
          </div>
          <div v-else>
                   <div class="row d-flex justify-content-center">
	             <div class="list-image col-md-4">
	                 <img class="list-img" :src="book.image" style="width:60%;height:auto;">
	             </div>
	             <div class="list-book">
	                <br><br><br><br><br>
	                <h4 class="list-title">{{book.name}}</h4>
	                <h3 class="list-author">{{book.author}}</h3>
	                <h3 class="list-isbn">{{book.author}}</h3>
	                <p class="list-publisher">{{book.pname}}</p>
	             </div>     
            </div>  
            <div class="card-deck flex justify-content-center" style="margin-top:20px;">
                <div v-for="vendor in details">
                     <div class="card vendor-item" style="margin-top:50px;!important">
					  <h5 class="card-header">{{vendor.retailer}}</h5>
					  <div class="card-body">
					    <h5 class="card-title">Price :&pound;<span class="vendor-price">{{round(vendor.price)}}</span></h5>
					    <button class="btn btn-primary"><a :href="vendor.url" style="color:#fff;" class="vendor-url">Visit Site</a></button>
					  </div>
	                </div>
                </div>
                </div>
            </div>
          </div> 
    </div>
` ,

props:['details','book'],
methods:{
	//rounds off number for prper display
	round : function(num){
        return parseFloat(num).toFixed(2);
	}
}

};