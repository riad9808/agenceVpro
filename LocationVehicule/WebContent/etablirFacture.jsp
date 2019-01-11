<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1", content="width=device-width">
<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script src="materialize/js/materialize.js"></script>
<title>Etablir Facture</title>
<script type="text/javascript">
$(document).ready(function(){
	$('.sidenav').sidenav();

	$(".dropdown-trigger").dropdown();
	$('#simple,#penalite').change(function(){
		var x=$(this).val();
		switch (x){
		case "simple" : {
			$("#moncontrat").show();
			$("#montant").hide();

		};break;
		case "penalite" : {
			$("#moncontrat").show();
			$("#montant").show();
		};break;
 		}

});
$("#idForm").submit(function(e) {
		
	    var url = "gererLocationControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "POST",
	           url: url,
	           
	           data: $("#idForm").serialize(), 
	           success: function (data) {
	        	
	        	   alert(data);
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});

	});
});	

</script>
<style type="text/css">
body {
	background-image: url("css/conexion.jpg");
	background-size: cover;
}
</style>
</head>
<body>
 <nav class="teal">
 <div class="container">
    <div class="nav-wrapper">
      <a href="menuEmploye.jsp" class="brand-logo">vPro</a>
      <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
     <li><a href="gererClientControl">Gerer Clients</a></li>
        <li><a class="dropdown-trigger" href="#!" data-target="dropdown1">Gerer Location<i class="material-icons right">arrow_drop_down</i></a></li>
        <li><a href="modifierPrixVehicule.jsp">Modifier Prix Vehicule</a></li>
        <li><a href="logoutGestionnaireControl">Logout</a></li>
      </ul>
    </div>
    <ul id="dropdown1" class="dropdown-content">
  <li><a href="etablirContrat.jsp">etablir Contrat</a></li>
  <li><a href="etablirFacture.jsp">Etablir Facture</a></li>
  <li><a href="etablirAvisFinLocation.jsp">Etablir Avis Fin Location</a></li>
</ul>
    
    </div>
  </nav>

  <ul class="sidenav" id="mobile-demo">
         <li><a href="gererClientControl">Gerer Clients</a></li>
         <li><a href="etablirContrat.jsp">etablir Contrat</a></li>
         <li><a href="etablirFacture.jsp">etablir facture</a></li>
         <li><a href="etablirAvisFinLocation.jsp">etablir Avis Fin location</a></li>
        <li><a href="modifierPrixVehicule.jsp">Modifier Prix Vehicule</a></li>
        <li><a href="logoutGestionnaireControl">Logout</a></li>
  </ul>



<div class="container">	
<div class="row">
<div class="col l3"></div>



	   <div id="facture" >
         	  <div class="row" >
    <div class="col s12 l6">
      <div class="card white lighten-4">
        <div class="card-content ">
          <span class="card-title black-text">Etablir Facture</span>
         	 <div class="row">	
         	  <form action="gererLocationControl" method="post" id="idForm">
         	 <input type="hidden" value="facture" name="typeO">
         	 
         	 <div class="row">
         	 <label><input id="simple" type="radio" name="type" value="simple" required/><span>facture simple</span></label>
           	 <label><input id="penalite" type="radio" name="type" value="penalite" /><span>facture de penailté</span></label>
       </div>
       <div id="moncontrat" style="display:none">
         	 <input type="text" name="idContrat" id="contratt">
         	 <label for="contratt">id Contrat</label>
         	          	 </div>	
         	   <div id="montant" style="display:none">
         	 <input type="text" name="montant" id="montantt">
         	 <label for="montantt">montant</label>
         	          	 </div>	       	 
         	 <div class="card-action">
           <input type="submit" class="btn" value="Etablir">
        </div>
        </form>
         	 </div></div></div></div></div>
         	 
         	 </div>
         	 
        
         	 </div></div>
</body>
</html>