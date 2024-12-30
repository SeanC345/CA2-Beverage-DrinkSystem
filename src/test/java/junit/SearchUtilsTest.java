package junit;
import data.CustomHashTable;
import models.Drink;
import utils.SearchUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchUtilsTest {

    @Test
    public void testSearchDrinksByName() {
        CustomHashTable<String, Drink> drinksTable = new CustomHashTable<>(10);
        drinksTable.put("Mojito", new Drink("Mojito", "Cuba", "Refreshing cocktail", null, null));
        assertEquals(1, SearchUtils.searchDrinksByName(drinksTable, "Mojito").size());
    }
}
