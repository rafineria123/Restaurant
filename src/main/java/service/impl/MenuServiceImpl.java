package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Product;
import service.api.MenuService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {

    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        return findFoodByType(meals, DietType.VEGETARIAN);
    }

    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        if (meals == null || diet == null) return Collections.emptyList();

        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getDietType().compare(diet))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodCheaperThan(List<Meal> meals, Integer price) {
        if (meals == null || price == null) return Collections.emptyList();

        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getPrice() < price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        if (meals == null || minCalories == null || maxCalories == null) return Collections.emptyList();

        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> minCalories < meal.getCalories() && meal.getCalories() < maxCalories)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodStartingWithString(List<Meal> meals, String name) {
        if (meals == null || name == null) return Collections.emptyList();

        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Product product) {
        if (meals == null || product == null) return Collections.emptyList();

        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getProducts().contains(product))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Product> products) {
        if (meals == null || products == null) return Collections.emptyList();


        //disjoint returns true if the two specified collections have no elements in common.
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> Collections.disjoint(meal.getProducts(), products))
                .collect(Collectors.toList());


/*
        a better solution?
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> meal.getProducts().stream().noneMatch(produce -> products.contains(produce)))
                .collect(Collectors.toList());

*/

    }
}
