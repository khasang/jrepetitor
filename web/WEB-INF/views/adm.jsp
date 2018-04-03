<%--
  Created by IntelliJ IDEA.
  User: AELyskovets
  Date: 03.04.2018
  Time: 21:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
</head>

<body>

<%--user's table section--%>

<h1>User's list</h1>

<h3>
    <button type="button" onclick="<%--TODO: add user (method)--%>">Add new user</button>
</h3>

<table class="table">
    <tr>
        <th>Login</th>
        <th>Email</th>
        <th>Last activity</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <tr>
        <td><%--TODO: login--%></td>
        <td><%--TODO: email--%></td>
        <td><%--TODO: last activity--%></td>
        <td>
            <button type="button" onclick="<%--TODO: edit (method)--%>">Edit</button>
        </td>
        <td>
            <button type="button" onclick="<%--TODO: delete (method)--%>">Delete</button>
        </td>
    </tr>
</table>

<%--news' table section--%>

<h3>
    <button type="button" onclick="<%--TODO: add news (method)--%>">Add news</button>
</h3>

<h1>News' list</h1>

<table class="table">
    <tr>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>
            <button type="button" onclick="<%--TODO: edit (method)--%>">Edit</button>
        </td>
        <td>
            <button type="button" onclick="<%--TODO: delete (method)--%>">Delete</button>
        </td>
    </tr>
</table></body>
</html>
