package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.dto.QuizTryItemDTOInterface;
import io.khasang.jrepetitor.entity.QuizTryItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizTryItemDTOImpl implements QuizTryItemDTOInterface {
    private Long id;

    private QuestionDTOInterface question;

    private List<ItemDTOInterface> items;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public QuestionDTOInterface getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(QuestionDTOInterface question) {
        this.question = question;
    }

    @Override
    public List<ItemDTOInterface> getItems() {
        return items;
    }

    @Override
    public void setItems(List<ItemDTOInterface> items) {
        this.items = items;
    }

    @Override
    public QuizTryItemDTOInterface getQuizTryItem(QuizTryItem quizTryItem) {
        QuizTryItemDTOInterface quizTryItemDTO = new QuizTryItemDTOImpl();
        quizTryItemDTO.setId(quizTryItem.getId());
        quizTryItemDTO.setQuestion(new QuestionDTOImpl().getQuestionDTO(quizTryItem.getQuestion()));
        quizTryItemDTO.setItems(new ItemDTOImpl().getItemDTOList(quizTryItem.getSelectedItems()));
        return quizTryItemDTO;
    }

    public List<QuizTryItemDTOInterface> getQuizTryItems(List<QuizTryItem> quizTryItems) {
        List<QuizTryItemDTOInterface> quizTryItemDTOs = new ArrayList<>();
        if (quizTryItems.size() == 0) {
            return quizTryItemDTOs;
        }
        for (QuizTryItem quizTryItem : quizTryItems) {
            quizTryItemDTOs.add(getQuizTryItem(quizTryItem));
        }
        return quizTryItemDTOs;
    }
}
