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
                                <h2 class="title">Rechercher un article</h2>
                            </div>
                            <div class="content">
                                <form method="post" action="searchArticle">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Intitule</label>
                                                <input type="text" class="form-control"  name="intitule">
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label>Categorie</label>
                                                <input type="text" class="form-control" name="categorie">
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
                                <h2 class="title">Liste des articles </h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                            <div class="listeDesAgents">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Article</th>
                                        <th>Categorie</th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="a" items="${articles}">
	                                        <tr>
	                                        	<td>${a.intitule}</td>
	                                        	<td>${a.categorie.intitule}</td>
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
				