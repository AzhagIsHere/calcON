import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("***Консольный калькулятор***ave caesar***");
        System.out.println("Введите строку для вычисления вида: a + b");

        Scanner scanner = new Scanner(System.in);
        String stroka = scanner.nextLine();

        String result = calc(stroka);

        scanner.close();
        System.out.println(result);

    }
    public static String calc(String input) throws Exception {

    String result = new String();
    String[] subStr = input.split(" ");

    if (subStr.length == 1){
        throw new Exception("Строка не является математической операцией");
    } else if (subStr.length > 3) {
        throw new Exception("формат математической операции не удовлетворяет заданию");
    }

    int firstNumber = 0;
    int secondNumber = 0;
    int result_calc = 0;
    boolean RomanFlag = false;

    String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    boolean first_valid = subStr[0].matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    boolean second_valid = subStr[2].matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    
    if( first_valid && second_valid ) {
        firstNumber = RomanToInt(subStr[0]);
        secondNumber = RomanToInt(subStr[2]);
        RomanFlag = true;
    } else if (first_valid ^ second_valid) {
        throw new Exception("Используются одновременно разные системы счисления");
    } else{
        try {
            firstNumber = Integer.parseInt(subStr[0]);
            if ( 1 < firstNumber && firstNumber > 10){
                throw new Exception("Допустимы лишь числа от 1 до 10 включительно");
            }
        } catch(NumberFormatException  e){
            throw new Exception(" ");
        }

        try {
            secondNumber = Integer.parseInt(subStr[2]);
            if ( 1 < secondNumber && secondNumber > 10){
                throw new Exception("Допустимы лишь числа от 1 до 10 включительно");
            }
        } catch(NumberFormatException  e){
            throw new Exception("");
        }

    }



    switch (subStr[1]) {
            case "+":
                result_calc = firstNumber + secondNumber;
                break;
            case "-":
                result_calc = firstNumber - secondNumber;
                break;
            case "*":
                result_calc = firstNumber * secondNumber;
                break;
            case "/":
                result_calc = firstNumber / secondNumber;
                break;
            default:
                throw new Exception("Не правильно введен символ операции, используйте только +, -, *, /");
    }
    if(RomanFlag){
        if (result_calc > 0){
            result = String.valueOf(integerToRoman(result_calc));
        }else{
            throw new Exception("в римской системе нет отрицательных чисел");
        }
    }else{
        result = String.valueOf(result_calc);
    }
    return result;
    }

    static String integerToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }
    static int RomanToInt(String s) {

        Map<Character,Integer> translations=new HashMap<Character,Integer>();

        //Adding elements to map
        translations.put('I',1);
        translations.put('V',5);
        translations.put('X',10);
        translations.put('L',50);
        translations.put('C',100);
        translations.put('D',500);
        translations.put('M',1000);

        s = s.replace("IV","IIII");
        s = s.replace("IX","VIIII");
        s = s.replace("XL","XXXX");
        s = s.replace("XC","LXXXX");
        s = s.replace("CD","CCCC");
        s = s.replace("CM","DCCCC");

        int number = 0;
        for (int i = 0; i < s.length(); i++)
        {
            number = number + (translations.get(s.charAt(i)));
        }
        return number;
    }





}
