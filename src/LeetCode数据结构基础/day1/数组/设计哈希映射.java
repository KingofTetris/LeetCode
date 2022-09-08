package LeetCode数据结构基础.day1.数组;

/**
 * @author KingofTetris
 * @File 设计哈希映射
 * @Time 2021/10/11  17:21
 */
/*706. 设计哈希映射
        不使用任何内建的哈希表库设计一个哈希映射（HashMap）。

        实现 MyHashMap 类：

        MyHashMap() 用空映射初始化对象
        void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
        int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
        void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。


        示例：

        输入：
        ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
        [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
        输出：
        [null, null, null, 1, -1, null, 1, null, -1]

        解释：
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]


        提示：

        0 <= key, value <= 106
        最多调用 104 次 put、get 和 remove 方法

        提示：

        0 <= key, value <= 10^6
        最多调用 10^4 次 put、get 和 remove 方法

        进阶：你能否不使用内置的 HashMap 库解决此问题？*/
//思路1：用一个超大一维数组模拟哈希表 一个index对应一个Key
//优点：查找和删除的性能非常快，只用访问 1 次数组；
//缺点：使用了非常大的空间，当元素范围很大时，无法使用该方法；当存储的元素个数较少时，
//        性价比极低；需要预知数据的取值范围。

/*public class 设计哈希映射 {
    int N = 10000009;

    int[] map;
    public 设计哈希映射() {
        map = new int[N];
        //全部初值为-1表示空
        Arrays.fill(map,-1);
    }

    public void put(int key, int value) {
        map[key] =value;
    }

    public int get(int key) {
        return map[key];
    }

    public void remove(int key) {
        //把这一行设置长null就行了
        map[key] = -1;
    }
}*/

//思路2：拉链法
//
public class 设计哈希映射{

}