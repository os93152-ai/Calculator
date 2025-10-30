import static org.junit.Assert.assertEquals;

import org.junit.*; // For JUnit 5
public class App {

    @Test
    public void addTest(){
        System.out.println("TESTING .....");
        assertEquals("Test Case 1 : Failed", add(""), 0);
    }

    public static int add(String numbers){
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
