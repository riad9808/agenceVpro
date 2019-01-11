<%@page import="beans.Moto"%>
<%@page import="beans.Bus"%>
<%@page import="beans.Vehicule" %>
<%@page import="beans.Voiture" %>
<%@page import="java.util.ArrayList"%>
<%@page import="traitement.EspaceGestionnaire" %>
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
<title>Modifier Prix Vehicule</title>

<script type="text/javascript">



$(document).ready(function(){
$('.sidenav').sidenav();
$('.modal').modal();

$(".dropdown-trigger").dropdown();
        
window.history.pushState(null, "", window.location.href);        
window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
};


});


function sub(x){
	var y=x.value;
    document.getElementById("confirmation").innerHTML = " <button name=\"matricule\" id=\"hhh\" onclick=\"send(this)\" value="+y+" class=\"modal-close waves-effect waves-green btn-flat\">confirmer</button>"+"<button class=\"modal-close waves-effect waves-green btn-flat\">annuler</button>";

}
function send(x){
	var s="matricule="+x.value+"&tarifHeure="+document.getElementById("tarifHeure").value+"&tarifJour="+document.getElementById("tarifJour").value;
	
	var r=confirm("voulez vous vraiment modifier le prix");
	if(r){

		
		  var xhttp = new XMLHttpRequest();
		 
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    alert( this.responseText);
		    console.log( this.responseText);

		  
		    }
		  };
		  xhttp.open("post", "modifierPrixControl", true);
		  xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

		  xhttp.send(s);
	}else{
		alert("operation annuler")
	}
}
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





<%EspaceGestionnaire g=new EspaceGestionnaire();
ArrayList<Voiture> voiture=new ArrayList<Voiture>();
voiture=g.voiture();
ArrayList<Bus> bus=new ArrayList<Bus>();
bus=g.bus();
ArrayList<Moto> moto=new ArrayList<Moto>();
moto=g.moto();
%>

<div class="container">

 <div class="card white lighten-4">
        <div class="card-content black-text">
          <span class="card-title black-text"> liste des vehicule</span>
         	 <div class="row">	
         	 		
         	 		<table class="responsive-table striped">
	<caption><b> </b></caption>
	<thead>
		<tr><th>Type du Vehicule</th><th>Marque </th><th> Modele </th><th> prix par heure </th><th> prix par jour </th></tr>
	</thead>
	<tbody>	
		<tr><td colspan="5"> Voitures  </td></tr>
	
<%for(Voiture v:voiture){ %>
	<tr><td> voiture <%=v.getCategorie() %></td><td> <%=v.getMarque() %></td><td><%=v.getModele() %> </td> <td><%= v.getTarifHeure() %> </td><td><%= v.getTarifJour() %> </td>
	 <td><button data-target="modal1" value=<%=v.getMatricule() %>  onclick="sub(this)" class="btn modal-trigger">Modifier Prix</button></td>
	 <td></td>
	  </tr>

<%} %>
	<tr><td colspan="5"> Bus  </td></tr>

<%for(Bus v:bus){ %>
	<tr><td> bus de <%=v.getNombrePlace() %> places </td><td> <%=v.getMarque() %></td><td><%=v.getModele() %> </td> <td><%= v.getTarifHeure() %> </td><td><%= v.getTarifJour() %> </td>
	 <td><button data-target="modal1" value=<%=v.getMatricule() %>  onclick="sub(this)" class="btn modal-trigger">Modifier Prix</button></td>
	 <td></td>
	  </tr>

<%} %>
	<tr><td colspan="5"> Moto  </td></tr>

<%for(Moto v:moto){ %>
	<tr><td> moto <%=v.getCategorie() %></td><td> <%=v.getMarque() %></td><td><%=v.getModele() %> </td> <td><%= v.getTarifHeure() %> </td><td><%= v.getTarifJour() %> </td>
	 <td><button data-target="modal1" value=<%=v.getMatricule() %>  onclick="sub(this)" class="btn modal-trigger">Modifier Prix</button></td>
	 <td></td>
	  </tr>

<%} %>
</tbody>
</table>
         	 
         	 </div></div></div></div>
         	 
           <form  id="forma">
         	 
  <div id="modal1" class="modal">
    <div class="modal-content">
      <h4>Entrez le nouveau Prix</h4>
      
      	<div class="input-field col s6">
          <input type="number" name="tarifHeure" id="tarifHeure">
          <label for="tarifHeure">prix par heure</label>
        </div>
        <div class="input-field col s6">
          <input type="number" name="tarifJour"  id="tarifJour">
          <label for="tarifJour">prix par jour</label>
        </div>
           </div>
    <div id="confirmation" class="modal-footer">
      <button class=" waves-effect waves-green btn-flat">confirmer</button>
    </div>
  </div>         	 
</form>

</body>
</html>