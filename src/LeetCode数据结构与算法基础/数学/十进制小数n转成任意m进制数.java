package LeetCode数据结构与算法基础.数学;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 十进制小数n转成任意m进制数 {

    public static String convertDecimalToAnyBase(double num, int base) {
        if (num < 0 || base < 2 || base > 36) {
            return "Invalid input";
        }

        int integerPart = (int) num;
        double decimalPart = num - integerPart;

        StringBuilder result = new StringBuilder();
        result.append(convertIntegerToAnyBase(integerPart, base));

        if (decimalPart > 0) {
            result.append(".");

            HashMap<Double, Integer> seenRemainders = new HashMap<>();
            while (decimalPart > 0) {
                if (seenRemainders.containsKey(decimalPart)) {
                    result.insert(seenRemainders.get(decimalPart), "(");
                    result.append(")");
                    break;
                }

                seenRemainders.put(decimalPart, result.length());
                decimalPart *= base;
                int digit = (int) decimalPart;
                result.append(Character.forDigit(digit, base));
                decimalPart -= digit;
            }
        }

        return result.toString();
    }

    public static String convertIntegerToAnyBase(int num, int base) {
        return Integer.toString(num, base);
    }

    public static void main(String[] args) {
        double decimalNumber = 2.35;
        int targetBase = 2;

        String result = convertDecimalToAnyBase(decimalNumber, targetBase);
        System.out.println("2.35 in base 2 is: " + result);
    }
}
