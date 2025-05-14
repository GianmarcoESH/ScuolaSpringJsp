<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Elenco discenti</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Elenco discenti</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/discenti/nuovo'/>">Nuovo Discente</a>
<c:choose>
    <c:when test="${filterType == 'promossi'}">
        <a class="btn btn-warning mb-3" href="<c:url value='/discenti/lista'/>">Torna a lista discenti</a>
    </c:when>
    <c:otherwise>
        <a class="btn btn-warning mb-3" href="<c:url value='/discenti/promossi'/>">discenti promossi</a>
    </c:otherwise>
</c:choose>

<form action="<c:url value='/discenti/lista'/>" method="get" class="mb-3">
  <div class="input-group">
    <input
      type="text"
      name="keyword"
      value="${param.keyword}"
      class="form-control"
      placeholder="Cerca per nome o cognome..."
    />
    <button class="btn btn-outline-secondary" type="submit">Cerca</button>
  </div>
</form>

<form action="<c:url value='/discenti/lista'/>" method="get" class="mb-3">
  <div class="input-group">
    <input
      type="text"
      name="citta"
      value="${param.citta}"
      class="form-control"
      placeholder="Cerca per città..."
    />
    <button class="btn btn-outline-secondary" type="submit">Cerca</button>
  </div>
</form>


<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Città di residenza</th><th>Voto</th><th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="a" items="${discenti}">
        <tr>
            <td>${a.id}</td>
            <td>${a.nome}</td>
            <td>${a.cognome}</td>
            <td>${a.dataDiNascita}</td>
            <td>${a.cittaDiResidenza}</td>
            <td>${a.voto}</td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/discenti/${a.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/discenti/${a.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
