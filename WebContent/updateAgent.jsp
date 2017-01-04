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
                                <h2 class="title">Modifier un agent</h2>
                            </div>
                            <div class="content">
                                <s:form action="updateAgent" method="post" >
                                    <div class="row">
                                    	<div class="col-md-2">
                                            <div class="form-group">
                                                <label>ID</label>
                                                <input type="text" class="form-control"   name="idAgent"  value="${agent.id}" readonly="readonly" >
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Nom</label>
                                                
                                                <input type="text" class="form-control"  placeholder="Nom" name="nomAgent"  value="${agent.nom}">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Prenom</label>
                                                <input type="text" class="form-control" placeholder="Prenom" name="prenomAgent"  value="${agent.prenom}" >
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Sexe</label>
                                                <select class="form-control" name="sexeAgent" id="sexe">
                                                	<option value="homme" <c:if test="${'homme' == agent.sexe}" >selected</c:if>>Homme</option>
                                                	<option value="femme" <c:if test="${'femme' == agent.sexe}" >selected</c:if>>Femme</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Taille</label>
                                                <select class="form-control" name="tailleAgent" id="taille">
                                                	<option value="--">indisponible</option>
                                                	<option value="XS" <c:if test="${'XS' == agent.mesure.taille}" >selected</c:if>>XS</option>
                                                	<option value="S" <c:if test="${'S' == agent.mesure.taille}" >selected</c:if>>S</option>
                                                	<option value="M" <c:if test="${'M' == agent.mesure.taille}" >selected</c:if>>M</option>
                                                	<option value="L" <c:if test="${'L' == agent.mesure.taille}" >selected</c:if>>L</option>
                                                	<option value="XL" <c:if test="${'XL' == agent.mesure.taille}" >selected</c:if>>XL</option>
                                                	<option value="XXL" <c:if test="${'XXL' == agent.mesure.taille}" >selected</c:if>>XXL</option>
                                                	<option value="XXXL" <c:if test="${'XXXL' == agent.mesure.taille}" >selected</c:if>>XXXL</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Pointure</label>
                                                <input type="number" class="form-control" placeholder="pointure" name="pointureAgent" id="pointure" value="${agent.mesure.pointure}" min="0">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<div class="col-md-8">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Poste</label>
                                                <select class="form-control" name="posteAgent" >
                                                	<c:forEach var="p" items="${postes}">
                                                		<option value="${p.intitule}" <c:if test="${p.intitule == agent.poste.intitule}" >selected</c:if>>${p.intitule}</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Entite</label>
                                                <input type="text" class="form-control" placeholder="Entite" name="entiteAgent" value="${agent.entite }"  id="entite">
                                            </div>
                                        </div>
                                    </div>
                                    
                                     <button type="submit" class="btn btn-success btn-fill pull-right">Modifier Agent</button>
                                    </s:form>
                                   	
                                    
                                    <div class="clearfix"></div>
                                
                                
                            </div>
                        </div>
                    </div>

                </div>
                
                
                
                
                
                
               
                
                
                </div>
                </div>