<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="initial-scale=1", content="width=device-width">
<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script src="materialize/js/materialize.js"></script>
<style type="text/css">
body{
	background-image: url("css/gerent.jpg");
	background-size: cover;
}
</style>
<title>Etablir etat location d'agence</title>
<script type="text/javascript">

$(document).ready(function(){
window.history.pushState(null, "", window.location.href);        
window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
    
  
};
$('.sidenav').sidenav();  
$('select').formSelect();

$('.collapsible').collapsible();
$('.modal').modal();



$("#form1").submit(function(e) {
	
	
    var url = "etatLocationAgenceControl"; 
    e.preventDefault();
    $.ajax({
    	
           type: "get",
           url: url,
           
           data: $("#form1").serialize(), 
           success: function (data) {
        	 
        		   $('td').remove();
        		   $('#resultat').show();
        		   
        		   
       
        		   $.each(data,function(index,value){
	        		   
  	                 $('#result').append("<tr><td>" + data[index].matricule + 
  	                		 "</td><td>" + data[index].marque +
  	                		 "</td><td>" + data[index].modele + 
  	                		 "</td><td>"+data[index].tarifHeure+
  	                		 "</td><td>"+data[index].tarifJour+
  	                		 
  	                		 "</td><td>"+"<button data-target=\"modal1\" onclick=\"sub(this)\" class=\"btn modal-trigger\" name=\"matricule\" value="+data[index].matricule + ">Etat de Location</button>"+
  	                		 "</td></tr>");
  	        	   })
        		   
        	
        	   
        
        	
        	  	
            },
            
           
});

});


$("#form2").submit(function(e) {
	
	
    var url = "etatLocationAgenceControl"; 
    e.preventDefault();
    $.ajax({
    	
           type: "get",
           url: url,
           
           data: $("#form2").serialize(), 
           success: function (data) {
        	 
        		   $('td').remove();
        		   $('#resultat').show();
        		   
        		   
       
        		   $.each(data,function(index,value){
	        		   
  	                 $('#result').append("<tr><td>" + data[index].matricule + 
  	                		 "</td><td>" + data[index].marque +
  	                		 "</td><td>" + data[index].modele + 
  	                		 "</td><td>"+data[index].tarifHeure+
  	                		 "</td><td>"+data[index].tarifJour+
  	                		 
  	                		 "</td><td>"+"<button data-target=\"modal1\"  onclick=\"sub(this)\"  class=\"btn modal-trigger\" name=\"matricule\" value="+data[index].matricule + ">Etat de location</button>"+
  	                		 "</td></tr>");
  	        	   })
        		   
        	
        	   
        
        	
        	  	
            },
            
           
});

});





});
function sub(x) {
	var s="matricule="+x.value;
		

	
		var txt="";
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	if(this.responseText=="aucune location pour ce vehicule"){

					alert(this.responseText);
					window.location.reload();
		    	}
		    	else{
			    	 var myArr = JSON.parse(this.responseText);
			    	 var t=""; var tt=""
			    	 for (i in myArr){
			    		 t=""; 
			    		 tt="";
			    		 if(myArr[i].heure){
			    			 t=myArr[i].heureDebut;
			    			 tt=myArr[i].heureFin;
			    		 }
			    		 console.log( myArr[i]);
			    		 txt += "<tr><td>" + myArr[i].idLocation + "</td>"+
		    	            "<td>" + myArr[i].idClient + "</td>"+
		    	            "<td>" + myArr[i].dateDebut + "</td>"+
		    	            "<td>" + t + "</td>"+
		    	            "<td>" + myArr[i].dateFin + "</td>"+
		    	            "<td>" + tt + "</td></tr>";
			    	 }
		    		
    	        
    	        document.getElementById("result2").innerHTML = txt;
		    	}
		    	       
		    }
		  };
		  xhttp.open("post", "etatLocationAgenceControl", true);
		  xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

		  xhttp.send(s);
	}

</script>
</head>
<body>



<nav class="white" role="navigation">


    <div class="nav-wrapper container">
    	<a id="logo-container" href="menuGerantPrincipal.jsp" class="brand-logo black-text">vPro</a>
     
      <a href="#" data-target="mobile-demo" class="sidenav-trigger black-text"><i class="material-icons">menu</i></a>
       <ul class="right hide-on-med-and-down">
        	<li> <a href="recette.jsp" class="black-text ">Recette</a> </li>
	<li> <a href="etablirEtatLocation.jsp" class="black-text "> Etablir Etats De Location  </a> </li>
	
        <li><a href="logoutGestionnaireControl" class="black-text ">Logout</a></li>
      </ul>
    </div>
    
  </nav>

  <ul class="sidenav" id="mobile-demo">
       	<li> <a href="recette.jsp"> Calculer la Recette</a> </li>
	<li> <a href="etablirEtatLocation.jsp"> Etablir Etats De Location  </a> </li>
	
        <li><a href="logoutGestionnaireControl">Logout</a></li>
        </ul>


<div class="container">


<div class="row">
<div class="col l3"></div>     
   
  	<div class="col s12 l6">
  		<ul class="collapsible">
    		<li>
      
  					<div class="collapsible-header">etablir etat de location de l'agence</div>
      					<div class="collapsible-body white"><span></span>
        				<form method="get" action="etatLocationAgenceControl" id="form1">
      						<div class="row">
          		
          					
      						<input type="hidden" value="agence" name="type">
      						
      							<label>type du vehicule</label>
            		<label><input type="radio" name="typeV" value="voiture" id="voiture" /><span>voiture</span></label>
            		<label><input type="radio" name="typeV" value="bus"  id="bus" /><span>bus </span></label>
            		<label><input type="radio" name="typeV" value="moto" id="moto"/><span>moto</span></label>
            		<input type ="hidden" name="typeV" checked="checked">
            		</div>
            		
            		<input type="submit" class="btn black" value="etablir">
            		
						</form>
							
	       				</div>	

    		</li>
    
    		<li>
      			<div class="collapsible-header">etablir etat de location d'un depot</div>
      			<div class="collapsible-body white">
      
      
      				<form method="get" action="etatLocationAgenceControl" id="form2">		
  			 				<input type="hidden" value="depot" name="type">
          		
          					<div class="row">
          		
      	         
      	          				<select name="depot" id="depot">
  									<option value="--" disabled="disabled" selected="selected">depot</option> 
  									<option value="1">Depot 1 - Nouvelle Ville</option>
  									<option value="2">Depot 2 - Centre Ville</option>
  									<option value="3">Depot 3 - Zwaghi </option>
  									<option value="4">Depot 4 - Boussouf</option>
  								</select>
  								
          					
      	       				</div>
      			
  							<input type="submit" class="btn black" value="etablir">
  					</form>
      
      			</div>
    		</li>
    
  		</ul>
	</div>
</div>



  <div class="row" id="resultat" style="display: none;">
    <div class="col s12 m12 l12">
      <div class="card blue-grey darken-1">
        <div class="card-content white-text" >
          <span class="card-title">Resultat</span>
        	
 <table class="responsive-table" >
  <thead>
  <tr>
    <th>matricule</th>
    <th>marque</th>
    <th>modele</th>
    <th>tarif par heure</th>
    <th>tarif par jour</th>
   
  </tr>
  </thead>
  <tbody id="result">
  
  </tbody>
 
</table> 


        	
        	
        </div>
       
      </div>
    </div>
  </div>



  <div id="modal1" class="modal">
    <div class="modal-content" id="z">
    <h4>etat du vehciule</h4>
       <table class="responsive-table" >
  <thead>
  <tr>
    <th>id Location</th>
    <th>id Client</th>
    <th>date Debut</th>
    <th>heure Debut</th>
    <th>date fin</th>
    <th>heure fin</th>
    
   
  </tr>
  </thead>
  <tbody id="result2">
  
  </tbody>
 
</table> 

    </div>
    <div id="mine" class="modal-footer">
      <button class="modal-close waves-effect waves-green btn-flat">fermer</button>
            
      
    </div>
  </div>  


</div>


</body>
</html>