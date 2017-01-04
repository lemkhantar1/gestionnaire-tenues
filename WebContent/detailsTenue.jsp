<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <div class="content">
            <div class="container-fluid">
 					<div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h2 class="title">Articles de le tenue : ${intitule}</h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                            <div class="listeDesArticles" >
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Article</th>
                                    	<th>Quantite</th>
                                    	<th></th><th>Op. Quantite</th>
                                    </thead>
                                    
	                                    <tbody>
	                                   
	                                    <c:forEach var="r" items="${relations}" varStatus="loop">
	                                        <tr>
	                                        	<td>${r.id.idArticle}</td>
	                                        	<td>${r.quantite}</td>
	                                        	<td>
	                                        		<div hidden id="idArticle${loop.index}">${r.id.idArticle}</div>
	                                        		 <div hidden id="idTenue${loop.index}">${r.id.idTenue}</div>
	                                        		
	                                        		<button class="btn btn-danger btn-fill" onclick="supprimerRelation(${loop.index})" >Supprimer </button>
                                        		</td>
                                        		<td>
                                        			<button class="btn btn-warning btn-fill" onclick="decrementerQuantite(${loop.index})" >MOINS</button>
                                        			<button class="btn btn-success btn-fill" onclick="incrementerQuantite(${loop.index})" >PLUS</button>
                                        			
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
				<div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h2 class="title">Ajouter un article a la tenue : ${intitule}</h2>
                            </div>
                            <div class="content">
                                <form method="post" id="formulaireTenue">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Article</label>
                                                <select class="form-control" name="article" id="article">
                                                <c:forEach var="c" items="${categories }">
                                                <optgroup label="${c.intitule}">
                                                <c:forEach var="a" items="${articles}">
                                                	<c:if test="${c.intitule == a.categorie.intitule }">
                                                	<option value="${a.intitule}">${a.intitule}</option>
                                                	</c:if>
                                                </c:forEach>
                                                </optgroup>
                                                </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Quantite</label>
                                                <input type="text" value="${intitule }" name="tenue" id="tenue" hidden>
                                                <input type="number" class="form-control" name="quantite" id="quantite" value="0" min="0">
                                            </div>
                                        </div>
                                    </div>

                                    <h2><button type="submit" class="btn btn-success btn-fill pull-right">Ajouter Article</button></h2>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                
				</div>
				</div>