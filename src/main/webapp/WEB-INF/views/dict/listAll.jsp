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

<div id="container" class="panel panel-default">
    <div class="panel-body">

        <div class="panel panel-primary">
            <div class="panel-heading" id="dict-word">Dictionary entries</div>
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <th>Word</th>
                        <th>Definition</th>
                        <th>Rank</th>
                    </tr>
                    <c:forEach items="${entries}" var="entry">
                        <tr>
                            <td><c:out value="${entry.wordName}"/></td>
                            <td><c:out value="${entry.definition}"/></td>
                            <td><c:out value="${entry.rankValue}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

    </div>
</div>

</body>
</html>