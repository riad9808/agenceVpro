<%@page import="beans.Gestionnaire"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Agence De Location De Véhicule </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1", content="width=device-width">
<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script src="materialize/js/materialize.js"></script>
<style type="text/css">
body{
	background-image: url("css/garagiste.jpg");
	background-size: cover;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
$('.sidenav').sidenav();  
$('select').formSelect();

$('.collapsible').collapsible();



$('#moto,#bus,#voiture').change(function(){
	var x=$('input[name=type]:checked').val();
	switch (x){
	case 'voiture' :{
		
		 $('#avancerVoiture').show();
	        $('#avancerBus').hide();
	        $('#avancerMoto').hide();
	        
	       
	};break;
	case 'moto':{
		$('#avancerMoto').show();
        $('#avancerVoiture').hide();
        $('#avancerBus').hide();
	};break;
	case 'bus':{
		$('#avancerBus').show();
        $('#avancerMoto').hide();
        $('#avancerVoiture').hide();
	};break;
	
	
	
	}
		
	
});







$("#idForm").submit(function(e) {
	var hhh=confirm("voulez vous vraiment ajouter ce vehicule");
	if(hhh){
    var url = "ajouterVehiculeControl"; 
    e.preventDefault();
    $.ajax({
    	
           type: "POST",
           url: url,
           
           data: $("#idForm").serialize(), 
           success: function (data) {
        	   
        	   alert(data);
        	   
        	   
        
        	
        	  	
            },
            
           
});
	}else
		alert("operation annuler");

});



	
	$("#retirer").submit(function(e) {
		
	    var url = "retirerVehiculeControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "POST",
	           url: url,
	           
	           data: $("#retirer").serialize(), 
	           success: function (data) {
	        	 
	        	   alert(data);
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});

	});
	
	

		
		$("#reinte").submit(function(e) {
			
		    var url = "reintegrerVehiculeControl"; 
		    e.preventDefault();
		    $.ajax({
		    	
		           type: "POST",
		           url: url,
		           
		           data: $("#reinte").serialize(), 
		           success: function (data) {
		        	  
		        	   alert(data);
		        	   
		        	   
		        
		        	
		        	  	
		            },
		            
		           
		});

		});


window.history.pushState(null, "", window.location.href);        
window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
};


$("#tyepR").change(function(e) {
	
	var y=$(this).val();
	switch (y){
	case "retour" : {
		$("#det").show();

	};break;
	case "hors" : {
		$("#det").hide();

	};break;
	
	}

});	
});	
</script>
</head>
<body>

<% Gestionnaire g=(Gestionnaire)(session.getAttribute("Gestionnaire")); %>





<nav class="white" role="navigation">


    <div class="nav-wrapper container">
    	<a id="logo-container" href="menuGaragiste.jsp" class="brand-logo black-text">vPro</a>
     
      <a href="#" data-target="mobile-demo" class="sidenav-trigger black-text"><i class="material-icons">menu</i></a>
       <ul class="right hide-on-med-and-down">
        
        <li><a href="logoutGestionnaireControl" class="black-text ">Logout</a></li>
      </ul>
    </div>
    
  </nav>

  <ul class="sidenav" id="mobile-demo">
       
        <li><a href="logoutGestionnaireControl">Logout</a></li>
        </ul>



<div class="container">	

	 <div class="row">
    <div class="col s12 l6">
      <div class="card white lighten-4">
        <div class="card-content white-text">
          <span class="card-title black-text">Ajouter un vehicule</span>
         	 <div class="row">	
         	 
         	 
         	   <form method="POST" action="ajouterVehiculeControl" id="idForm">
        <div class="row">
					<div class=" col s12 l12">
            		<label>type du vehicule</label>
            		<label><input type="radio" name="type" value="voiture" id="voiture" /><span>voiture</span></label>
            		<label><input type="radio" name="type" value="bus"  id="bus" /><span>bus </span></label>
            		<label><input type="radio" name="type" value="moto" id="moto"/><span>moto</span></label>
            		<input type ="hidden" name="type" checked="checked">
            		
           		</div>
           		
        		
            	</div>
        <div>
                <div id="avancerBus" style="display: none;">
                    <label>nombre de place</label>
                    <select name="nbplace" required>
                        <option value="24">24</option>
                        <option value="40">40</option>
                        <option value="60">60</option>
                    </select>
                </div>
                <div id="avancerVoiture" style="display: none;">
                    <label>type de voiture</label>
                    <select name="categorieVoiture" required>
                        <option value="comercial">comercial</option>
                        <option value="touristique">touristique</option>
                    </select>
                </div>
                <div id="avancerMoto" style="display: none;">
                   <label>type de moto</label>
                	<select name="categorieMoto" id="typeM">
            			
            			
                    	<option value="sport">sport</option>
                    	<option value="scouter">scouter</option>
                	</select>
                </div>
        </div>

        
        
        <div class="input-field col s12 l12">
          <input type="text" name="matricule" required id="matricule">
          <label for="matricule">Matricule</label>
        </div>
        <div class="input-field col s6">
          <input type="text" name="marque" required id="Marque">
          <label for="Marque">Marque</label>
        </div>
        <div class="input-field col s6">
          <input type="text" name="modele" required id="Modele">
          <label for="Modele">Modele</label>
        </div>
        <div class="input-field col s6">
          <input type="number" name="tarifHeure" required id="tarifHeure">
          <label for="tarifHeure">tarif par heure</label>
        </div>
        <div class="input-field col s6">
          <input type="number" name="tarifJour" required id="tarifJour">
          <label for="tarifJour">tarif par jour</label>
        </div>
        <div cass="row">
        	 <label>code depot</label>
            <select name="codeDepot" required>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
        </div>
        
        <input type="submit" class="btn black" value="ajouter">
        
    </form>
         	 
         	 
         	 
         	 
         	 </div>
         	 </div>
         	 </div>
         	 </div>
         
         	 
         	 
         	 

   
	
	
  
  <div class="col s12 l6">
  <ul class="collapsible">
    <li>
      <div class="collapsible-header"><i class="material-icons">remove</i>retirer un vehicule</div>
      <div class="collapsible-body white"><span>choisissez</span>
      
      	
	 
  <form method="POST" action="retirerVehiculeControl" id="retirer">
  			 
          <input type="text" name="matricule" required id="matricule1">
           <label for="matricule1">matricule</label>						
          <div class="row">
            <label><input type="radio" value="location" name="typeR"/><span>pour location</span></label>
          <label><input type="radio" value="definitif" name="typeR"/><span>definitivement</span></label>
      	  <label><input type="radio" value="temporaire" name="typeR"/><span>temporairent</span></label></div>
      	  <select name="depot" id="depot">
  									<option value="" selected="selected" required>depot</option> 
  									<option value="1">Depot 1 - Nouvelle Ville</option>
  									<option value="2">Depot 2 - Centre Ville</option>
  									<option value="3">Depot 3 - Zwaghi </option>
  									<option value="4">Depot 4 - Boussouf</option>
  								</select>	
 		<input type="number" name="nombre de jour"  id="nbjour">
  		 <label for="nbjour">nombre de jour</label>						
  		<div class="row"></div><input type="submit" class="btn black" value="retirer">
  </form>

      
      </div>
    
    
    
    
    </li>
    <li>
      <div class="collapsible-header"><i class="material-icons">add</i>reintgerer un vehicule</div>
      <div class="collapsible-body white">
      
      
       <form method="POST" action="reintegrerVehiculeControl" id="reinte">
  				<input type="text" name="matricule" required id="matricule2">
          		<label for="matricule2">matricule</label>
  				<div class="row">
  				
  				<select name="typeR" id="tyepR">
  					<option value="" disabled="disabled" selected="selected">type de retour</option> 
  					<option value="retour">retour de location</option>
  					<option value="hors">hors service</option>
  					
  				</select>
  				</div>
  				<div id="det" style="display: none;">
    				<div class="col l6">
    				<input type="text" name="description" id="desc">
          		<label for="desc">description</label>
          		</div>
  				<div class="input-field col l6"> 
         			<p class="range-field">
         			<label>Etat de Retour <output name="prixdmn">--</output> %</label></label>
      				<input name="etat" type="range" id="prixDmin" min="0" max="100" value="100" step="1" oninput="prixdmn.value=parseInt(prixDmin.value)"/>
   					</p>
   				</div>
   				
  				<div class="row">
          		<div class="col l12">
          			   <input type="text" name="idcontrat" id="cont" >
      	          <label for="cont">id contrat</label>
      	          </div>
  				</div>
  				
  				
  				
  			 
          		
          		<div class="row">
          		<div class="col l12">
          			   <input type="date" name="dateR"  id="date" >
      	          <label for="date">date de retour</label>
      	         
      	          <div class="">
      	          <input type="time" name="heurR"  id="heur" >
      	          <label for="heur">heure de retour</label>
      	           </div>
          		</div>
          		</div>
          		</div>
          		<div class="row">
          		<div class="col l6">
      	         
      	          <select name="depot" id="depot">
  					<option value="" selected="selected">depot</option> 
  					<option value="1" >Depot 1 - Nouvelle Ville </option>
  					<option value="2">Depot 2 - Centre Ville </option>
  					<option value="3">Depot 3 - Zouaghi </option>
  					<option value="4">Depot 4 - boussouf</option>
  				</select>
          		</div>
      	      </div>
      	       
      			
  		<input type="submit" class="btn black" value="reintgerer vehicule">
  </form>
      
      </div>
    </li>
    
  </ul>
  </div>
	

</div>
</div>
</body>
</html>