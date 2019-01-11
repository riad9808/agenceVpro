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
	window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};

	$(document).ready(function(){
	    $('.sidenav').sidenav();
	    $('#user').click(function(){
	    	$('#newuser').toggle();
	    });
	    $('#adresse').click(function(){
	    	$('#newadresse').toggle();
	    });
	    $('#mail').click(function(){
	    	$('#newmail').toggle();
	    });
	    $('#telephone').click(function(){
	    	$('#newtelephone').toggle();
	    });
	    $('#password').click(function(){
	    	$('#newpassword').toggle();
	    });
	    
	    $("#idform").submit(function(e) {
			
		    var url = "gererProfilClientControl"; 
		    e.preventDefault();
		    $.ajax({
		    	
		           type: "POST",
		           url: url,
		           
		           data: $("#idform").serialize(), 
		           success: function (data) {
		        	   if(data=="sucess"){
		        		   window.location.href='profilClient.jsp';
		        	   }else
		        	   alert(data);
		        	   
		        	   
		        
		        	
		        	  	
		            },
		            
		           
		});

		});

	
	});
	 
	</script>
	<link rel="stylesheet" type="text/css" href="css/gererprofil.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
body{
	background-image: url("css/profil.jpg");
	background-size: cover;
}


</style>
<title>Gerer Profil Client</title>
</head>
<body>

<% Client c=(Client)request.getSession().getAttribute("client"); %>
<%EspaceClient e=new EspaceClient(); %>



<nav class="black">
<div class="container">


    <div class="nav-wrapper black">
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
        <li><a href="logoutGestionnaireControl">Logout</a></li>
        </ul>
        
  



	<form action="gererProfilClientControl"  method="post" id="idform">
   <ul class="collection with-header">
        <li class="collection-header"><h4>information</h4></li>
        <li class="collection-item"><div>Nom : <div id="info"><%= c.getNom() %> </div></div></li>
        <li class="collection-item"><div>Prenom :<div id="info"> <%= c.getPrenom()%></div></div></li>
        <li class="collection-item"><div>Date de naissance :<div id="info"> <%= c.getDateNaissance() %></div></div></li>
       
        <li class="collection-item"><div>Numero de permis de conduire :<div id="info"> <%= c.getIdClient() %></div></div></li>
        <li class="collection-item"><div>date d'inscription :<div id="info"> <%= c.getDateInscription() %></div></div></li>
       	
       	<li class="collection-item">
       		user :
       			<div id="info">
       				<%= c.getUser() %>
       				<a href="#!" class="secondary-content black-text" id="user">modifier</a>
       			</div>
       			
					<div class="input-field col s6 " style="display: none;" id="newuser">
          				<input  type="text" name="User" >
         				 <label for="user">entrez le nouveau user</label>
        			</div>
       		
       	</li>
       <li class="collection-item">
       		adresse : 
       		<div id="info">
       			<%= c.getAdresse() %>	
       			<a href="#!" class="secondary-content black-text" id="adresse">modifier</a>
       		</div>
       		<div class="input-field col s6" style="display: none;" id="newadresse">
          				<input  type="text" name="adresse" >
         				 <label for="adresse">entrez l'adresse</label>
        			</div>
       		
       </li>
       <li class="collection-item">
       		mail :
       		<div id="info"> 
       			<%= c.getMail() %>
       			<a  href="#!" class="secondary-content black-text" id="mail">modifier</a>
       		</div>
       		<div class="input-field col s6" style="display: none;" id="newmail">
          				<input  type="email" name="mail" >
         				 <label for="mail">entrez l'email</label>
        			</div>
       	</li>
       	<li class="collection-item">
       	telephone :
       		<div id="info"> 
       			<%= c.getTelephone() %>
       			<a   href="#!" class="secondary-content black-text" id="telephone">modifier</a>
       		</div>
       	
       	<div class="input-field col s6" style="display: none;" id="newtelephone">
          				<input  type="text" maxlength="10" minlength="10" name="telephone" pattern="-?[0-9]*(\.[0-9]+)?">
         				<label for="telephone">entrez le telephone</label>
        			</div>
       	
       	
       	</li>
       <li>
       		<li class="collection-item">
       	mot de passe 
       		
       			
       			<a href="#!"  class="secondary-content black-text" id="password">modifier</a>
       		
       	
       	<div class="input-field col s6" style="display: none;" id="newpassword">
          				<div>
          				<input  type="password" name="password" >
         				<label for="password" maxlength="10" minlength="5">entrez le mot de passe</label></div>
         				<div>
         				<input  type="password" name="password1" >
         				<label for="password1" maxlength="10" minlength="5">confirmer le mot de passe</label></div>
         				
        			</div>
       
       </li>

            <li class="collection-item">
					<div class="input-field col s6 white" >
          				<input  type="password" name="ancienpassword" >
         				 <label for="last_name">ancien mot de passe</label>
        			</div>
        			 <button class="btn waves-effect waves-light black"  name="action">Modifier Profil</button>
    			</li>		
  
		      </ul>
	</form>
	</div>
	
	
       				
	
                 
                    
</body>
</html>