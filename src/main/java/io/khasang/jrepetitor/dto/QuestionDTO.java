package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.model.CreateTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDTO {

    private static final Logger log = LoggerFactory.getLogger(QuestionDTO.class);

    private Long id;

    /**
     * question content
     */
    private String content;
    /**
     *question type: radio/CheckBox
     */
    private String type;

    /**
     * Answer variants
     */
    private List<ItemDTO> items = new ArrayList<ItemDTO>();

    private QuizDTO quiz;

    /**
     *
     */
    private String explanation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public QuizDTO getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizDTO quiz) {
        this.quiz = quiz;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<QuestionDTO> getQuestionDTOList(List<Question> list) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
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
        }
        catch (Exception e){
            log.info("QuestionDTO is Empty ");
        }
        return questionDTOList;
    }
}
