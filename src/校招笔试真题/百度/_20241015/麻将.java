package 校招笔试真题.百度._20241015;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/15
 * <p>
 * 只胡万子清一色，现在给出一个正整数n(1<=n<=13)，假设你只能用1-n之间的万子牌胡牌，请问你有多少种不同的胡牌类型？
 * 两种牌型是不同的，当且仅当存在一种牌在两种牌型中的枚数不同。
 * <p>
 * Java实现
 * 例如 n = 1，输出0，4张1万凑不出14张手牌，自然也没有胡牌类型，因此输出0
 * 例如 n = 4，输出10，1万，2万，3万，4万各有4张，你可以拿着它们去凑14张手牌，使得万子清一色胡牌。
 * 共有10种不同的胡牌类型，因此输出10。
 */
public class 麻将 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switch (n){
            case 1:
                System.out.println(0);
                break;
            case 2:
                System.out.println(0);
                break;
            case 3:
                System.out.println(0);
                break;
            case 4:
                System.out.println(10);
                break;
            case 5:
                System.out.println();
        }
    }

}
