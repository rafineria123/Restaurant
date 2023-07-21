package domain.eto;

import service.exception.NoFoodFoundException;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Product, Integer> mapOfProductsAndQuantities;

    public Storage() {
        this.mapOfProductsAndQuantities = new HashMap<>();
    }

    public int getProductQuantity(Product product) {
        Integer quantity = mapOfProductsAndQuantities.get(product);
        return quantity == null ? 0 : quantity;
    }

    public void addProduct(Product product) {
        if (product == null) return;

        if (mapOfProductsAndQuantities.containsKey(product))
            mapOfProductsAndQuantities.put(product, mapOfProductsAndQuantities.get(product) + 1);

        else mapOfProductsAndQuantities.put(product, 1);
    }

    public Map<Product, Integer> getMapOfProductsAndQuantities() {
        return mapOfProductsAndQuantities;
    }


    public static class CommonStorage{
        private static Storage storage = new Storage();

        public static boolean checkInStorage(Product product){
            return storage.getMapOfProductsAndQuantities().containsKey(product) && storage.getProductQuantity(product)>0;
        }

        public static void addToStorage(Product product, Integer quantity){
            if(storage.mapOfProductsAndQuantities.containsKey(product))
                storage.mapOfProductsAndQuantities.put(product, storage.getProductQuantity(product) + quantity);
            else storage.mapOfProductsAndQuantities.put(product, quantity);
        }

        public static void removeFromStorage(Product product, Integer quantity){
            if(storage.mapOfProductsAndQuantities.containsKey(product)&&storage.getProductQuantity(product)>=quantity)
                storage.mapOfProductsAndQuantities.put(product, storage.getProductQuantity(product) - quantity);
            else throw new NoFoodFoundException();
        }

        public static void clearStorage(){
            storage = new Storage();
        }

    }

}
