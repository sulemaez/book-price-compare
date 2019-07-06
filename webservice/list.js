const list = { template: `
      <div class="container" style="margin-top:50px !important;">
      <link rel="stylesheet" href="styles/list.css" /> 
           <div class="container result-list" style="margin-top:20px;">
              <div v-if="results == undefined || results.length  == 0" class="text-center">
                 <h3>No Book FOUND</h3>
                 <button class="btn btn-light"><router-link to="/home">Search Again</router-link></button>
              </div>
              <div v-else>
                    <h2 class="text-center">Search Results</h2>
                  <div v-for="element in results">
                  <div class="row result-item">
                  <div class="list-image col-md-2">
                    <img :src="element.image">
                  </div>
                  <div class="list-book">
                     <h4 class="list-title">{{element.name}}</h4>
                     <h3 class="list-tile">{{element.author}}</h3>
                     <h3 class="list-isbn">{{element.isbn}}</h3>
                     <p class="list-publisher">{{element.pname}}</p>
                     <button class="btn btn-primary" @click="view(element)" >View Prices</button>
                 </div>
                 </div>
               </div>
              </div>
          </div>
      </div>
	` ,
    props : ['results'],
     methods: {
      //haanlde a view btn click sendds user to view seleced boook 
     	view: function(element){
           //emit event to parent component
           bus.$emit('view',element);
     	}
     }
};