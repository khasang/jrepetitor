package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;

import java.util.List;

public class QuestionWrapper {
    private String content;
    private String type;
    private String explanation;
    private List<ItemWrapper> items;

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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<ItemWrapper> getItems() {
        return items;
    }

    public void setItems(List<ItemWrapper> items) {
        this.items = items;
    }

    public Question getQuestion() {
        Question question = new Question();
        question.setContent(content);
        question.setType(type);
        question.setExplanation(explanation);
        if (items == null || items.isEmpty()) {
            return question;
        }

        for (ItemWrapper item : items) {
            Item currentItem = new Item();
            currentItem.setQuestion(question);
            question.addItem(currentItem);
        }
        return question;
    }
}
