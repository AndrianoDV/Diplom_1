package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testValuesLength() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
    }

    @Test
    public void testFirstValueIsSauce() {
        IngredientType[] values = IngredientType.values();
        assertEquals(IngredientType.SAUCE, values[0]);
    }

    @Test
    public void testSecondValueIsFilling() {
        IngredientType[] values = IngredientType.values();
        assertEquals(IngredientType.FILLING, values[1]);
    }

    @Test
    public void testValueOfSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void testValueOfFilling() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}