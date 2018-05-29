package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizPreviewDTO {
    private Long id;

    private String name;

    private Byte level;

    private QuizPreviewDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.name = quiz.getName();
        this.level = quiz.getLevel();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Byte getLevel() {
        return level;
    }

    public static List<QuizPreviewDTO> getListQuizPreviewDTO(List<Quiz> quizzes) {
        List<QuizPreviewDTO> listQuizPreviewDto = new ArrayList<>();
        if (quizzes.isEmpty()) {
            return listQuizPreviewDto;
        }
        for (Quiz quiz : quizzes) {
            listQuizPreviewDto.add(new QuizPreviewDTO(quiz));
        }
        return listQuizPreviewDto;
    }

    public static QuizPreviewDTO getPreviewDTO(Quiz quiz) {
        if (quiz == null) {
            return null;
        } else {
            return new QuizPreviewDTO(quiz);
        }
    }


}
