<%-- <jsp:useBean id="calcula" class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page"></jsp:useBean> --%>

<%@page import="models.UsuarioModel"%>
<%@page import="dao.DaoLogin"%>
<%@page import="dao.DaoUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cadastro de Usuário</title>

<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
    </script>
    
            
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
		<h1>Cadastro de Usuário</h1>
	</center>
	
	<%-- 
	<%
	DaoUsuario user = new DaoUsuario(); 
	user.listar(); 
	%>
	
	${someData}
	--%>
	
	<!-- <form action="salvarUsuario" method="post" id="formUser" onsubmit="validarCampos()"> -->
	<!-- <form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;" > -->
	<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data" >
		<ul class="form-style-1">
		
		
		
			<li style="columns: 1;">
				<label>ID</label> 
				<input type="text" id="id" name="id" value="${user.id}" readonly="readonly" class="field-divided" />
			</li>
			<li>
				<label>Login <span class="required">*</span></label> <!-- <input type="text" name="field1" class="field-divided" placeholder="First" /> -->
				<input type="text" id="login" name="login" value="${user.login}" class="field-long" placeholder="username" />
				<label>Senha <span class="required">*</span></label> <!-- <input type="email" name="field3" class="field-long" /> -->
				<input type="password" id="senha" name="senha" value="${user.senha}" class="field-long" placeholder="password" />
			</li>
			<!-- <li style="columns: 1;"> -->
			<li>
				<label>Nome <span class="required">*</span></label>
		        <input type="text" id="nome" name="nome" value="${user.nome}" class="field-long" placeholder="nome" />
		        <%-- <input type="text" id="telefone" name="telefone" value="${user.telefone}" class="field-long" placeholder="telefone" /> --%>
		        
		        <label>Perfil <span class="required">*</span></label>
		        <select id="perfil" name="perfil" style="width:100%;">
		        	<option value="nao_informado">[--SELECIONE--]</option>
		        	
		        	<option value="administrador"
		        	<% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getPerfil().equalsIgnoreCase("administrador")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("selected=\"selected\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        	%>
		        	>Administrador</option>
		        	
		        	<option value="diretor"
		        	<% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getPerfil().equalsIgnoreCase("diretor")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("selected=\"selected\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        	%>
		        	>Diretor</option>
		        	
		        	<option value="coordenador"
		        	<% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getPerfil().equalsIgnoreCase("coordenador")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("selected=\"selected\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        	%>
		        	>Coordenador</option>
		        	
		        	<option value="supervisor"
		        	<% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getPerfil().equalsIgnoreCase("supervisor")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("selected=\"selected\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        	%>
		        	>Supervisor</option>
		        	
		        	<option value="analista"
		        	<% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getPerfil().equalsIgnoreCase("analista")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("selected=\"selected\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        	%>
		        	>Analista</option>
		        	
		        </select>
		        
			</li>
			<li>
				<label>Ativo <span class="required">*</span></label>
		        <input type="checkbox" id="ativo" name="ativo"   
		        
		        <% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.isAtivo()) {
		        			out.print(" "); //caractere de espaço
		        			out.print("checked=\"checked\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        %>
		        
		        />
				<label>Sexo <span class="required">*</span></label>
		        <input type="radio" id="sexo" name="sexo" 
		        
		        <% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getSexo().equalsIgnoreCase("M")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("checked=\"checked\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        %>
		        
		        value="M" >Masculino</input>
		        <input type="radio" id="sexo" name="sexo" 
		        
		        <% 
		        	if (request.getAttribute("user") != null) {
		        		UsuarioModel user = (UsuarioModel) request.getAttribute("user");
		        		if (user.getSexo().equalsIgnoreCase("F")) {
		        			out.print(" "); //caractere de espaço
		        			out.print("checked=\"checked\"");
		        			out.print(" "); //caractere de espaço
		        		}
		        	}
		        %>
		        
		        value="F" >Feminino</input>
				
		    </li>
			<li>
				<label>Cep <span class="required">*</span></label>
		        <input type="text" id="cep" name="cep" onblur="consultaCep();" value="${user.cep}" class="field-divided" maxlength="10" />
				<label>Logradouro <span class="required">*</span></label>
		        <input type="text" id="rua" name="rua" class="field-long" value="${user.rua}" readonly="readonly" />
			</li>
			<li>
				<label>Bairro <span class="required">*</span></label>
		        <input type="text" id="bairro" name="bairro" class="field-long" value="${user.bairro}" readonly="readonly" />
				<label>Cidade <span class="required">*</span></label>
		        <input type="text" id="cidade" name="cidade" class="field-long" value="${user.cidade}" readonly="readonly" />
			</li>
			<li>
				<label>Código IBGE <span class="required">*</span></label>
		        <input type="text" id="ibge" name="ibge" class="field-long" value="${user.ibge}" readonly="readonly" />
				<label>Estado <span class="required">*</span></label>
		        <input type="text" id="estado" name="estado" class="field-divided" value="${user.estado}" readonly="readonly" />
			</li>
			
			
			<%-- <li>
				<label>ID</label> 
				<input type="text" id="id" name="id" value="${user.id}" readonly="readonly" class="field-divided" />
				
				<label>Cep <span class="required">*</span></label>
		        <input type="text" id="cep" name="cep" onblur="consultaCep();" value="${user.cep}" class="field-divided" maxlength="10" />
			</li>
			<li>
				<label>Login <span class="required">*</span></label> <!-- <input type="text" name="field1" class="field-divided" placeholder="First" /> -->
				<!-- <input type="text" name="field2" class="field-divided" placeholder="Last" /></li> -->
				<input type="text" id="login" name="login" value="${user.login}" class="field-long" placeholder="username" />
				<label>Logradouro <span class="required">*</span></label>
		        <input type="text" id="rua" name="rua" class="field-long" value="${user.rua}" readonly="readonly" />
			</li>
			<li>
				<label>Senha <span class="required">*</span></label> <!-- <input type="email" name="field3" class="field-long" /> -->
				<input type="password" id="senha" name="senha" value="${user.senha}" class="field-long" placeholder="password" />
				<label>Bairro <span class="required">*</span></label>
		        <input type="text" id="bairro" name="bairro" class="field-long" value="${user.bairro}" readonly="readonly" />			
			</li>
			
		    <li>
		        <label>Nome <span class="required">*</span></label>
		        <input type="text" id="nome" name="nome" value="${user.nome}" class="field-long" placeholder="nome" />
		        
		        <label>Cidade <span class="required">*</span></label>
		        <input type="text" id="cidade" name="cidade" class="field-long" value="${user.cidade}" readonly="readonly" />
		    </li>
		    <li>
		        <label>Telefone <span class="required">*</span></label>
		        <input type="text" id="telefone" name="telefone" value="${user.telefone}" class="field-long" placeholder="telefone" />
		    	<label>Estado <span class="required">*</span></label>
		        <input type="text" id="estado" name="estado" class="field-divided" value="${user.estado}" readonly="readonly" />
		    </li>
		    <li style="columns: 1;">
		        <label>Código IBGE <span class="required">*</span></label>
		        <input type="text" id="ibge" name="ibge" class="field-long" value="${user.ibge}" readonly="readonly" />
		         		 
		    </li> --%>
		    <%-- <li>
		        <label>Cep <span class="required">*</span></label>
		        <input type="text" id="cep" name="cep" onblur="consultaCep();" value="${user.cep}" class="field-divided" maxlength="10" />
		    </li> --%>
		    <%-- <li>
		        <label>Logradouro <span class="required">*</span></label>
		        <input type="text" id="rua" name="rua" class="field-long" value="${user.rua}" readonly="readonly" />
		    </li> --%>
		    <%-- <li>
		        <label>Bairro <span class="required">*</span></label>
		        <input type="text" id="bairro" name="bairro" class="field-long" value="${user.bairro}" readonly="readonly" />
		    </li> --%>
		    <%-- <li>
		        <label>Cidade <span class="required">*</span></label>
		        <input type="text" id="cidade" name="cidade" class="field-long" value="${user.cidade}" readonly="readonly" />
		    </li> --%>
		    <%-- <li>
		        <label>Estado <span class="required">*</span></label>
		        <input type="text" id="estado" name="estado" class="field-divided" value="${user.estado}" readonly="readonly" />
		    </li> --%>
		    <%-- <li>
		        <label>Código IBGE <span class="required">*</span></label>
		        <input type="text" id="ibge" name="ibge" class="field-long" value="${user.ibge}" readonly="readonly" />
		    </li> --%>
		    
			<!-- 
		    <li>
		        <label>Subject</label>
		        <select name="field4" class="field-select">
		        <option value="Advertise">Advertise</option>
		        <option value="Partnership">Partnership</option>
		        <option value="General Question">General</option>
		        </select>
		    </li> 
		    -->
			<!-- 
		    <li>
		        <label>Your Message <span class="required">*</span></label>
		        <textarea name="field5" id="field5" class="field-long field-textarea"></textarea>
		    </li> 
		    -->
		    
		    <li>
				<label>Foto <span class="required">*</span></label>
		    	<input type="file" name="foto" value="Foto" />
		    	<%-- 
		    	<input type="input" style="display: none;" name="fotoTemp" value="${user.fotoBase64}" />
		    	<input type="input" style="display: none;" name="fotoMiniaturaTemp" value="${user.fotoBase64miniatura}" />
		    	<input type="input" style="display: none;" name="contentTypeTemp" value="${user.contentType}" />
		    	<input type="input" style="display: none;" name="fotoNomeTemp" value="${user.fotoNome}" />
		    	 --%>
		    	<label></label>
		    </li>
		    
		    <li>
				<label>Documento <span class="required">*</span></label>
		    	<input type="file" name="documento" value="Documento" />
		    	<%-- 
		    	<input type="input" style="display: none;" name="documentoTemp" value="${user.documentoBase64}" />
		    	<input type="input" style="display: none;" name="documentoContentTypeTemp" value="${user.contentTypeDocumento}" />
		    	<input type="input" style="display: none;" name="documentoNomeTemp" value="${user.documentoNome}" />
		    	 --%>
		    	<label></label>
		    </li>
		    
		    
			<li>
				<!-- <span> </span> --> 
				<input type="submit" value="Salvar" /> 
				<!-- <input type="submit" value="Submit" /> -->
				
				<input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'" /> 
			</li>
		</ul>
	</form>
	<br />


	<form method="post" action="servletPesquisa">
		<ul class="form-style-1">
			<li style="columns: 1;">
			<!-- <li>
				<table>
					<tr>
						<td>Descrição</td>
						<td><input type="text" id="consulta" name="consulta" /></td>
						<td><input type="submit" value="Pesquisar" /></td>
					</tr>
				</table>
			</li> -->
				<!-- <label style="float:left; margin-right: 15px;">Descrição</label> -->
				<label>Pesquisar por nome</label>
				<input type="text" id="consulta" name="consulta" alt="Consulta Nome" title="Consulta Nome" />
				<input type="submit" value="Pesquisar" />
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
				<th>Foto</th>
				<th>Documento</th>
				<th>Login</th>
				<!-- <th>Senha</th> -->
				<!-- <th style="width:150px;">Senha</th> -->
				<th>Nome</th>
				<!-- <th>Telefone</th> -->
				
				<th>CEP</th>
				<th>Logradouro</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>IBGE</th>
				<!-- <th colspan="2" style="width:300px; text-align: center; ">Ações</th> -->
				<th>Contato</th>
				<th>Editar</th>
				<th>Excluir</th>
			</tr>

		</thead>
		<tbody>

			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					
					
					<c:if test="${not empty user.fotoBase64miniatura or user.fotoBase64miniatura.isEmpty() == false}">
						<td>
							<a href="salvarUsuario?acao=download&tipo=Img&user=${user.id}">
								<!-- <img src='<c:out value="${user.tempFotoUser}"/>' title="Baixar Foto do Usuário" alt=" Baixar Foto do Usuário" width="35px" height="35px"> -->
								<img src='<c:out value="${user.fotoBase64miniatura}"/>' title="Baixar Foto do Usuário" alt=" Baixar Foto do Usuário" width="35px" height="35px">
							</a>
						</td>
					</c:if>
					<c:if test="${empty user.fotoBase64miniatura or user.fotoBase64miniatura.isEmpty() == true}">
						<td>
							<img src="resources/img/icons/user_not_found.png" title="Foto do Usuário não encontrada" alt="Usuário não encontrado" width="35px" height="35px" onclick="alert('Foto não foi inserida')">
						</td>
					</c:if>
					
					
					<%--  
					<c:choose>
						<c:when test="${user.fotoBase64miniatura.isEmpty() == false or not empty user.fotoBase64miniatura}">
							<a href="salvarUsuario?acao=download&tipo=Img&user=${user.id}">
								<!-- <img src='<c:out value="${user.tempFotoUser}"/>' title="Baixar Foto do Usuário" alt=" Baixar Foto do Usuário" width="35px" height="35px"> -->
								<img src='<c:out value="${user.fotoBase64miniatura}"/>' title="Baixar Foto do Usuário" alt=" Baixar Foto do Usuário" width="35px" height="35px">
							</a>
						</c:when>
						<c:when test="${user.fotoBase64miniatura.isEmpty() == true or empty user.fotoBase64miniatura}">
							<td>
								<img src="resources/img/icons/user_not_found.png" title="Foto do Usuário não encontrada" alt="Usuário não encontrado" width="35px" height="35px" onclick="alert('Foto não foi inserida')">
							</td>						
						</c:when>
					</c:choose>
					 --%>
					
					
					<c:if test="${not empty user.documentoBase64 or user.documentoBase64.isEmpty() == false}">
						<td>
							<a href="salvarUsuario?acao=download&tipo=Doc&user=${user.id}"> <!-- Documento -->
								<img src="resources/img/icons/downloadFile.png" title="Baixar Documento do Usuário" alt="Baixar Arquivo do Usuário" width="35px" height="35px">
							</a>
						</td>
					</c:if>
					<c:if test="${empty user.documentoBase64 or user.documentoBase64.isEmpty() == true}">
						<td>
							<img src="resources/img/icons/doc_not_found.png" title="Documento do Usuário não encontrado" alt="Documento não encontrado" width="35px" height="35px" onclick="alert('Documento não foi inserido')">
						</td>
					</c:if>
					
					<td><c:out value="${user.login}"></c:out></td>
					
					<%-- <td><c:out value="${user.senha}"></c:out></td> --%>
					
					<td><c:out value="${user.nome}"></c:out></td>
					<%-- <td><c:out value="${user.telefone}"></c:out></td> --%>
					
					<td><c:out value="${user.cep}"></c:out></td>
					<td><c:out value="${user.rua}"></c:out></td>
					<td><c:out value="${user.bairro}"></c:out></td>
					<td><c:out value="${user.cidade}"></c:out></td>
					<td><c:out value="${user.estado}"></c:out></td>
					<td><c:out value="${user.ibge}"></c:out></td>
					
					<td>
						<%-- <a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a> --%>
						<a href="salvarTelefones?acao=addFone&user=${user.id}">
							<img src="resources/img/icons/phone.png" title="Telefones" alt="Telefones" width="20px" height="20px">
						</a>
					</td>
					<td>
						<%-- <a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a> --%>
						<a href="salvarUsuario?acao=editar&user=${user.id}">
							<img src="resources/img/icons/edit.png" title="Editar" alt="Editar" width="20px" height="20px">
						</a>
					</td>
					<td>
						<%-- <a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a> --%>
						<a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirma a exclusão deste registro?');">
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
		if (document.getElementById("login").value == '') {
			alert('Login não foi preenchido');
			return false;
		} else if (document.getElementById("senha").value == '') {
			alert('Senha não foi preenchida');
			return false;
		} else if (document.getElementById("nome").value == '') {
			alert('Nome não foi preenchido');
			return false;
		/*
		} else if (document.getElementById("telefone").value == '') {
			alert('Telefone não foi preenchido');
			return false;
		*/
		}
		
		return true;		
	}
	
	function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        //$("#cep").val("");
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#estado").val("");
        $("#ibge").val("");
    }

	function consultaCep() {
		var cep = $("#cep").val(); // "#cep" obtém o valor pesquisado na caixa de texto cujo id é "cep"
		//alert(cep);
		
		//máscara de CEP
		//document.getElementById('cep').value = cep.substring(0,2) + "." + cep.substring(2,5) + "-" + cep.substring(5);
		
		
		 //Preenche os campos com "..." enquanto consulta webservice.
        $("#rua").val("...");
        $("#bairro").val("...");
        $("#cidade").val("...");
        $("#estado").val("...");
        $("#ibge").val("...");
		
		//Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) { //o retorno da consulta é inserido dentro da variavel "dados"

        	//alert(dados);
        	
            if (!("erro" in dados)) {
                //Atualiza os campos com os valores da consulta.
                $("#rua").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#cidade").val(dados.localidade);
                $("#estado").val(dados.uf);
                $("#ibge").val(dados.ibge);
            } //end if.
            else {
                //CEP pesquisado não foi encontrado.
                limpa_formulário_cep();
                alert("CEP não encontrado.");
            }
        });
		
	}
	
	
	
</script>

</body>
</html>