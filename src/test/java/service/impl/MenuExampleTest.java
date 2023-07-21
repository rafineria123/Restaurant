package service.impl;

import domain.eto.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Hint: To create a test class use intellij ->  go to a class, press alt+enter and use "create test" option.
 * Alternatively use alt + insert (einfugen) combination, and select "Test" use Alt
 */
class MenuExampleTest implements TestHelper {

  MenuExample menuExample = new MenuExample();

  /**
   * Hint: Tests compromises itself on 3 blocks: given when then. Given is a block where you create your test data.
   * When is a block where you test your method. Then is a block where you assert (check) your code results.
   */
  @Test
  public void shouldFindVegetarianFood() {

    // given
    List<Meal> meals = new ArrayList<>();
    meals.add(createVegetarianMeal());
    meals.add(createRegularMeal());
    // when
    List<Meal> result = menuExample.findVegetarianFood(meals);
    // then
    int expectedSize = 1;
    //HINT: write what result you expect after the method was called
    Assertions.assertEquals(expectedSize, result.size());
    Assertions.assertEquals("Salad", result.get(0).getName());
  }

  @Test
  public void shouldThrowExceptionWhenInputDoesNotContainVegetarian() {

    // given
    List<Meal> meals = new ArrayList<>();
    meals.add(createRegularMeal());
    meals.add(createRegularMeal());
    // when
    // then
    //HINT: When checking for exceptions, when and then are usually written in one method call
    Assertions.assertThrows(NoFoodFoundException.class, ()->
        menuExample.findVegetarianFood(meals));
  }

  @Test
  public void shouldThrowExceptionWhenInputWasEmpty() {

    // given
    List<Meal> meals = new ArrayList<>();
    // when
    // then
    //HINT: When checking for exceptions, when and then are usually written in one method call
    Assertions.assertThrows(NoFoodFoundException.class, ()->
        menuExample.findVegetarianFood(meals));
  }

  @Test
  public void shouldProperlyHandleNullAsInput() {

    // given
    List<Meal> meals = null;
    // when
    // then
    //HINT: When checking for exceptions, when and then are usually written in one method call
    Assertions.assertThrows(IllegalArgumentException.class, ()->
        menuExample.findVegetarianFood(meals));
  }

}