<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<template:admin>
	<jsp:attribute name="extraStyles">
<link rel="stylesheet"
			href="<c:url value='/assets/css/pagination/jqpagination.css'/>" />
</jsp:attribute>
	<jsp:attribute name="extraScripts">
<script src="<c:url value='/assets/js/jquery.jqpagination.js'/>"></script>
</jsp:attribute>
	<jsp:body>
  <div>
    <div class="container min-container">
      <h2 class="basic-title">Dados do Pagamento</h2>
        <div class="well">
          <table
						class="table table-condensed table-bordered table-striped table-hover">
          		  <thead>
	                  <tr>
		                  	<td>Nome do Cliente</td>
		                  	<td>Serviço para o Cliente</td>
		                  	<td>Plano do Cliente</td>
		                  	<td>Valor</td>
		                  	<td>Valor com Desconto</td>
	                  </tr>
                  </thead>
                  <tbody>
	                  <tr>
		                  	<td>${clienteXServico.cliente.nome}</td>
		                  	<td>${clienteXServico.servico.nome}</td>
		                  	<td>${clienteXServico.cliente.plano}</td>
		                  	<td>R$${clienteXServico.servico.preco}</td>
		                  	<td>R$${valorDescontado}</td>
					  </tr>
          
					</table>
		<a href="<c:url value='/clientexservico'/>" class="btn btn-success">Voltar</a>
        </div>
    </div>
  </div>
</jsp:body>
</template:admin>
