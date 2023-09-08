package 一些面试常考的Java基础.atguigu.java;

import JAVA基础_反射.Student;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */
public class 高级数据结构的操作 {
    public static void main(String[] args) {
        //按照value从小到大排序，加入队列。相当于小根堆，-o.value就是大根堆
        //pq默认是小根堆
        //这个东西 图的克鲁斯卡尔会用到
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        Random random = new Random();
        for (int j = 0; j < 9; j++) {
            int s = random.nextInt(1,100);
            int e = random.nextInt(1,100);
            int v = random.nextInt(1,100);
            pq.add(new Edge(s,e,v));
        }
        while (!pq.isEmpty()){
            Edge edge = pq.poll(); //每次拿队头的元素，队头就是权值最小的边
            System.out.println(edge);
        }
        //二维数组的排序
        int[][] intervals = {{1,2},{2,3},{1,3},{4,2},{3,1}};
        //二维数组排序，按照o[1]从小到大排序
//        Arrays.sort(intervals,Comparator.comparing(o -> o[1]));
        //按照o[1]从大到小排序
        Arrays.sort(intervals,Comparator.comparing(o -> o[0]));
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
        //给HashMap排序，按照key或者value
        HashMap<Integer,Integer> map = new HashMap<>();
        int t = 5;
        while (t-- > 0){
            map.put(random.nextInt(1,100),random.nextInt(1,100));
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
        //map的排序需要借助treemap 直接把map作为参数传入即可
        //默认按照key从小大到排序
        TreeMap<Integer, Integer> treeMap1 = new TreeMap<>(map);
        System.out.println("按照key从小到大排序后:");
        for (Map.Entry<Integer, Integer> entry : treeMap1.entrySet()) {
            System.out.println(entry);
        }
        TreeMap<Integer, Integer> treeMap2 = new TreeMap<>(Comparator.reverseOrder());
        //如果要从大到小，那需要先传入比较器
        treeMap2.putAll(map);
        System.out.println("按照key从大到小排序后:");
        for (Map.Entry<Integer, Integer> entry : treeMap2.entrySet()) {
            System.out.println(entry);
        }
        //按value排序就有点复杂了。不建议使用treeMap，借助list遍历
        System.out.println("按照value从小到大排序后:");
        sort(map);
    }

    //方法二：获取map的entry集合
    public static void sort(Map<Integer, Integer> m1) {
        //将entry集合存入list
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(m1.entrySet());
        //从大到小
        list.sort(Map.Entry.comparingByValue(Comparator.comparingInt(o -> -o)));
        for (Map.Entry<Integer, Integer> entry : list) {
            System.out.println(entry);
        }
    }

}
class Edge{
    public int s;
    public int e;
    public int value;

    public Edge() {
    }

    public Edge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.value = v;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "s=" + s +
                ", e=" + e +
                ", value=" + value +
                '}';
    }
}
