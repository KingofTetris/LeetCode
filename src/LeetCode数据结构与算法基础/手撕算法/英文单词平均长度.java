package LeetCode数据结构与算法基础.手撕算法;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 * <p>
 * 输入一个合法的英文句子，该英文句子中可能出现的字符包括大小写英文字母、
 * 数字、特殊字符、标点符号（逗号，句子，空格，感叹号，问号，分号，双引号）。请你求出该句子中出现的单词个数及单词平均长度。
 * <p>
 * 注意： 大小写字母和数字算单词组成部分，其他都不算
 *
 * http://luo.hustoj.com/problem.php?cid=1108&pid=4
 */
public class 英文单词平均长度 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个合法的英文句子：");
        String input = scanner.nextLine();
        calculateAverageWordLength(input);
    }

    public static void calculateAverageWordLength(String input) {
        int totalLength = 0;
        int wordCount = 0;
        // 使用正则表达式将非字母、数字的字符替换为空格，然后按空格分割为单词数组
        String replaceAll = input.replaceAll("[^a-zA-Z0-9 ]", " ").trim();
        System.out.println(replaceAll);
        String[] words = replaceAll.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                totalLength += word.length();
                wordCount++;
            }
        }
        if (wordCount == 0) {
            return;
        }
        double avg = (double) totalLength / wordCount;
        System.out.println("单词个数: " + wordCount);
        System.out.println("单词平均长度为: " + avg);
    }
}
