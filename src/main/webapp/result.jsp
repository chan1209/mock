<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>mock查询结果</title>
    <style type="text/css">
        body{text-align: center;margin-left: auto;margin-right: auto;}
    </style>
</head>
<body>
<div class="mockdata">
    mock查询结果如下：
    <table border="1"  width="90%">
        <tr>
            <th>No</th>
            <th>id</th>
            <th>mockServiceName</th>
            <th>mockUrlPath</th>
            <th>mockResponse</th>
            <th>mockParams</th>
        </tr>
        <c:forEach items="${mocklist}"  var="entry">
        <tr>
            <td><c:out value="${entry.key}"/></td>
            <td><c:out value="${entry.value.id}"/></td>
            <td><c:out value="${entry.value.mockServiceName}"/></td>
            <td><c:out value="${entry.value.mockUrlPath}"/></td>
            <td><c:out value="${entry.value.mockResponse}"/></td>
            <td><c:out value="${entry.value.mockParams}"/></td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
