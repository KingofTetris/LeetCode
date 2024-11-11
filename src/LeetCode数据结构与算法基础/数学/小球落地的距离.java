package LeetCode数据结构与算法基础.数学;

public class 小球落地的距离 {

    /**
     * 某个小球从N米的高度掉落，每次落地反弹回原来高度的1/M
     * 计算其在第Q次落地时共经历了多少米;
     * 默认值:N=100，M=2，Q=10
     * @param args
     */
    public static void main(String[] args) {
        int totalDistance = totalDistance(100, 2, 10);
        System.out.println(totalDistance);
    }

    public static int totalDistance(int N, int M, int Q) {
        int totalDistance = 0;
        for (int i = 0; i < Q; i++) {
            totalDistance += N;  // 落地
            N /= M;  // 弹起
            if (i != Q - 1) {  // 最后一次不算反弹距离
                totalDistance += N;  // 反弹
            }
        }

        return totalDistance;
    }
}
