<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 01.04.2018
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js" type="text/javascript"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
</head>
<script>
    var service = 'http://localhost:8080/cat';
    var RestGet = function () {
        $.ajax({
            type: 'GET',
            url: service + '/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#responce').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#responce').html(JSON.stringify(jqXHR))
            },
        })
    };
</script>
<body>
<h1>Cat Menu</h1>
<table class="table">
    <tr>
        <th>Requst type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>GET (Get all cats)</td>
        <td>/cat/all</td>
        <td>
            <button type="button" onclick="RestGet()">Try</button>
        </td>
    </tr>
</table>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>RESPONSE</strong>
    </div>
    <div class="panel-body" id="response"></div>
</div>
</body>
</html>
