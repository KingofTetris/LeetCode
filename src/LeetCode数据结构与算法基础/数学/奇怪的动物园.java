package LeetCode数据结构与算法基础.数学;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/10/23
 */

//某个动物园里住着一群奇怪的猫和狗，这里有 20%的猫错误的以为自己是其实狗，
// 有 20%的狗错误的以为自己其实是猫，共有115只动物以为自己是猫，85只动物以为自己是狗，请问：有多少只猫？有多少只狗？
public class 奇怪的动物园 {
    public static void main(String[] args) {
        List<Integer[]> results = new ArrayList<>();

        for (int cat = 1; cat <= 200; cat++) {
            int dog = 200 - cat;
            if (0.8 * cat + 0.2 * dog == 115 && 0.8 * dog + 0.2 * cat == 85) {
                results.add(new Integer[]{cat, dog});
            }
        }

        for (Integer[] result : results) {
            System.out.println("Cat: " + result[0] + ", Dog: " + result[1]);
        }
    }
}
