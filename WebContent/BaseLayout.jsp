<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="assets/img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title><tiles:insertAttribute name="title" ignore="true" /> - Bank al maghrib</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
    <style type="text/css">
#overlay {
     visibility: hidden;
     position: absolute;
     left: 0px;
     top: 0px;
     width:100%;
     height:100%;
     text-align:center;
     z-index: 1000;
}

#overlay div {
     width:300px;
     margin: 100px auto;
     background-color: #fff;
     border:1px solid #000;
     padding:15px;
     text-align:center;
}
    </style>



</head>
<body>
<div class="wrapper">
    <tiles:insertAttribute name="menu" />
    <div class="main-panel">
         <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                   	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><tiles:insertAttribute name="title" ignore="true" /></a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="logout">
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
    </div>
</div>


</body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

	<!--  Charts Plugin -->
	<script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="assets/js/light-bootstrap-dashboard.js"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="assets/js/demo.js"></script>
	 <script>

 	function supprimer(id) {
 		$.ajax({
				type:'POST',
				data:{idAgent:id},
				url : '${pageContext.request.contextPath}/deleteAgent',
				success : function(response)
				{
					$(".listeDesAgents").load(location.href + " .listeDesAgents");
					AccuseSuppression("Agent supprimee avec succes !");
					return;
				}
				
			});
 		
 	}
 	
 	function supprimerRelation(index)
 	{
 		var idArticle = $('#idArticle'+index).text();
 		var idTenue = $('#idTenue'+index).text();
 		$.ajax({
			type:'POST',
			data:{idT:idTenue,idA:idArticle},
			url : '${pageContext.request.contextPath}/deleteRelation',
			success : function(response)
			{
				$(".listeDesArticles").load(location.href + " .listeDesArticles");
				AccuseSuppression("Article supprimer avec succes !");
				return;
			}
			
		});
 	}
 	
 	function supprimerPoste(index)
 	{
 		var posteVal = $('#poste'+index).text();
 		$.ajax({
			type:'POST',
			data:{poste:posteVal},
			url : '${pageContext.request.contextPath}/deletePoste',
			success : function(response)
			{
					if(response.localeCompare("OK")==1)
						{
						$(".listeDesPostes").load(location.href + " .listeDesPostes");
						AccuseSuppression("Poste supprime avec succes !");
						return;
						}
						else
						{
							FormInvalid("Des agents dépendent de ce poste ! suppression Impossible !");
						}

			}
			
		});
 	}
 	
 	function incrementerQuantite(index)
 	{
 		var idArticle = $('#idArticle'+index).text();
 		var idTenue = $('#idTenue'+index).text();
 		$.ajax({
			type:'POST',
			data:{idT:idTenue,idA:idArticle},
			url : '${pageContext.request.contextPath}/incrementerQuantite',
			success : function(response)
			{
				$(".listeDesArticles").load(location.href + " .listeDesArticles");
				return;
			}
			
		});
 	}
 	
 	function decrementerQuantite(index)
 	{
 		var idArticle = $('#idArticle'+index).text();
 		var idTenue = $('#idTenue'+index).text();
 		$.ajax({
			type:'POST',
			data:{idT:idTenue,idA:idArticle},
			url : '${pageContext.request.contextPath}/decrementerQuantite',
			success : function(response)
			{
				$(".listeDesArticles").load(location.href + " .listeDesArticles");
				return;
			}
			
		});
 	}
 	
 	$(document).ready(function () {
 		var form = $('#formulaire');
 		form.on('submit', function(e) {
 	        e.preventDefault();
 	       	var nomVal= $('#nom').val();
  	      	var prenomVal= $('#prenom').val();
 	      	var sexeVal= $('#sexe').val();
 	      	var posteVal= $('#poste').val();
 	      	var entiteVal= $('#entite').val();
 	      	var tailleVal= $('#taille').val();
 	      	var pointureVal= $('#pointure').val();
 	      	if(nomVal === '' || prenomVal === '' ||sexeVal === '' ||posteVal === '' ||entiteVal === '')
 	      	{
 	      		FormInvalid("Veuillez remplir tous les champs !");
 	      	}
 	      	else
	      	{
 	      	 	$('#nom').val('');
 	      	 	$('#prenom').val('');
 	      		$('#entite').val('');
 	      		$('#taille').val('');
 	      		$('#pointure').val('');
 	      		$.ajax({
 					type:'POST',
 					data:{nom:nomVal, prenom:prenomVal, sexe:sexeVal, poste:posteVal, entite:entiteVal, taille:tailleVal, pointure:pointureVal},
 					url : '${pageContext.request.contextPath}/addAgent',
 					success : function(response){
 						$(".listeDesAgents").load(location.href + " .listeDesAgents");
 						AccuseAjout("Agent ajoute avec succes !");
 						return;
 					}
 					
 				}); 
	      	}
  	 		
 	        
 	    });
 		
 		
 		var form2 = $('#formulaireTenue');
 		form2.on('submit', function(e) {
 	        e.preventDefault();
 	       	var articleVal= $('#article').val();
  	      	var quantiteVal= $('#quantite').val();
 	      	var tenueVal= $('#tenue').val();
 	      	if(articleVal == '' || quantiteVal == 0)
 	      	{
 	      		FormInvalid("Veuillez choisir une quantite > 0");
 	      	}
 	      	else
	      	{
 	      		$.ajax({
 					type:'POST',
 					data:{quantite:quantiteVal, tenue:tenueVal, article:articleVal},
 					url : '${pageContext.request.contextPath}/addRelation',
 					success : function(response){
 						if(response.localeCompare("OK")==1)
 						{
 							$(".listeDesArticles").load(location.href + " .listeDesArticles");
 	 						AccuseAjout("Article ajoute avec succes !");
 	 						return;
 						}
 						else
 						{
 							FormInvalid("Cet article existe deja dans la liste !");
 						}
 						
 					}
 					
 				}); 
	      	}
  	 		
 	        
 	    });
 		
 		var form3 = $('#formulairePoste');
 		form3.on('submit', function(e) {
 	        e.preventDefault();
 	       	var intituleVal= $('#poste').val();
  	      	var tenueVal= $('#tenue').val();
 	      	if(intituleVal == '' || tenueVal == 0)
 	      	{
 	      		FormInvalid("Veuillez saisir un intitule pour le nouveau poste ");
 	      	}
 	      	else
	      	{
 	      		$.ajax({
 					type:'POST',
 					data:{poste:intituleVal, tenue:tenueVal},
 					url : '${pageContext.request.contextPath}/addPoste',
 					success : function(response){
 						if(response.localeCompare("OK")==1)
 						{
 							$(".listeDesPostes").load(location.href + " .listeDesPostes");
 	 						AccuseAjout("Poste ajoute avec succes !");
 	 						return;
 						}
 						else
 						{
 							FormInvalid("Ce poste existe deja dans la liste !");
 						}
 						
 					}
 					
 				}); 
	      	}
  	 		
 	        
 	    });
 		
 	});
 	
	function FormInvalid(text){    	
    	$.notify({
        	icon: "pe-7s-close-circle",
        	message: text
        	
        },{
            type: 'danger',
            timer: 4000,
            placement: {
                from: 'top',
                align: 'center'
            }
        });
	}
	
	function AccuseAjout(text){    	
    	$.notify({
        	icon: "pe-7s-like2",
        	message: text
        	
        },{
            type: 'success',
            timer: 4000,
            placement: {
                from: 'top',
                align: 'center'
            }
        });
	}
	function AccuseSuppression(text){    	
    	$.notify({
        	icon: "pe-7s-attention",
        	message: text
        	
        },{
            type: 'warning',
            timer: 4000,
            placement: {
                from: 'top',
                align: 'center'
            }
        });
	}
 </script>

</html>
