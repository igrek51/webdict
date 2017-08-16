<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<div id="container" class="panel panel-default">
    <div class="panel-body">

        <div class="panel panel-primary">
            <div class="panel-heading" id="dict-word">Settings</div>
            <div class="panel-body">

                <form:form method="post" action="?" modelAttribute="settingsDTO"
                           class="form-horizontal">
                    <div>
                        User:
                        <form:select path="userLogin" class="form-control" id="userLogin">
                            <form:options items="${users}"/>
                        </form:select>
                    </div>

                    <div>
                        Dictionary:
                        <form:select path="dictionaryCode" class="form-control" id="dictionaryCode">
                            <form:options items="${dictionaries}"/>
                        </form:select>
                    </div>

                    <button type="submit" class="btn btn-success">
                        Select
                    </button>
                </form:form>

            </div>
        </div>

    </div>
</div>

</body>
</html>