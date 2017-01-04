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
                                <h2 class="title">Liste des Tenues</h2>
                                
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Tenue</th>
                                    	<th></th>
                                    </thead>
                                    
	                                    <tbody>
	                                    <c:forEach var="t" items="${tenues}">
	                                        <tr>
	                                        	<td>${t.intitule}</td>
	                                        	<td>
	                                        		<form method="get" action="detailsTenue"><input type="text" name="intitule" value="${t.intitule}" hidden/><button type="submit" class="btn btn-info btn-fill">Articles Attach&eacute;s </button></form>
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