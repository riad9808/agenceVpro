<%@page import="traitement.EspaceGestionnaire"%>
<%@page import="beans.*"%>

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
	background-image: url("css/gerent.jpg");
	background-size: cover;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.sidenav').sidenav();  
	$('select').formSelect();

	$('.collapsible').collapsible();

	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
	});	
	

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
         <% EspaceGestionnaire e=new EspaceGestionnaire();
	
	%>
<div class="container">
<div class="row">
     <div class="card white col l3">
        <div class="card-content black-text" >
          <span class="card-title">Voitures</span>
			<%out.print("nombre de voiture : "+e.voiture().size());%>
	<br>
        	
<%
			float voiture=e.recetteVoiture();
			out.print(" Recette voitures = "+voiture);%>
		
        	
        </div>
       
      </div>
      <div class="col l1"></div>
        <div class="card white col l3">
        <div class="card-content black-text" >
          <span class="card-title">Bus</span>

		 
	


			<%out.print("nombre de bus : "+e.bus().size());%>
	<br>

        	<% 
	float bus=e.recetteBus();
	
	
	out.print(" Recette bus = "+bus);%>
        	
        </div>
       
      </div>
            <div class="col l1"></div>
      
        <div class="card white col l3">
        <div class="card-content black-text" >
          <span class="card-title">Moto</span>

			<%out.print("nombre de moto : "+e.moto().size());%>
<br>
		 <% 
			
	float moto=e.recetteMoto();
	out.print(" Recette moto = "+moto);


%>
        	

        	
        </div>
       
      </div>
      </div>
      
      <div class="row">
        <div class="card white col l5" >
        <div class="card-content black-text" >
          <span class="card-title">Depot 1 - Nouvelle Ville</span>


		 <% 
	
	Depot a1=new Depot();
	a1.setCode(1);
	float dv1=e.recetteVoitureDepot(a1);
	float db1=e.recetteBusDepot(a1);
	float dm1=e.recetteMotoDepot(a1);
	out.println("Recette Voiture : " + dv1 + " Da");%>
	<br>
	<%out.println("Recette Bus : "+ db1 + " Da");%>
	<br>
	<% out.println("Recette Moto : "+dm1+ " Da");
	int a=e.nombreLocationDepot(a1);%>
	<br>
	<%out.println("Nombre de vehicule louer : "+a);%>
        	
        	
        </div>
       
      </div>
            <div class="col l1"></div>
      
        <div class="card white col l5">
        <div class="card-content black-text" >
          <span class="card-title">Depot 2 - Centre Ville</span>


		 <% 
	
			Depot a2=new Depot();
			a2.setCode(2);
			float dv2=e.recetteVoitureDepot(a2);
			float db2=e.recetteBusDepot(a2);
			float dm2=e.recetteMotoDepot(a2);
			out.println("Recette Voiture " + dv2 +" Da");%>
			<br>
			<%
			out.println("Recette VBs "+ db2+ " Da");%>
			<br>
			<%
			out.println("Recette Moto "+dm2 + " Da");%>
        	<br>
        	<%int b=e.nombreLocationDepot(a2);
        	out.println("Nombre de vehicule louer : "+b);%>
        	
        </div>
       </div>
      </div>
      <div class="row">
        <div class="card white col l5">
        <div class="card-content black-text" >
          <span class="card-title">Depot 3 - Zwaghi</span>


		 <%
			Depot a3=new Depot();
			a3.setCode(3);
			float dv3=e.recetteVoitureDepot(a3);
			float db3=e.recetteBusDepot(a3);
			float dm3=e.recetteMotoDepot(a3);
			out.println("Recette Voiture " + dv3 +" Da");%>
			<br>
			<% out.println("Recette Bus "+ db3+ " Da");%>
			<br>
			<%out.println("Recette Moto "+dm3+ " Da");%>
			<br>
        	<%int c=e.nombreLocationDepot(a3);
        	out.println("Nombre de vehicule louer : "+c);%>


        	
        </div>
       
      </div>
      <div class="col l1"></div>
      
            <div class="card white col l5">
        <div class="card-content black-text" >
          <span class="card-title">Depot 4 - Bossouf</span>


		 <%
			Depot a4=new Depot();
			a4.setCode(4);
			float dv4=e.recetteVoitureDepot(a4);
			float db4=e.recetteBusDepot(a4);
			float dm4=e.recetteMotoDepot(a4);
			out.println("Recette Voiture " + dv4+ " Da");%>
			<br>
			<% out.println("Recette Bus "+ db4 +" Da");%>
			<br>
			<%out.println("Recette Moto "+dm4 + " Da");%>
			<br>
			<%int d=e.nombreLocationDepot(a3);
        	out.println("Nombre de vehicule louer : "+d);%>
			
        	
        	
        </div>
       
      </div>  
      
     </div> 
      
 </div>     
</body>
</html>