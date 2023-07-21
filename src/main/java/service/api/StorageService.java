package service.api;

import domain.eto.Meal;
import domain.eto.Storage;
import service.exception.NoFoodFoundException;

public interface StorageService {
    /**
     * For a given meal and storage throws NoFoodFoundException if storage does not contain all products required to prepare meal.
     * @param meal
     * @param storage
     * @throws NoFoodFoundException
     */
    public void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage);
}
