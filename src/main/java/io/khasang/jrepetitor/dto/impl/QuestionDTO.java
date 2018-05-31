package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.QuestionDTOInterface;
import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDTO implements QuestionDTOInterface {

    private static final Logger log = LoggerFactory.getLogger(QuestionDTO.class);

    private Long id;

    /**
     * question content
     */
    private String content;
    /**
     * question type: radio/CheckBox
     */
    private String type;

    /**
     * Answer variants
     */
    private List<ItemDTO> items = new ArrayList<ItemDTO>();

    private QuizDTOInterface quiz;

    /**
     *
     */
    private String explanation;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<ItemDTO> getItems() {
        return items;
    }

    @Override
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public QuizDTOInterface getQuiz() {
        return quiz;
    }

    @Override
    public void setQuiz(QuizDTOInterface quiz) {
        this.quiz = quiz;
    }

    @Override
    public String getExplanation() {
        return explanation;
    }

    @Override
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public List<QuestionDTOInterface> getQuestionDTOList(List<Question> list) {
        List<QuestionDTOInterface> questionDTOList = new ArrayList<>();
        try {
            for (Question question : list) {
                List<ItemDTO> itemDTOList = new ArrayList<>();

                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setContent(question.getContent());
                questionDTO.setType(question.getType());
                questionDTO.setExplanation(question.getExplanation());

                nextItem:
                for (Item item : question.getItems()) {
                    for (ItemDTO itemDTO : itemDTOList) {
                        if (itemDTO.getId() == item.getId())
                            continue nextItem;
                    }
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setContent(item.getContent());
                    itemDTO.setCorrect(item.getCorrect());

                    itemDTOList.add(itemDTO);
                }

                questionDTO.setItems(itemDTOList);
                questionDTOList.add(questionDTO);
            }
        } catch (Exception e) {
            log.info("QuestionDTO is Empty ");
        }
        return questionDTOList;
    }

    @Override
    public QuestionDTOInterface getQuestionDTO(Question question) {
        return null;
    }
}
