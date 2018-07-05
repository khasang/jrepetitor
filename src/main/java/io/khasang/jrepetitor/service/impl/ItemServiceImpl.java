package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.ItemDao;
import io.khasang.jrepetitor.dao.QuestionDao;
import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.dto.impl.ItemDTOImpl;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import io.khasang.jrepetitor.model.wrappers.ItemByQuestionIdRequestWrapper;
import io.khasang.jrepetitor.model.wrappers.ItemWrapper;
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

    @Autowired
    private QuestionDao questionDao;

    @Override
    public ItemDTOInterface addItem(Item item) {
        return itemDTO.getItemDTO(itemDao.create(item));
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

    @Override
    public ItemDTOInterface addByQuestionId(ItemByQuestionIdRequestWrapper itemByQuestionIdRequestWrapper) {
        Question question = questionDao.getById(itemByQuestionIdRequestWrapper.getId());
        if (question == null) {
            return null;
        }
        ItemWrapper itemWrapper = itemByQuestionIdRequestWrapper.getItem();
        Item currentItem = itemWrapper.getItem();
        Item item = itemDao.create(currentItem);
        item.setQuestion(question);
        question.addItem(item);
        itemDao.update(item);
        questionDao.updateQuestion(question);
        return itemDTO.getItemDTO(item);
    }

}
