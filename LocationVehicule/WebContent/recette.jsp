<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> calculer la recette </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<script type="text/javascript">


$(document).ready(function(){
	$('.sidenav').sidenav();  
	$('select').formSelect();
	
	
$("#recette").submit(function(e) {
		
	    var url = "calculerRecetteControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "POST",
	           url: url,
	           
	           data: $("#recette").serialize(), 
	           success: function (data) {
	        	   if(data=="entrez une date valide"||data=="entrez une date s.v.p"||data=="entrez une periode svp"||data=="entrez une periode valide"){
	        		   alert(data);
	        	   }else{
	        		   $("#toshow ").show();
	        		   $("#resultat").text(data+"  DZD");
	        		   
	        	   }
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});

	});

	
	
	

	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
	});	
function select(){
    
    if(document.getElementById('type').value=='jour'){
        document.getElementById('jour').style.display="block";
        document.getElementById('periode').style.display="none";
    }else{
        document.getElementById('periode').style.display="block";
        document.getElementById('jour').style.display="none";
    }
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
   <div>    
   <form method="post" action="calculerRecetteControl" id="recette">     
        <div class="row">
    <div class="col s12 m6">
      <div class="card white">
        <div class="card-content black-text">
          <span class="card-title">Recette</span>
          <select name="type" id="type" onchange="select()">
            <option value="jour" >pour un jour</option>
            <option value="periode">pour une periode</option>
        </select>
        <div id="jour"> 
                <input type="date" name="date" id="date">
                <label for="date">date</label>
        </div>

        <div id="periode" style="display: none">
            <p>entre</p>
                <input type="date" name="dateDebut"> et
                <input type="date" name="dateFin">
        </div>
        </div>
        <div class="card-action">
          <input type="submit" class="btn" value="Calculer">
         
        </div>
      </div>
    </div>
  </div>
        
        
        
        
           
		
        </form>
            <div class="row">
            <div class="col l3"></div>
    <div class="col s12 m6">
      <div class="card white" style="display: none" id="toshow">
        <div class="card-content black-text">
          <span class="card-title">Montant</span>
          
        	<div id="resultat"></div>
			
        
        </div>
        
      </div>
    </div>
  </div>
    </div>
        </div> 
        </div>
        
</body>
</html>