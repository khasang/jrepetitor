package io.khasang.jrepetitor.dao.impl;

import io.khasang.jrepetitor.dao.ItemDao;
import io.khasang.jrepetitor.entity.Item;

public class ItemDaoImpl extends BasicDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl(Class<Item> entityClass) {
        super(entityClass);
    }
}