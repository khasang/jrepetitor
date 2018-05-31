package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDTOImpl implements QuizDTOInterface {
    private Long id;

    private String name;

    private List<QuestionDTOInterface> questions;

    private GroupDTO group;

    /**
     * question level
     */
    private byte level;

    public QuizDTOImpl() {
        questions = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<QuestionDTOInterface> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestions(List<QuestionDTOInterface> questions) {
        this.questions = questions;
    }

    @Override
    public GroupDTO getGroup() {
        return group;
    }

    @Override
    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    @Override
    public byte getLevel() {
        return level;
    }

    @Override
    public void setLevel(byte level) {
        this.level = level;
    }

    @Override
    public List<QuizDTOInterface> getQuizDTOList(List<Quiz> list) {
        List<QuizDTOInterface> quizDTOInterfaces = new ArrayList<>();
        if (list.isEmpty()) {
            return quizDTOInterfaces;
        }
        for (Quiz quiz : list) {
            quizDTOInterfaces.add(getQuiz(quiz));
        }
        return quizDTOInterfaces;
    }

    @Override
    public QuizDTOInterface getQuiz(Quiz quiz) {
        if (quiz == null) {
            return null;
        }
        QuizDTOInterface quizDTOImpl = new QuizDTOImpl();
        quizDTOImpl.setId(quiz.getId());
        quizDTOImpl.setName(quiz.getName());
        quizDTOImpl.setLevel(quiz.getLevel());
        quizDTOImpl.setGroup(GroupDTO.getGroupDTO(quiz.getGroup()));
        quizDTOImpl.setQuestions(new QuestionDTO().getQuestionDTOList(quiz.getQuestions()));
        return quizDTOImpl;
    }
}
