package io.khasang.jrepetitor.dto.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.dto.QuestionPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDTOImpl implements ItemDTOInterface {

    @JsonIgnore
    @Autowired
    protected QuestionPreviewDTOImpl questionPreviewDTO;

    private Long id;

    /**
     * Answer content
     */
    private String content;

    /**
     * 0 or 1: 0 -incorrect answer, 1 - correct answer
     */
    private byte correct;

    private QuestionPreviewDTOInterface question;

    ItemDTOImpl() {

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
    public QuestionPreviewDTOInterface getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(QuestionPreviewDTOInterface question) {
        this.question = question;
    }

    @Override
    public ItemDTOInterface getItemDTO(Item item) {
        if (item == null) {
            return null;
        }
        ItemDTOImpl itemDTO = new ItemDTOImpl();
        itemDTO.setId(item.getId());
        itemDTO.setContent(item.getContent());
        itemDTO.setCorrect(item.getCorrect());
        itemDTO.setQuestion(questionPreviewDTO.getQuestion(item.getQuestion()));
        return itemDTO;
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

    public Item getItem(ItemDTOInterface itemDTOInterface) {
        Item item = new Item();
        item.setId(itemDTOInterface.getId());
        item.setContent(itemDTOInterface.getContent());
        item.setCorrect(itemDTOInterface.getCorrect());
        return item;
    }

    public List<Item> getItems(List<ItemDTOInterface> itemDTOInterfaces) {
        List<Item> items = new ArrayList<>();
        if (itemDTOInterfaces.isEmpty()) {
            return items;
        }

        for (ItemDTOInterface itemDTOInterface : itemDTOInterfaces) {
            items.add(getItem(itemDTOInterface));
        }
        return items;
    }
}