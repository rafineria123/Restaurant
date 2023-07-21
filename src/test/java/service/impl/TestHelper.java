package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Product;

import java.util.ArrayList;
import java.util.List;

public interface TestHelper {
    default Meal createRegularMeal() {

        Meal meal = new Meal();
        meal.setName("Scrambled Eggs");
        meal.setCalories(300);
        meal.setDietType(DietType.REGULAR);
        meal.setPrice(10);

        List<Product> products = new ArrayList<>();
        products.add(createButter());
        products.add(createEggs());
        products.add(createBeacon());
        meal.setProducts(products);
        return meal;
    }

    default Product createButter() {

        Product butter = new Product();
        butter.setName("Oil");
        butter.setProductType(ProductType.DAIRY);
        return butter;
    }

    default Product createEggs() {

        Product eggs = new Product();
        eggs.setName("Chicken Eggs");
        eggs.setProductType(ProductType.EGGS);
        return eggs;
    }

    default Product createBeacon() {
        Product beacon = new Product();
        beacon.setName("Beacon");
        beacon.setProductType(ProductType.MEAT);
        return beacon;
    }

    default Meal createVegetarianMeal() {

        Meal meal = new Meal();
        meal.setName("Salad");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGETARIAN);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createVegetable("Lettuce"));
        products.add(createVegetable("Tomato"));
        products.add(createVegetable("Cucumber"));
        meal.setProducts(products);
        return meal;
    }

    default Product createVegetable(String vegetableName) {

        Product veg = new Product();
        veg.setName(vegetableName);
        veg.setProductType(ProductType.VEGETABLE);
        return veg;
    }

    default Meal createMealWithPrice(int price) {
        Meal meal = createRegularMeal();
        meal.setPrice(price);
        return meal;
    }

    default Meal createMealWithCalories(int calories) {
        Meal meal = createRegularMeal();
        meal.setCalories(calories);
        return meal;
    }

    default Meal createMealWithName(String name) {
        Meal meal = createRegularMeal();
        meal.setName(name);
        return meal;
    }

    default Meal createMealWithEggs() {
        Meal meal = new Meal();
        meal.setName("meal with eggs");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGETARIAN);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createEggs());
        meal.setProducts(products);
        return meal;
    }

    default Meal createMealWithBeacon() {
        Meal meal = new Meal();
        meal.setName("meal with beacon");
        meal.setCalories(250);
        meal.setDietType(DietType.REGULAR);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createBeacon());
        meal.setProducts(products);
        return meal;
    }

    default Meal createMealWithButter() {
        Meal meal = new Meal();
        meal.setName("meal with butter");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGETARIAN);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createButter());
        meal.setProducts(products);
        return meal;
    }

    default Meal createVeganMeal() {
        Meal meal = new Meal();
        meal.setName("vegan meal");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGAN);
        meal.setPrice(25);
        List<Product> products = new ArrayList<>();
        products.add(createVegetable("salad"));
        meal.setProducts(products);
        return meal;
    }

    default Meal createMealWithLettuceTomatoAndCucumber() {

        Meal meal = new Meal();
        meal.setName("Salad");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGAN);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createVegetable("Lettuce"));
        products.add(createVegetable("Tomato"));
        products.add(createVegetable("Cucumber"));
        meal.setProducts(products);
        return meal;
    }

    default Meal createMealWithLettuceAndMealName(String name) {
        Meal meal = new Meal();
        meal.setName(name);
        meal.setCalories(250);
        meal.setDietType(DietType.VEGAN);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createVegetable("Lettuce"));
        meal.setProducts(products);
        return meal;
    }





    default Meal createLettuceMealWithPrice(int price) {
        Meal meal = new Meal();
        meal.setName("meal with lettuce");
        meal.setCalories(250);
        meal.setDietType(DietType.VEGAN);
        meal.setPrice(price);

        List<Product> products = new ArrayList<>();
        products.add(createVegetable("Lettuce"));
        meal.setProducts(products);
        return meal;
    }

    default Meal createLettuceMealWithCalories(int calories) {
        Meal meal = new Meal();
        meal.setName("meal with lettuce");
        meal.setCalories(calories);
        meal.setDietType(DietType.REGULAR);
        meal.setPrice(25);

        List<Product> products = new ArrayList<>();
        products.add(createVegetable("Lettuce"));
        meal.setProducts(products);
        return meal;
    }

}
