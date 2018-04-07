<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    </head>

    <script>
        const postData =
        {
            "id":1,
            "name":"Java Basics",
            "questions":[
                {
                    "id":1,
                    "content":"Which of the following are features of Java?",
                    "type":"radio",
                    "items":[
                        {
                            "id":1,
                            "content":"Every class must have a main method so that it can be tested individually from command line.",
                            "correct":0,
                            "question":1
                        },
                        {
                            "id":2,
                            "content":"Every class belongs to a package.",
                            "correct":0,
                            "question":1
                        },
                        {
                            "id":3,
                            "content":"A package must have more than one class.",
                            "correct":0,
                            "question":1
                        },
                        {
                            "id":4,
                            "content":"A class may inherit from another class.",
                            "correct":1,
                            "question":1
                        }
                    ],
                    "quiz":1,
                    "explanation":"It is not required for a class to have a main method. The main method is required only if you want to execute that class directly from a command line.\nFurther, running from command line is not the only way to test a class."
                },
                {
                    "id":2,
                    "content":"Given the following contents of two java source files:  package util.log4j; public class Logger  {   public void log(String msg){       System.out.println(msg);   } }  and  package util; public class TestClass {     public static void main(String[] args) throws Exception {         Logger logger = new Logger();         logger.log(\"hello\");     } }  What changes, when made independently, will enable the code to compile and run?",
                    "type":"checkbox",
                    "items":[
                        {
                            "id":5,
                            "content":"Replace Logger logger = new Logger(); with: log4j.Logger logger = new log4j.Logger();",
                            "correct":0,
                            "question":2
                        },
                        {
                            "id":6,
                            "content":"Replace package util.log4j; with package util;",
                            "correct":1,
                            "question":2
                        },
                        {
                            "id":7,
                            "content":"Replace Logger logger = new Logger(); with: util.log4j.Logger logger = new util.log4j.Logger();",
                            "correct":1,
                            "question":2
                        },
                        {
                            "id":8,
                            "content":"Remove package util.log4j; from Logger.",
                            "correct":0,
                            "question":2
                        },
                        {
                            "id":9,
                            "content":"Add import log4j; to TestClass.",
                            "correct":0,
                            "question":2
                        }
                    ],
                    "quiz":1,
                    "explanation":"If you are not importing a class or the package of the class, you need to use the class's fully qualified name while using it. Here, you need to use util.log4j.Logger instead of just log4j.Logger: util.log4j.Logger logger = new util.log4j.Logger();"
                }
            ],
            "group":{
                "id":1,
                "quizes":[
                    1
                ],
                "name":"Java Core"
            },
            "level":1
        };

        function myFunction(){
            alert("Done");
        }

        function questionList(que){
            var divQuest=`
      <form class="question">
         <p><b>${que.content}</b></p>

   `
            var itemsHtml;
            var itemsCount=que.items.length;

            for (i=0;i<itemsCount;i++){
                itemsHtml+=`<p><input name=${que.id} type=${que.type}>${que.items[i].content}</p>`
            }
            itemsHtml+=`<input type="button" id="myBtn" onclick="myFunction()" value="Try it">`
            itemsHtml+=`</form>`;

            return divQuest+`<br>`+itemsHtml;
        }

        document.getElementById('app').innerHTML =
                `
<h1>${
         postData.questions.
            map(questionList).join('')}</h1>
`
    </script>

<body>
    <div id="app"></div>
</body>

</html>
