<%--
  Created by IntelliJ IDEA.
  User: Utente
  Date: 20/05/2025
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Modifica Corso</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h1>Modifica Corso</h1>

<a class="btn btn-warning mb-3" href="<c:url value='/corsi/lista'/>">Torna a Lista Corsi</a>

<div class="row justify-content-center">
    <div class="col-8">
        <form:form action="${pageContext.request.contextPath}/corsi/${corsoDTO.id}" method="post" class="bg-light p-4 border rounded" modelAttribute="corsoDTO">
            <form:hidden path="id"/>

            <div class="mb-3">
                <label for="nomeCorso" class="form-label">Nome Corso:</label>
                <form:input path="nomeCorso" id="nomeCorso" name="nomeCorso" value="${corsoDTO.nomeCorso}" />
            </div>

            <div class ="mb-3">
                <label for="oreCorso">Ore Corso:</label>
                <form:input path="oreCorso" id="oreCorso" name="oreCorso" value="${corsoDTO.oreCorso}"/>
            </div>

            <div class ="mb-3">
                <label for="annoAccademico">Anno Accademico:</label>
                <form:input path="annoAccademico" id="annoAccademico" name="annoAccademico" value="${corsoDTO.annoAccademico}"/>
            </div>

            <div class ="mb-3">
                <label for="docente">Docente:</label>
                <select id="docente" name="docente.id" required>
                    <option value="">-- Seleziona Docente --</option>
                    <c:forEach var="docente" items="${docenti}">
                        <option value="${docente.id}"
                                <c:if test="${corsoDTO.docente != null && corsoDTO.docente.id == docente.id}">selected</c:if>>
                                ${docente.nome} ${docente.cognome}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label>Discenti:</label><br/>
                <c:forEach var="discente" items="${discenti}">
                    <input type="checkbox" name="discenteIds" value="${discente.id}"
                           <c:if test="${corsoDTO.discenteIds != null && corsoDTO.discenteIds.contains(discente.id)}">checked</c:if> />
                    ${discente.nome} ${discente.cognome} <br/>
                </c:forEach>
            </div>

            <div class="mb-3 d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">Aggiorna Corso</button>
            </div>
        </form:form>

    </div>
</div>

</body>
</html>

