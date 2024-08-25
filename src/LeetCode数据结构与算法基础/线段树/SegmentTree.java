package LeetCode数据结构与算法基础.线段树;

import java.util.ArrayList;


/**
 * 给出线段树的模板
 * 当需要频繁查询区间最值，频繁更新区间的时候，就可以用线段树
 * <p>
 * 动态区间删减，并且需要查询区间最大值，该特性可以使用线段树实现。
 */

// 线段树结构体，用于处理区间查询和更新
public class SegmentTree {

    int size; // 数据数组的大小
    ArrayList<Integer> tree; // 线段树数组
    ArrayList<Integer> data; // 存储元素的数据数组


    // 初始化线段树，传入数据长度和数据
    void init(int len, ArrayList<Integer> d) {
        size = len;
        tree = new ArrayList<>(4 * size); // 分配足够大小的线段树数组 一定要4N 不然会溢出
        for (int i = 0; i < 4 * size; i++) {
            tree.add(0);
        }
        data = d; // 保存传入的数据
    }

    // 构建线段树
    void build(int node, int start, int end) {
        if (start == end) {
            // 如果是叶节点，保存索引
            tree.set(node, start);
        } else {
            int mid = (start + end) / 2;
            // 递归构建左右子树
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            // 合并结果，根据条件选择较大值或索引较小者
            tree.set(node, compare(tree.get(2 * node), tree.get(2 * node + 1)));
        }
    }

    // 比较两个索引对应的值，返回较大值或索引较小者
    int compare(int idx1, int idx2) {
        if (data.get(idx1) > data.get(idx2) || (data.get(idx1).equals(data.get(idx2)) && idx1 < idx2)) {
            return idx1;
        }
        return idx2;
    }


    // 更新线段树中的一个元素
    void update(int node, int start, int end, int idx, int value) {
        // 如果是叶节点，直接更新
        if (start == end) {
            data.set(idx, value);
            tree.set(node, idx);
        } else {
            int mid = (start + end) / 2;
            // 递归更新对应的子树
            if (idx <= mid) {
                update(2 * node, start, mid, idx, value);
            } else {
                update(2 * node + 1, mid + 1, end, idx, value);
            }
            // 合并结果
            tree.set(node, compare(tree.get(2 * node), tree.get(2 * node + 1)));
        }
    }


    // 查询区间[l, r]内的最大值索引
    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return -1; // 如果区间不重叠，返回-1
        }
        if (l <= start && end <= r) {
            return tree.get(node); // 如果区间完全包含，返回当前节点结果
        }
        int mid = (start + end) / 2;
        // 递归查询左右子树并合并结果
        int leftResult = query(2 * node, start, mid, l, r);
        int rightResult = query(2 * node + 1, mid + 1, end, l, r);
        if (leftResult == -1) return rightResult;
        if (rightResult == -1) return leftResult;
        return compare(leftResult, rightResult);
    }

    // 添加元素到线段树中（即更新元素值）
    void add(int idx, int value) {
        update(1, 1, size, idx, value);
    }

    // 从线段树中移除元素（即将值设为-1）
    void remove(int idx) {
        update(1, 1, size, idx, -1);
    }

    // 获取区间[l, r]内的最大值索引
    int getMaxIndex(int l, int r) {
        return query(1, 1, size, l, r);
    }
}
