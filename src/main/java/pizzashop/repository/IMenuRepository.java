package pizzashop.repository;

import pizzashop.model.MenuItem;

import java.util.List;

public interface IMenuRepository {
    List<MenuItem> getMenu();
    void addMenuItem(MenuItem item);
}
