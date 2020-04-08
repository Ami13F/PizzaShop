package pizzashop.mock;

import pizzashop.model.MenuItem;
import pizzashop.repository.IMenuRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuRepositoryMock implements IMenuRepository {
    private List<MenuItem> listMenu;

    public MenuRepositoryMock() {
        listMenu = new ArrayList<>();
        MenuItem menuItem1 =new MenuItem("Hawaii",2,  30.0);
        MenuItem menuItem2 =new MenuItem("Hawaii",2,  30.0);
//        listMenu.add(menuItem1);
//        listMenu.add(menuItem2);
    }

    @Override
    public List<MenuItem> getMenu() {
        return null;
    }

    @Override
    public void addMenuItem(MenuItem item) {

    }

    @Override
    public void clear() {

    }
}
