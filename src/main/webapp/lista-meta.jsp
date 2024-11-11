<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fintech</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div class="mt-5 ms-5 me-5">

        <div class="card mb-3">
            <div class="card-header">
                LISTA DE METAS
            </div>
            <div class="card-body">
                <h5 class="card-title">Gestão de metas</h5>
                <p class="card-text">Mantenha suas metas sempre atualizados e acessíveis.</p>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th class="text-end">Descrição</th>
                        <th class="text-end">Valor</th>
                        <th class="text-center">Data da meta</th>
                        <th class="text-center"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${metas}" var="meta">
                    <tr>
                        <td>${meta.nome}</td>
                        <td class="text-end">${meta.valor}</td>
                        <td class="text-end">
                            <fmt:parseDate
                                    value="${meta.data}"
                                    pattern="yyyy-MM-dd"
                                    var="dataMetaFmt"/>
                            </fmt:formatDate
                                    value="${dataMetaFmt}"
                                    pattern="dd/MM/yyyy"/>
                        </td>
                        </td> class="text-center">${meta.descricao}</td>
                        </td class="text-center">
                                <c:url value="metas" var="link">
                                   <c:param name="acao" value="abrir-form-edicao"/>
                                    <c:param name="codigo" value="${meta.codigo}"
                                </c:url>
                        <a href="${link}" class="btn btn-primary">Editar</a>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="cadastro-meta.jsp" class="btn btn-primary">Adicionar meta</a>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>