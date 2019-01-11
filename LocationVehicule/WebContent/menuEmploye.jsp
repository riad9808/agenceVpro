<%@page import="beans.Depot"%>
<%@page import="traitement.EspaceGestionnaire"%>
<%@page import="beans.Gestionnaire"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1", content="width=device-width">
<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script src="materialize/js/materialize.js"></script>
<title> Agence De Location De Véhicule </title>
<script type="text/javascript">


$(document).ready(function(){
$('.sidenav').sidenav();
$(".dropdown-trigger").dropdown();
	
	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
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

<% Gestionnaire g=(Gestionnaire)(session.getAttribute("Gestionnaire")); %>


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
<div class="col l4"></div>


     <div class="card white col l4">
        <div class="card-content black-text" >
                  <span class="card-title">satistique</span>
        
          
          <%
EspaceGestionnaire e=new EspaceGestionnaire();
out.println("nombre de client inscrit : " + e.nombreClient());%>
<br>
<%out.println("nombre de client Bloquer : " + e.nombreClientBloquer());%>
<br>
<% out.print("Nombre de location Aujourd'hui : "+e.nombreLocationToDay()); %>
<br>
</div>
    </div></div>      

<% 
Depot d1=new Depot();
d1.setCode(1);
Depot d2=new Depot();
d2.setCode(2);

Depot d3=new Depot();
d3.setCode(3);

Depot d4=new Depot();
d4.setCode(4);

%>
<div class="row">
<div class="col l3"></div>


     <div class="card white col l6">
<%out.print("Nombre de location depot Nouvelle Ville : "+e.nombreLocationDepot(d1)); %>
<br>
<%out.print("Nombre de location depot Centre Ville : "+e.nombreLocationDepot(d2)); %>
<br>
<%out.print("Nombre de location depot Zwaghi : "+e.nombreLocationDepot(d3)); %>
<br>
<%out.print("Nombre de location depot boussouf : "+e.nombreLocationDepot(d4)); 
%>
   </div></div> 
</div>


</body>
</html>