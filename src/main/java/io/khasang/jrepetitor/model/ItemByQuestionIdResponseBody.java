package io.khasang.jrepetitor.model;

import io.khasang.jrepetitor.entity.Item;

public class ItemByQuestionIdResponseBody {
    private Long Id;

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
