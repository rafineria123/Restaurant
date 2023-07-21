package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Product;
import domain.eto.Storage;
import service.exception.NoFoodFoundException;

import java.util.List;

public class MenuStorageServiceImpl extends MenuServiceImpl {
    private void canMealBePreparedFromProductsInCommonStorage(Meal meal) {
        if (meal == null) throw new NoFoodFoundException();
        if (meal.getProducts().stream().anyMatch(product -> Storage.CommonStorage.checkInStorage(product) == false)) {
            throw new NoFoodFoundException();
        }
    }

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        List<Meal> filteredList = super.findVegetarianFood(meals);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        List<Meal> filteredList = super.findFoodByType(meals, diet);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }

    @Override
    public List<Meal> findFoodCheaperThan(List<Meal> meals, Integer price) {
        List<Meal> filteredList =  super.findFoodCheaperThan(meals, price);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }

    @Override
    public List<Meal> findFoodWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        List<Meal> filteredList = super.findFoodWithCalories(meals, minCalories, maxCalories);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }

    @Override
    public List<Meal> findFoodStartingWithString(List<Meal> meals, String name) {
        List<Meal> filteredList = super.findFoodStartingWithString(meals, name);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Product product) {
        List<Meal> filteredList = super.findFoodContaining(meals, product);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Product> products) {
        List<Meal> filteredList = super.findFoodExcludingAll(meals, products);
        filteredList.forEach(this::canMealBePreparedFromProductsInCommonStorage);
        return filteredList;
    }
}
