<jsp:useBean id="calcula" class="models.UsuarioModel"
	type="models.UsuarioModel" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP</title>


<!-- <link rel="stylesheet" type="text/css" href="resources/css/login_green.css"> -->

<link rel="stylesheet" type="text/css" href="resources/css/random.css">

</head>
<body>

	<%-- 
	<c:out value="${'Bem-vindo ao JSTL'}"></c:out>
	<br /> 
	--%>

	<%-- REDIRECIONAMENTO
<c:set var="numero" value="${100/2}"></c:set>

<c:if test="${numero >= 50}">
	<c:redirect url="acessoliberado.jsp"></c:redirect>
</c:if>

<c:if test="${numero < 50}">
	<c:redirect url="acessoliberado.jsp"></c:redirect>
</c:if> 
--%>


	<%-- 
<c:url value="/acessoliberado.jsp" var="acesso">
	<c:param name="param1" value="123"></c:param>
	<c:param name="param2" value="456"></c:param>
</c:url>

${acesso}
 --%>
	<%-- 
<!-- Quando se fala em Tokens em Java significa quebrar uma String por um determinado caractere, neste caso o delimitador é o hifen -->
<c:forTokens items="Thiers-Francisco-Barizon" delims="-" var="nome">
	Nome: <c:out value="${nome}"></c:out> <br/>
	
</c:forTokens>
 --%>

	<%-- 
<c:forEach var="n" begin="0" end="${numero}">
	Item : ${n}
	<br/>
</c:forEach>
 --%>
	<%-- 
<c:choose>
	
	<c:when test="${numero > 50}">
		<c:out value="${'Maior que 50'}"></c:out>
	</c:when>
	
	<c:when test="${numero < 50}">
		<c:out value="${'Menor que 50'}"></c:out>
	</c:when>
	
	<c:otherwise>
		<c:out value="${'Igual a 50'}"></c:out>
	</c:otherwise>
	
</c:choose>
 --%>

	<%-- 
<c:import var ="data" url="https://www.google.com.br"></c:import>
<c:out value="${data}"></c:out>
 --%>

	<%-- 
<c:set var="data" scope="page" value="${500 * 6}"></c:set><br/>

<c:remove var="data	"/>

<c:out value="${data}"></c:out><br/>

<c:catch var="erro">
	<% int var = 100/0; %>
</c:catch>

<c:if test="${erro != null}">
	${erro.message}
</c:if> 
--%>

	<p>
	<p />
	<p>
	<p />
	<p>
	<p />
	<p>
	<p />

	<!-- <form action="LoginServlet" method="get"> busca a Annotation "LoginServlet" declarado no servlet.LoginServlet.java
	
		
		Login:
		<input type="text" id="login" name="login" />
		<br/>
		
		Senha:
		<input type="password" id="senha" name="senha" />
		<br/>
		
		<input type="submit" value="Entrar" />
		<br/>
		
		
	</form> -->

	<!--  
	<div class="login-page">
		<div class="form">
		<c:out value="${'Bem-vindo ao JSTL'}"></c:out>
		<p></p>
		
			<form action="LoginServlet" method="get" class="login-form">
				<input type="text" id="login" name="login" placeholder="username" />
				<input type="password" id="senha" name="senha" placeholder="password" /> -->
				<!-- <input type="submit" value="Entrar" /> -->
				<!-- 
				<button type="submit">Entrar</button>
			</form>
		</div>
	</div>
	-->
	
	<div class="body"></div>
		<div class="grad"></div>
		<div class="header">
			<!-- <div>Meu<span> Projeto</span></div> -->
			<div>Curso<span> JSP</span></div>
		</div>
		<br>
		<form action="LoginServlet" method="get" class="login-form">
		<div class="login">
				<!-- <input type="text" placeholder="username" name="user"><br>
				<input type="password" placeholder="password" name="password"><br> -->
				<input type="text" id="login" name="login" placeholder="username" />
				<input type="password" id="senha" name="senha" placeholder="password" />
				
				<!-- <input type="button" value="Login"> -->
				<input type="submit" value="Entrar">
		</div>
		</form>
	
</body>
</html>