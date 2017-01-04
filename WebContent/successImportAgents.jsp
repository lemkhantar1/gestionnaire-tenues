<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
            <div class="container-fluid">
                <div class="card">
                    <div class="header">
                        <h2 class="title">Importation des agents via Excel</h2>

                    </div>
                    <div class="content">
                        <div class="row">
                            <div class="col-md-12">
                                <h4 class="danger">Importation du fichier Excel reussie</h4>
                                <div class="alert alert-success alert-with-icon" data-notify="container">
                                    <span data-notify="icon" class="pe-7s-success"></span>
                                    <span data-notify="message" style="font-weight:bold;">
									Le fichier Excel est charge avec succes, Veuillez valider l'ajout des agents.
									<br>
									</span>
                                </div>
                            </div>
							<div class="col-md-12"><h4>Les agents charges du fichier ${fileUploadFileName} : </h4></div>
							                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                            <div class="listeDesAgents">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Nom</th>
                                    	<th>Prenom</th>
                                    	<th>Sexe</th>
                                    	<th>Poste</th>
                                    	<th>Entite</th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="a" items="${agents}">
	                                        <tr>
	                                        	<td>${a.nom}</td>
	                                        	<td>${a.prenom}</td>
	                                        	<td>${a.sexe}</td>
	                                        	<td>${a.poste.intitule}</td>
	                                        	<td>${a.entite}</td>
	                                        </tr>
	                                    </c:forEach>
	                                    </tbody>
                                    
                                </table>
								</div>
                            </div>
                        </div>
                    </div>
							<a href="agents"><div class="col-md-6"><button class="btn btn-danger btn-fill pull-right">Annuler l'importation</button></div></a>
							<a href="validateAgents"><div class="col-md-6"><button class="btn btn-success btn-fill ">Valider l'importation</button></div></a>
                        </div>
                        <br>
                        <br>
                    </div>
					
                </div>
            </div>
        </div>