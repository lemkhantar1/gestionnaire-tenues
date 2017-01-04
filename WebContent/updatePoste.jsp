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
                                <h2 class="title">Modifier la tenue d'un poste</h2>
                            </div>
                            <div class="content">
                                <s:form action="updatePoste" method="post" >
                                    <div class="row">
                                    	<div class="col-md-4">
                                            <div class="form-group">
                                                <label>intitule</label>
                                                <input type="text" class="form-control"   name="intitule"  value="${poste.intitule}" readonly="readonly" >
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Tenue</label>
                                                <select class="form-control" name="tenue"  >
                                                	<c:forEach var="t" items="${tenues}">
                                                		<option value="${t.intitule}" <c:if test="${t.intitule == poste.tenue.intitule}" >selected</c:if>>${t.intitule}</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                     <button type="submit" class="btn btn-success btn-fill pull-right">Modifier Tenue</button>
                                    </s:form>
                                   	
                                    
                                    <div class="clearfix"></div>
                                
                                
                            </div>
                        </div>
                    </div>

                </div>
                </div>
                </div>