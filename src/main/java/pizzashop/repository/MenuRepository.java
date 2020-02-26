package pizzashop.repository;

import pizzashop.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename = "data/menu.txt";
    private List<MenuItem> listMenu;

    public MenuRepository(){
    }

    private void readMenu(){
        ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        this.listMenu= new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                if(!line.equals("")) {
                    MenuItem menuItem = getMenuItem(line);
                    listMenu.add(menuItem);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            System.out.println("Price could not be converted");
            return null;
        }
        item = new MenuItem(name, 0, price);
        return item;
    }

    public List<MenuItem> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }

}