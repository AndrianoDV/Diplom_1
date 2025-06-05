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

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"black bun", 100, new Ingredient[] {
                        new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                        new Ingredient(IngredientType.FILLING, "cutlet", 100)
                }},
                {"white bun", 200, new Ingredient[] {
                        new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                        new Ingredient(IngredientType.FILLING, "dinosaur", 200)
                }}
        };
    }

    public BurgerTest(String bunName, float bunPrice, Ingredient[] ingredientsArray) {
        bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        ingredients = Arrays.asList(ingredientsArray);
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredients.get(0));
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredients.get(0), burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.ingredients.addAll(ingredients);
        burger.removeIngredient(0);
        assertEquals(ingredients.size() - 1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.ingredients.addAll(ingredients);
        Ingredient first = burger.ingredients.get(0);
        burger.moveIngredient(0, 1);
        assertEquals(first, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.ingredients.addAll(ingredients);

        float expectedPrice = bun.getPrice() * 2;
        for (Ingredient ingredient : ingredients) {
            expectedPrice += ingredient.getPrice();
        }

        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.ingredients.addAll(ingredients);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains(bun.getName()));
        for (Ingredient ingredient : ingredients) {
            assertTrue(receipt.contains(ingredient.getName()));
        }
    }
}
