package io.khasang.jrepetitor.model.wrappers;

public class ItemByQuestionIdRequestWrapper {
    private Long Id;

    private ItemWrapper item;

    public ItemWrapper getItem() {
        return item;
    }

    public void setItem(ItemWrapper item) {
        this.item = item;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
