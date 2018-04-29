package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ItemRightAnsDao;
import io.khasang.jrepetitor.entity.ItemRightAns;
import io.khasang.jrepetitor.service.ItemRightAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemRightAnsService")
public class ItemRightAnsServiceImpl implements ItemRightAnsService {
    @Autowired
    private ItemRightAnsDao itemRightAnsDao;

    @Override
    public ItemRightAns addItemRightAns(ItemRightAns itemRightAns) {
        return itemRightAnsDao.create(itemRightAns);
    }

    @Override
    public List<ItemRightAns> getAllItemRightAns() {
        return itemRightAnsDao.getList();
    }

    @Override
    public ItemRightAns getItemRightAnsById(long id) {
        return itemRightAnsDao.getById(id);
    }
}
