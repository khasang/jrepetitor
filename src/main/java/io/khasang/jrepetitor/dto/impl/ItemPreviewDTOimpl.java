package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.ItemPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemPreviewDTOimpl implements ItemPreviewDTOInterface {

    private Long id;

    private String content;

    private byte correct;

    public ItemPreviewDTOimpl() {
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
    public ItemPreviewDTOInterface getItemDTO(Item item) {
        ItemPreviewDTOInterface itemPreview = new ItemPreviewDTOimpl();
        itemPreview.setContent(item.getContent());
        itemPreview.setCorrect(item.getCorrect());
        itemPreview.setId(item.getId());
        return itemPreview;
    }

    @Override
    public List<ItemPreviewDTOInterface> getItemDTOList(List<Item> items) {
        List<ItemPreviewDTOInterface> itemPreviewDTO = new ArrayList<>();
        if (items.isEmpty()) {
            return itemPreviewDTO;
        }
        for (Item item : items) {
            itemPreviewDTO.add(getItemDTO(item));
        }
        return itemPreviewDTO;
    }
}
