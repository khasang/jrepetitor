package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.ItemDTO;
import io.khasang.jrepetitor.entity.Item;

import java.util.List;

public interface ItemService {
    /**
     * method for add Item
     *
     * @param Item = new Item for creation in DB
     * @return created Item
     */
    Item addItem(Item Item);

    /**
     * method for receiving all Items
     *
     * @return all Items
     */
    List<ItemDTO> getAllItems();

    /**
     * method for receive specify Item by id
     *
     * @param id = uniq Item id
     * @return specify Item by id
     */
    Item getItemById(long id);

    /**
     * method for Item delete
     *
     * @param id = Item's id for delete
     * @return removed Item
     */
    Item deleteItem(long id);
}
