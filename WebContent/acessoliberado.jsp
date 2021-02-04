<jsp:useBean id="calcula" class="models.UsuarioModel"
	type="models.UsuarioModel" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <jsp:setProperty property="*" name="calcula"/> --%>
	
	<center style="padding-top: 3%;">
		<h3>Bem-vindo ao sistema JSP</h3>
				
				
		<a href="salvarUsuario?acao=listartodos" style="text-decoration: none;">
			<img src="resources/img/icons/add_users_3d.png" title="Cadastrar Novo Usuário" alt="Cadastrar Novo Usuário" width="80px" height="80px">
		</a>
		
		<a href="salvarProduto?acao=listartodos" style="text-decoration: none;">
			<img src="resources/img/icons/add_product_3d.png" title="Cadastrar Novo Produto" alt="Cadastrar Novo Produto" width="80px" height="80px">
		</a>
				
		<a href="index.jsp" style="text-decoration: none;">
			<img src="resources/img/icons/logout.png" title="Sair" alt="Sair" width="80px" height="80px">
		</a>
	</center>

</body>
</html>

