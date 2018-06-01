package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDTOImpl implements ItemDTOInterface {
    private Long id;

    /**
     * Answer content
     */
    private String content;

    /**
     * 0 or 1: 0 -incorrect answer, 1 - correct answer
     */
    private byte correct;

    private Question question;

    ItemDTOImpl() {

    }

    private ItemDTOImpl(Item item) {
        id = item.getId();
        content = item.getContent();
        correct = item.getCorrect();
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
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public byte getCorrect() {
        return correct;
    }

    @Override
    public void setCorrect(byte correct) {
        this.correct = correct;
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public ItemDTOInterface getItemDTO(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemDTOImpl(item);
    }

    @Override
    public List<ItemDTOInterface> getItemDTOList(List<Item> list) {
        List<ItemDTOInterface> itemDTOList = new ArrayList<>();

        for (Item item : list) {
            ItemDTOImpl itemDTO = new ItemDTOImpl();
            itemDTO.setId(item.getId());
            itemDTO.setContent(item.getContent());
            itemDTO.setCorrect(item.getCorrect());

            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }
}