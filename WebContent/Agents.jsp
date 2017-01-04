<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
 <div class="content">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h2 class="title">Liste des agents <a href="downloadAgents"><button  class="btn btn-info btn-fill ">TELECHARGER LA LISTE</button></a> <a href="searchPage"><button  class="btn btn-success btn-fill pull-right">RECHERCHER</button></a></h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                            <div class="listeDesAgents">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Nom Complet</th>
                                    	<th>Sexe</th>
                                    	<th>Poste</th>
                                    	<th>Entite</th>
                                    	<th>Taille</th>
                                    	<th>Pointure</th>
                                    	<th></th>
                                    	<th></th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="a" items="${agents}" varStatus="loop">
	                                        <tr>
	                                        	
	                                        	<td>${a.id}</td>
	                                        	<td>${a.nom} ${a.prenom}</td>
	                                        	<td>${a.sexe}</td>
	                                        	<td><a href="/Test/detailsTenuePoste?poste=${a.poste.intitule}">${a.poste.intitule}</a></td>
	                                        	<td>${a.entite}</td>
	                                        	<td>${a.mesure.taille}</td>
	                                        	<td>${a.mesure.pointure}</td>
	                                        	<td>
	                                        		<form method="post" action="detailsAgent"><input type="text" name="idAgent" value="${a.id}" hidden/><button type="submit" class="btn btn-info btn-fill">Modifier </button></form>
                                        		</td>
                                        		<td>
	                                        		<button type="submit" class="btn btn-danger btn-fill" onclick="supprimer(${a.id})">Supprimer</button>
	                                       		</td>
	                                        </tr>
	                                    </c:forEach>
	                                    <tr><td colspan="9"><a href="searchPage"><button style="display: block; width: 100%;"  class="btn btn-success btn-fill pull-center">AFFICHER PLUS</button></a></td></tr>
	                                    </tbody>
	                                    
                                    
                                </table>
                                
								</div>
								
                            </div>
                        </div>
                        
                    </div>
				</div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h2 class="title">Ajouter un agent</h2>
                            </div>
                            <div class="content">
                                <form method="post" id="formulaire">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Nom</label>
                                                <input type="text" class="form-control"  placeholder="Nom" name="nom" id="nom">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Prenom</label>
                                                <input type="text" class="form-control" placeholder="Prenom" name="prenom" id="prenom" >
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Sexe</label>
                                                <select class="form-control" name="sexe" id="sexe">
                                                	<option value="homme">Homme</option>
                                                	<option value="femme">Femme</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Taille</label>
                                                <select class="form-control" name="taille" id="taille">
                                                	<option value="--">indisponible</option>
                                                	<option value="XS">XS</option>
                                                	<option value="S">S</option>
                                                	<option value="M">M</option>
                                                	<option value="L">L</option>
                                                	<option value="XL">XL</option>
                                                	<option value="XXL">XXL</option>
                                                	<option value="XXXL">XXXL</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Pointure</label>
                                                <input type="number" class="form-control" placeholder="pointure" name="pointure" id="pointure" value="0" min="0">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<div class="col-md-6">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Poste</label>
                                                <select class="form-control" name="poste" id="poste">
                                                	<option selected></option>
                                                	<c:forEach var="p" items="${postes}">
                                                		<option value="${p.intitule}">${p.intitule}</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Entite</label>
                                                <input type="text" class="form-control" placeholder="Entite" name="entite"  id="entite">
                                            </div>
                                        </div>
                                    </div>

                                    <h2><button type="submit" class="btn btn-success btn-fill pull-right">Ajouter Agent</button></h2>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h2 class="title">Importer des agents via Excel</h2>
                            </div>
                            <div class="content">
								<s:form action="agentUpload"  method="POST" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Veuillez choisir un fichier Excel</label>
                                                <input type="file" name="fileUpload" class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <button type="submit" class="btn btn-success btn-fill pull-right">T&eacute;l&eacute;verser</button>
                                    <div class="clearfix"></div>
                                </s:form>
                            </div>
                        </div>
                    </div>

                </div>
                
                
            </div>
        </div>