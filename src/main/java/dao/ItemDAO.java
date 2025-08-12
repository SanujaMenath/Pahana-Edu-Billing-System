package dao;

import com.pahanaedu.model.Item;
import java.util.List;

public interface ItemDAO {
    void addItem(Item item) throws Exception;
    List<Item> getAllItems() throws Exception;
    Item getItemById(int id) throws Exception;
    void updateItem(Item item) throws Exception;
    void deleteItem(int id) throws Exception;
}
