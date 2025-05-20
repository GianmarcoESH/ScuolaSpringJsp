<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modifica Discente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h1>Modifica Discente</h1>

<a class="btn btn-warning mb-3" href="<c:url value='/discenti/lista'/>">Torna a Lista Discenti</a>

<div class="row justify-content-center">
    <div class="col-8">
        <form:form method="POST" action="${pageContext.request.contextPath}/discenti/${discente.id}" modelAttribute="discente" class="bg-light p-4 border rounded">

            <form:hidden path="id"/>

            <div class="mb-3">
                <label class="form-label" for="nome">Nome</label>
                <form:input path="nome" cssClass="form-control" id="nome"/>
            </div>

            <div class="mb-3">
                <label class="form-label" for="cognome">Cognome</label>
                <form:input path="cognome" cssClass="form-control" id="cognome"/>
            </div>

            <div class="mb-3">
                <label class="form-label" for="dataDiNascita">Data di nascita</label>
                <form:input path="dataDiNascita" type="date" cssClass="form-control" id="dataDiNascita"/>
            </div>

            <div class="mb-3">
                <label class="form-label" for="cittaDiResidenza">Città di residenza</label>
                <form:input path="cittaDiResidenza" cssClass="form-control" id="cittaDiResidenza"/>
            </div>

            <div class="mb-3">
                <label class="form-label" for="voto">Voto</label>
                <form:input path="voto" type="number" cssClass="form-control" id="voto"/>
            </div>

            <div class="mb-3">
                <h5>Corsi associati (selezionare per eliminare il corso)</h5>
                <c:forEach var="corso" items="${corsiAssociati}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="corsiIdsDaRimuovere" id="corsoRemove${corso.id}" value="${corso.id}"/>
                        <label class="form-check-label" for="corsoRemove${corso.id}">${corso.nomeCorso}</label>
                    </div>
                </c:forEach>
            </div>

            <div class="mb-3">
                <h5>Corsi non associati (selezionare per associare il corso)</h5>
                <c:forEach var="corso" items="${corsiNonAssociati}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="corsiIdsDaAggiungere" id="corsoAdd${corso.id}" value="${corso.id}"/>
                        <label class="form-check-label" for="corsoAdd${corso.id}">${corso.nomeCorso}</label>
                    </div>
                </c:forEach>
            </div>

            <div class="mb-3 d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">Aggiorna Discente</button>
            </div>

        </form:form>
    </div>
</div>

</body>
</html>
