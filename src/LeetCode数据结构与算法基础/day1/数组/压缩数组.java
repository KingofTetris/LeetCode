package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2024/11/11
 */
import java.util.ArrayList;
import java.util.List;

public class 压缩数组 {

    public static void main(String[] args) {
        int[][] numsA = {{1, 2}, {28, 3}, {3, 1}};
        int[][] numsB = {{1, 3}, {28, 2}, {3, 1}};

        int[][] result = multiplyCompressedArrays(numsA, numsB);

        for (int[] pair : result) {
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
        }
    }

    public static int[][] multiplyCompressedArrays(int[][] numsA, int[][] numsB) {
        List<Integer> result = new ArrayList<>();

        int idxA = 0, idxB = 0;

        while (idxA < numsA.length || idxB < numsB.length) {
            int numA = (idxA < numsA.length) ? numsA[idxA][0] : 0;
            int freqA = (idxA < numsA.length) ? numsA[idxA][1] : 0;

            int numB = (idxB < numsB.length) ? numsB[idxB][0] : 0;
            int freqB = (idxB < numsB.length) ? numsB[idxB][1] : 0;

            int minFreq = Math.min(freqA, freqB);

            result.add(numA * numB);

            if (freqA > minFreq) {
                numsA[idxA][1] -= minFreq;
            } else {
                idxA++;
            }

            if (freqB > minFreq) {
                numsB[idxB][1] -= minFreq;
            } else {
                idxB++;
            }
        }

        // 将结果转换成二维数组
        int[][] finalResult = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            finalResult[i][0] = result.get(i);
            finalResult[i][1] = 1;
        }

        return finalResult;
    }
}
