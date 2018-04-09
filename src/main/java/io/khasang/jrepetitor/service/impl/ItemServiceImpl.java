package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ItemDao;
import io.khasang.jrepetitor.dto.ItemDTO;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemDTO itemDTO;

    @Override
    public Item addItem(Item item) {
        return itemDao.create(item);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemDTO.getItemDTOList(itemDao.getList());
    }

    @Override
    public Item getItemById(long id) {
        return itemDao.getById(id);
    }

    @Override
    public Item deleteItem(long id) {
        return itemDao.delete(getItemById(id));
    }
}
