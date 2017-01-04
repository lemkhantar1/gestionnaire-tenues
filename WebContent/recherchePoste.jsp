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
                                <h2 class="title">Rechercher un poste</h2>
                            </div>
                            <div class="content">
                                <form method="post" action="searchPoste">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Intitule</label>
                                                <input type="text" class="form-control"  name="intitule">
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label>Tenue</label>
                                                <input type="text" class="form-control" name="tenue">
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
                                <h2 class="title">Liste des postes</h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                            <div class="listeDesAgents">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Intitule</th>
                                    	<th>Tenue attribu&eacute;e</th>
                                    	<th></th><th></th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="p" items="${postes}">
	                                        <tr>
	                                        	<td>${p.intitule}</td>
	                                        	<td>${p.tenue.intitule}</td>
	                                        	<td>
	                                        		<form method="post" action="detailsPoste"><input type="text" name="intitule" value="${p.intitule}" hidden/><button type="submit" class="btn btn-info btn-fill">Modifier </button></form>
                                        		</td>
                                        		<td>
                                        		<div id="poste${loop.index}" hidden>${p.intitule}</div>
	                                        		<button  class="btn btn-danger btn-fill" onclick="supprimerPoste(${loop.index})">Supprimer </button>
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
				