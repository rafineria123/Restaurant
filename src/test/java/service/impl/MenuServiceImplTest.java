package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuServiceImplTest implements TestHelper {

    private MenuServiceImpl menuService;

    @BeforeEach
    void init() {
        menuService = new MenuServiceImpl();
    }

    @Test
    public void shouldFindVegetarianFood() {
        List<Meal> meals = new ArrayList<>();
        meals.add(createVegetarianMeal());
        meals.add(createRegularMeal());

        List<Meal> result = menuService.findVegetarianFood(meals);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldReturnEmptyListWhenDoesNotContainVegetarianFood() {
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createRegularMeal());

        List<Meal> result = menuService.findVegetarianFood(meals);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindVegetarianFoodInputIsNull() {
        List<Meal> nullList = null;

        List<Meal> result = menuService.findVegetarianFood(nullList);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindVegetarianFoodInputIsEmpty() {
        List<Meal> result = menuService.findVegetarianFood(Collections.emptyList());

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindFoodByType() {

        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createVegetarianMeal());
        DietType foodType = DietType.REGULAR;

        List<Meal> result = menuService.findFoodByType(meals, foodType);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        result.stream()
                .forEach(meal -> Assertions.assertTrue(meal.getDietType() == foodType));
    }

    @Test
    public void shouldFindFoodByTypeWhenTypeIsVegetarianResultShouldIncludeVeganAndVegetarianFood(){
        List<Meal> meals = new ArrayList<>();
        meals.add(createRegularMeal());
        meals.add(createVegetarianMeal());
        meals.add(createVeganMeal());
        DietType foodType = DietType.VEGETARIAN;

        List<Meal> result = menuService.findFoodByType(meals, foodType);

        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldReturnEmptyListWhenDoesNotContainInputTypeFood() {
        List<Meal> meals = new ArrayList<>();
        meals.add(createVegetarianMeal());
        meals.add(createVegetarianMeal());
        DietType foodType = DietType.REGULAR;

        List<Meal> result = menuService.findFoodByType(meals, foodType);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodByTypeDietInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        meals.add(createVegetarianMeal());
        meals.add(createVegetarianMeal());
        DietType foodType = null;

        List<Meal> result = menuService.findFoodByType(meals, foodType);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodByTypeListInputIsNull() {
        List<Meal> meals = null;
        DietType foodType = DietType.REGULAR;

        List<Meal> result = menuService.findFoodByType(meals, foodType);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodByTypeListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        DietType foodType = DietType.REGULAR;

        List<Meal> result = menuService.findFoodByType(meals, foodType);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindFoodCheaperThan() {
        List<Meal> meals = new ArrayList<>();
        Meal cheapMeal = createMealWithPrice(5);
        Meal expensiveMeal = createMealWithPrice(20);
        meals.addAll(List.of(cheapMeal, expensiveMeal));
        int cheaperThan = 10;

        List<Meal> result = menuService.findFoodCheaperThan(meals, cheaperThan);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertTrue(result.contains(cheapMeal));
    }

    @Test
    public void shouldReturnEmptyListWhenCheaperFoodNotFound() {
        List<Meal> meals = new ArrayList<>();
        Meal cheapMeal = createMealWithPrice(5);
        Meal expensiveMeal = createMealWithPrice(20);
        meals.addAll(List.of(cheapMeal, expensiveMeal));
        int cheaperThan = 5;

        List<Meal> result = menuService.findFoodCheaperThan(meals, cheaperThan);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodCheaperThanListInputIsNull() {
        List<Meal> meals = null;
        int cheaperThan = 30;

        List<Meal> result = menuService.findFoodCheaperThan(meals, cheaperThan);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodCheaperThanPriceInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        Meal cheapMeal = createMealWithPrice(5);
        Meal expensiveMeal = createMealWithPrice(20);
        meals.addAll(List.of(cheapMeal, expensiveMeal));

        List<Meal> result = menuService.findFoodCheaperThan(meals, null);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodCheaperThanListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        int cheaperThan = 20;

        List<Meal> result = menuService.findFoodCheaperThan(meals, cheaperThan);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindFoodWithCalories() {
        List<Meal> meals = new ArrayList<>();
        Meal lowCaloriesMeal = createMealWithCalories(100);
        Meal midCaloriesMeal = createMealWithCalories(400);
        Meal highCaloriesMeal = createMealWithCalories(1000);
        meals.addAll(List.of(lowCaloriesMeal, midCaloriesMeal, highCaloriesMeal));
        int inputMinCalories = 300;
        int inputMaxCalories = 500;

        List<Meal> result = menuService.findFoodWithCalories(meals, inputMinCalories, inputMaxCalories);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertTrue(
                result.stream()
                        .allMatch(meal ->
                                meal.getCalories() >= inputMinCalories && meal.getCalories() <= inputMaxCalories)
        );
    }

    @Test
    public void shouldReturnEmptyListWhenDoesNotFindFoodWithinInputCaloriesRange() {
        List<Meal> meals = new ArrayList<>();
        Meal lowCaloriesMeal = createMealWithCalories(100);
        Meal midCaloriesMeal = createMealWithCalories(400);
        Meal highCaloriesMeal = createMealWithCalories(1000);
        meals.addAll(List.of(lowCaloriesMeal, midCaloriesMeal, highCaloriesMeal));
        int inputMinCalories = 1;
        int inputMaxCalories = 99;

        List<Meal> result = menuService.findFoodWithCalories(meals, inputMinCalories, inputMaxCalories);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodWithCaloriesListInputIsNull() {
        List<Meal> meals = null;
        int inputMinCalories = 300;
        int inputMaxCalories = 500;

        List<Meal> result = menuService.findFoodWithCalories(meals, inputMinCalories, inputMaxCalories);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodWithCaloriesMinCaloriesInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        Meal lowCaloriesMeal = createMealWithCalories(100);
        Meal midCaloriesMeal = createMealWithCalories(400);
        Meal highCaloriesMeal = createMealWithCalories(1000);
        meals.addAll(List.of(lowCaloriesMeal, midCaloriesMeal, highCaloriesMeal));
        int inputMaxCalories = 500;

        List<Meal> result = menuService.findFoodWithCalories(meals, null, inputMaxCalories);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodWithCaloriesMaxCaloriesInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        Meal lowCaloriesMeal = createMealWithCalories(100);
        Meal midCaloriesMeal = createMealWithCalories(400);
        Meal highCaloriesMeal = createMealWithCalories(1000);
        meals.addAll(List.of(lowCaloriesMeal, midCaloriesMeal, highCaloriesMeal));
        int inputMinCalories = 300;

        List<Meal> result = menuService.findFoodWithCalories(meals, inputMinCalories, null);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodWithCaloriesListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        int inputMinCalories = 300;
        int inputMaxCalories = 500;

        List<Meal> result = menuService.findFoodWithCalories(meals, inputMinCalories, inputMaxCalories);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindFoodStartingWithString() {
        List<Meal> meals = new ArrayList<>();
        Meal saladMeal = createMealWithName("salad");
        Meal saldekMeal = createMealWithName("saldek");
        Meal beefMeal = createMealWithName("beef");
        meals.addAll(List.of(saladMeal, saldekMeal, beefMeal));
        String inputString = "sal";

        List<Meal> result = menuService.findFoodStartingWithString(meals, inputString);

        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertTrue(result.stream()
                .allMatch(meal -> meal.getName().startsWith(inputString)));
    }

    @Test
    public void shouldReturnEmptyListWhenDoesNotFindFoodStartingWithString() {
        List<Meal> meals = new ArrayList<>();
        Meal saladMeal = createMealWithName("salad");
        Meal saldekMeal = createMealWithName("saldek");
        Meal beefMeal = createMealWithName("beef");
        meals.addAll(List.of(saladMeal, saldekMeal, beefMeal));
        String inputString = "good";

        List<Meal> result = menuService.findFoodStartingWithString(meals, inputString);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodStartingWithStringListInputIsNull() {
        List<Meal> meals = null;
        String inputString = "good";

        List<Meal> result = menuService.findFoodStartingWithString(meals, inputString);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodStartingWithStringStringInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        Meal saladMeal = createMealWithName("salad");
        Meal saldekMeal = createMealWithName("saldek");
        Meal beefMeal = createMealWithName("beef");
        meals.addAll(List.of(saladMeal, saldekMeal, beefMeal));
        String inputString = null;

        List<Meal> result = menuService.findFoodStartingWithString(meals, inputString);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodStartingWithStringListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        String inputString = "good";

        List<Meal> result = menuService.findFoodStartingWithString(meals, inputString);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnAllMealsWhenFindFoodStartingWithStringStringInputIEmpty() {
        List<Meal> meals = new ArrayList<>();
        Meal saladMeal = createMealWithName("salad");
        Meal saldekMeal = createMealWithName("saldek");
        Meal beefMeal = createMealWithName("beef");
        meals.addAll(List.of(saladMeal, saldekMeal, beefMeal));
        String inputString = "";

        List<Meal> result = menuService.findFoodStartingWithString(meals, inputString);

        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, result.size());
    }

    @Test
    public void shouldFindFoodContaining() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        meals.addAll(List.of(beaconMeal, eggMeal));
        Product searchedProduct = createEggs();

        List<Meal> result = menuService.findFoodContaining(meals, searchedProduct);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertTrue(result.stream()
                .allMatch(meal -> meal.getProducts().contains(searchedProduct)));
    }

    @Test
    public void shouldReturnEmptyListWhenDoesNotFindFoodContaining() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        meals.addAll(List.of(beaconMeal, eggMeal));
        Product searchedProduct = createButter();

        List<Meal> result = menuService.findFoodContaining(meals, searchedProduct);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodContainingListInputIsNull() {
        List<Meal> meals = null;
        Product searchedProduct = createButter();

        List<Meal> result = menuService.findFoodContaining(meals, searchedProduct);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodContainingProductInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        meals.addAll(List.of(beaconMeal, eggMeal));
        Product searchedProduct = null;

        List<Meal> result = menuService.findFoodContaining(meals, searchedProduct);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodContainingListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        Product searchedProduct = createBeacon();

        List<Meal> result = menuService.findFoodContaining(meals, searchedProduct);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindFoodExcludingAll() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        Meal butterMeal = createMealWithButter();
        meals.addAll(List.of(beaconMeal, eggMeal, butterMeal));
        List<Product> searchedProducts = List.of(createEggs(), createBeacon());

        List<Meal> result = menuService.findFoodExcludingAll(meals, searchedProducts);

        int expectedSize = 1;
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertTrue(result.stream().noneMatch(meal -> meal.getProducts().containsAll(searchedProducts)));
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodExcludingAllDoesNotFindFood() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        Meal butterMeal = createMealWithButter();
        meals.addAll(List.of(beaconMeal, eggMeal, butterMeal));
        List<Product> searchedProducts = List.of(createEggs(), createBeacon(), createButter());

        List<Meal> result = menuService.findFoodExcludingAll(meals, searchedProducts);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodExcludingAllMealsListInputIsNull() {
        List<Meal> meals = null;
        List<Product> searchedProducts = List.of(createEggs(), createBeacon(), createButter());

        List<Meal> result = menuService.findFoodExcludingAll(meals, searchedProducts);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodExcludingAllSearchedProductsListInputIsNull() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        Meal butterMeal = createMealWithButter();
        meals.addAll(List.of(beaconMeal, eggMeal, butterMeal));
        List<Product> searchedProducts = null;

        List<Meal> result = menuService.findFoodExcludingAll(meals, searchedProducts);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenFindFoodExcludingAllMealsListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        List<Product> searchedProducts = null;

        List<Meal> result = menuService.findFoodExcludingAll(meals, searchedProducts);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnAllMealsWhenFindFoodExcludingAllSearchedProductsListInputIsEmpty() {
        List<Meal> meals = new ArrayList<>();
        Meal beaconMeal = createMealWithBeacon();
        Meal eggMeal = createMealWithEggs();
        Meal butterMeal = createMealWithButter();
        meals.addAll(List.of(beaconMeal, eggMeal, butterMeal));
        List<Product> searchedProducts = Collections.emptyList();

        List<Meal> result = menuService.findFoodExcludingAll(meals, searchedProducts);

        int expectedSize = 3;

        Assertions.assertEquals(expectedSize, result.size());
    }


}
