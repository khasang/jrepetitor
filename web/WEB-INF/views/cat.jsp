<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<script src="js/jquery.min.js" type="text/javascript"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<script type="text/javascript">
    var service = 'http://localhost:8080/cat';
    /*добавление кота*/
    var RestPut = function (name) {
        var JSONObject = {'name':name};
        $.ajax({
            type: 'POST',
            url: service + "/add",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(JSONObject),
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

    /*получение всех котов*/
    var RestGet = function () {
        $.ajax({
            type: 'GET',
            url: service + '/all',
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
    
    /*получение кота по ID*/
    var RestGetById = function (id) {
        $.ajax({
            type: 'GET',
            url: service + '/get/' + id,
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
    
    /*удаление кота по ID*/
    var RestDelete = function (id) {
        /*var data = {};
        data["id"] = $("#id").val();*/
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json;charset=utf-8',
            url: service + '/delete?id=' + id,
            /*url: service + '/delete',
            data: JSON.stringify(data),*/
            /*data: {id: id},*/
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

    /*Update Кота*/
    var RestUpdate = function (id, name) {
        var JSONObject = {'id':id,'name':name};
        $.ajax({
            type: 'PUT',
            url: service + '/upd',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    }

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
    <tr>
        <td>Get Cat by ID</td>
        <td><code><strong>GET</strong>/cat/get/id/{id}</code></td>
        <td>
            Id: <input id="getCatById" value=""/>
            <button type="button" onClick="RestGetById($('#getCatById').val())">Try get by ID</button>
        </td>
    </tr>
    <tr>
        <td>Add cat</td>
        <td><code><strong>POST</strong>/cat/add</code></td>
        <td>
            <form class="form-inline">
                name: <input type="text" id="putName" value="CatName">
                <button type="button" onclick="RestPut($('#putName').val())">Try add Cat</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>Delete cat by ID</td>
        <td><code><strong>DELETE</strong>/cat/delete?id={id}</code></td>
        <td>
            ID: <input id="CatIdForDelete" value="">
            <button type="button" onclick="RestDelete($('#CatIdForDelete').val())">Try delete Cat</button>
        </td>
    </tr>
    <tr>
        <td>Update cat</td>
        <td><code><strong>PUT</strong>/cat/upd</code></td>
        <td>
            <form class="form-inline">
                ID: <input id="CatIdForUpdate" value="">
                Name: <input id="CatNameForUpdate" value="">
                <button type="button" onclick="RestUpdate($('#CatIdForUpdate').val(), $('#CatNameForUpdate').val())">Try update cat</button>
            </form>
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