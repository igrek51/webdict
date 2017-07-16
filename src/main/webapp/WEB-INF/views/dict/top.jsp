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
    <script src="<c:url value="/js/top.js" />"></script>
</head>
<body>


<div id="container" class="panel panel-default">
    <div class="panel-body">

        <div class="panel panel-primary">
            <div class="panel-heading" id="dict-word">
                <c:out value="${entry.word}"/>
            </div>
            <div class="panel-body">
                <div id="dict-definition"></div>
            </div>
            <div class="panel-footer" id="buttons-panel">
                <button type="button" id="button-skip" class="btn btn-primary">
                    <span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span> Skip
                </button>
                <button type="button" id="button-check" class="btn btn-default">
                    <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span> Check
                </button>
                <button type="button" id="button-answer-correct" class="btn btn-success">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Correct
                </button>
                <button type="button" id="button-answer-wrong" class="btn btn-danger">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Wrong
                </button>
            </div>
        </div>

        <div class="well">
            <div class="dictentry-row">
                Rank: <c:out value="${entry.rank}"/>
            </div>
            <div class="dictentry-row">
                Last use: <c:out value="${entry.lastUse}"/>
            </div>
        </div>

    </div>
</div>

<script>
    dictEntryId = <c:out value="${entry.id}"/>;
    dictEntryDefinition = "<c:out value="${entry.definition}"/>";
</script>

</body>
</html>