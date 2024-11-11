package LeetCode数据结构与算法基础.动态规划;


/**
 * 题目大意是，一个字符串仅包含A、B、C三个字符，比如"ABCCBCCC"，然后连续重复的字符可以一起消去，
 * 最后得到"A",总共消去了7个字符，那么得分就是7。现在给你字符串，然后你可以选择在字符串任意位置，
 * 包括第一个字符之前和最后一个位置之后，插入A、B、C任意一个，问这个字符串能得到的最大分数是多少。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//思路:
//暴力枚举+双指针+栈统计分数
public class 消除重复字符得到的最大分数 {
    public static void main(String[] args) {
//        String input = "CCABBAABCBCC";
//        String input = "ABCCBCCC";
        String input = "ABCCBCCCAA";
        List<Character> s = new ArrayList<>();
        for (char c : input.toCharArray()) {
            s.add(c);
        }
        System.out.println(getRes(s));
    }

    public static int countScore(List<Character> s) {
        Stack<Character> stack = new Stack<>();
        int hisScore = 0, curScore = 0;

        while (!s.isEmpty()) {
            int r = 0;
            while (r < s.size()) {
                if (stack.isEmpty()) {
                    stack.push(s.get(r));
                    if (r < s.size() - 1) {
                        r++;
                    } else {
                        break;
                    }
                } else {
                    if (s.get(r) != stack.peek()) {
                        stack.push(s.get(r));
                        if (r < s.size() - 1) {
                            r++;
                        } else {
                            break;
                        }
                    } else if (s.get(r) == stack.peek()) {
                        curScore++;
                        for (int nR = r; nR < s.size() + 1; nR++) {
                            if (nR == s.size()) {
                                r = nR;
                                break;
                            }
                            if (s.get(nR) == stack.peek()) {
                                curScore++;
                            } else {
                                r = nR;
                                break;
                            }
                        }
                        stack.pop();
                    }
                }
            }

            if (curScore > hisScore) {
                hisScore = curScore;
                s = new ArrayList<>(stack);
                stack.clear();
            } else {
                return hisScore;
            }
        }

        return hisScore;
    }

    public static String getRes(List<Character> s) {
        int insertIdx = -1;
        char insertSign = ' ';
        int maxScore = 0;

        for (int i = 0; i < s.size() + 1; i++) {
            List<Character> nS = new ArrayList<>(s);
            for (char j : new char[]{'A', 'B', 'C'}) {
                nS.add(i, j);
                int cMaxScore = countScore(nS);
                if (maxScore < cMaxScore) {
                    maxScore = cMaxScore;
                    insertIdx = i;
                    insertSign = j;
                }
                nS.remove(i); // 回溯
            }
        }

        return "在索引：" + insertIdx + ", 插入字符：" + insertSign + ", 得到最大分数：" + maxScore;
    }
}
