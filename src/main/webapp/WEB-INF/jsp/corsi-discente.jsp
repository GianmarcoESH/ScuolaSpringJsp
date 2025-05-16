<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Corsi del Discente</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h2>Corsi associati a: ${discente.nome} ${discente.cognome}</h2>

<a class="btn btn-warning mb-3" href="<c:url value='/discenti/lista'/>">Torna alla lista discenti</a>

<table class="table table-bordered">
    <thead>
    <tr>

        <th>Nome Corso</th>
        <th>Ore Corso</th>
        <th>Anno Accademico</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="corso" items="${corsi}">
        <tr>
            <td>${corso.nomeCorso}</td>
            <td>${corso.oreCorso}</td>
            <td>${corso.annoAccademico}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
