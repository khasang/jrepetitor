<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тест</title>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>--%>
<%@ include file = "header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    var service = 'http://localhost:8080/';
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

    var RestGetAllQuestions = function () {
      $.ajax({
          type: 'GET',
          url: service + 'quiz/all',
          dataType: 'json',
          async: false,
          success: function (result) {
              var output = '';
              var stringData = JSON.stringify(result);
              var arrData = JSON.parse(stringData)[0];
              var arrQue1 = arrData.questions[0];
              var arrQue2 = arrData.questions[4];
              var items1 = arrQue1.items;
              var items2 = arrQue2.items;


              output +=
                  '<p>'+arrData.id+'  '+arrData.name+'</p>'+

                  '<p>'+arrQue1.id+'  '+arrQue1.content+'</p>'
                  // '<p>'+arrQue1.content+'</p>'
              for (i in items1){
                  output +=
                      '<p>' + '<input type="radio" id="customRadio1" name="customRadio" class="custom-control-input" checked>'+
                      '<label class="form-check-label">' +
                      items1[i].id+items1[i].content+
                      '</label>' +
                      '</p>'
              }
              output += '<p>'+'Explanation: ' + arrQue1.explanation +'</p>'

              output += '<p>'+arrQue2.id+'  '+arrQue2.content+'</p>'
              for (i in items2) {
                  output += '<p>' + '<input type="checkbox" name="checkbox-1" id="checkbox-1">' +
                      '<label for="checkbox-2">' +
                      items2[i].id + items2[i].content +
                      '</label>' +
                      '</p>'
              }

              $('#response').html(
                  output
              );
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
        <button type="button" onclick="RestGetAllQuestions()">Try</button>
    </div>
</div>
</body>
<%@ include file = "footer.jsp" %>
<%--</html>--%>
