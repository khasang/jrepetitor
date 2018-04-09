<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тест</title>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>--%>
<%--<%@ include file = "header.jsp" %>--%>
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
            url: service + 'quiz/get/1',
            dataType: 'json',
            async: false,
            success:
                    function (result)
                    {
                        var output = '';
                        var stringData = JSON.stringify(result);
                        var postData = JSON.parse(stringData);

                        function myFunction(){
                            alert("Done");
                        }

                        var itemsHtml;
                        var itemsCount=que.items.length;

                        function questionList(que){
                            var divQuest=`
                  for (i=0;i<itemsCount;i++){
                      itemsHtml+='<p><input name='+que.id+' type='+que.type+'>'+que.items[i].content'+'</p>';
                  }
                    itemsHtml+='<input type="button" id="myBtn" onclick="myFunction()" value="Try it">';
                  itemsHtml+='</form>';

                  return divQuest+'<br>'+itemsHtml;
              }

                output= '<h1>'+postData.questions.map(questionList).join('')+'</h1>';

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
<%--<%@ include file = "footer.jsp" %>--%>
<%--</html>--%>
