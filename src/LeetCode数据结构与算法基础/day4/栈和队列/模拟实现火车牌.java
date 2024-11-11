package LeetCode数据结构与算法基础.day4.栈和队列;

/**
 * @author by KingOfTetris
 * @date 2024/11/11
 * <p>
 * 模拟实现火车牌，双方各有自己的牌库，双方轮流顺序出牌，把出的牌交替、顺序摆放，当出牌时出到一样的，
 * 就收回两张相同牌之间的所有牌放到自己的牌库，有一方牌库清空则对方胜利。返回胜利方、回合数、胜利方的剩余牌情况
 */

/**
 * 模拟实现火车牌，双方各有自己的牌库，双方轮流顺序出牌，把出的牌交替、顺序摆放，当出牌时出到一样的，
 * 就收回两张相同牌之间的所有牌放到自己的牌库，有一方牌库清空则对方胜利。返回胜利方、回合数、胜利方的剩余牌情况
 *
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 思路:
 * 3个队列(只要会模拟，桌子上的牌堆，玩家手里的牌堆就行了)。
 *
 * 玩家手里的牌堆用双端队列deque模拟，因为是队列，先进先出，从左边出队。
 *
 * 牌桌上的牌堆，用普通的list即可。
 */
public class 模拟实现火车牌 {
    public static void main(String[] args) {
        Deque<Integer> cardQ1 = new ArrayDeque<>(List.of(1, 2, 3, 4, 5)); // 玩家1手里的牌堆
        Deque<Integer> cardQ2 = new ArrayDeque<>(List.of(8, 7, 6, 4, 9)); // 玩家2手里的牌堆
        List<Integer> table = new ArrayList<>(); // 模拟牌桌
        int turn = 0; // 模拟轮次，判断是玩家1还是玩家2的出牌回合

        while (!cardQ1.isEmpty() && !cardQ2.isEmpty()) {
            if (turn % 2 == 0) {
                int curCard = cardQ1.poll();
                if (table.isEmpty()) {
                    table.add(curCard);
                } else {
                    table.add(curCard);
                    for (int j = table.size() - 2; j >= 0; j--) {
                        if (table.get(j) == curCard) {
                            cardQ1.addAll(table.subList(j, table.size()));
                            table = new ArrayList<>(table.subList(0, j));
                            break;
                        }
                    }
                }
            } else {
                int curCard = cardQ2.poll();
                if (table.isEmpty()) {
                    table.add(curCard);
                } else {
                    table.add(curCard);
                    for (int j = table.size() - 2; j >= 0; j--) {
                        if (table.get(j) == curCard) {
                            cardQ2.addAll(table.subList(j, table.size()));
                            table = new ArrayList<>(table.subList(0, j));
                            break;
                        }
                    }
                }
            }
            turn++;
        }

        String winner;
        if (cardQ1.isEmpty()) {
            winner = "玩家2";
        } else {
            winner = "玩家1";
        }

        System.out.println("玩家1牌库:" + cardQ1);
        System.out.println("玩家2牌库:" + cardQ2);
        System.out.println("获胜者:" + winner);
        System.out.println("桌上牌堆" + table);
    }
}
