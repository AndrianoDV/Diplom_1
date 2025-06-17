package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {
    private Burger burger;
    private Bun bun;
    private List<Ingredient> ingredients;

    @Parameterized.Parameters(name = "Булочка: {0}, Цена: {1}, Ингредиенты: {2}")
    public static Object[][] getData() {
        return new Object[][] {
                {"black bun", 100f, Arrays.asList(
                        new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                        new Ingredient(IngredientType.FILLING, "cutlet", 100)
                )},
                {"white bun", 200f, Arrays.asList(
                        new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                        new Ingredient(IngredientType.FILLING, "dinosaur", 200)
                )}
        };
    }

    public BurgerTest(String bunName, float bunPrice, List<Ingredient> ingredientsList) {
        bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        ingredients = ingredientsList;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBuns_ShouldSetBunCorrectly() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredient_ShouldIncreaseIngredientsSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(ingredients.get(0));
        assertEquals(initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void addIngredient_ShouldAddCorrectIngredient() {
        Ingredient ingredient = ingredients.get(0);
        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient_ShouldDecreaseIngredientsSize() {
        burger.ingredients.addAll(ingredients);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals(initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredient_ShouldChangeIngredientPosition() {
        burger.ingredients.addAll(ingredients);
        Ingredient first = burger.ingredients.get(0);
        int newIndex = 1;
        burger.moveIngredient(0, newIndex);
        assertEquals(first, burger.ingredients.get(newIndex));
    }

    @Test
    public void getPrice_ShouldReturnCorrectBurgerPrice() {
        burger.setBuns(bun);
        burger.ingredients.addAll(ingredients);

        float expectedPrice = bun.getPrice() * 2;
        for (Ingredient ingredient : ingredients) {
            expectedPrice += ingredient.getPrice();
        }

        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceipt_ShouldContainBunName() {
        burger.setBuns(bun);
        burger.ingredients.addAll(ingredients);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(bun.getName()));
    }

    @Test
    public void getReceipt_ShouldContainAllIngredientsNames() {
        burger.setBuns(bun);
        burger.ingredients.addAll(ingredients);
        String receipt = burger.getReceipt();
        for (Ingredient ingredient : ingredients) {
            assertTrue(receipt.contains(ingredient.getName()));
        }
    }

    @Test
    public void getReceipt_ShouldNotBeNull() {
        burger.setBuns(bun);
        burger.ingredients.addAll(ingredients);
        assertNotNull(burger.getReceipt());
    }
}