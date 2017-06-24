<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <title>${title}</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
    <script src="<c:url value="/js/welcome.js" />"></script>
</head>
<body>
<div id="outer">
    <div id="inner">
        <div>${message}</div>
    </div>
</div>
</body>

</html>
