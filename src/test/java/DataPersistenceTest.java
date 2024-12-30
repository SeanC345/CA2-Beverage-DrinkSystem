import data.CustomHashTable;
import data.DataPersistence;
import models.Drink;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class DataPersistenceTest {

    @Test
    public void testSaveAndLoadDrinks() {
        CustomHashTable<String, Drink> drinksTable = new CustomHashTable<>(10);
        drinksTable.put("Mojito", new Drink("Mojito", "Cuba", "Refreshing cocktail", null, null));
        DataPersistence.saveDrinks(drinksTable, "drinksTest.dat");

        CustomHashTable<String, Drink> loadedTable = new CustomHashTable<>(10);
        DataPersistence.loadDrinks(loadedTable, "drinksTest.dat");
        assertNotNull(loadedTable.get("Mojito"));
        new File("drinksTest.dat").delete(); // Cleanup test file
    }
}

