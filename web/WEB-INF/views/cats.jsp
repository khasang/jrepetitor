<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cats</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <%--<script src="js/jquery.min.js" type="text/javascript"></script>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<script type="text/javascript">
    var service = 'http://localhost:8080/cat';
    var get = '/get/';
    var all = '/all';
    var add = '/add';
    var del = '/delete';

    var restGet = function (id) {
        $.ajax({
            type: 'GET',
            url: service + get + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var restGetAll = function() {
        $.ajax({
            type: 'GET',
            url: service + all,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    // var restPut = function(name) {
    //     var JSONObject = {
    //         name: name
    //     };
    //
    //     $.ajax({
    //         type: 'PUT',
    //         url: service + add,
    //         contentType: 'application/json;utf-8',
    //         data: JSON.stringify(JSONObject),
    //         dataType: 'json',
    //         async: false,
    //         success: function (result) {
    //             $('#response').html(JSON.stringify(result));
    //         },
    //         error: function (jqXHR, testStatus, errorThrown) {
    //             $('#response').html(JSON.stringify(jqXHR));
    //         }
    //     });
    // };

    var restDelete = function(id) {
        $.ajax({
            type: 'DELETE',
            url: service + del,
            data: {id: id},
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };
</script>
<body>
<table class="table">
    <tr>
        <th>Activity</th>
        <th>URL</th>
        <th>Button</th>
    </tr>
    <tr>
        <td>Get cat by id</td>
        <td><code><strong>GET</strong>/cat/get/id/{id}</code></td>
        <td>
            Id: <input id="getCatById" value=""/>
            <button type="button" onclick="restGet($('#getCatById').val())">Get cat</button>
        </td>        
    </tr>
    <%--<tr>--%>
        <%--<td>Add cat</td>--%>
        <%--<td><code><strong>PUT</strong>/cat/add</code></td>--%>
        <%--<td>--%>
            <%--<form class="form-inline">--%>
                <%--name: <input type="text" id="putName" value="catName">--%>
                <%--<button type="button" onclick="restPut($('#putName').val())">Put cat</button>--%>
            <%--</form>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <tr>
        <td>Get all cats</td>
        <td><code><strong>GET ALL</strong>/cat/all</code></td>
        <td>
            <button type="button" onclick="restGetAll()">Get all cats</button>
        </td>
    </tr>
    <tr>
        <td>Delete cat by id</td>
        <td><code><strong>DEL CAT</strong>/cat/delete/{id}</code></td>
        <td>
            Id: <input id="delCatById" value=""/>
            <button type="button" onclick="restDelete($('#delCatById').val())">Delete cat</button>
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
