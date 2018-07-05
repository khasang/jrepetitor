package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.Item;

public class ItemWrapper {
    private String content;
    private Byte correct;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getCorrect() {
        return correct;
    }

    public void setCorrect(Byte correct) {
        this.correct = correct;
    }

    public Item getItem() {
        Item item = new Item();
        item.setContent(content);
        item.setCorrect(correct);
        return item;
    }
}
