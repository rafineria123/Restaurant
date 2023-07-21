package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Product;
import domain.eto.Storage.CommonStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

public class MenuStorageServiceImplTest implements TestHelper{

    private MenuStorageServiceImpl menuStorageService;

    @BeforeEach
    public void init() {
        menuStorageService = new MenuStorageServiceImpl();
        CommonStorage.clearStorage();
    }

    @Test
    public void shouldFindVegetarianFood() {
        List<Meal> meals = new ArrayList<>();
        Meal mealWithLettuceTomatoAndCucumber = createMealWithLettuceTomatoAndCucumber();
        Meal mealWithButter = createMealWithButter();
        meals.add(mealWithLettuceTomatoAndCucumber);
        meals.add(mealWithButter);
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        CommonStorage.addToStorage(createButter(), 1);

        List<Meal> result = menuStorageService.findVegetarianFood(meals);

        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindVegetarianFoodResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal mealWithLettuceTomatoAndCucumber = createMealWithLettuceTomatoAndCucumber();
        Meal mealWithButter = createMealWithButter();
        meals.add(mealWithLettuceTomatoAndCucumber);
        meals.add(mealWithButter);

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findVegetarianFood(meals));
    }

    @Test
    public void shouldFindFoodByType() {
        List<Meal> meals = new ArrayList<>();
        Meal mealWithLettuceTomatoAndCucumber = createMealWithLettuceTomatoAndCucumber();
        Meal mealWithButter = createMealWithButter();
        meals.add(mealWithLettuceTomatoAndCucumber);
        meals.add(mealWithButter);
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        CommonStorage.addToStorage(createVegetable("Tomato"), 1);
        CommonStorage.addToStorage(createVegetable("Cucumber"), 1);
        CommonStorage.addToStorage(createButter(), 1);

        List<Meal> result = menuStorageService.findFoodByType(meals, DietType.VEGETARIAN);

        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindFoodByTypeResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal mealWithLettuceTomatoAndCucumber = createMealWithLettuceTomatoAndCucumber();
        Meal mealWithButter = createMealWithButter();
        meals.add(mealWithLettuceTomatoAndCucumber);
        meals.add(mealWithButter);

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findFoodByType(meals, DietType.VEGETARIAN));
    }

    @Test
    public void shouldFindFoodCheaperThan() {
        List<Meal> meals = new ArrayList<>();
        Meal lettuceMealWithLowPrice = createLettuceMealWithPrice(5);
        Meal lettuceMealWithHighPrice = createLettuceMealWithPrice(50);
        meals.add(lettuceMealWithLowPrice);
        meals.add(lettuceMealWithHighPrice);
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        int cheaperThan = 10;

        List<Meal> result = menuStorageService.findFoodCheaperThan(meals, cheaperThan);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindFoodCheaperThanResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal lettuceMealWithLowPrice = createLettuceMealWithPrice(5);
        Meal lettuceMealWithHighPrice = createLettuceMealWithPrice(50);
        meals.add(lettuceMealWithLowPrice);
        meals.add(lettuceMealWithHighPrice);
        int cheaperThan = 10;

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findFoodCheaperThan(meals, cheaperThan));
    }

    @Test
    public void shouldFindFoodWithCalories() {
        List<Meal> meals = new ArrayList<>();
        Meal lettuceMealWithLowCalories = createLettuceMealWithCalories(200);
        Meal lettuceMealWithHighCalories = createLettuceMealWithCalories(1000);
        meals.add(lettuceMealWithLowCalories);
        meals.add(lettuceMealWithHighCalories);
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        int minCalories = 50;
        int maxCalories = 500;

        List<Meal> result = menuStorageService.findFoodWithCalories(meals, minCalories, maxCalories);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindFoodWithCaloriesResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal lettuceMealWithLowCalories = createLettuceMealWithCalories(200);
        Meal lettuceMealWithHighCalories = createLettuceMealWithCalories(1000);
        meals.add(lettuceMealWithLowCalories);
        meals.add(lettuceMealWithHighCalories);
        int minCalories = 50;
        int maxCalories = 500;

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findFoodWithCalories(meals, minCalories, maxCalories));
    }

    @Test
    public void shouldFindFoodStartingWithString() {
        List<Meal> meals = new ArrayList<>();
        Meal lettuceGoodMeal = createMealWithLettuceAndMealName("GoodMeal");
        Meal lettuceBadMeal= createMealWithLettuceAndMealName("BadMeal");
        meals.add(lettuceGoodMeal);
        meals.add(lettuceBadMeal);
        CommonStorage.addToStorage(createVegetable("Lettuce"), 1);
        String inputString = "Good";

        List<Meal> result = menuStorageService.findFoodStartingWithString(meals, inputString);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindFoodStartingWithStringResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal lettuceGoodMeal = createMealWithLettuceAndMealName("GoodMeal");
        Meal lettuceBadMeal= createMealWithLettuceAndMealName("BadMeal");
        meals.add(lettuceGoodMeal);
        meals.add(lettuceBadMeal);
        String inputString = "Good";

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findFoodStartingWithString(meals, inputString));
    }

    @Test
    public void shouldFindFoodContaining(){
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        meals.addAll(List.of(beaconMeal, eggMeal));
        Product searchedProduct = createEggs();
        CommonStorage.addToStorage(searchedProduct, 1);

        List<Meal> result = menuStorageService.findFoodContaining(meals, searchedProduct);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertTrue(result.stream()
                .allMatch(meal -> meal.getProducts().contains(searchedProduct)));
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindFoodContainingResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        meals.addAll(List.of(beaconMeal, eggMeal));
        Product searchedProduct = createEggs();

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findFoodContaining(meals, searchedProduct));
    }

    @Test
    public void shouldFindFoodExcludingAll(){
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        Meal butterMeal = createMealWithButter();
        meals.addAll(List.of(beaconMeal, eggMeal, butterMeal));
        List<Product> searchedProducts = List.of(createEggs(), createBeacon());
        CommonStorage.addToStorage(createButter(), 1);

        List<Meal> result = menuStorageService.findFoodExcludingAll(meals, searchedProducts);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldThrowNoFoodFoundExceptionWhenStorageDoesNotHaveAllProductsFromFindFoodExcludingAllResultList() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        Meal butterMeal = createMealWithButter();
        meals.addAll(List.of(beaconMeal, eggMeal, butterMeal));
        List<Product> searchedProducts = List.of(createEggs(), createBeacon());

        Assertions.assertThrows(NoFoodFoundException.class, () -> menuStorageService.findFoodExcludingAll(meals, searchedProducts));
    }

}
