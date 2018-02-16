<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<div class="form-group">
	<label for="cliente.id">Nome do Cliente:</label>
	<div class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-unchecked"></i></span>
		<form:select path='cliente.id' items='${clientes}' itemValue='id'
			itemLabel='nome'>
		</form:select>
		<form:errors path='cliente.id' />

	</div>
</div>

<div class="form-group">
	<label for="servico.id">Serviço a ser prestado:</label>
	<div class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-unchecked"></i></span>
		<form:select path='servico.id' items='${servicos}' itemValue='id'
			itemLabel='nome'>
		</form:select>
		<form:errors path='servico.id' />

	</div>
</div>

<div class="form-group">
	<label for="dataInicio">Data de Início:</label>
	<div class="input-group">
		<span class="input-group-addon"> <i
			class="glyphicon glyphicon-unchecked"></i>
		</span>
		<form:input path="dataInicio" />
		<form:errors path="dataInicio" />
	</div>
</div>

<div class="form-group">
	<label for="dataEntregaString">Data de Entrega:</label>
	<div class="input-group">
		<span class="input-group-addon"> <i
			class="glyphicon glyphicon-unchecked"></i>
		</span>
		<form:input path="dataEntrega" />
		<form:errors path="dataEntrega" />
	</div>
</div>

