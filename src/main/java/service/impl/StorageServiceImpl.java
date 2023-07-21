package service.impl;

import domain.eto.Meal;
import domain.eto.Storage;
import service.api.StorageService;
import service.exception.NoFoodFoundException;

public class StorageServiceImpl implements StorageService {
    @Override
    public void canMealBePreparedFromProductsInStorage(Meal meal, Storage storage) {
        if (meal == null || storage == null) throw new NoFoodFoundException();

        if (meal.getProducts().stream().anyMatch(product -> storage.getProductQuantity(product) < 1)){
            throw new NoFoodFoundException();
        }
    }
}
