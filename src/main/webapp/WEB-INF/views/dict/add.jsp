<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${title}</title>
    <script src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
    <%-- Bootstrap --%>
    <link href="<c:url value="/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap-theme.min.css" />" rel="stylesheet">
    <script src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>

    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>

<div id="container">

    <div class="panel panel-primary">
        <div class="panel-heading" id="dict-word">Add new word</div>
        <div class="panel-body">

            <c:forEach items="${alerts}" var="alert">
                <div class="alert alert-<c:out value="${alert.getBootstrapStyle()}"/> alert-dismissible"
                     role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <c:out value="${alert.getMessage()}"/>
                </div>
            </c:forEach>

            <form action="?" method="POST" modelAttribute="addWordDTO">

                <div class="input-group row-form-add-word">
                    <span class="input-group-addon" id="basic-addon1">Word</span>
                    <input type="text" class="form-control" placeholder="Word"
                           aria-describedby="basic-addon1" name="word">
                </div>

                <div class="input-group row-form-add-word">
                    <span class="input-group-addon" id="basic-addon2">Definition</span>
                    <input type="text" class="form-control" placeholder="Definition"
                           aria-describedby="basic-addon2" name="definition">
                </div>

                <div class="row-form-add-word">
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add
                    </button>
                </div>

            </form>

        </div>
    </div>

</div>

</body>
</html>