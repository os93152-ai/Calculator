import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.*; // For JUnit 5

public class App {

    @Test
    public void addTest(){
        assertEquals("Test Case 1 : Failed", add(""), 0);
        assertEquals("Test Case 2 : Failed", add("0"), 0);
        assertEquals("Test Case 3 : Failed", add("1"), 1);
        assertEquals("Test Case 4 : Failed", add("5"), 5);
        assertEquals("Test Case 5 : Failed", add("10"), 10);
        assertEquals("Test Case 6 : Failed", add("25"), 25);
        assertEquals("Test Case 7 : Failed", add("153"), 153);
        assertEquals("Test Case 8 : Failed", add("9368"), 9368);
        assertEquals("Test Case 9 : Failed", add("9876543210"), 9876543210L);
        assertEquals("Test Case 10 : Failed", add("98767485955524"), 98767485955524L);
        assertEquals("Test Case 11 : Failed", add("1,2"), 3);
    }

    public static long add(String numbers){
        long n = 0;
        if(numbers.length() > 0)
            n = Long.valueOf(numbers);
        return n;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
