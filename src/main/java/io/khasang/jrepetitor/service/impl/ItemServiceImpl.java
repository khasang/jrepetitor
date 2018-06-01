package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ItemDao;
import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.dto.impl.ItemDTOImpl;
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
    private ItemDTOImpl itemDTO;

    @Override
    public Item addItem(Item item) {
        return itemDao.create(item);
    }

    @Override
    public List<ItemDTOInterface> getAllItems() {
        return itemDTO.getItemDTOList(itemDao.getList());
    }

    @Override
    public ItemDTOInterface getItemById(long id) {
        return itemDTO.getItemDTO(itemDao.getById(id));
    }

    @Override
    public ItemDTOInterface deleteItem(long id) {
        Item item = itemDao.getById(id);
        if (item == null) {
            return null;
        }
        return itemDTO.getItemDTO(itemDao.delete(item));
    }
}
