<%-- <jsp:useBean id="calcula" class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page"></jsp:useBean> --%>

<%@page import="dao.DaoLogin"%>
<%@page import="dao.DaoUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Telefones de Usuário</title>

<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css">
            
<script>
	//whatever kind of mobile test you wanna do.
	if (screen.width < 500) {

		$("body").addClass("nohover");
		$("td, th").attr("tabindex", "1").on("touchstart", function() {
			$(this).focus();
		});

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
		<h1>Cadastro de Telefones de Usuário</h1>
	</center>
	
	
	<!-- <form action="salvarTelefones" method="post" id="formUser" onsubmit="return validarCampos() ? true : false"> -->
	<form action="salvarTelefones" method="post" id="formUser" >
		<ul class="form-style-1">
		
		
		
			<li>
				<label>User ID</label> 
				<input type="text" id="id" name="id" value="${userEscolhido.id}" readonly="readonly" class="field-divided" />
			<!-- 
			</li>
			<li> 
			-->
				<label>Nome</label> 
				<input type="text" id="nome" name="nome" value="${userEscolhido.nome}" readonly="readonly" class="long" />
			</li>
			<li>
		        <label>Tipo <span class="required">*</span></label>
		        <select id="tipo" name="tipo">
		        	<option>Telefone Fixo</option>
		        	<option>Celular Pessoal</option>
		        	<option>Celular Corporativo</option>
		        </select>
				<label>Número <span class="required">*</span></label>
		        <input type="text" id="numero" name="numero" class="field-long" placeholder="nº telefone" />
			</li>
			
			
			<li>
				<input type="submit" value="Salvar" /> 
			<!-- 
			</li>
			<li> 
			-->
				<input type="submit" value="Voltar" onclick="document.getElementById('formUser').action='salvarTelefones?acao=voltar'" /> 
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
				<th>Número</th>
				<th>Tipo</th>
				<th>Excluir</th>
			</tr>

		</thead>
		<tbody>

			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td><c:out value="${fone.id}"></c:out></td>
					<td><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>
					<%-- <td><c:out value="${user.telefone}"></c:out></td> --%>
										
					<td>
						<a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}" onclick="return confirm('Confirma a exclusão deste registro?');">
							<img src="resources/img/icons/delete.png" title="Excluir" alt="Excluir" width="20px" height="20px">
						</a>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

<script type="text/javascript">


	function validarCampos() {
		//alert(document.getElementById("login").value); //imprime o que foi digitado na tela
		if (document.getElementById("numero").value == '') {
			alert('Número não foi preenchido');
			return false;
		} else if (document.getElementById("tipo").value == '') {
			alert('Tipo não foi preenchido');
			return false;
		} 
		return true;		
	}
		
</script>

</body>
</html>