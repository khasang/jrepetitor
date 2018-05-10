<%@ include file = "header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    var service = 'http://localhost:8080/';
    arrCorrect = [];

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
                    output +='<form>';

                    for (i in arrItems) {
                        output +=
                            '<p>' + '<input type='+ que.type +' id='+arrItems[i].id+' name="customRadio" class="custom-control-input">' +
                            '<label class="form-check-label">' +
                            '  '+arrItems[i].content +
                            '</label>' +
                            '</p>';
                        /*если правильный ответ, добавляем в массив ID правильного ответа*/
                        if(arrItems[i].correct == 1){
                            arrCorrect.push(arrItems[i].id);
                        }

                    }
                    output +='</form>';
                    output += '<p>' + 'Explanation: ' + que.explanation + '</p>'
                    output +='</div>'
                }

                output+='<div class="panel-body ">\n' +
                    '        <button type="button" onclick="ChekAnswer()">Проверить результат</button>\n' +
                    '    </div>'

                $('#response').html(
                    output
                );
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };

    var ChekAnswer = function () {
        var arrJsonRightAns = [];
        var cntAllRightAns = arrCorrect.length;
        var cntRightAns = 0;
        var procentRight = 0;
        // проходим по массиву правильных ответов
        for (i in arrCorrect){
            // если правильный ответ выбран, увеличиваем количество правильных ответов
            if($('#'+arrCorrect[i]).is(':checked')){
                cntRightAns+=1;
                arrJsonRightAns.push({"attemptId":1,"rightAnsId":arrCorrect[i]});
            }
        }
        procentRight = (cntRightAns/cntAllRightAns)*100;
        alert('Процент правильных ответов: '+procentRight);
        //вызываем функцию отправки JSON POST запросом на сервер
        RestPutRightAns(arrJsonRightAns);
    }

    var RestPutRightAns = function (arrJsonRightAns) {
    $.ajax({
        type: 'POST',
        url: service + "rightans/add",
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify({rightAnsList:arrJsonRightAns}),
        dataType: 'json',
        async: false,
        success: function (result) {
            alert('Тест сохранен');
        },
        error: function (jqXHR, testStatus, errorThrown) {
            alert('Ошибка сохранения теста');
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
</div>
</body>
<%@ include file = "footer.jsp" %>
