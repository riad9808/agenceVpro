
$(document).ready(function(){
    $("#infor").click(function(){
        $("#info").toggle();
    });
    
    $('#typerech').change(function(){
    	var y = $("#typerech option:selected").val();
   
    	if(y=="date"){
    		  $('.pard').show();
    		  $('#prixD').show();
    		  $('#prixH').hide();
    		  $('.parh').hide();
    		  $("#heureD").prop('required',false);
    		  $("#heureF").prop('required',false);
    		  $("#datef").prop('required',true);


    	}else{
    		$('.pard').hide();
    		$('#prixH').show();
  		  $('#prixD').hide();
		  $('.parh').show();
		  $("#heureD").prop('required',true);
		  $("#heureF").prop('required',true);
		  $("#datef").prop('required',false);

    	}
    	
    	
    });	
    	
    $('.modal').modal();
    $('.sidenav').sidenav();
    $('select').formSelect();
        
  
    
    $('#moto,#bus,#voiture').change(function(){
    	var x=$('input[name=type]:checked').val();
    	switch (x){
    	case 'voiture' :{
    		
    		 $('#avanceVoiture').show();
    	        $('#avanceBus').hide();
    	        $('#avanceMoto').hide();
    	        
    	       
    	};break;
    	case 'moto':{
    		$('#avanceMoto').show();
            $('#avanceVoiture').hide();
            $('#avanceBus').hide();
    	};break;
    	case 'bus':{
    		$('#avanceBus').show();
            $('#avanceMoto').hide();
            $('#avanceVoiture').hide();
    	};break;
    	
    	
    	
    	}
    		
    	
    });
    
    
    $('#typeV').change(function(){
    	var y = $("#typeV option:selected").val();
    	$('#marqueV').find('option').remove();
    	$('#modeleV').find('option').remove();
		$('#enmarqueV').show();
    	$('#enmarqueB').hide();
    	$('#enmarqueM').hide();
    	$('#enmodeleV').hide();
	
    	switch(y){
    	case 'touristique' :{
    		$('#marqueV').append($("<option></option>").attr("value",'').text("--"));
    		$('#marqueV').append($("<option></option>").attr("value",'renault').text("renault"));
    		$('#marqueV').append($("<option></option>").attr("value",'peugeot').text("peugeot"));
    		$('#marqueV').append($("<option></option>").attr("value",'bmw').text("bmw"));
    		$('#marqueV').append($("<option></option>").attr("value",'volkswagen').text("volkswagen"));
    		$('#marqueV').append($("<option></option>").attr("value",'chevrolet').text("chevrolet"));
    		$('#marqueV').append($("<option></option>").attr("value",'kia').text("kia"));
    		$('#marqueV').append($("<option></option>").attr("value",'hyundai').text("hyundai"));
    		$('#marqueV').append($("<option></option>").attr("value",'seat').text("seat"));
    		
    	};break;
    	case 'comercial' :{
    		$('#marqueV').append($("<option></option>").attr("value",'').text("--"));

    		$('#marqueV').append($("<option></option>").attr("value",'renaultC').text("renault comerce"));
    		$('#marqueV').append($("<option></option>").attr("value",'peugeotC').text("peugeot comerce"));
    		$('#marqueV').append($("<option></option>").attr("value",'hyundaiC').text("hyundai comerce"));

    	};break;
    	case 'tout':{
    		$('#marqueV').append($("<option></option>").attr("value",'').text("--"));

    		$('#marqueV').append($("<option></option>").attr("value",'renault').text("renault"));
    		$('#marqueV').append($("<option></option>").attr("value",'peugeot').text("peugeot"));    		
    		$('#marqueV').append($("<option></option>").attr("value",'bmw').text("bmw"));    		
    		$('#marqueV').append($("<option></option>").attr("value",'volkswagen').text("volkswagen"));   
    		$('#marqueV').append($("<option></option>").attr("value",'dacia').text("dacia"));
    		$('#marqueV').append($("<option></option>").attr("value",'chevrolet').text("chevrolet"));
    		$('#marqueV').append($("<option></option>").attr("value",'kia').text("kia"));
    		$('#marqueV').append($("<option></option>").attr("value",'hyundai').text("hyundai"));
    		$('#marqueV').append($("<option></option>").attr("value",'seat').text("seat"));
       		$('#marqueV').append($("<option></option>").attr("value",'renaultC').text("renault comerce"));
    		$('#marqueV').append($("<option></option>").attr("value",'peugeotC').text("peugeot comerce"));
    		$('#marqueV').append($("<option></option>").attr("value",'hyundaiC').text("hyundai comerce"));
    	};break;
    	}
    	
    });
    $('#marqueV').change(function(){
    	var y = $("#marqueV option:selected").val();
    	$('#modeleV').find('option').remove();
    	
		 $('#enmodeleV').show();
		 $('#enmodeleB').hide();
		 $('#enmodeleM').hide();

	
    	switch(y){
    	case 'renault' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'clio').text("clio"));
    		$('#modeleV').append($("<option></option>").attr("value",'fluence').text("fluence"));
    		$('#modeleV').append($("<option></option>").attr("value",'megane').text("megane"));
    		$('#modeleV').append($("<option></option>").attr("value",'symbol').text("symbol"));

    	};break;
    	case 'peugeot' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'208').text("208"));
    		$('#modeleV').append($("<option></option>").attr("value",'308').text("308"));
    		$('#modeleV').append($("<option></option>").attr("value",'301').text("301"));


    	};break;
    	
    	case 'bmw' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'serie 1').text("serie 1"));
    		$('#modeleV').append($("<option></option>").attr("value",'x1').text("x1"));
    		$('#modeleV').append($("<option></option>").attr("value",'320d').text("320d"));

    	};break;
    	
    	case 'volkswagen' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'polo').text("polo"));
    		$('#modeleV').append($("<option></option>").attr("value",'golf').text("golf"));
    		$('#modeleV').append($("<option></option>").attr("value",'tiguan').text("tiguan"));
    		

    	};break;
    	
    
    	case 'dacia' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'logan').text("logan"));
    		$('#modeleV').append($("<option></option>").attr("value",'sandero').text("sandero"));

    	};break;
    	case 'chevrolet' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'aveo').text("aveo"));
    		

    	};break;
    	case 'hyundai' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'atos').text("atos"));
    		$('#modeleV').append($("<option></option>").attr("value",'accent').text("accent"));
  

    	};break;
    	case 'kia' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'picanto').text("picanto"));
    		$('#modeleV').append($("<option></option>").attr("value",'sportage').text("sportage"));
    		

    	};break;
    	case 'seat' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'ibiza').text("ibiza"));
    		$('#modeleV').append($("<option></option>").attr("value",'leon').text("leon"));
    		

    	};break;
    
    	case 'renaultC' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'trafic').text("trafic"));
    		$('#modeleV').append($("<option></option>").attr("value",'kangoo').text("kangoo"));
    	};break;case 'peugeotC' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'partner').text("partner"));
    		$('#modeleV').append($("<option></option>").attr("value",'expert').text("expert"));
    	};break;case 'hyundaiC' :{
    		$('#modeleV').append($("<option></option>").attr("value",'').text("--"));
    		$('#modeleV').append($("<option></option>").attr("value",'h1').text("h1"));
    	};break;
    	
  
    	
    
	}
    	
    });
    
    
    







    
$('#typeM').change(function(){
	var y = $("#typeM option:selected").val();
	$('#marqueM').find('option').remove()
	$('#modeleM').find('option').remove();
	$('#enmodeleM').hide();

	
	$('#enmarqueM').show();
	$('#enmarqueV').hide();
	$('#enmarqueB').hide();
	switch(y){
	case 'sport' :{
		$('#marqueM').append($("<option></option>").attr("value",'').text("--"));
		$('#marqueM').append($("<option></option>").attr("value",'kawasaki').text("kawsaki"));
		$('#marqueM').append($("<option></option>").attr("value",'suzuki').text("suzuki"));
		$('#marqueM').append($("<option></option>").attr("value",'bmw').text("bmw"));


	
	};break;
	case 'scouter' :{
		$('#marqueM').append($("<option></option>").attr("value",'').text("--"));
		$('#marqueM').append($("<option></option>").attr("value",'sym').text("sym"));
		$('#marqueM').append($("<option></option>").attr("value",'yamaha').text("yamaha"));

	
	};break;
	case 'tout' :{
		$('#marqueM').append($("<option></option>").attr("value",'').text("--"));
		$('#marqueM').append($("<option></option>").attr("value",'kawasaki').text("kawsaki"));
		$('#marqueM').append($("<option></option>").attr("value",'sym').text("sym"));
		$('#marqueM').append($("<option></option>").attr("value",'suzuki').text("suzuki"));
		$('#marqueM').append($("<option></option>").attr("value",'bmw').text("bmw"));

	};break;
	}
});
    
$('#nbplace').change(function(){
	var y = $("#nbplace option:selected").val();
	$('#marqueB').find('option').remove()
	 $('#enmarqueB').show();
	$('#enmodeleB').hide();
	
	$('#modeleB').find('option').remove();
	$('#enmarqueV').hide();
	$('#enmarqueM').hide();
	switch(y){
	case '24' :{
		$('#marqueB').append($("<option></option>").attr("value",'').text("--"));
		$('#marqueB').append($("<option></option>").attr("value",'isuzu').text("isuzu"));
		
		$('#marqueB').append($("<option></option>").attr("value",'nissan').text("nissan"));

		$('#marqueB').append($("<option></option>").attr("value",'haier').text("haier"));
	
	};break;
	case '40' :{
		$('#marqueB').append($("<option></option>").attr("value",'').text("--"));
		$('#marqueB').append($("<option></option>").attr("value",'higer').text("higer"));
		$('#marqueB').append($("<option></option>").attr("value",'mitsubishi').text("mitsubishi"));
		

		$('#marqueB').append($("<option></option>").attr("value",'king long').text("king long"));

		$('#marqueB').append($("<option></option>").attr("value",'sonacom').text("sonacom"));
	
	};break;
	case '60' :{
		$('#marqueB').append($("<option></option>").attr("value",'').text("--"));
		$('#marqueB').append($("<option></option>").attr("value",'yutong').text("yutong"));
		$('#marqueB').append($("<option></option>").attr("value",'foton').text("foton"));
		$('#marqueB').append($("<option></option>").attr("value",'volvo').text("volvo"));
		$('#marqueB').append($("<option></option>").attr("value",'iveco').text("iveco"));

		$('#marqueB').append($("<option></option>").attr("value",'tata').text("tata"));
	
	};break;
	case '0' :{
		$('#marqueB').append($("<option></option>").attr("value",'').text("--"));

		$('#marqueB').append($("<option></option>").attr("value",'isuzu').text("isuzu"));
		$('#marqueB').append($("<option></option>").attr("value",'nissan').text("nissan"));
		$('#marqueB').append($("<option></option>").attr("value",'king long').text("king long"));
		$('#marqueB').append($("<option></option>").attr("value",'yutong').text("yutong"));

		$('#marqueB').append($("<option></option>").attr("value",'mitsubishi').text("mitsubishi"));

		$('#marqueB').append($("<option></option>").attr("value",'foton').text("foton"));
		$('#marqueB').append($("<option></option>").attr("value",'higer').text("higer"));
		$('#marqueB').append($("<option></option>").attr("value",'tata').text("tata"));	
		$('#marqueB').append($("<option></option>").attr("value",'sonacom').text("sonacom"));
		$('#marqueB').append($("<option></option>").attr("value",'haier').text("haier"));


	};break;
	}
});
        

$('#marqueB').change(function(){
	var y = $("#marqueB option:selected").val();
	$('#modeleB').find('option').remove();
	
	
	 $('#enmodeleB').show();
	 $('#enmodeleV').hide();
	 $('#enmodeleM').hide();


	switch(y){	
	case 'haier' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'236').text("236"));
		
	};break;
	case 'sonacom' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'100v8').text("100v8"));
		
	};break;
	case 'tata' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'loc').text("loc"));
		
	};break;
	case 'isuzu' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'novo').text("novo"));
		
	};break;
	case 'nissan' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'civilian').text("civilian"));
		
	};break;
	case 'foton' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'autocar').text("autocar"));
		
	};break;
	case 'mitsubishi' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'rosa').text("rosa"));
		
	};break;
	case 'higer' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'v91').text("v91"));
		
	};break;
	case 'yutong' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'zk6118hga').text("zk6118hga"));
		
	};break;
	case 'king long' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'XMQ6858').text("XMQ6858"));
		
	};break;
	case 'volvo' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'GX57').text("GX57"));
		
	};break;
	case 'iveco' :{
		$('#modeleB').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleB').append($("<option></option>").attr("value",'eurorider').text("eurorider"));
		
	};break;
	}
});


$('#marqueM').change(function(){
	var y = $("#marqueM option:selected").val();
	$('#modeleM').find('option').remove();
	 $('#enmodeleM').show();
	 $('#enmodeleV').hide();
	 $('#enmodeleB').hide();



	switch(y){	
	
	case 'kawasaki' :{
		$('#modeleM').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleM').append($("<option></option>").attr("value",'z1000').text("z1000"));
		$('#modeleM').append($("<option></option>").attr("value",'z750').text("z750"));
		$('#modeleM').append($("<option></option>").attr("value",'z800').text("z800"));
		$('#modeleM').append($("<option></option>").attr("value",'z900').text("z900"));


	};break;
	case 'sym' :{
		$('#modeleM').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleM').append($("<option></option>").attr("value",'125').text(" wolf 125"));

		$('#modeleM').append($("<option></option>").attr("value",'fiddle').text(" fiddle"));
		$('#modeleM').append($("<option></option>").attr("value",'cruisym').text(" cruisym"));


	};break;
	case 'yamaha' :{
		$('#modeleM').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleM').append($("<option></option>").attr("value",'tmax').text(" Tmax"));
		$('#modeleM').append($("<option></option>").attr("value",'xmax').text(" Xmax"));
		$('#modeleM').append($("<option></option>").attr("value",'vmax').text(" vmax"));


	};break;
	case 'suzuki' :{
		$('#modeleM').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleM').append($("<option></option>").attr("value",'GSX-R600').text(" GSX-R600"));
		$('#modeleM').append($("<option></option>").attr("value",'GSX-R1000').text(" GSX-R1000"));


	};break;
	case 'bmw' :{
		$('#modeleM').append($("<option></option>").attr("value",'').text("--"));
		$('#modeleM').append($("<option></option>").attr("value",'1600').text(" 1600"));
		$('#modeleM').append($("<option></option>").attr("value",'1200').text(" 1200"));


	};break;
	}

});



  













$("#idForm").submit(function(e) {
	var heuref=($("#heureF").val());
	var heured=($("#heureD").val());
	
    var url = "trouverVehiculeControl"; 
    e.preventDefault();
    $.ajax({
    	
           type: "POST",
           url: url,
           
           data: $("#idForm").serialize()+"&heureD="+heured+"&heuref="+heuref, 
           success: function (data) {
        	   if(data=="date errone" ||data=="une erreur est survenue"|| data=="les vehicules avec vos caracteristique sont indisponible" ||data == "entrez une periode S.V.P completer les champs date et heure de debut et de fin"){
        		   alert(data);
        	  }else{
        		  if(data=="notlogged"){
        			  alert(data);
        		  }else{
        		   $('td').remove();
        		   $('#resultat').show();
        		   
        		   
       
        		   $.each(data,function(index,value){
	        		   
  	                 $('#result').append("<tr><td>" + data[index].matricule + 
  	                		 "</td><td>" + data[index].marque +
  	                		 "</td><td>" + data[index].modele + 
  	                		 "</td><td>"+data[index].tarifHeure+
  	                		 "</td><td>"+data[index].tarifJour+
  	                		 
  	                		 "</td><td>"+"<button data-target=\"modal1\" onclick=\"sub(this)\" class=\"btn modal-trigger\" name=\"louer\" value="+data[index].matricule + ">Louer</button>"+
  	                		 "</td></tr>");
  	        	   })
        		   
        	   }
        	
        	 
        	  }
        	   
        
        	
        	  	
            },
            
           
});

});











    
});





function sub(x) {
	var s=x.value;
	
	
	
		var txt="";
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	 var myArr = JSON.parse(this.responseText);
		    	        console.log(this.response);
		    	            txt += "<tr><td>" + myArr.matricule + "</td>"+
		    	            "<td>" + myArr.marque + "</td>"+
		    	            "<td>" + myArr.modele + "</td>"+
		    	            "<td>" + myArr.type + "</td>"+
		    	            "<td>" + myArr.dateD + "</td>"+
		    	            "<td>" + myArr.dateF + "</td>"+
		    	            "<td>" + myArr.heurD + "</td>"+
		    	            "<td>" + myArr.heurF + "</td>"+
		    	            "<td>" + myArr.adresse + "</td>"+

		    	            "<td>" + myArr.periode + "</td>"+	
		    	            "<td>" + myArr.tarif + "</td></tr>"	
		    	            ;
		    	        
		    	        document.getElementById("result2").innerHTML = txt;
		    	        document.getElementById("mine").innerHTML = " <button onclick=\"submit(this)\" name=\"louer\" value="+myArr.matricule +" class=\"modal-close waves-effect waves-green btn-flat\">confirmer</button>"+"<button class=\"modal-close waves-effect waves-green btn-flat\">annuler</button>";

		    }
		  };
		  xhttp.open("get", "ListeVehicule?louer="+x.value, true);
		  xhttp.send();

	
	}
function submit(x) {
	var s="louer="+x.value;
	
	var r=confirm("voulez vous vraiment louer ce vehicule");
	if(r){

		var url = "louerVehiculeControl";
		  var xhttp = new XMLHttpRequest();
		 
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    alert( this.responseText);
		    }
		  };
		  xhttp.open("get", "louerVehiculeControl?louer="+x.value, true);
		  xhttp.send(s);
	}else{
		alert("operation annuler")
	}
	
	}


