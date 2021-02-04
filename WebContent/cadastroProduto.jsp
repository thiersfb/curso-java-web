<%-- <jsp:useBean id="calcula" class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page"></jsp:useBean> --%>

<%-- 
<%@page import="dao.DaoLogin"%>
<%@page import="dao.DaoProduto"%> 
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cadastro de Produtos</title>

<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css">

<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script> -->
<!-- 
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js" type="text/javascript"></script>
 -->
<script src="resources/javascript/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.mask.min.js" type="text/javascript"></script>

<script>
	//whatever kind of mobile test you wanna do.
	if (screen.width < 500) {

		$("body").addClass("nohover");
		$("td, th").attr("tabindex", "1").on("touchstart", function() {
			$(this).focus();
		});

	}
</script>



<script type="text/javascript">

	/* mascara do campo "preco" no formato "999.999.999.990,00" */
	$(document).ready(function() {
		$("#preco").mask("999.999.999.990,00", {
			reverse : true
		});
	});

	/* campo "qtdEstoque" permite apenas digitação de números */
	$(document).ready(function() {
		$("#qtdEstoque").keyup(function() {
			$("#qtdEstoque").val(this.value.match(/[0-9]*/));
		});
	});
	
	function validarCampos() {
		//alert(document.getElementById("nome").value); //imprime o que foi digitado na tela
		if (document.getElementById("nome").value == '') {
			alert('Nome não foi preenchido');
			return false;
		} else if (document.getElementById("descricao").value == '') {
			alert('Descrição não foi preenchida');
			return false;
		} else if (document.getElementById("qtdEstoque").value == '') {
			alert('Quantidade em estoque não foi preenchido');
			return false;
		} else if (document.getElementById("preco").value == '') {
			alert('Preço não foi preenchido');
			return false;
		}

		return true;
	}
	
</script>



</head>
<body>

<!-- 
<a href="acessoliberado.jsp">Início</a>
<a href="index.jsp">Sair</a>
 -->
 
<a href="acessoliberado.jsp" style="text-decoration: none;">
	<img src="resources/img/icons/home.png" title="Início" alt="Início" width="50px" height="50px">
</a>

<!-- 
<a href="index.jsp" style="text-decoration: none;">
	<img src="resources/img/icons/back.png" title="Voltar" alt="Voltar" width="50px" height="50px">
</a>
 -->

<a href="index.jsp" style="text-decoration: none;">
	<img src="resources/img/icons/logout.png" title="Sair" alt="Sair" width="50px" height="50px">
</a>

	<center>
		<h1>Cadastro de Produtos</h1>
	</center>
	
	<!-- <form action="salvarProduto" method="post" id="formProduct" onsubmit="validarCampos()> -->
	<form action="salvarProduto" method="post" id="formProduct" onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>
				<label>ID</label> 
				<input type="text" id="id" name="id" value="${product.id}" readonly="readonly" class="field-divided" />
			</li>
			<li>
				<label>Nome do Produto <span class="required">*</span></label>
				<input type="text" id="nome" name="nome" value="${product.nome}" class="field-long" placeholder="nome" maxlength="100" />
			</li>
			<li>
				<label>Descrição do Produto <span class="required">*</span></label>
				<input type="text" id="descricao" name="descricao" value="${product.descricao}" class="field-long" placeholder="descricao" /></li>
			
		    <li>
		        <label>Quantidade em Estoque <span class="required">*</span></label>
		        <input type="text" id="qtdEstoque" name="qtdEstoque" value="${product.qtdEstoque}" class="field-long" placeholder="Quantidade em Estoque" maxlength="9" />
		        <%-- <input type="number" id="qtdEstoque" name="qtdEstoque" value="${product.qtdEstoque}" class="field-long" placeholder="Quantidade em Estoque" max="999999" /> --%>
		    </li>
		    <li>
		        <label>Preço <span class="required">*</span></label>
		        <%-- <input type="text" id="preco" name="preco" value="${product.preco}" class="field-long" placeholder="Preço" /> --%>
		        <%-- <input type="number" id="preco" name="preco" value="${product.preco}" class="field-long" placeholder="Preço" data-number-to-fixed="2" step=".01" /> --%>
		        
		        <%-- <input type="text" id="preco" name="preco" value="${product.preco}" class="field-long" placeholder="Preço" data-number-to-fixed="2" step=".01" data-thousands="." data-decimal=","  minFractionDigits="2" /> --%>
		        <%-- <input type="text" id="preco" name="preco" value="${product.PrecoEmTexto}" class="field-long" placeholder="Preço" data-number-to-fixed="2" step=".01" data-thousands="." data-decimal=","  /> <!-- PrecoEmTexto é um metodo do objeto Produto --> --%>
		        
		        <input type="text" id="preco" name="preco" value="${product.preco}" class="field-long" placeholder="Preço" />
		        
		    </li>
		    <li>
		    	<label>Categoria <span class="required">*</span></label>
		        <select id="categoria" name="categoria_id">
		        <option value="0">[--SELECIONE--]</option>
		        <c:forEach items="${categorias}" var="cat">
		        	<!-- id do registro obtido na base -->
		        	<option value="${cat.id}" id="${cat.id}"
		        		<c:if test="${cat.id == product.categoria_id}">
		        			<c:out value="selected=selected" />
		        		</c:if>
		        	>
			        	${cat.descricao} <!-- texto do registro obtido na base -->
		        	</option>
		        </c:forEach>
		        </select>
		    </li>
		    
			
			<li> 
				<input type="submit" value="Salvar" /> 
				<input type="submit" value="Cancelar" onclick="document.getElementById('formProduct').action='salvarProduto?acao=reset'" /> 
			</li>
		</ul>
	</form>
	<br />
	
	<center>
		<h3>${msg}</h3>
	</center>	

	<br />

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<!-- <th>Senha</th> -->
				<!-- <th style="width:150px;">Senha</th> -->
				<th>Descrição</th>
				<th>Quantidade em Estoque</th>
				<th>Preço</th>
				<!-- <th colspan="2" style="width:300px; text-align: center; ">Ações</th> -->
				<th>Editar</th>
				<th>Excluir</th>
			</tr>

		</thead>
		<tbody>

			<c:forEach items="${produtos}" var="product">
				<tr>
					<td><c:out value="${product.id}"></c:out></td>
					<td><c:out value="${product.nome}"></c:out></td>
					<td><c:out value="${product.descricao}"></c:out></td>
					<td><c:out value="${product.qtdEstoque}"></c:out></td>
					<%-- <td><c:out value="${product.preco}"></c:out></td> --%>
					<td>
						<fmt:setLocale value="pt_br"/>
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${product.preco}" />
					</td>
					<td>
						<a href="salvarProduto?acao=editar&product=${product.id}">
							<img src="resources/img/icons/edit.png" title="Editar" alt="Editar" width="20px" height="20px">
						</a>
					</td>
					<td>
						<a href="salvarProduto?acao=delete&product=${product.id}" onclick="return confirm('Confirma a exclusão deste registro?');">
						<%-- <a href="salvarProduto?acao=delete&product=${product.id}" > --%>
							<img src="resources/img/icons/delete.png" title="Excluir" alt="Excluir" width="20px" height="20px">
						</a>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

</html>