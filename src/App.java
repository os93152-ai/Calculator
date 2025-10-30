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
        assertEquals("Test Case 12 : Failed", add("1035,26390"), (1035+26390));
        assertEquals("Test Case 13 : Failed", add("9876543210,98767485955524,1035,26390"), 
                                                            (9876543210L+98767485955524L+1035L+26390L));
        assertEquals("Test Case 14 : Failed", add("9876543210,98767485955524,1035,26390,"), 
                                                            (9876543210L+98767485955524L+1035L+26390L));
        assertEquals("Test Case 15 : Failed", add("1035,26390\n567"), (1035+26390+567));
        assertEquals("Test Case 16 : Failed", add("10,26\n39,46\n5,6,7\n"), 
                                                    (10+26+39+46+5+6+7));
        assertEquals("Test Case 17 : Failed", add("//;\n1;2"), 3);

    }

    public static ArrayList<Long> parseNumberString(char delimiter, String str){
        str = str+delimiter;
        int len = str.length();
        String token = "";
        ArrayList<Long> numberList = new ArrayList<>();
        for(int i = 0; i < len; i++){
            char ch = str.charAt(i);
            if(ch == delimiter || ch == '\n'){
                long n0 = 0;
                if(token.length() > 0)
                n0 = Long.valueOf(token);
                numberList.add(n0);
                token = "";
            }
            else{
                token += ch;
            }
        }
        return numberList;
    }

    public static long add(String numbers){
        char delimiter = ',';
        if(numbers.startsWith("//")){
            delimiter = numbers.charAt(2);
            numbers = numbers.substring(4);
        }
        ArrayList<Long> numberList = new ArrayList<>();
        numberList = parseNumberString(delimiter, numbers);
        Long answer = 0L;
        for(Long num : numberList)
            answer += num;

        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
