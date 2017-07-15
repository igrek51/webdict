<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
    <script src="<c:url value="/js/welcome.js" />"></script>
</head>
<body>

<div id="outer">
    <div id="inner">
        <div>
            <table>
                <c:forEach items="${entries}" var="entry">
                    <tr>
                        <td>word: <c:out value="${entry.word}"/></td>
                        <td>definition: <c:out value="${entry.definition}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>