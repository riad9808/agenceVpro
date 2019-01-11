$(document).ready(function(){
	
	$("#idForm").submit(function(e) {
		
	    var url = "identificationControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "POST",
	           url: url,
	           
	           data: $("#idForm").serialize(), 
	           success: function (data) {
	        	   if(data=="sucess"){
	        		   window.location.href='MenuClient.jsp';
	        	   }else
	        	   alert(data);
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});

	});
$("#idform2").submit(function(e) {
		
	    var url = "connexionGestionnaireControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "POST",
	           url: url,
	           
	           data: $("#idform2").serialize(), 
	           success: function (data) {
	        	   if(data=="mot de passe incorrect"||data=="gestionnaire inexsitant"){
	        		   alert(data);
	        	   }else{
	        		   window.location.href=data;
	        		   
	        	   }
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});

	});

	
	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
	
	
	
	
	$("#mdp").click(function(e) {
		
	    var url = "identificationControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "get",
	           url: url,
	           
	           data: $("#idForm").serialize(), 
	           success: function (data) {
	        	   if(data=="sucess"){
	        		   window.location.href='MenuClient.jsp';
	        	   }else
	        	   alert(data);
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});
	});
 

	
});









   
