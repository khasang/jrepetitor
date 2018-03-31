<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="dogs.do" method="POST" commandName="dog">
    <table>
        <tr>
            <td>ID</td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><form:input path="description" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" name="action" value="insert">
                <input type="submit" name="action" value="update">
                <input type="submit" name="action" value="delete">
                <input type="submit" name="action" value="search">
            </td>
        </tr>
    </table>
    <br/>
    <table border="1">
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <c:forEach items="${dogsList}" var="dog">
            <tr>
                <td>${dog.id}</td>
                <td>${dog.name}</td>
                <td>${dog.description}</td>
            </tr>

        </c:forEach>
    </table>
</form:form>

</body>
</html>
