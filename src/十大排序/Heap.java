package 十大排序;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 * 这一个自建的堆数据结构 而且是一个大根堆
 * 实际上API自带的优先队列 PriorityQueue 就是堆。而且是小根堆
 */
public class Heap<T extends Comparable<T>> {

    /**
     * 堆中某个节点的值总是大于等于或小于等于其子节点的值，并且堆是一颗完全二叉树。
     *
     * 堆可以用数组来表示，这是因为堆是完全二叉树，而完全二叉树很容易就存储在数组中。
     * 位置 k 的节点的父节点位置为 k/2，而它的两个子节点的位置分别为 2k 和 2k+1。
     * 这里不使用数组索引为 0 的位置，是为了更清晰地描述节点的位置关系。
     */
    private T[] heap;
    private int N = 0;

    public Heap(int maxN) {
        this.heap = (T[]) new Comparable[maxN + 1];//maxN + 1 取n+1 0舍去不要。方便描述
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void swap(int i, int j) {
        T t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    /**
     * 上浮
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            swap(k, j);
            k = j;
        }
    }

    /**
     * 插入元素
     * @param v
     */
    public void insert(Comparable v) {
        heap[++N] = (T) v;
        swim(N);
    }

    /**
     * 删除元素
     */
    public T delMax() {
        T max = heap[1];
        swap(1, N--);
        heap[N + 1] = null;
        sink(1);
        return max;
    }


}
