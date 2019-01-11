<%@page import="traitement.EspaceClient"%>

<%@page import="beans.Location"%>
<%@page import="beans.*"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<meta name="viewport" content="initial-scale=1", content="width=device-width">
<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script src="materialize/js/materialize.js"></script>
<style type="text/css">
body{
	background-image: url("css/trouver.jpg");
	background-size:110% ;

</style>
<script type="text/javascript">
$(document).ready(function() {
	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
$('.sidenav').sidenav();

});
function submit(x) {
	
	var r=confirm("voulez vous vraiment annuler cette location");
	if(r){

		var url = "gererLocationClientControl";
		  var xhttp = new XMLHttpRequest();
		 
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
	           	   
		    	if(this.responseText=="bon")
		    	window.location.reload();
		    	else
		    	alert( this.responseText);

		  
		    
		    
		    
		    
		    }
		  };
		  xhttp.open("post", "gererLocationClientControl", true);
		  xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		  xhttp.send("annuler="+ x.value);
	}else{
		alert("operation annuler")
	}
	
	}


</script>
<title>Gerer Mes Locations </title>
</head>
<body>



<nav>

    <div class="nav-wrapper black">
    <div class="container">
      <a href="MenuClient.jsp" class="brand-logo">vPro</a>
      <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
       <ul class="right hide-on-med-and-down">
        <li><a href="TrouverVehicule.jsp">Trouver Vehicule</a></li>
        <li><a href="gererLocationClientControl">Gerer mes Locations</a></li>
        <li><a href="profilClient.jsp">Gerer mon profil</a></li>
        <li><a href="logoutClientControl">Logout</a></li>
      </ul>
    </div>
    </div>
  </nav>
  
<div class="container">
  <ul class="sidenav" id="mobile-demo">
        <li><a href="TrouverVehicule.jsp">Trouver Vehicule</a></li>
        <li><a href="gererLocationClientControl">Gerer mes Locations</a></li>
        <li><a href="profilClient.jsp">Gerer mon profil</a></li>
        <li><a href="logoutClientControl">Logout</a></li>
        </ul>
</div>
<%EspaceClient e=new EspaceClient(); %>
<% ArrayList<Location> location= new ArrayList<Location>( ); %>
<% ArrayList<Vehicule> vehicule= new ArrayList<Vehicule>( ); %>
<div class="container">
<div class="row" id="resultat">
    <div class="col s12 m12 l12">
      <div class="card white">
        <div class="card-content black-text">
          <span class="card-title">Mes Locations</span><b style="color: red">
          <%if(request.getAttribute("message")!=null){
        	out.println(request.getAttribute("message"));  
          } else{
        	  location= new ArrayList<Location>( (ArrayList<Location>) request.getAttribute("location"));
        	 vehicule= new ArrayList<Vehicule>( (ArrayList<Vehicule>) request.getAttribute("vehicule")); 
          }%>
          </b>



<table class="responsive-table">
	
	<thead>
		<tr><th>ID</th><th>Type de Vehicule</th><th>Marque </th><th> Modele </th><th> Periode </th><th>  </th></tr>
	</thead>
	<tbody>	
		<%for(Location l:location) { %>
		<%for(Voiture v:e.voiture()){%>
		<%if(l.getMatricule().equals(v.getMatricule())){ %>
				
	<tr><td> <%=l.getIdLocation() %></td><td> Voiture <%=v.getCategorie()%></td><td> <%=v.getMarque() %></td><td><%=v.getModele() %> </td> <td> <%=l.getDateDebut()%> <% if(l.isHeure()){out.print("à "+l.getHeureDebut());} %> et <%=l.getDateFin() %> <% if(l.isHeure()){out.print("à "+l.getHeureFin());} %></td>
	<td><% if(l.isEtatLocation())
	{out.println("effecuté");}
	else{out.println("<button name=\"annuler\" onclick=\"submit(this)\" id=\"xx\" value="+l.getIdLocation()+" class=\"btn waves-effect black white-text\">annuler</button>");} 
	
	%> 

	
	</td> </tr>
			<%}  %>
		<%} %>
			<%for(Bus v:e.bus()){%>
		<%if(l.getMatricule().equals(v.getMatricule())){ %>
				
	<tr><td> <%=l.getIdLocation() %></td><td> bus de  <%=v.getNombrePlace() %> places</td><td> <%=v.getMarque() %></td><td><%=v.getModele() %> </td> <td> <%=l.getDateDebut()%> <% if(l.isHeure()){out.print("à "+l.getHeureDebut());} %> et <%=l.getDateFin() %> <% if(l.isHeure()){out.print("à "+l.getHeureFin());} %></td>
	<td><% if(l.isEtatLocation())
	{out.println("effecuté");}
	else{out.println("<button name=\"annuler\" onclick=\"submit(this)\" id=\"xx\" value="+l.getIdLocation()+" class=\"btn waves-effect black white-text\">annuler</button>");} 
	
	%> 

	
	</td> </tr>
			<%}  %>
		<%} %>
			<%for(Moto v:e.moto()){%>
		<%if(l.getMatricule().equals(v.getMatricule())){ %>
				
	<tr><td> <%=l.getIdLocation() %></td><td> moto <%=v.getCategorie() %></td><td> <%=v.getMarque() %></td><td><%=v.getModele() %> </td> <td> <%=l.getDateDebut()%> <% if(l.isHeure()){out.print("à "+l.getHeureDebut());} %> et <%=l.getDateFin() %> <% if(l.isHeure()){out.print("à "+l.getHeureFin());} %></td>
	<td><% if(l.isEtatLocation())
	{out.println("effecuté");}
	else{out.println("<button name=\"annuler\" onclick=\"submit(this)\" id=\"xx\" value="+l.getIdLocation()+" class=\"btn waves-effect black white-text\">annuler</button>");} 
	
	%> 

	
	</td> </tr>
			<%}  %>
		<%} %>
		
		<% } %>	
	</tbody>
</table>

</div></div></div></div></div>
</body>
</html>