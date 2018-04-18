package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.dao.impl.BasicDaoImpl;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDTO {

    private Long id;

    private String content; //текст вопроса
    private String type; //тип вопроса: "radio/CheckBoz"

    private List<ItemDTO> items = new ArrayList<ItemDTO>(); //ответ

    private QuizDTO quiz;

    private String explanation; //объясенение правильного ответа

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
                question= BasicDaoImpl.initializeAndUnproxy(question);

                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setContent(question.getContent());
                questionDTO.setType(question.getType());
                questionDTO.setExplanation(question.getExplanation());

//                nextItem:
                for (Item item : question.getItems()) {
//                    for (ItemDTO itemDTO : itemDTOList) {
//                        if (itemDTO.getId() == item.getId())
//                            continue nextItem;
//                    }
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
            e.printStackTrace();
        }
        return questionDTOList;
    }
}
