import utils.CustomLinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    @Test
    public void testAddAndSize() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Item1");
        list.add("Item2");
        assertEquals(2, list.size());
    }

    @Test
    public void testGet() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Item1");
        assertEquals("Item1", list.get(0));
    }

    @Test
    public void testRemove() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Item1");
        list.remove("Item1");
        assertEquals(0, list.size());
    }
}
