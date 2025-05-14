<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Corso</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>

<body>
    <h1>Nuovo Corso</h1>
    <a class="btn btn-warning mb-3" href="<c:url value='/corsi/lista'/>">Torna alla Lista dei corsi</a>
    <div class="row mt-4 justify-content-center">
        <div class="row mt-4 justify-content-center">
            <div class="col-6">
                <form:form class="bg-light p-4 border rounded" method="POST" action="${pageContext.request.contextPath}/corsi/add" modelAttribute="corso" >
                    <form:hidden path="id"/>
                    <div class="mb-3">
                      <label for="exampleFormControlInput1" class="form-label">Nome Corso</label>
                      <form:input type="text" cssClass="form-control" path="nomeCorso" />
                    </div>
                     <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Anno Accademico</label>
                        <form:input type="number" cssClass="form-control" path="annoAccademico" />
                     </div>
                      <div class="mb-3">
                          <label for="exampleFormControlInput1" class="form-label">Ore corso</label>
                          <form:input type="number" cssClass="form-control" path="oreCorso" />
                      </div>
                      <div class="mb-3">
                         <label for="docente" class="form-label">Docente</label>
                          <form:select path="docente.id" class="form-control">
                              <form:option value="" label="Seleziona Docente" />
                              <c:forEach items="${docenti}" var="docente">
                                  <form:option value="${docente.id}" label="${docente.nome} ${docente.cognome}" />
                              </c:forEach>
                          </form:select>
                      </div>
                      <div class="mb-3 d-flex justify-content-end">
                        <button type="submit" class="btn btn-success mt-4">
                            <a>Salva Corso</a>
                        </button>
                      </div>
                </form:form>
            </div>
        </div>
    <div>
</body>
</html>