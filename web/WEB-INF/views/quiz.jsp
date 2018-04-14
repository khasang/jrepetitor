<%@ include file = "header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    var service = 'http://localhost:8080/';

    var RestGetAllQuestions = function () {
        $.ajax({
            type: 'GET',
            url: service + 'quiz/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
               var arrData = JSON.parse(stringData)[1];
                var questions=arrData.questions;

                var questionCount = questions.length;
                for (n=0;n<questionCount;n++) {
                    var que = questions[n];
                    var arrItems = que.items;

                    output +=
                            '<br><br>' +
                            '<div class="panel panel-default">' +
                            '	<div class="panel panel-heading">' +
                            '	<h4><p>' + que.content + '</p></h4>'+
                            '	</div>'

                    for (i in arrItems) {
                        output +=
                                '<p>' + '<input type='+ que.type +' name="customRadio" class="custom-control-input">' +
                                '<label class="form-check-label">' +
                                '  '+arrItems[i].content +
                                '</label>' +
                                '</p>'
                    }
                    output += '<p>' + 'Explanation: ' + que.explanation + '</p>'
                    output +='</div>'
                }


                output += '<a href="http://localhost:8080/results" class="btn btn-default">Завершить тест</a>'

                $('#response').html(
                    output
                );
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };
    window.onload = RestGetAllQuestions;
</script>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>Прохождение теста</strong>
    </div>
    <div class="panel-body" id="response"></div>
<%--
    <div class="panel-body ">
        <button type="button" onclick="RestGetAllQuestions()">Try</button>
    </div>
--%>
</div>
</body>
<%@ include file = "footer.jsp" %>

