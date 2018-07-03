package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Item;

import java.util.List;

public interface ItemDTOInterface {

    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    byte getCorrect();

    void setCorrect(byte correct);

    List<ItemDTOInterface> getItemDTOList(List<Item> list);

    ItemDTOInterface getItemDTO(Item item);

    Item getItem(ItemDTOInterface itemDTOInterface);

    List<Item> getItems(List<ItemDTOInterface> itemDTOInterfaces);

    QuestionPreviewDTOInterface getQuestion();

    void setQuestion(QuestionPreviewDTOInterface question);

}
