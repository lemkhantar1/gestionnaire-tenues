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
                                <h2 class="title">DEMANDE D'ACHAT <a href="downloadDemande"><button  class="btn btn-success btn-fill ">TELECHARGER LA COMMANDE</button></a></h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                    	<th>Article</th>
                                        <th>Taille / Pointure</th>
                                        <th>Quantite</th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="a" items="${QperArticle}">
	                                        <tr><td class="text-primary">${a.key}</td><td></td><td></td></tr>
 	                                        	<c:forEach var="v" items="${a.value}">
	                                        		<tr>
	                                        			<td></td>
	                                        			<td>${v.key}</td>
	                                        			<td>${v.value}</td>
	                                        		</tr>
	                                        	</c:forEach> 
	                                    </c:forEach>
	                                    </tbody>
	                                    
                                    
                                </table>
                                
								
                            </div>
                        </div>
                        
                    </div>
				</div>
				</div>
				</div>