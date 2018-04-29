INSERT INTO public.jr_group (id, name) VALUES (1, 'Java Core');
INSERT INTO public.jr_quiz (id, level, name, group_id) VALUES (1, 1, 'Java Basics', 1);
INSERT INTO public.jr_quiz (id, level, name, group_id) VALUES (2, 1, 'Khasang', 1);
INSERT INTO public.jr_question (id, content, explanation, type, quiz_id) VALUES (1, 'Which of the following are features of Java?', 'It is not required for a class to have a main method. The main method is required only if you want to execute that class directly from a command line.
Further, running from command line is not the only way to test a class.', 'radio', 1);
INSERT INTO public.jr_question (id, content, explanation, type, quiz_id) VALUES (2, 'Given the following contents of two java source files:  package util.log4j; public class Logger  {   public void log(String msg){       System.out.println(msg);   } }  and  package util; public class TestClass {     public static void main(String[] args) throws Exception {         Logger logger = new Logger();         logger.log("hello");     } }  What changes, when made independently, will enable the code to compile and run?', 'If you are not importing a class or the package of the class, you need to use the class''s fully qualified name while using it. Here, you need to use util.log4j.Logger instead of just log4j.Logger: util.log4j.Logger logger = new util.log4j.Logger();', 'CheckBox', 1);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (1, 'Every class must have a main method so that it can be tested individually from command line.', 0, 1);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (2, 'Every class belongs to a package.', 0, 1);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (3, 'A package must have more than one class.', 0, 1);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (4, 'A class may inherit from another class.', 1, 1);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (5, 'Replace Logger logger = new Logger(); with: log4j.Logger logger = new log4j.Logger();', 0, 2);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (6, 'Replace package util.log4j; with package util;', 1, 2);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (7, 'Replace Logger logger = new Logger(); with: util.log4j.Logger logger = new util.log4j.Logger();', 1, 2);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (8, 'Remove package util.log4j; from Logger.', 0, 2);
INSERT INTO public.jr_item (id, content, correct, question_id) VALUES (9, 'Add import log4j; to TestClass.', 0, 2);
