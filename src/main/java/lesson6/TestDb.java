package lesson6;

import lesson6.dao.ProductsMapper;
import lesson6.dao.CategoriesMapper;
import lesson6.model.Categories;
import lesson6.model.Products;
import lesson6.model.ProductsExample;

public class TestDb {

    public static void main(String[] args) {

        DbService dbService = new DbService();
        ProductsMapper productsMapper = dbService.getProductsMapper();
        CategoriesMapper categoriesMapper = dbService.getCategoriesMapper();

        Products forCreate = new Products();
            forCreate.setTitle("ACER");
            forCreate.setPrice(15000);
            forCreate.setCategoryId(2L);
            productsMapper.insert(forCreate);

        //Создать 4 категории
        String[] catName = {"Car", "House", "OS", "Other"};
        Categories catCreate = new Categories();
        for (int i = 0; i <= 3; i++) {
            catCreate.setTitle(catName[i]);
            categoriesMapper.insert(catCreate);
        }

        //Добавить по 3 продукта в каждой категории
        String[] autoItems = {"Mercedes", "Audi", "BMW"};
        int[] priceAutoItems = {5000, 4500, 5500};

        String[] houseItems = {"Apartment", "1 bedroom", "2 bedroom"};
        int[] priceHouseItems = {99000, 120000, 150000};

        String[] osItems = {"Windows", "MacOs", "Linux"};
        int[] priceOsItems = {1000, 500, 100};

        String[] otherItems = {"Bike", "Horse", "Boat"};
        int[] priceOtherItems = {5000, 15000, 25000};

        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(autoItems[i]);
            forCreate.setPrice(priceAutoItems[i]);
            forCreate.setCategoryId(3L);
            productsMapper.insert(forCreate);
        }

        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(houseItems[i]);
            forCreate.setPrice(priceHouseItems[i]);
            forCreate.setCategoryId(4L);
            productsMapper.insert(forCreate);
        }
        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(osItems[i]);
            forCreate.setPrice(priceOsItems[i]);
            forCreate.setCategoryId(5L);
            productsMapper.insert(forCreate);
        }
        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(otherItems[i]);
            forCreate.setPrice(priceOtherItems[i]);
            forCreate.setCategoryId(6L);
            productsMapper.insert(forCreate);
        }

        //Вывести продукты в каждой из категорий
        ProductsExample filter = new ProductsExample();
        for (long i = 1L; i <= 6L; i++) {
            filter.clear();
            filter.createCriteria().andCategoryIdEqualTo(i);
            System.out.println("Продукты из категории " + i + ":");
            System.out.println(productsMapper.selectByExample(filter) + "\n");
        }

        dbService.commitSession();
        dbService.closeSession();

    }

}
