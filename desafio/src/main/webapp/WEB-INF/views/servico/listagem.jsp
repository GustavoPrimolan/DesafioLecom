<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/template" prefix="template" %>
<template:admin>
<jsp:attribute name="extraStyles">
<link rel="stylesheet" href="<c:url value='/assets/css/pagination/jqpagination.css'/>" />
</jsp:attribute>
<jsp:attribute name="extraScripts">
<script src="<c:url value='/assets/js/jquery.jqpagination.js'/>"></script>
</jsp:attribute>
<jsp:body>
  <div>
    <div class ="container min-container">
      <h2 class="basic-title">Servi�os</h2>
        <div class="well">
          <table class="table table-condensed table-bordered table-striped table-hover">
          		  <thead>
	                  <tr>
	                  	<td>id</td>
		                  	<td>Nome</td>
		                  	<td>Pre�o</td>
						<td>actions</td>
	                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items='${paginatedList.currentList}' var='object'>         		
	                  <tr>
						<td><a href="<c:url value='/servico'/>/${object.id}">${object.id}</a></td>
		                  	<td>${object.nome}</td>
		                  	<td>${object.preco}</td>
	                    <td><a href="<c:url value='/servico/remove'/>/${object.id}">Remover</a></td>
					  </tr>
                  </c:forEach>
                  </tbody>
          </table>
		  <template:paginationComponent paginatedList="${paginatedList}" page="${param.page}" action="/servico"/>
          <a href="<c:url value='/servico/form'/>" class="btn btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar novo servi�o</a>
        </div>
    </div>
  </div>
</jsp:body>
</template:admin>
