package pizzashop.repository;

import pizzashop.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuRepository implements IMenuRepository{
    private static String filename = "data/menu.txt";
    private List<MenuItem> listMenu;

    public MenuRepository(){
        readMenu();
    }

    private void readMenu(){
        ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        this.listMenu= new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = null;
            while((line=br.readLine())!=null){
                if(!line.equals("")) {
                    MenuItem menuItem = getMenuItem(line);
                    listMenu.add(menuItem);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, e.getMessage());
        }
    }

    private MenuItem getMenuItem(String line){
        MenuItem item=null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = 0f;
        try {
            price = Double.parseDouble(st.nextToken());
        } catch (NumberFormatException ex){
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Price could not be converted");
            return null;
        }
        item = new MenuItem(name, 0, price);
        return item;
    }

    @Override
    public void addMenuItem(MenuItem item){
        listMenu.add(item);
    }

    @Override
    public List<MenuItem> getMenu(){
        return listMenu;
    }

}
