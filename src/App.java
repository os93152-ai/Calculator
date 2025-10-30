import static org.junit.Assert.assertEquals;

import org.junit.*; // For JUnit 5
public class App {

    @Test
    public void addTest(){
        assertEquals("Test Case 1 : Failed", add(""), 0);
        assertEquals("Test Case 2 : Failed", add("0"), 0);
        assertEquals("Test Case 3 : Failed", add("1"), 1);
        assertEquals("Test Case 3 : Failed", add("5"), 5);
        assertEquals("Test Case 3 : Failed", add("10"), 10);
        assertEquals("Test Case 3 : Failed", add("25"), 25);
        assertEquals("Test Case 3 : Failed", add("153"), 153);
        assertEquals("Test Case 3 : Failed", add("9368"), 9368);
        assertEquals("Test Case 3 : Failed", add("9876543210"), 9876543210);
    }

    public static int add(String numbers){
        int n = 0;
        if(numbers.length() > 0)
            n = Integer.parseInt(numbers);
        return n;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
