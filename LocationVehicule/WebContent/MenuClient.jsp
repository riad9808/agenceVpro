<%@page import="beans.Depot"%>
<%@page import="beans.Vehicule"%>
<%@page import="beans.Moto"%>
<%@page import="beans.Voiture"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Bus"%>
<%@page import="traitement.EspaceClient"%>
<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JS/jquery.min.js"></script>
	<meta name="viewport" content="initial-scale=1", content="width=device-width">
	  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
	<script src="materialize/js/materialize.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	    $('.sidenav').sidenav();
	 	    	
	    	window.history.pushState(null, "", window.location.href);        
	    	window.onpopstate = function() {
	    	    window.history.pushState(null, "", window.location.href);
	    	};
	    	
	  
	});
	</script>
  		<style type="text/css">
  		.mdl-navigation{
  			align-content: left
  		}
  		body {
	background-image : url("css/background1.jpg");
	background-size: contain;
}
  		
  		</style>
  		<% Client c=(Client)(request.getSession().getAttribute("client"));%>
</head>
<body>
<% EspaceClient e=new EspaceClient();
	ArrayList<Bus> b=e.bus();
	ArrayList<Voiture> v=e.voiture();
	ArrayList<Moto> m=e.moto();
	Depot d1=new Depot();
	d1.setCode(1);
	Depot d2=new Depot();
	d2.setCode(2);
	Depot d3=new Depot();
	d3.setCode(3);
	Depot d4=new Depot();
	d4.setCode(4);

	ArrayList<Vehicule> veh1=e.libreParDepot(d1);
	ArrayList<Vehicule> veh2=e.libreParDepot(d2);
	ArrayList<Vehicule> veh3=e.libreParDepot(d3);
	ArrayList<Vehicule> veh4=e.libreParDepot(d4);

%>
<nav>

    <div class="nav-wrapper black">
    <div class="container">
      <a href="MenuClient.jsp" class="brand-logo">vPro  </a>
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

 <div class="card white lighten-4">
        <div class="card-content black-text">
          <span class="card-title black-text"> <i> Disponible dés maintenant ... </i></span>
         	 <div class="row">	
         	 		
         	 		<table class="responsive-table striped">
	<caption><b> </b></caption>
	<thead>
		<tr><th>Type du Vehicule</th><th>Marque </th><th> Modele </th><th> prix par heure </th><th> prix par jour </th></tr>
	</thead>
	<tbody>	
			<%if(veh1.size()!=0) { %>
	
		<tr><td > </td><td colspan="2" ><h5> <i> #vPro Nouvelle Ville    </i> </h5> </td><td colspan="2" > </td></tr>
	<%} %>
<%for(Vehicule va:veh1){ %>
	<%for(Voiture voit:v) {%>
		<% if(va.getMatricule().equals(voit.getMatricule())) {%>
	<tr><td> <i>Voiture</i> <%=voit.getCategorie() %></td><td> <i><%=voit.getMarque() %></i></td><td><i> <%=voit.getModele() %></i> </td> <td> <i> <%= voit.getTarifHeure() %></i> </td><td><i><%= voit.getTarifJour() %></i> </td> </tr>
		<%} %>

	<% } %>
	

<%for(Bus bu:b){ %>
		<% if(va.getMatricule().equals(bu.getMatricule())) {%>

	<tr><td> <i>Bus </i> de <%=bu.getNombrePlace() %> places </td><td><i> <%=bu.getMarque() %> </i></td><td><i><%=bu.getModele() %></i> </td> <td><i><%= bu.getTarifHeure() %></i> </td><td><i><%= bu.getTarifJour() %></i> </td> </tr>

		<%} %>
		<%} %>
	<%for(Moto mo:m){ %>
		<% if(va.getMatricule().equals(mo.getMatricule())) {%>

	<tr><td> <i>Moto</i> <%=mo.getCategorie() %>  </td><td> <i><%=mo.getMarque() %></i></td><td><i><%=mo.getModele() %></i> </td> <td><i><%= mo.getTarifHeure() %> </i></td><td><i><%= mo.getTarifJour() %> </i></td> </tr>

		<%} %>
		<% } } %>
				<%if(veh2.size()!=0) { %>
		
		<tr><td > </td><td colspan="2" ><h5> <i> #vPro Centre Ville </i> </h5> </td><td colspan="2" > </td></tr>
	<%} %>
<%for(Vehicule va:veh2){ %>
	<%for(Voiture voit:v) {%>
		<% if(va.getMatricule().equals(voit.getMatricule())) {%>
	<tr><td> <i>Voiture</i> <%=voit.getCategorie() %></td><td> <i><%=voit.getMarque() %></i></td><td><i> <%=voit.getModele() %></i> </td> <td> <i><%= voit.getTarifHeure() %> </i></td><td> <i><%= voit.getTarifJour() %></i> </td> </tr>
		<%} %>

	<% } %>
	

<%for(Bus bu:b){ %>
		<% if(va.getMatricule().equals(bu.getMatricule())) {%>

	<tr><td> <i>Bus </i> de <%=bu.getNombrePlace() %> places </td><td><i> <%=bu.getMarque() %> </i></td><td><i><%=bu.getModele() %></i> </td> <td><i><%= bu.getTarifHeure() %></i> </td><td><i><%= bu.getTarifJour() %></i> </td> </tr>

		<%} %>
		<%} %>
	<%for(Moto mo:m){ %>
		<% if(va.getMatricule().equals(mo.getMatricule())) {%>

	<tr><td> <i>Moto</i> <%=mo.getCategorie() %>  </td><td> <i><%=mo.getMarque() %></i></td><td><i><%=mo.getModele() %></i> </td> <td><i><%= mo.getTarifHeure() %> </i></td><td><i><%= mo.getTarifJour() %> </i></td> </tr>

		<%} %>
		<% } } %>
				<%if(veh3.size()!=0) { %>
		
	<tr><td > </td><td colspan="2" ><h5> <i> #vPro Zwaghi  </i> </h5> </td><td colspan="2" > </td></tr>

				<%} %>
<%for(Vehicule va:veh3){ %>
	<%for(Voiture voit:v) {%>
		<% if(va.getMatricule().equals(voit.getMatricule())) {%>
	<tr><td> <i>Voiture</i> <%=voit.getCategorie() %></td><td> <i><%=voit.getMarque() %></i>></td><td><i> <%=voit.getModele() %></i> </td> <td><i><%= voit.getTarifHeure() %> </i></td><td><i><%= voit.getTarifJour() %></i> </td> </tr>
		<%} %>

	<% } %>
	

<%for(Bus bu:b){ %>
		<% if(va.getMatricule().equals(bu.getMatricule())) {%>

	<tr><td> <i>Bus </i> de <%=bu.getNombrePlace() %> places </td><td><i> <%=bu.getMarque() %> </i></td><td><i><%=bu.getModele() %></i> </td> <td><i><%= bu.getTarifHeure() %></i> </td><td><i><%= bu.getTarifJour() %></i> </td> </tr>

		<%} %>
		<%} %>
	<%for(Moto mo:m){ %>
		<% if(va.getMatricule().equals(mo.getMatricule())) {%>

	<tr><td> <i>Moto</i> <%=mo.getCategorie() %>  </td><td> <i><%=mo.getMarque() %></i></td><td><i><%=mo.getModele() %></i> </td> <td><i><%= mo.getTarifHeure() %> </i></td><td><i><%= mo.getTarifJour() %> </i></td> </tr>

		<%} %>
		<% } } %>
		<%if(veh4.size()!=0) { %>
		<tr><td > </td><td colspan="2" ><h5> <i> #vPro Boussouf </i> </h5> </td><td colspan="2" > </td></tr>
	<%} %>
<%for(Vehicule va:veh4){ %>
	<%for(Voiture voit:v) {%>
		<% if(va.getMatricule().equals(voit.getMatricule())) {%>
	<tr><td> <i>Voiture</i> <%=voit.getCategorie() %></td><td> <i><%=voit.getMarque() %></i></td><td> <i> <%=voit.getModele() %></i> </td> <td><i><%= voit.getTarifHeure() %></i> </td><td><i><%= voit.getTarifJour() %> </i></td> </tr>
		<%} %>

	<% } %>
	

<%for(Bus bu:b){ %>
		<% if(va.getMatricule().equals(bu.getMatricule())) {%>

	<tr><td> <i>Bus </i> de <%=bu.getNombrePlace() %> places </td><td><i> <%=bu.getMarque() %> </i></td><td><i><%=bu.getModele() %></i> </td> <td><i><%= bu.getTarifHeure() %></i> </td><td><i><%= bu.getTarifJour() %></i> </td> </tr>

		<%} %>
		<%} %>
	<%for(Moto mo:m){ %>
		<% if(va.getMatricule().equals(mo.getMatricule())) {%>

	<tr><td> <i>Moto</i> <%=mo.getCategorie() %>  </td><td> <i><%=mo.getMarque() %></i></td><td><i><%=mo.getModele() %></i> </td> <td><i><%= mo.getTarifHeure() %> </i></td><td><i><%= mo.getTarifJour() %> </i></td> </tr>

		<%} %>
		<% } } %>
		
</tbody>
</table>
         	 
         	 </div></div></div></div>
         	 

  <ul class="sidenav" id="mobile-demo">
        <li><a href="TrouverVehicule.jsp">Trouver Vehicule </a></li>
        <li><a href="gererLocationClientControl">Gerer mes Locations</a></li>
        <li><a href="profilClient.jsp">Gerer mon profil</a></li>
        <li><a href="logoutClientControl">Logout</a></li>
        </ul>
      











</body>

</html>