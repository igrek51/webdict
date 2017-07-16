<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
    <script src="<c:url value="/js/top.js" />"></script>
</head>
<body>

<div id="container">
    <div class="dictentry-row">
        Word: <c:out value="${entry.word}"/>
    </div>
    <div class="dictentry-row">
        Definition: <span id="dict-definition"><c:out value="${entry.definition}"/></span>
    </div>
    <div class="dictentry-row">
        Rank: <c:out value="${entry.rank}"/>
    </div>
    <div class="dictentry-row">
        Last use: <c:out value="${entry.lastUse}"/>
    </div>
    <div id="buttons">
        <button id="button-skip">Skip</button>
        <button id="button-check">Check</button>
        <button id="button-answer-correct">Correct</button>
        <button id="button-answer-wrong">Wrong</button>
    </div>
</div>

<script>
    dictEntryId = <c:out value="${entry.id}"/>;
</script>

</body>
</html>