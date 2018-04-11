<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    </head>

    <script>
        var service = 'http://localhost:8080/quiz';
        var RestGetOne = function () {

            var output;

            $.ajax({
                type: 'GET',
                url: service + '/get/1',
                dataType: 'json',
                async: false,
                success: function (result) {
                    var stringData=JSON.stringify(result);
                    var getData=JSON.parse(stringData);
                    var questions=getData.questions;
                    questionsCount=questions.length;

                    for (i=0;i<questionsCount;i++){
                        output+='<br>';

                        output='<form class="question">';
                        output+='<p><b>'+questions[i].content+'</b></p>';

                        var items = questions[i].items;
                        var itemsCount = items.length;

                        for (j=0;j<itemsCount;j++){
                            output+='<p><input name='+items[j].id+' type=' + items[i].type+'>'+items[j].content+'</p>';
                        }

                        output+='<br>';
                    }

                    $('#response').html(output)
                },
                error: function (jqXHR, testStatus, errorThrown) {
                    $('#response').html(JSON.stringify(jqXHR))
                }
            });
        };

     </script>

<body>

    <table class="table">
     <tr>
        <td>
            <button type="button" onclick="RestGetOne()">Try</button>
        </td>
    </tr>
    </table>

    <div class="panel-body" id="response"></div>
</body>

</html>
