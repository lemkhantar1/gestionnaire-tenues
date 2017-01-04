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
                                <h2 class="title">Ajouter un poste</h2>
                            </div>
                            <div class="content">
                                <form method="post" id="formulairePoste">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Intitule</label>
                                                <input type="text" class="form-control"  placeholder="Intitule" name="poste" id="poste">
                                            </div>
                                        </div>
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Tenue</label>
                                                <select class="form-control" name="tenue" id="tenue">
                                                	<c:forEach var="t" items="${tenues}">
                                                		<option value="${t.intitule}">${t.intitule}</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                    </div>
                                    
                                    

                                    <h2><button type="submit" class="btn btn-success btn-fill pull-right">Ajouter Poste</button></h2>
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
                                <h2 class="title">Liste des postes <a href="searchPagePostes"><button  class="btn btn-success btn-fill pull-right">RECHERCHER</button></a></h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                            <div class="listeDesPostes">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Poste</th>
                                    	<th>Tenue attribu&eacute;e</th>
                                    	<th></th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="p" items="${postes}" varStatus="loop">
	                                        <tr>
	                                        	<td>${p.intitule}</td>
	                                        	<td>${p.tenue.intitule}</td>
	                                        	<td>
	                                        		<form method="post" action="detailsPoste"><input type="text" name="intitule" value="${p.intitule}" hidden/><button type="submit" class="btn btn-info btn-fill">Modifier </button></form>
                                        		</td>
<%--                                         		<td>
                                        		<div id="poste${loop.index}" hidden>${p.intitule}</div>
	                                        		<button  class="btn btn-danger btn-fill" onclick="supprimerPoste(${loop.index})">Supprimer </button>
                                        		</td> --%>
	                                        </tr>
	                                    </c:forEach>
	                                    <tr><td colspan="6"><a href="searchPagePostes"><button style="display: block; width: 100%;"  class="btn btn-success btn-fill pull-center">AFFICHER PLUS</button></a></td></tr>
	                                    </tbody>
	                                    
                                    
                                </table>
                                
								</div>
								
                            </div>
                        </div>
                        
                    </div>
				</div>
				</div>
				</div>
				