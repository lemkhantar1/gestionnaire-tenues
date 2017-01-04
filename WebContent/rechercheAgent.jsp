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
                                <h2 class="title">Rechercher un agent</h2>
                            </div>
                            <div class="content">
                                <form method="post" action="searchAgent">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Nom</label>
                                                <input type="text" class="form-control"  name="nomAgent">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Prenom</label>
                                                <input type="text" class="form-control" name="prenomAgent">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Sexe</label>
                                                <select class="form-control" name="sexeAgent" id="sexe">
                                                <option value="" selected></option>
                                                <option value="homme">HOMME</option>
                                                <option value="femme">FEMME</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Poste</label>
                                                <select class="form-control" name="posteAgent" id="poste">
                                                <option value="" selected></option>
                                                	<c:forEach var="p" items="${postes}">
                                                		<option value="${p.intitule}">${p.intitule}</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Entite</label>
                                                <input type="text" class="form-control" name="entiteAgent">
                                            </div>
                                        </div>

                                    </div>


                                    <button type="submit" class="btn btn-info btn-fill pull-right">RECHERCHER</button>
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
                                <h2 class="title">Liste des agents <a href="downloadAgents"><button  class="btn btn-info btn-fill ">TELECHARGER LA LISTE</button></a> </h2>
                                
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
	                                    <c:forEach var="a" items="${agents}">
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
	                                    </tbody>
	                                    
                                    
                                </table>
                                
								</div>
								
                            </div>
                        </div>
                        
                    </div>
				</div>
				</div>
				</div>
				