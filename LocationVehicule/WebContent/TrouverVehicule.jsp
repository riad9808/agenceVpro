<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="traitement.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1", content="width=device-width">
<link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script src="materialize/js/materialize.js"></script>
<style type="text/css">
body{
	background-image: url("css/trouver.jpg");
	background-size:110% ;

</style>
<title>Trouver Un vehicule</title>

<script type="text/javascript" src="JS/trouver.js"></script>
<script type="text/javascript">
window.history.pushState(null, "", window.location.href);        
window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
};

</script>
</head>
<body>

    
  
  
<div class="container">	


<nav>
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
  </nav>

  <ul class="sidenav" id="mobile-demo">
        <li><a href="TrouverVehicule.jsp">Trouver Vehicule</a></li>
        <li><a href="gererLocationClientControl">Gerer mes Locations</a></li>
        <li><a href="profilClient.jsp">Gerer mon profil</a></li>
        <li><a href="logoutClientControl">Logout</a></li>
        </ul>
 
    		
    			
  
		

	<form class="col s12" action="trouverVehiculeControl" method="Post" id="idForm">	
  <div class="row">
    <div class="col s12">
      <div class="card grey lighten-4">
        <div class="card-content white-text">
          <span class="card-title black-text">Trouver un vehicule</span>
         	 <div class="row">	
		  	
    		<div class="col s12">
    			<div class="row">
					<div class=" col s6 l6">
            		<label>type du vehicule</label>
            		<label><input type="radio" name="type" value="voiture" id="voiture" required/><span>voiture</span></label>
            		<label><input type="radio" name="type" value="bus"  id="bus" /><span>bus </span></label>
            		<label><input type="radio" name="type" value="moto" id="moto"/><span>moto</span></label>
            		<input type ="hidden" name="type" checked="checked">
            		
           		</div>
           		
        		
            	</div>
            	
            	
            	<div class="row">
            	<div class="input-field col s12 l2">
      			<select name="typerech" id="typerech" required="required">
        		 <option value="date" selected>date</option>
        		<option value="heure">heure </option>
        		</select>
        		 </div>   	
        		
                    <div class="input-field col s6 l2">
          				<input id="nom" type="date"   name="dateD" required="required">
          				<label for="nom">date de debut</label>
        			</div>
        			<div  class="pard" >
        			<div class="input-field col s6 l2">
          				<input id="datef" type="date"  name="dateF" required="required">
          				<label for="nom">date de fin</label>
        			</div>
        			
        			
        		</div>
        			<div  class="parh" style="display: none;">
                    <div class="input-field col s6 l2" >
          				<input id="heureD" type="time"   >
          				<label for="heureD">heure de debut</label>
        			</div>
        			<div class="input-field col s6 l2">
          				<input id="heureF" type="time" >
          				<label for="heureF">heure de fin</label>
        			</div>
        			
        			
        		</div>
        		
        		</div>
			<div class="row">
			<div class="input-field col s3 l1">
        	<a href="#" class="secondary-content black-text " id="infor">filtrer</a></div>
        	 </div>	
        	<div id="info" class="row" style="display: none;">
        		
        		<div class="col l3 s6">
        	 <label>code depot</label>
            <select name="codeDepot" >
                <option value="0" selected="selected">choisissez une region</option>
            
                <option value="1">nouvelle ville</option>
                <option value="2">centre ville</option>
                <option value="3">zwaghi</option>
                <option value="4">bossouf</option>
            </select>
        </div>
        		
        		
        		<div class="row">
        		
        		
        		
       			<div id="prixD" >	 
        		<div class="input-field col s6 l3"> 
         			<p class="range-field">
         			<label>prix minimum par jour : <output name="prixdmn">--</output></label></label>
      				<input name="prixDmin" type="range" id="prixDmin" min="0" max="10000" value="0" step="10" oninput="prixdmn.value=parseInt(prixDmin.value)"/>
   					</p>
   				</div>
   				<div class="input-field col s6 l3">
     				<p class="range-field">
     				<label>prix maximum par jour :  <output name="prixdmx">--</output></label>
      				<input name="prixDmax" type="range" id="prixDmax" min="0" max="10000" value="0" step="10" oninput="prixdmx.value=parseInt(prixDmax.value)"/>
      				
      				
    				</p>     
       			</div>
       			</div>
       			
       			
       			<div id="prixH" style="display: none;">
       			<div class="input-field col s6 l3"> 
         			<p class="range-field">
         			<label>prix minimum par heure : <output name="prixhmn">--</output></label></label>
      				<input name="prixhmin" type="range" id="prixhmin" min="0" max="1000" value="0" step="10" oninput="prixhmn.value=parseInt(prixhmin.value)"/>
   					</p>
   				</div>
   				<div class="input-field col s6 l3">
     				<p class="range-field">
     				<label>prix maximum par heure :  <output name="prixhmx">--</output></label>
      				<input name="prixhmax" type="range" id="prixhmax" min="0" max="1000" value="0" step="10" oninput="prixhmx.value=parseInt(prixhmax.value)"/>
      				
      				
    				</p>     
       			</div>
        		</div>
        		</div>
        		
        		<div class="row">
        			<div id="avanceBus" style="display: none;" >
        			<div class=" col s12 l3">
                	<label>nombre de place</label>
                	<select name="nbplace" id="nbplace">
                 		<option value="0" selected="selected" class="browser-default"> All </option>
                       	<option value="24">24</option>
                    	<option value="40">40</option>
                    	<option value="60">60</option>
                	</select>
                	</div>
                	<div id="enmarqueB" class=" col s12 l4" style="display: none;"> 
        			<label>marque</label>
        			<select name="marqueB" id="marqueB" class="browser-default">
        				<option  >
        				</option>
        			</select>
          		</div>
        		<div id="enmodeleB" class=" col s12 l3" style="display: none;"> 
        			<label>modele</label>
        			<select name="modeleB" id="modeleB" class="browser-default">
        				<option >
        				</option>
        			</select>
        		</div>
                	
            	</div>
            	
            	<div id="avanceVoiture" style="display: none;">
            		<div class="col s12 l3">
                	<label>type de voiture</label>
                	<select name="typeV" id="typeV" class="browser-default">
                		<option value="tout"  selected="selected">All</option>
                    	<option value="comercial">comercial</option>
                    	<option value="touristique">touristique</option>
                    	
                	</select>
                	</div>
                	<div id="enmarqueV" style="display: none;" class="col s12 l3"> 
        			<label>marque</label>
        			<select name="marqueV" id="marqueV" class="browser-default">
        				<option  >
        				</option>
        			</select>
          		</div>
        		<div id="enmodeleV"  style="display: none;" class="col s12 l3"> 
        			<label>modele</label>
        			<select name="modeleV" id="modeleV" class="browser-default">
        				<option>
        				</option>
        			</select>
        		</div>
                	
            	</div>
            	
            	
            	
           		<div id="avanceMoto" style="display: none;" >
           			<div class=" col s12 l3">
                	<label>type de moto</label>
                	<select name="typeM" id="typeM" class="browser-default">
            			
            			<option value="tout" selected="selected">All</option>
                    	<option value="sport">sport</option>
                    	<option value="scouter">scouter</option>
                	</select>
                	</div>
                	<div id="enmarqueM" class=" col s12 l2" style="display: none;"> 
        			<label>marque</label>
        			<select name="marqueM" id="marqueM" class="browser-default">
        				<option >
        				</option>
        			</select>
          		</div>
        		<div id="enmodeleM" class=" col s12 l2" style="display: none;"> 
        			<label>modele</label>
        			<select name="modeleM" id="modeleM" class="browser-default">
        				<option >
        				</option>
        			</select>
        		</div>
                	
            	</div>
        		       
        	
       	 	</div>
        	
        		
        		
        		
        	</div>
       	 	
        	
    	</div>
    	
    	</div>
        </div>
        <div class="card-action">
         <button class="btn waves-effect waves-brown  black white-text text-lighten-2" id="h">trouver</button>
        </div>
      </div>
    </div>
  </div>

</form>



  <div class="row" id="resultat" style="display: none;">
    <div class="col s12 m12 l12">
      <div class="card blue-grey darken-1">
        <div class="card-content white-text">
          <span class="card-title">Resultat</span>
        	
 <table class="responsive-table" >
  <thead>
  <tr>
    <th>matricule</th>
    <th>marque</th>
    <th>modele</th>
    <th>tarif par heure</th>
    <th>tarif par jour</th>
   
  </tr>
  </thead>
  <tbody id="result">
  
  </tbody>
 
</table> 


        	
        	
        </div>
       
      </div>
    </div>
  </div>




      	 
  <div id="modal1" class="modal">
    <div class="modal-content" >
    <h4>voulez vous vraiment louer ce vehicule</h4>
       <table class="responsive-table" >
  <thead>
  <tr>
    <th>matricule</th>
    <th>marque</th>
    <th>modele</th>
     <th>type</th>
    <th>date debut</th>
    <th>date fin</th>
    <th>heure debut</th>
    <th>heure fin</th>
     <th>adresse</th> 
    
    <th>periode</th>
   <th>tarif</th>
  </tr>
  </thead>
  <tbody id="result2">
  
  </tbody>
 
</table> 

    </div>
    <div id="mine" class="modal-footer">
     
      
    </div>
  </div>  



</div>

  
</body>
</html>