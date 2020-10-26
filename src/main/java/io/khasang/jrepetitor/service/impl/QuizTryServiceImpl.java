package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.dao.QuizTryDao;
import io.khasang.jrepetitor.dao.QuizTryItemDao;
import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.dto.QuizTryDTOInterface;
import io.khasang.jrepetitor.dto.impl.QuizTryDTOImpl;
import io.khasang.jrepetitor.entity.*;
import io.khasang.jrepetitor.model.wrappers.QuestionAnswerWrapper;
import io.khasang.jrepetitor.model.wrappers.SelectedItemWrapper;
import io.khasang.jrepetitor.model.wrappers.UserTryWrapper;
import io.khasang.jrepetitor.service.QuizTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("quizTryService")
public class QuizTryServiceImpl implements QuizTryService {
    @Autowired
    QuizTryDao quizTryDao;

    @Autowired
    QuizTryItemDao quizTryItemDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    UserDao userDao;

    @Autowired
    QuizTryDTOImpl quizTryDTO;

    @Override
    public QuizTryDTOInterface createTry(UserTryWrapper userTryWrapper, String userLogin) {
        Quiz quiz = quizDao.getById(userTryWrapper.getQuizId());
        List<Question> questions = quiz.getQuestions();
        List<QuestionAnswerWrapper> answers = userTryWrapper.getQuestionAnswerWrappers();

        if (!isAnswersListCorrect(questions, answers)) {
            return null;
        }

        Map<Long, Question> questionMap = new HashMap<>();
        Map<Long, Item> itemsMap = new HashMap<>();
        for (Question question : questions) {
            questionMap.put(question.getId(), question);
            for (Item item : question.getItems()) {
                itemsMap.put(item.getId(), item);
            }
        }

        QuizTry quizTry = new QuizTry();
        quizTry.setQuiz(quiz);
        quizTry.setQuestionsCount(questions.size());
        User user = userDao.getUserByLogin(userLogin);
        quizTry.setUser(user);

        int rightAnswerCount = 0;
        int incorrectAnswerCount = 0;

        for (QuestionAnswerWrapper answer : answers) {
            QuizTryItem quizTryItem = new QuizTryItem();
            if (!isAnswerCorrect(questionMap.get(answer.getQuestionId()), answer)) {
                incorrectAnswerCount++;
                quizTryItem.setAnswerIsCorrect((byte) 0);
            } else {
                rightAnswerCount++;
                quizTryItem.setAnswerIsCorrect((byte) 1);
            }
            quizTryItem.setQuestion(questionMap.get(answer.getQuestionId()));
            for (SelectedItemWrapper selectedItem : answer.getSelectedItemWrappers()) {
                quizTryItem.addSelectedItem(itemsMap.get(selectedItem.getSelectedItemId()));
            }
            quizTry.addQuizTryItem(quizTryItem);
            quizTryItem.setQuizTry(quizTry);
        }
        Date date = new Date();
        quizTry.setTimestamp(date);

        quizTry.setIncorrectAnswerCount(incorrectAnswerCount);
        quizTry.setRightAnswerCount(rightAnswerCount);

        QuizTry quizTryUpdated = quizTryDao.create(quizTry);
        return quizTryDTO.getQuizTryDTO(quizTryUpdated);
    }

    @Override
    public List<QuizTryDTOInterface> getAll() {
        return quizTryDTO.getQuizTryDTOList(quizTryDao.getList());
    }

    @Override
    public QuizTryDTOInterface getById(Long id) {
        return quizTryDTO.getQuizTryDTO(quizTryDao.getById(id));
    }

    @Override
    public QuizTryDTOInterface deleteById(long Id) {
        QuizTry quizTry = quizTryDao.getById(Id);
        QuizTry deletedTry = quizTryDao.delete(quizTry);
        return quizTryDTO.getQuizTryDTO(deletedTry);
    }

    @Override
    public List<QuizTryDTOInterface> getMyTries(String login) {
        User user = userDao.getUserByLogin(login);
        return quizTryDTO.getQuizTryDTOList(user.getUserTries());

    }

    private Boolean isAnswersListCorrect(List<Question> questions, List<QuestionAnswerWrapper> answers) {
        if (questions.size() != answers.size()) {
            return false;
        }
        List<Long> questionId = new ArrayList<>();
        for (Question question : questions) {
            questionId.add(question.getId());
        }
        List<Long> answerId = new ArrayList<>();
        for (QuestionAnswerWrapper answer : answers) {
            answerId.add(answer.getQuestionId());
        }

        Map<Long, Question> questionMap = new HashMap<>();

        Map<Long, QuestionAnswerWrapper> answerMap = new HashMap<>();

        for (Question question : questions) {
            questionMap.put(question.getId(), question);
        }

        for (QuestionAnswerWrapper answer : answers) {
            answerMap.put(answer.getQuestionId(), answer);
        }

        Set<Long> keys = questionMap.keySet();

        for (Long key : keys) {
            if (!isSelectedItemsCorrect(answerMap.get(key), questionMap.get(key))) {
                return false;
            }
        }
        return true;
    }

    private Boolean isSelectedItemsCorrect(QuestionAnswerWrapper answer, Question question) {
        List<Item> questionItems = question.getItems();
        List<SelectedItemWrapper> selectedItems = answer.getSelectedItemWrappers();
        List<Long> itemsId = new ArrayList<>();
        for (Item questionItem : questionItems) {
            itemsId.add(questionItem.getId());
        }
        List<Long> selectedItemsId = new ArrayList<>();
        for (SelectedItemWrapper selectedItem : selectedItems) {
            selectedItemsId.add(selectedItem.getSelectedItemId());
        }
        return itemsId.containsAll(selectedItemsId);
    }

    private Boolean isAnswerCorrect(Question question, QuestionAnswerWrapper questionAnswerWrapper) {
        HashMap<Long, Item> correctItems = new HashMap<>();
        List<Item> allItems = question.getItems();
        for (Item allItem : allItems) {
            if (allItem.getCorrect() == (byte) 1) {
                correctItems.put(allItem.getId(), allItem);
            }
        }

        for (SelectedItemWrapper selectedItemWrapper : questionAnswerWrapper.getSelectedItemWrappers()) {
            if (!correctItems.containsKey(selectedItemWrapper.getSelectedItemId())) {
                return false;
            }
        }
        return true;
    }


}
