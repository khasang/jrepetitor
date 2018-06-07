package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.QuizDao;
import io.khasang.jrepetitor.dao.QuizTryDao;
import io.khasang.jrepetitor.dao.QuizTryItemDao;
import io.khasang.jrepetitor.dao.UserDao;
import io.khasang.jrepetitor.dto.QuizTryDTOInterface;
import io.khasang.jrepetitor.dto.impl.QuizTryDTOImpl;
import io.khasang.jrepetitor.entity.*;
import io.khasang.jrepetitor.model.QuestionAnswerWrapper;
import io.khasang.jrepetitor.model.SelectedItemWrapper;
import io.khasang.jrepetitor.model.UserTryWrapper;
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
    public QuizTry createTry(UserTryWrapper userTryWrapper, String userLogin) {
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

        User user = userDao.getUserByLogin(userLogin);
        quizTry.setUser(user);

        for (QuestionAnswerWrapper answer : answers) {
            QuizTryItem quizTryItem = new QuizTryItem();
            quizTryItem.setQuestion(questionMap.get(answer.getQuestionId()));
            for (SelectedItemWrapper selectedItem : answer.getSelectedItemWrappers()) {
                quizTryItem.addSelectedItem(itemsMap.get(selectedItem.getSelectedItemId()));
            }
            quizTry.addQuizTryItem(quizTryItem);
        }
        QuizTry quizTryUpdated = quizTryDao.create(quizTry);
        return quizTryUpdated;
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

}
