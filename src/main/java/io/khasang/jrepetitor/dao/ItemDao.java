package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Item;

public interface ItemDao extends BasicDao<Item> {
    Item update(Item item);
}
