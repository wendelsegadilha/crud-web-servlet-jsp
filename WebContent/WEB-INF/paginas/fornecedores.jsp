<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Fornecedores</title>
</head>
<body>
	<h2>Fornecedores Cadastrados</h2>
	
	<form method="POST" action="fornecedorServlet">
		<input type="hidden" name="codigo" value="${fornecedor.codigo}"> 
		Código:  <input type="text" name="codigo" disabled="disabled" value="${fornecedor.codigo}"> <br>
		Nome Fantasia:  <input type="text" name="nome" value="${fornecedor.nome}"> <br>
		Razão Social:  <input type="text" name="razaoSocial" value="${fornecedor.razaoSocial}"> <br>
		CNPJ:  <input type="text" name="cnpj" value="${fornecedor.cnpj}"> <br>
		E-mail:  <input type="text" name="email" value="${fornecedor.email}"> <br>
		<input type="submit" value="Cadastrar">
	</form>
	
	
	<h4 style="color:red;">${mensagem}</h4>
	<table border="1">
		<tr>
			<th>Código</th>
			<th>Nome Fantasia</th>
			<th>Razão Social</th>
			<th>CNPJ</th>
			<th>Email</th>
			<th colspan="2">Ações</th>
		</tr>
		<c:forEach var="f" items="${fornecedores}">
			<tr>
				<td>${f.codigo}</td>
				<td>${f.nome}</td>
				<td>${f.razaoSocial}</td>
				<td>${f.cnpj}</td>
				<td>${f.email}</td>
				<td><a href="fornecedorServlet?acao=editar&codigo=${f.codigo}">Editar</a></td>
				<td><a href="fornecedorServlet?acao=excluir&codigo=${f.codigo}">Excluir</a></td>
			</tr>
		</c:forEach>
	
	</table>
</body>
</html>