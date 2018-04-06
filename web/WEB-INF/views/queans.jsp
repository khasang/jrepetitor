<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тест</title>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<script>
    var service = 'http://localhost:8080/queans';
    var RestGet = function () {
      $.ajax({
          type: 'GET',
          url: service + '/all',
          dataType: 'json',
          async: false,
          success: function (result) {
              $('#response').html(JSON.stringify(result))
          },
          error: function (jqXHR, testStatus, errorThrown) {
              $('#response').html(JSON.stringify(jqXHR))
          }
      });
    };

    var RestGetById = function () {
        $.ajax({
            type: 'GET',
            url: service + '/get/35',
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };
</script>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>Прохождение теста</strong>
    </div>
    <div class="panel-body" id="response"></div>
    <div class="panel-body ">
        <button type="button" onclick="RestGetById()">Try</button>
    </div>
</div>
</body>
</html>
