package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.ItemDTOInterface;
import io.khasang.jrepetitor.entity.Item;
import io.khasang.jrepetitor.model.ItemByQuestionIdResponseBody;

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
    List<ItemDTOInterface> getAllItems();

    /**
     * method for receive specify Item by id
     *
     * @param id = uniq Item id
     * @return specify Item by id
     */
    ItemDTOInterface getItemById(long id);

    /**
     * method for Item delete
     *
     * @param id = Item's id for delete
     * @return removed Item
     */
    ItemDTOInterface deleteItem(long id);

    /**
     * @param itemByQuestionIdResponseBody wrapper includes question id and item structure;
     * @return created item
     */

    ItemDTOInterface addByQuestionId(ItemByQuestionIdResponseBody itemByQuestionIdResponseBody);
}
