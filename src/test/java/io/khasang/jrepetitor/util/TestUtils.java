package io.khasang.jrepetitor.util;

import io.khasang.jrepetitor.entity.*;
import io.khasang.jrepetitor.model.wrappers.*;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtils {
    private static final String ROOT = "http://localhost:8080";
    private static final String QUIZ = "/quiz";
    private static final String ADD = "/add";
    private static final String DELETE = "/delete";
    private static final String GROUP = "/group";
    private static final String QUESTION = "/question";
    private static final String ITEM = "/item";
    private static final String USER = "/users";
    private static final String GET_BY_ID = "/get";

    /**
     * Create user (SEND REST)
     * fields values:
     *
     * @param login       user login
     * @param name        user name
     * @param pass        user pass
     * @param middleName  user middleName
     * @param surname     user surname
     * @param email       user email
     * @param phoneNumber user phoneNumber
     * @param role        user role
     * @param root        ROOT path
     * @param add         ADD path
     * @return created User
     */
    public static User createUser(String login, String name, String pass, String middleName, String surname,
                                  String email, String phoneNumber, String role, String root, String add) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser(login, name, pass, middleName, surname, email, phoneNumber, role);

        HttpEntity entity = new HttpEntity(user, headers);

        RestTemplate template = new RestTemplate();

        User receivedUser = template.exchange(
                root + add,
                HttpMethod.POST,
                entity,
                User.class
        ).getBody();

        assertNotNull(receivedUser.getName());
        assertEquals(user.getName(), receivedUser.getName());

        return receivedUser;
    }

    /**
     * Pre-fill user entity
     * fields values:
     *
     * @param login       user login
     * @param name        user name
     * @param pass        user pass
     * @param middleName  user middleName
     * @param surname     user surname
     * @param email       user email
     * @param phoneNumber user phoneNumber
     * @param role        user role
     * @return pre-filled User structure
     */
    public static User prefillUser(String login, String name, String pass, String middleName, String surname,
                                   String email, String phoneNumber, String role) {
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setPassword(pass);
        user.setRoleName(role);

        Profile profile = new Profile();
        profile.setName(name);
        profile.setMiddleName(middleName);
        profile.setSurname(surname);
        profile.setEmail(email);
        profile.setPhoneNumber(phoneNumber);

        user.setProfile(profile);
        return user;
    }

    /**
     * Return session ID
     *
     * @param login    - user login
     * @param password - password login
     * @return session ID
     */
    public static String formAuth(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", login);
        map.add("password", password);
        map.add("submit", "Login");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        HttpHeaders httpHeaders = response.getHeaders();
        String cookie = httpHeaders.get("Set-Cookie").get(0);
        String[] string = cookie.split(";");
        String sessionId = string[0].split("=")[1];
        return sessionId;
    }

    /**
     * Delete quiz from DB if exist
     *
     * @param quiz input quiz
     * @return deleted Quiz
     */
    public static Quiz deleteFromDB(Quiz quiz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Quiz> responseEntity = restTemplate.exchange(
                ROOT + QUIZ + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Quiz.class,
                quiz.getId()
        );
        return responseEntity.getBody();
    }

    /**
     * Delete group from DB if exist
     *
     * @param group
     * @return deleted Group
     */
    public static Group deleteGroupFromDB(Group group) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Group> responseEntity = restTemplate.exchange(
                ROOT + GROUP + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Group.class,
                group.getId()
        );
        return responseEntity.getBody();
    }

    /**
     * Create Quiz in group
     *
     * @param quizByGroupIdRequestWrapper input wrapper
     * @return created Quiz
     */
    public static Quiz createQuiz(QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(quizByGroupIdRequestWrapper, headers);

        RestTemplate template = new RestTemplate();

        Quiz receivedQuiz = template.exchange(
                ROOT + QUIZ + ADD,
                HttpMethod.POST,
                entity,
                Quiz.class
        ).getBody();

        assertNotNull(receivedQuiz.getName());
        assertEquals(quizByGroupIdRequestWrapper.getQuiz().getName(), receivedQuiz.getName());
        return receivedQuiz;
    }

    /**
     * create and pre-fill QuizByGroupIdRequestWrapper
     *
     * @param id group id
     * @return pre-filed structure
     */
    public static QuizByGroupIdRequestWrapper prefillQuizStructure(Long id, String quizName, byte quizLevel) {
        QuizWrapper quiz = new QuizWrapper();
        quiz.setLevel(quizLevel);
        quiz.setName(quizName);
        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = new QuizByGroupIdRequestWrapper();
        quizByGroupIdRequestWrapper.setQuiz(quiz);
        quizByGroupIdRequestWrapper.setId((id));
        return quizByGroupIdRequestWrapper;
    }

    public static GroupWrapper prefillGroup(String groupName) {
        GroupWrapper groupWrapper = new GroupWrapper();
        groupWrapper.setName("group_name");
        return groupWrapper;
    }

    /**
     * @return created Group
     */
    public static Group createGroup(GroupWrapper groupWrapper) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(groupWrapper, headers);
        RestTemplate template = new RestTemplate();
        Group receivedGroup = template.exchange(
                ROOT + GROUP + ADD,
                HttpMethod.POST,
                entity,
                Group.class
        ).getBody();

        return receivedGroup;
    }

    /**
     * delete Question fromDB if exist
     *
     * @param question - question for deletion
     * @return deleted Question
     */
    public static Question deleteFromDB(Question question) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Question> responseEntity = restTemplate.exchange(
                ROOT + QUESTION + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Question.class,
                question.getId()
        );

        return responseEntity.getBody();
    }

    /**
     * Create question in quiz (SEND REST)
     *
     * @param questionByQuizIdRequestWrapper input wrapper
     * @return created question
     */
    public static Question createQuestion(QuestionByQuizIdRequestWrapper questionByQuizIdRequestWrapper) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(questionByQuizIdRequestWrapper, headers);
        RestTemplate template = new RestTemplate();
        QuestionWrapper question = questionByQuizIdRequestWrapper.getQuestion();

        Question receivedQuestion = template.exchange(
                ROOT + QUESTION + ADD,
                HttpMethod.POST,
                entity,
                Question.class
        ).getBody();

        assertNotNull(receivedQuestion.getContent());
        assertEquals(question.getContent(), receivedQuestion.getContent());

        return receivedQuestion;
    }

    /**
     * Pre-fill question entity
     *
     * @param type        - string type
     * @param content     - string content
     * @param explanation - string explanation
     * @return pre-filed question entity
     */
    public static QuestionByQuizIdRequestWrapper prefillQuestion(String type, String content, String explanation, Long quizId) {
        QuestionByQuizIdRequestWrapper questionByQuizIdRequestWrapper = new QuestionByQuizIdRequestWrapper();
        questionByQuizIdRequestWrapper.setId(quizId);
        QuestionWrapper questionWrapper = new QuestionWrapper();
        questionWrapper.setType(type);
        questionWrapper.setContent(content);
        questionWrapper.setExplanation(explanation);
        questionByQuizIdRequestWrapper.setQuestion(questionWrapper);
        return questionByQuizIdRequestWrapper;
    }

    /**
     * delete Item
     *
     * @param item for deletion
     * @return deleted Item
     */
    public static Item deleteFromDB(Item item) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Item> responseEntity = restTemplate.exchange(
                ROOT + ITEM + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Item.class,
                item.getId()
        );

        return responseEntity.getBody();
    }

    public static Item createItem(ItemByQuestionIdRequestWrapper itemByQuestionIdRequestWrapper) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(itemByQuestionIdRequestWrapper, headers);

        ItemWrapper item = itemByQuestionIdRequestWrapper.getItem();

        RestTemplate template = new RestTemplate();

        Item receivedItem = template.exchange(
                ROOT + ITEM + ADD,
                HttpMethod.POST,
                entity,
                Item.class
        ).getBody();

        assertNotNull(receivedItem.getContent());
        assertEquals(item.getContent(), receivedItem.getContent());

        return receivedItem;
    }

    public static ItemByQuestionIdRequestWrapper prefillItem(Long questionId, String content, byte correct) {
        ItemByQuestionIdRequestWrapper itemByQuestionIdRequestWrapper = new ItemByQuestionIdRequestWrapper();
        itemByQuestionIdRequestWrapper.setId(questionId);
        ItemWrapper itemWrapper = new ItemWrapper();
        itemWrapper.setContent(content);
        itemWrapper.setCorrect(correct);
        itemByQuestionIdRequestWrapper.setItem(itemWrapper);
        return itemByQuestionIdRequestWrapper;
    }

    public static Quiz createTestQuiz() {
        GroupWrapper groupWrapper = prefillGroup("group_name_1");
        Group group = createGroup(groupWrapper);

        QuizByGroupIdRequestWrapper quizByGroupIdRequestWrapper = prefillQuizStructure(
                group.getId(),
                "name_1",
                (byte) 1
        );

        Quiz quiz = createQuiz(quizByGroupIdRequestWrapper);

        QuestionByQuizIdRequestWrapper question1ByQuizIdRequestWrapper = prefillQuestion(
                "Radio",
                "question_content_1",
                "question_explanation_1",
                quiz.getId()
        );

        QuestionByQuizIdRequestWrapper question2ByQuizIdRequestWrapper = prefillQuestion(
                "Radio",
                "question_content_2",
                "question_explanation_2",
                quiz.getId()
        );

        QuestionByQuizIdRequestWrapper question3ByQuizIdRequestWrapper = prefillQuestion(
                "Radio",
                "question_content_3",
                "question_explanation_3",
                quiz.getId()
        );

        QuestionByQuizIdRequestWrapper question4ByQuizIdRequestWrapper = prefillQuestion(
                "Radio",
                "question_content_4",
                "question_explanation_4",
                quiz.getId()
        );

        Question question1 = createQuestion(question1ByQuizIdRequestWrapper);
        Question question2 = createQuestion(question2ByQuizIdRequestWrapper);
        Question question3 = createQuestion(question3ByQuizIdRequestWrapper);
        Question question4 = createQuestion(question4ByQuizIdRequestWrapper);

        //question1 items
        createItem(prefillItem(
                question1.getId(),
                "answer1_question1",
                (byte) 1
                )
        );

        createItem(prefillItem(
                question1.getId(),
                "answer2_question1",
                (byte) 0)
        );
        createItem(prefillItem(
                question1.getId(),
                "answer3_question1",
                (byte) 0)
        );
        createItem(prefillItem(
                question1.getId(),
                "answer4_question1",
                (byte) 0)
        );

        //question2 items
        createItem(prefillItem(
                question2.getId(),
                "answer1_question2",
                (byte) 1
                )
        );

        createItem(prefillItem(
                question2.getId(),
                "answer2_question2",
                (byte) 0
                )
        );

        createItem(prefillItem(
                question2.getId(),
                "answer3_question2",
                (byte) 0
                )
        );

        createItem(prefillItem(
                question2.getId(),
                "answer4_question2",
                (byte) 0
                )
        );

        //// question 3 items
        createItem(prefillItem(
                question3.getId(),
                "answer1_question3",
                (byte) 1
                )
        );

        createItem(prefillItem(
                question3.getId(),
                "answer2_question3",
                (byte) 0
                )
        );

        createItem(prefillItem(
                question3.getId(),
                "answer3_question3",
                (byte) 0
                )
        );

        createItem(prefillItem(
                question3.getId(),
                "answer4_question3",
                (byte) 0
                )
        );

        //question4 items

        createItem(prefillItem(
                question4.getId(),
                "answer1_question4",
                (byte) 1
                )
        );

        createItem(prefillItem(
                question4.getId(),
                "answer2_question4",
                (byte) 0
                )
        );

        createItem(prefillItem(
                question4.getId(),
                "answer3_question4",
                (byte) 0
                )
        );

        createItem(prefillItem(
                question4.getId(),
                "answer4_question4",
                (byte) 0
                )
        );

        RestTemplate template = new RestTemplate();
        ResponseEntity<Quiz> responseEntity = template.exchange(
                ROOT + QUIZ + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Quiz.class,
                quiz.getId()
        );

        return responseEntity.getBody();
    }

    public static void removeUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + USER + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                User.class,
                id
        );
    }
}
