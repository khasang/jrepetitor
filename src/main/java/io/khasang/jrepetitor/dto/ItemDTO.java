package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDTO {
    private Long id;

    //Answer content
    private String content;
    // 0 or 1: 0 -incorrect answer, 1 - correct answer
    private byte correct;

    private Question question;

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

    public byte getCorrect() {
        return correct;
    }

    public void setCorrect(byte correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<ItemDTO> getItemDTOList(List<Item> list)
    {
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (Item item : list)
        {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setContent(item.getContent());
            itemDTO.setCorrect(item.getCorrect());

            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }
}