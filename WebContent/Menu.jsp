<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="sidebar"  data-image="assets/img/sidebar-5.jpg">
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    Gestionnaire des tenues
                </a>
            </div>

            <ul class="nav">
                <li>
                    <s:a href="agents">
                    	<i class="pe-7s-users"></i>
                        <p>AGENTS</p>
                 	</s:a>
                </li>
                <li>
                    <s:a href="postes">
                    	<i class="pe-7s-network"></i>
                        <p>POSTES</p>
                 	</s:a>
                </li>
                <li>
                    <s:a href="tenues">
                    	<i class="pe-7s-study"></i>
                        <p>TENUES</p>
                 	</s:a>
                </li>
<%--                 <li>
                    <s:a href="articles">
                    	<i class="pe-7s-ticket"></i>
                        <p>ARTICLES</p>
                 	</s:a>
                </li> --%>
                <li>
                    <s:a href="mesures">
                    	<i class="pe-7s-scissors"></i>
                        <p>MESURES</p>
                 	</s:a>
                </li>
<%--                 <li>
                    <s:a href="commande">
                    	<i class="pe-7s-note2"></i>
                        <p>DETAILS DE LA COMMANDE</p>
                 	</s:a>
                </li> --%>
                <li>
                    <s:a href="demande">
                    	<i class="pe-7s-cart"></i>
                        <p>DEMANDE D'ACHAT</p>
                 	</s:a>
                </li>
            </ul>
    	</div>
    </div>