package service.impl;

import domain.eto.Meal;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

public class StorageServiceImplTest implements TestHelper {
    private StorageServiceImpl storageService;

    @BeforeEach
    public void init() {
        storageService = new StorageServiceImpl();
    }

    @Test
    public void shouldThrowNoExceptionWhenMealCanBePreparedFromProductsInStorage() {

        Meal meal = createMealWithLettuceTomatoAndCucumber();
        Storage storage = new Storage();
        meal.getProducts().stream().forEach(storage::addProduct);

        Assertions.assertDoesNotThrow(() -> storageService.canMealBePreparedFromProductsInStorage(meal, storage));
    }

    @Test
    public void shouldThrowNoFoodExceptionWhenMealCanNotBePreparedFromProductsInStorage() {

        Meal meal = createMealWithLettuceTomatoAndCucumber();
        Storage storage = new Storage();
        storage.addProduct(createVegetable("Lettuce"));
        storage.addProduct(createVegetable("Tomato"));

        Assertions.assertThrows(NoFoodFoundException.class, () -> storageService.canMealBePreparedFromProductsInStorage(meal, storage));
    }

    @Test
    public void shouldThrowNoFoodExceptionWhenCanMealBePreparedFromProductsInStorageMealInputIsNull() {

        Meal meal = null;
        Storage storage = new Storage();
        storage.addProduct(createVegetable("Lettuce"));
        storage.addProduct(createVegetable("Tomato"));

        Assertions.assertThrows(NoFoodFoundException.class, () -> storageService.canMealBePreparedFromProductsInStorage(meal, storage));
    }

    @Test
    public void shouldThrowFoodExceptionWhenCanMealBePreparedFromProductsInStorageStorageInputIsNull() {

        Meal meal = createMealWithLettuceTomatoAndCucumber();
        Storage storage = null;

        Assertions.assertThrows(NoFoodFoundException.class, () -> storageService.canMealBePreparedFromProductsInStorage(meal, storage));
    }

}
