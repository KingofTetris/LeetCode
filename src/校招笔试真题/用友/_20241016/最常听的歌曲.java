package 校招笔试真题.用友._20241016;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/10/16
 */
public class 最常听的歌曲 {

    @Test
    public void test(){
        int[] songs = {7, 3, 7, 2, 3, 4, 3, 7, 7, 1, 8, 4, 7, 4, 8};
        int k = 3;
        int[] res = favoriteSongs(songs, k);
        System.out.println(Arrays.toString(res));
    }

    //songs表示歌曲的编号数组，每听一次就出现一次
    //现在想知道最常听的k首歌，歌曲编号从小到大排序。
    public int[] favoriteSongs(int[] songs, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int song : songs) {
            freqMap.put(song, freqMap.getOrDefault(song, 0) + 1);
        }
        // topK
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ?
                        a.getKey() - b.getKey() : b.getValue() - a.getValue()
        );
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.offer(entry);
        }
        // 取出topK
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }
        //从小到大排序编号
        Arrays.sort(result);
        return result;
    }

}
