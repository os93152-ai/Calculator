import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.*; // For JUnit 5

public class App {

    
    @Test
    public void addTest() throws Exception{

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
        assertEquals("Test Case 18 : Failed", add("//+\n1+2+4+9+4+6\n0\n5\n7"), 
                                                                (1+2+4+9+4+6+0+5+7));
        assertEquals("Test Case 19 : Failed", assertThrows(Exception.class, () -> add("1,-2")).getMessage(), "negative numbers not allowed -2");      
        assertEquals("Test Case 20 : Failed", assertThrows(Exception.class, () -> add("1,-2,-14,7,14,-9\n-4\n-5")).getMessage(), "negative numbers not allowed -2,-14,-9,-4,-5");      
        
        assertEquals("Test Case 21 : Failed", add("203,5,8030,4,4,1001"), (203+5+8030+4+4+1001));
        assertEquals("Test Case 22 : Failed", add("//[***]\n1***2"), 3);
        assertEquals("Test Case 23 : Failed", add("//[++]\n210++43++943\n65\n8++42"), (210+43+943+65+8+42));
        assertEquals("Test Case 24 : Failed", add("//[*]\n1*2*3*45\n87"), (1+2+3+45+87));
        assertEquals("Test Case 25 : Failed", add("//[*][+]\n1*2+3*45\n87+2"), (1+2+3+45+87+2));
        assertEquals("Test Case 26 : Failed", add("//[***][++][%^&]\n1***2++3%^&4%^&5\n6++7***8\n9***10++11%^&12"), 
                                                                (1+2+3+4+5+6+7+8+9+10+11+12));
    }



    public static ArrayList<Long> parseNumberString(ArrayList<String> delimiters, String str) throws Exception{
        
        str = str+delimiters.get(0);
        int len = str.length();
        String token = "";
        ArrayList<Long> numberList = new ArrayList<>();
        String negativeNumbers = "";
        
        for(int i = 0; i < len; i++){
            char ch = str.charAt(i);
            boolean delimiterFlag = false;
            for(String delimiter : delimiters){
                int dlen = delimiter.length();
                if((ch == delimiter.charAt(0) &&  (str.substring(i, i+dlen).equals(delimiter))) || ch == '\n' || (i+1 < len && ch == '\\' && str.charAt(++i) == 'n' )){
                    long n0 = 0;
                    try{
                    if(token.length() > 0)
                        n0 = Long.valueOf(token);
                    }
                    catch(Exception e){
                        throw new Exception("Illegal token found ... "+token+" is not a number");
                    }
                    if(n0 < 0) 
                        negativeNumbers += ","+token;
                    else{
                        numberList.add(n0);
                    }
                    token = "";
                    if(str.substring(i, i+dlen).equals(delimiter))
                        i = i+dlen-1;
                    delimiterFlag = true;
                    break;
                }
            }
            if(delimiterFlag == false)
                token += ch;
        }
        if(negativeNumbers.length() > 0)
            throw new Exception("negative numbers not allowed "+negativeNumbers.substring(1));
        
        System.out.println("Number list formed is : ");
        for(Long i : numberList)
            System.out.print(i+", ");
        System.out.println();

        return numberList;
    }

    public static ArrayList<String> extractDelimiters(String str){
        
        ArrayList<String> delimiters = new ArrayList<>(); 

        if(str.startsWith("//")){

            // handle both '/n' as escape charater and not escape charater
            int dlmtrEnd = str.indexOf('\n'); 
            if(dlmtrEnd == -1 && str.indexOf('\\') >= 0)
                dlmtrEnd = str.indexOf('\\');
            String delimiterString = str.substring(2, dlmtrEnd);

            if(delimiterString.startsWith("[")){
                String dlmtr;
                while(delimiterString.length() > 0){
                    dlmtr = delimiterString.substring(delimiterString.indexOf('[')+1, delimiterString.indexOf(']'));
                    delimiters.add(dlmtr);
                    if(delimiterString.equals(dlmtr) == false)
                    delimiterString = delimiterString.substring(delimiterString.indexOf(']')+1);
                }
            }
            else
                delimiters.add(delimiterString);
        }

        if(delimiters.size() == 0)
            delimiters.add(",");

        return delimiters;
    }

    public static long add(String numbers)throws Exception{
        ArrayList<String> delimiters = extractDelimiters(numbers);
        if(numbers.startsWith("//")){
            if(numbers.indexOf('\n') > 0)
                numbers = numbers.substring(numbers.indexOf('\n')+1);
            else if(numbers.indexOf('\\') > 0){
                numbers = numbers.substring(numbers.indexOf('\\')+2);
            }
        }

        System.out.println("Number string : "+numbers);
        ArrayList<Long> numberList = new ArrayList<>();
        numberList = parseNumberString(delimiters, numbers);
        Long answer = 0L;
        for(Long num : numberList)
            answer += num;

        return answer;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the string : ");
        String numbeString = in.nextLine();
        System.out.println(add(numbeString));
    }
}
