<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-group">
	<label for="nome">Nome:</label>
	<div class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-unchecked"></i></span>
		<form:input path='nome' type='text' />
		<form:errors path='nome' />

	</div>
</div>

<div class="form-group">
	<label for="plano">Plano:</label>
	<div class="input-group">
		<span class="input-group-addon"> <i
			class="glyphicon glyphicon-unchecked"></i>
		</span> <select name="plano">
			<c:forEach items="${planos}" var="plano">
				<option value="${plano}">${plano}</option>
			</c:forEach>
		</select>
	</div>
</div>

