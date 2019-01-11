<%@page import="beans.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="traitement.EspaceGestionnaire" %>
<%@page import="beans.*" %>

<%@page import="beans.Location" %>

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
<title>Gerer Clients</title>


<% ArrayList<Client> lC= new ArrayList<Client>((ArrayList<Client>)request.getAttribute("listeClient")); %>


<script type="text/javascript">


$(document).ready(function(){
	$('.sidenav').sidenav();

	$(".dropdown-trigger").dropdown();
	        
    $('.modal').modal();
	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
	

	
	
	});	

	function submit(x) {
		
			var r=confirm("veuillez confirmer votre action");
			if(r){
				
			
			  var xhttp = new XMLHttpRequest();
			 
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	
			    	if(this.responseText=="bloquer avec succes"){
			    		confirm("notifier le client");
			    	}
			    	window.location.reload();
  					
			    	alert(this.responseText);
			    
			    
			    
			    
			    }
			  };
			  xhttp.open("post", "gererClientControl", true);
			  xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			  xhttp.send("bloque="+ x.value);
			}
		
		}
function sub(x) {
		
var yy="";
	var txt="";
			var url = "gererClientControl";
			  var xhttp = new XMLHttpRequest();
			 
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
		           	   if(this.responseText=="aucune location pour ce client"){
		           		   alert(this.responseText);
		           	   }
		           	   else{
		  		    	 var myArr = JSON.parse(this.responseText);
		  		    		var size =myArr.length;
		  		    	var modepayment=false;
		  		    	var t=""; var tt="";
		  		    		document.getElementById("autre").innerHTML ="<tr><td>"+size+"</td></tr>";
		        		   $('.modal').modal('open');
		        		   for (i in myArr){
		        			   t="";tt="";
		        			   if(myArr[i].modePayement){
		        				   modepayment="cheque";
		        			   }else{
		        				   modepayment="liquide";

		        			   }
		        			   if(myArr[i].heure){
		        				   t=myArr[i].heureDebut; tt=myArr[i].heureFin;
		        			   }
		        			   
	
		        			   txt += "<tr><td>" + myArr[i].idLocation + "</td>"+
			    	            "<td>" + myArr[i].matricule + "</td>"+
			    	            "<td>" + myArr[i].dateDebut + "</td>"+
			    	            "<td>" + myArr[i].dateFin + "</td>"+
			    	            "<td>" + t + "</td>"+
			    	            "<td>" + tt + "</td>"
			    	            	
			    	            ;
			    	           
			    	            
			    	            
			        		   document.getElementById("info").innerHTML = txt;
		        		   }
		        		  
		    	       
		           	   }


			  
			    
			    
			    
			    
			    }
			  };
			  xhttp.open("post", "gererClientControl", true);
			  xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			  xhttp.send("action="+ x.value);
		
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






      	 		

 <div class="card white lighten-4">
        <div class="card-content black-text">
          <span class="card-title black-text"> la liste des Client</span>
         	 <div class="row">	
  <table class="responsive-table ">
	<caption><b></b></caption>
	<thead>
		<tr><th> ID client </th><th> Nom </th><th> Prénom </th><th> Date de Naissance </th><th> Adresse </th><th> Telephone</th><th>User Name</th><th> Mail </th></tr>
	</thead>
	<tbody>	
	<%for(Client c:lC){%>
		<tr><td><%= c.getIdClient() %></td><td><%= c.getNom() %></td><td><%= c.getPrenom() %><td><%= c.getDateNaissance() %><td><%= c.getAdresse() %><td><%=c.getTelephone() %></td><td><%=c.getUser()%></td><td><%= c.getMail() %></td>
		<td>
		<%
		Integer s=c.getIdClient();
			if(c.isEstBloque())
				out.print("<button id=\"x\" onclick=\"submit(this)\" class=\"btn transparent teal-text btn-small\" name=\"bloque\" value=a"+s+" id=\"x\">debloquer</button>");
			else{
				out.print("<button id=\"x\" onclick=\"submit(this)\" name=\"bloque\" class=\"transparent teal-text btn-small\"  value=b"+s+">bloquer</button>");
				out.print("<button id=\"x\" onclick=\"submit(this)\" name=\"bloque\" class=\"transparent teal-text btn-small\"  value=c"+s+">SUPRIMER</button>");
		}%></td>
			<td><button id="xx" onclick="sub(this)" class="btn transparent teal-text btn"  value="<%=c.getIdClient()%>" name="action">Etat</button></td>
			
		
		</tr>

		<% }%>
</tbody>
</table>
       	 
         	 </div></div></div>
  
         	   <div id="modal1" class="modal">
    <div class="modal-content" >
    <h4>Liste des Locations</h4>


  <table class="responsive-table">

	<thead>
		<tr><th > nombre de location  </th></tr>
	</thead>
	<tbody id="autre">	
	
</tbody>
</table>	
    
    
      <table class="responsive-table">
	
	<thead>
		<tr><th>ID Location</th><th>Matricule du Véhicue</th><th>date debut</th><th>date fin </th><th> heure debut </th><th> heure fin </th></tr>
	</thead>
	<tbody id="info">	

	</tbody>
</table>



    </div>
    <div id="mine" class="modal-footer">
     
            <button class="modal-close waves-effect waves-green btn-flat" >fermer</button>
      
    </div>
  </div>  
         	 
      


</body>
</html>