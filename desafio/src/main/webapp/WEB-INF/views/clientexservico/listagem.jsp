<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/template" prefix="template" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
      <h2 class="basic-title">Lista de Clientes e seus Serviços</h2>
        <div class="well">
          <table class="table table-condensed table-bordered table-striped table-hover">
          		  <thead>
	                  <tr>
	                  	<td>id</td>
		                  	<td>Nome do Cliente</td>
		                  	<td>Serviço para o Cliente</td>
		                  	<td>Início</td>
		                  	<td>Entrega</td>
		                  	<td>Pago</td>
	                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items='${paginatedList.currentList}' var='object'>         		
	                  <tr>
						<td><a href="<c:url value='/clientexservico/'/>${object.id}">${object.id}</a></td>
		                  	<td>${object.cliente.nome}</td>
		                  	<td>${object.servico.nome}</td>
		                  	<td><fmt:formatDate pattern="dd/MM/yyyy" value="${object.dataInicio.time}"/></td>
		                  	<td><fmt:formatDate pattern="dd/MM/yyyy" value="${object.dataEntrega.time}"/></td>
		                  	<td>${object.pago}</td>
	                    <td><a href="<c:url value='/clientexservico/remove'/>/${object.id}">Remover</a></td>
	                    <td><a href="<c:url value='/clientexservico/efetuapag/'/>${object.id}">Pagar</a></td>
					  </tr>
                  </c:forEach>
                  </tbody>
          </table>
		  <template:paginationComponent paginatedList="${paginatedList}" page="${param.page}" action="/clientexservico"/>
          <a href="<c:url value='/clientexservico/form'/>" class="btn btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar novo serviço para cliente</a>
        </div>
    </div>
  </div>
</jsp:body>
</template:admin>
