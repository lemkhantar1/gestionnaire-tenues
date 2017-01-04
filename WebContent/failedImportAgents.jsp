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
                                <h4 class="danger">Erreur lors de l'importation du fichier Excel</h4>
                                <div class="alert alert-danger alert-with-icon" data-notify="container">
                                    <span data-notify="icon" class="pe-7s-attention"></span>
                                    <span data-notify="message">
									Le fichier Excel que vous essayez de t&eacute;l&eacute;verser n'est pas conforme au format attendu.
									<br>
									Cette erreur peut &ecirc;tre caus&eacute;e par : 
									<ul>
										<li>Fichier inexistant</li>
										<li>Fichier trop volumineux</li>
										<li>Format incompatible du fichier</li>
										<li>Valeur invalide (sexe et/ou poste)</li>
										<li>Une case vide</li>
										<li>Une ligne contenant plus/moins de colonnes que le mod&egrave;le ci-dessous</li>
									</ul>
									</span>
                                </div>
                            </div>
							<div class="col-md-12" ><h4 style="font-weight:bold;">Veuillez vous r&eacute;f&eacute;rer au format suivant: </h4></div>
							<div class="col-md-12"><img src="assets/img/templateExcelAgents.png"></div>
							<a href="agents"><div class="col-md-12"><button class="btn btn-success btn-fill pull-right">Importer un autre fichier</button></div></a>
                        </div>
                        <br>
                        <br>
                    </div>
					
                </div>
            </div>
        </div>