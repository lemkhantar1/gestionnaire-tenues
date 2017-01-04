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
                                <h2 class="title">T&eacute;l&eacute;charger le fichier des mesures</h2>
                            </div>
                            <div class="content">
								<div class="typo-line">
                                    <p class="category">FICHIER MESURE</p>
                                    <blockquote>
                                     <p>
                                     	Le fichier mesure est un fichier excel listant tous les agents avec leurs noms, prenoms et id respectifs.
                                     	<br>Il permet la collection facile des mesures aupres des agents et une importation efficace de ces mesures dans la base de donnees.
                                     </p>
                                     <p class="text-danger">ATTENTION : SEULEMENT LES MESURES DOIVENT ETRE AJOUTES DANS CE FICHIER. <br>TOUTE MODIFICATION DANS LE FORMAT DU FICHIER RENDRA IMPOSSIBLE L"IMPORTATION DES MESURES DANS LA BASE DE DONNEES.</p>
                                    </blockquote>
                                </div>
                                <div class="row">
                    				<div class="col-md-12">
                    					<a href="downMesures"><button style="display: block; width: 100%;"  class="btn btn-success btn-fill pull-center">TELECHARGER LE FICHIER MESURES.XLSX</button></a>
                    				</div>
                   				</div>
                            </div>
                        </div>
                    </div>

                </div>
    	          <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h2 class="title">Importer des mesures via Excel</h2>
                            </div>
                            <div class="content">
								<s:form action="mesureUpload"  method="POST" enctype="multipart/form-data">
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
