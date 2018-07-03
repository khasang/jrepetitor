package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Item;

import java.util.List;

public interface ItemPreviewDTOInterface {
    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    byte getCorrect();

    void setCorrect(byte correct);

    ItemPreviewDTOInterface getItemDTO(Item item);

    List<ItemPreviewDTOInterface> getItemDTOList(List<Item> items);

}
