package io.khasang.jrepetitor.util;

import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.Quiz;
import io.khasang.jrepetitor.entity.User;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtils {
    public static String ROOT = "http://localhost:8080";

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

    public static User prefillUser(String login, String name, String pass, String middleName, String surname,
                                   String email, String phoneNumber, String role) {
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setPassword(pass);
        user.setRoleName(role);

        Profile profile = new Profile();
        profile.setName(name);
        profile.setMiddlename(middleName);
        profile.setSurname(surname);
        profile.setEmail(email);
        profile.setPhoneNumber(phoneNumber);

        user.setProfile(profile);
        return user;
    }

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

    public static void createTestQuiz() {
        String group = "{\n" +
                "\t\"name\":\"group_name_1\"\n" +
                "}";

        String quiz = "{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"quiz\":{\n" +
                "\t\t\"name\":\"name_1\",\n" +
                "\t\t\"level\":\"1\"\n" +
                "\t}\n" +
                "}";

        List<String> questions = new ArrayList<>();

        questions.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"question\":{\n" +
                "\t\t\"content\":\"text_question_1\",\n" +
                "\t\t\"type\":\"Checkbox\",\n" +
                "\t\t\"explanation\":\"explanation_question_1\"\n" +
                "\t}\n" +
                "}"
        );
        questions.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"question\":{\n" +
                "\t\t\"content\":\"text_question_2\",\n" +
                "\t\t\"type\":\"Checkbox\",\n" +
                "\t\t\"explanation\":\"explanation_question_2\"\n" +
                "\t}\n" +
                "}"
        );

        questions.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"question\":{\n" +
                "\t\t\"content\":\"text_question_3\",\n" +
                "\t\t\"type\":\"Checkbox\",\n" +
                "\t\t\"explanation\":\"explanation_question_3\"\n" +
                "\t}\n" +
                "}"
        );
        questions.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"question\":{\n" +
                "\t\t\"content\":\"text_question_4\",\n" +
                "\t\t\"type\":\"Checkbox\",\n" +
                "\t\t\"explanation\":\"explanation_question_4\"\n" +
                "\t}\n" +
                "}"
        );

        List<String> items = new ArrayList<>();

        items.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer1 question1 correct\",\n" +
                "\t\t\"correct\":\"1\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer2 question1\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer3 question1\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"1\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer4 question1\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"2\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer1 question2 correct\",\n" +
                "\t\t\"correct\":\"1\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"2\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer2 question2\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"2\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer3 question2\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"2\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer4 question2\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );

        items.add("{\n" +
                "\t\"id\":\"3\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer1 question3 correct\",\n" +
                "\t\t\"correct\":\"1\"\n" +
                "\t}\n" +
                "}"
        );
        items.add("{\n" +
                "\t\"id\":\"3\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer2 question3\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );
        items.add("{\n" +
                "\t\"id\":\"3\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer3 question3\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );
        items.add("{\n" +
                "\t\"id\":\"3\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer4 question3\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );
        items.add("{\n" +
                "\t\"id\":\"4\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer1 question4 correct\",\n" +
                "\t\t\"correct\":\"1\"\n" +
                "\t}\n" +
                "}"
        );
        items.add("{\n" +
                "\t\"id\":\"4\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer2 question4\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}");
        items.add("{\n" +
                "\t\"id\":\"4\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer3 question4\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );
        items.add("{\n" +
                "\t\"id\":\"4\",\n" +
                "\t\"item\":{\n" +
                "\t\t\"content\":\"answer4 question4\",\n" +
                "\t\t\"correct\":\"0\"\n" +
                "\t}\n" +
                "}"
        );


        String groupUrl = ROOT + "/group/add";
        String quizByGroupID = ROOT + "/quiz/add_by_group_id";
        String questionByQuizID = ROOT + "/question/add_by_quiz_id";
        String itemByQuestionID = ROOT + "/item/add_by_question_id";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(group, headers);

        restTemplate.exchange(
                groupUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        entity = new HttpEntity<>(quiz, headers);

        restTemplate.exchange(
                quizByGroupID,
                HttpMethod.POST,
                entity,
                String.class
        );

        for (String question : questions) {
            HttpEntity<String> questionEntity = new HttpEntity<>(question, headers);
            restTemplate.exchange(
                    questionByQuizID,
                    HttpMethod.POST,
                    questionEntity,
                    String.class
            );
        }

        for (String item : items) {
            HttpEntity<String> itemEntity = new HttpEntity<>(item, headers);
            restTemplate.exchange(
                    itemByQuestionID,
                    HttpMethod.POST,
                    itemEntity,
                    String.class
            );
        }
    }

    public static Quiz removeTestQuiz(Long id, String adminName, String adminPassword) {
        String sessionId = formAuth(adminName, adminPassword);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(
                ROOT + "/quiz/delete/" + "?id=" + "{id}",
                HttpMethod.DELETE,
                requestEntity,
                Quiz.class,
                id
        );
        return (Quiz) responseEntity.getBody();
    }

}
