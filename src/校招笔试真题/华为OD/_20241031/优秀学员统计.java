package 校招笔试真题.华为OD._20241031;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class 优秀学员统计 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] count = new int[30];
        for (int i = 0; i < 30; i++) {
            count[i] = sc.nextInt();
        }
        // key记录员工id, value记录员工打卡天数
        HashMap<Integer, Integer> freq = new HashMap<>();
        // key记录员工id，value记录员工第一次打卡是哪天
        HashMap<Integer, Integer> first = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < count[i]; j++) {
                int id = sc.nextInt();

                freq.put(id, freq.getOrDefault(id, 0) + 1);
                first.putIfAbsent(id, i);
            }
        }
        StringJoiner sj = new StringJoiner(" ");
        freq.keySet().stream().sorted((idA, idB) -> {
            int freqA = freq.get(idA);
            int freqB = freq.get(idB);
            // 打卡天数多的员工排在前面
            if (freqA != freqB) {
                return freqB - freqA;
            }
            int firstA = first.get(idA);
            int firstB = first.get(idB);
            if (firstA != firstB) {
                // 首次打卡早的员工排在前面
                return firstA - firstB;
            } else {
                // id小的员工排在前面
                return idA - idB;
            }
        }).limit(5).forEach(id -> sj.add(id + "")); // 取打卡前5名
        System.out.println(sj);
    }
}
