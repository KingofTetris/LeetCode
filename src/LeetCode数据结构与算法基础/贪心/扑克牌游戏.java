package LeetCode数据结构与算法基础.贪心;

import java.util.*;


/**
 * 模拟扑克牌：每次只能出四张一样的，或对子，或三个一样的，或三带一，
 * <p>
 * 给你一个序列，请问最少几次可以把手里的牌出完。
 */
public class 扑克牌游戏 {

    public static void main(String[] args) {
        int[] cards = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 2, 5}; // 示例扑克牌序列
        int minTurns = minTurnsToFinish(cards);
        System.out.println("最少需要 " + minTurns + " 次可以将所有牌出完。");
    }

    public static int minTurnsToFinish(int[] cards) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            map.put(cards[i], map.getOrDefault(cards[i], 0) + 1);
        }
        //map没法排序，只能堆Map.Entry排序，把entry放入list中进行排序。
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        int turns = 0;//需要的回合数
        while (!list.isEmpty()) {
            Map.Entry<Integer, Integer> entry = list.get(0);
            if (entry.getValue() == 4 || entry.getValue() == 2) {
                turns++;
                list.remove(entry);
            }
            if (entry.getValue() == 3) {
                turns++;
                //尽量3带1
                Map.Entry<Integer, Integer> minEntry = list.get(list.size() - 1);
                if (minEntry.getValue() == 1) {
                    list.remove(entry);
                    list.remove(minEntry);
                }
            }
            if (entry.getValue() == 1) {
                turns++;
                list.remove(entry);
            }
        }
        return turns;
    }

}
