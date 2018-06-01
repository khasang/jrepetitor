package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizPreviewDTOImpl implements QuizPreviewDTOInterface {
    private Long id;

    private String name;

    private Byte level;

    public QuizPreviewDTOImpl() {
    }

    QuizPreviewDTOImpl(Quiz quiz) {
        this.id = quiz.getId();
        this.name = quiz.getName();
        this.level = quiz.getLevel();
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
    public Byte getLevel() {
        return level;
    }

    @Override
    public void setLevel(Byte level) {
        this.level = level;
    }

    @Override
    public List<QuizPreviewDTOInterface> getListQuizPreviewDTO(List<Quiz> quizzes) {
        List<QuizPreviewDTOInterface> listQuizPreviewDto = new ArrayList<>();
        if (quizzes.isEmpty()) {
            return listQuizPreviewDto;
        }
        for (Quiz quiz : quizzes) {
            listQuizPreviewDto.add(new QuizPreviewDTOImpl(quiz));
        }
        return listQuizPreviewDto;
    }

    @Override
    public QuizPreviewDTOInterface getPreviewDTO(Quiz quiz) {
        if (quiz == null) {
            return null;
        } else {
            return new QuizPreviewDTOImpl(quiz);
        }
    }

}
