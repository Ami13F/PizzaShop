package pizzashop.repository;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.MenuItem;

import static org.junit.jupiter.api.Assertions.*;

class MenuRepositoryTest {
    public IMenuRepository repo;
    MenuItem itemMock = Mockito.mock(MenuItem.class);

    @BeforeEach
    void setUp() {
        repo = new MenuRepository("filename.txt");
    }

    @Test
    void getMenu_1() {
        Assertions.assertTrue(repo.getMenu().isEmpty());
    }

    @Test
    void getMenu_2() {
        repo.addMenuItem(itemMock);
        Assertions.assertFalse(repo.getMenu().isEmpty());
    }
}