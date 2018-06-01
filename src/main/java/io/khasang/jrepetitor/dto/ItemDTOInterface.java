package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;

import java.util.List;

public interface ItemDTOInterface {

    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    byte getCorrect();

    void setCorrect(byte correct);

    Question getQuestion();

    void setQuestion(Question question);

    List<ItemDTOInterface> getItemDTOList(List<Item> list);

    ItemDTOInterface getItemDTO(Item item);

}
