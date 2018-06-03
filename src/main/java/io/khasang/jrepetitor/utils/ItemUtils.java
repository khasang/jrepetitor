package io.khasang.jrepetitor.utils;

import io.khasang.jrepetitor.dao.ItemDao;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemUtils {
    @Autowired
    ItemDao itemDao;

    public Item createItem(Byte correct, String content) {
        Item item = new Item();
        item.setContent(content);
        item.setCorrect(correct);
        itemDao.create(item);
        return item;
    }

    public Item updateItem(Item item) {
        return itemDao.update(item);
    }

    public void updateItems(List<Item> items) {
        for (Item item : items) {
            itemDao.update(item);
        }
    }

    public void addQuestion(List<Item> items, Question question) {
        for (Item item : items) {
            item.setQuestion(question);
            itemDao.update(item);
        }
    }
}
