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

<title>Etablir Contrat</title>
<script type="text/javascript">
$(document).ready(function(){
	$('.sidenav').sidenav();
	$('select').formSelect();
    $('.modal').modal();

	$(".dropdown-trigger").dropdown();
$("#idForm").submit(function(e) {
		
	    var url = "gererLocationControl"; 
	    e.preventDefault();
	    $.ajax({
	    	
	           type: "POST",
	           url: url,
	           
	           data: $("#idForm").serialize(), 
	           success: function (data) {
	        		if(data=="vous devez saissir un idClient valide"||data=="client inexistant"||data=="aucune location trouver"){
	 	        	   alert(data);

	        		}else{
	        			   $('td').remove();
	            		   $('#resultat').show();
	            		   
	            		   
	           				var t="";
	            		   $.each(data,function(index,value){
	            			   t=""
	    	        		  if(data[index].heure){
	    	        			   t="</td><td>"+data[index].heureDebut+ "</td> et <td>"+data[index].heureFin;
	    	        		   }
	      	                 $('#result').append("<tr><td>"+"<button data-target=\"modal1\" onclick=\"sub(this)\" class=\"btn modal-trigger\"  value="+data[index].idLocation + ">etablir</button>"+
	      	                		 "</td><td>" + data[index].idLocation + 
	      	                		 "</td><td>" + data[index].matricule +
	      	                		 "</td><td>" + data[index].dateDebut + 
	      	                		 "</td> et <td>"+data[index].dateFin+
	      	                		t+
	      	                		 
	      	                		 "</td></tr>");
	      	        	   })
	            		 
	        		}
	        	   
	        	   
	        
	        	
	        	  	
	            },
	            
	           
	});

	});
});	
function sub(x) {
	var s=x.value;
	 var xhttp = new XMLHttpRequest();
	 var txt="";
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    
	    	var myArr = JSON.parse(this.responseText);
	        
	            txt += "<tr><td>" + myArr.idlocation + "</td>"+
	            "<td>" + myArr.idclient + "</td>"+
	            "<td>" + myArr.nom + "</td>"+
	            "<td>" + myArr.prenom + "</td>"+
	            "<td>" + myArr.matricule + "</td>"+
	            "<td>" + myArr.type + "</td>"+
	            "<td>" + myArr.marque + "</td>"+
	            "<td>" + myArr.modele + "</td></tr>"	
	            ;
	        
	        document.getElementById("result2").innerHTML = txt;
	        document.getElementById("mine").innerHTML = " <button onclick=\"submit(this)\" name=\"louer\" value="+myArr.idlocation +" class=\"modal-close waves-effect waves-green btn-flat\">confirmer</button>"+"<button class=\"modal-close waves-effect waves-green btn-flat\">annuler</button>";

	    	
	    	
	    	
	    }
	  };
	  xhttp.open("get", "gererLocationControl?loc="+s, true);

	  xhttp.send();
	
	 document.getElementById("mine").innerHTML = " <button onclick=\"submit(this)\"  value="+s+" class=\"modal-close waves-effect waves-green btn-flat\">confirmer</button>"+"<button class=\"modal-close waves-effect waves-green btn-flat\">annuler</button>";

		 	}
function submit(x) {
	var s="idLocation="+x.value+"&modep="+document.getElementById("modep").value+"&typeO=contrat&contrat=choisirContrat";
	
	var r=confirm("voulez vous etablir ce contrat");
	if(r){

		
		  var xhttp = new XMLHttpRequest();
		 
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    alert( this.responseText);
		    }
		  };
		  xhttp.open("post", "gererLocationControl", true);
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


<div class="container">	
<div class="row">
<div class="col l3"></div>
	<div id="contrat" >
	 <div class="row">
    <div class="col s12 l6">
      <div class="card white lighten-4"  >
        <div class="card-content">
          <span class="card-title black-text">Etablir contrat</span>
         	 <div class="row">	
         	 <form action="gererLocationControl" method="post" id="idForm">
         	         	<input type="hidden" value="contrat" name="typeO">
         				<input type="hidden" value="getContrat" name="contrat">
         				
         	 
         	 
         	<input type="text" name="idclient" id="id" required>
         	 <label for="id">idClient</label>
         	 
         	 <input type="date" name="dateD" id="dd">
         	 <label for="dd">date de Debut</label>
         	 
         	 <input type="time" name="heurD" id="hh">
         	 <label for="hh">heure de Debut</label>	
         	 
         	 <div class="card-action">
           <input type="submit"  class="btn" value="Etablir">
        </div>
         	</form>
         	 </div></div></div></div></div>
       </div>
       </div>

  <div class="row" id="resultat" style="display: none;">
    <div class="col s12 m12 l12">
      <div class="card white">
        <div class="card-content black-text">
          <span class="card-title">Resultat</span>
        	
 <table class="responsive-table" >
  <thead>
  <tr>
  <th></th>
    <th>Id Location</th>
    <th>matricule</th>
    <th>periode</th>
   
   
  </tr>
  </thead>
  <tbody id="result">
  
  </tbody>
 
</table> 


        	
        	
        </div>
       
      </div>
    </div>
  </div>
</div>
  <div id="modal1" class="modal">
    <div class="modal-content" >
    <h4></h4>
          <table class="responsive-table" >
  <thead>
  <tr>
    <th>id location</th>
    <th>id client</th>
    <th>nom</th>
     <th>prenom</th>
    <th>matricule</th>
    <th>type</th>
    <th>marque</th>
    <th>modele</th>
     
  </tr>
  </thead>
  <tbody id="result2">
  
  </tbody>
 
</table> 
    
       <label>modalité de payment</label>
         	 <select name="modep" id="modep">
         	 <option value=""  selected="selected">--</option>
         	 	<option value="cheque">par cheque</option>
         	 	<option value="liquide">en liquide</option>
         	 	
         	 </select>

    </div>
    <div id="mine" class="modal-footer">
     
      
    </div>
  </div>  

</body>
</html>