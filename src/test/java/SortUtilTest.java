import models.Drink;
import utils.CustomLinkedList;
import utils.SortUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortUtilTest {

    @Test
    public void testSortDrinksByName() {
        CustomLinkedList<Drink> drinks = new CustomLinkedList<>();
        drinks.add(new Drink("Margarita", "Mexico", "Tequila-based", null, null));
        drinks.add(new Drink("Old Fashioned", "USA", "Classic whiskey cocktail", null, null));
        SortUtils.sortDrinksByName(drinks);
        assertEquals("Margarita", drinks.get(0).getName());
        assertEquals("Old Fashioned", drinks.get(1).getName());
    }
}
