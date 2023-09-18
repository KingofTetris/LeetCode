package 校招笔试真题.携程.科大讯飞;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/15
 */

class Person {
    public int yw;
    public int gt;

    Person(int yw, int gt) {
        this.yw = yw;
        this.gt = gt;
    }

    public int getYw() {
        return yw;
    }

    public int getGt() {
        return gt;
    }

    @Override
    public String toString() {
        return "Person{" +
                "yw=" + yw +
                ", gt=" + gt +
                '}';
    }
}

//如果你不用Comparator，就自己实现Comparator接口。
class PersonComparator implements Comparator<Person> {
    @Override
    //按照yw从小到大排序
    public int compare(Person p1, Person p2) {
        if (p1.yw - p2.yw != 0){
            return p1.yw - p2.yw;
        }else {
            return p1.gt - p2.gt;
        }
    }
}
public class 非凡计划 {

    @Test
    public void test() {
        ArrayList<Person> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int next1 = random.nextInt(101);
            int next2 = random.nextInt(101);
            arrayList.add(new Person(next1, next2));
        }

        for (Person person : arrayList) {
            System.out.println(person);
        }

        //按照yw从小到大排序，如果相同，按照gt从小大到排序
//        arrayList.sort(Comparator.comparing(o -> o.yw));
        arrayList.sort(Comparator.comparing(Person::getYw).thenComparing(Person::getGt));
        //不能写成先后关系的话，不能用o->o.xx
//        arrayList.sort(Comparator.comparing(o -> o.yw).thenComparing(o -> o.gt));
        arrayList.sort(Comparator.comparing((Person p) -> p.getYw())
                        .thenComparing((Person p) -> p.getGt())
        );

        System.out.println();
        for (Person person : arrayList) {
            System.out.println(person);
        }
    }

    @Test
    public void test2(){
        //接口
        Random random = new Random();
        ArrayList<Person> arrayList1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int next1 = random.nextInt(101);
            int next2 = random.nextInt(101);
            arrayList1.add(new Person(next1, next2));
        }
        for (Person person : arrayList1) {
            System.out.println(person);
        }
        arrayList1.sort(new PersonComparator());
        System.out.println();
        for (Person person : arrayList1) {
            System.out.println(person);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] ps = new int[n][2];
        for (int i = 0; i < n; i++) {
            ps[i][0] = sc.nextInt();
            ps[i][1] = sc.nextInt();
        }
        List<List<Integer>> groups = new ArrayList<>(); // 保存分组结果
        boolean[] visited = new boolean[ps.length]; // 标记成员是否已分组
        if (isPossible(ps, visited, groups, 0)) {
            for (List<Integer> group : groups) {
                System.out.print(group.size() + " ");
            }
            System.out.println();
            for (List<Integer> group : groups) {
                for (Integer integer : group) {
                    System.out.println(integer + 1 + " ");
                }
            }
        } else {
            System.out.println("-1");
        }
    }

    private static boolean isPossible(int[][] ps, boolean[] visited, List<List<Integer>> groups, int index) {
        if (index == ps.length) {
            return true; // 成员全部分组完成
        }
        for (int i = 0; i < groups.size(); i++) {
            List<Integer> group = groups.get(i);
            int ywSum = 0;
            int gtSum = 0;
            for (int memberIndex : group) {
                ywSum += ps[memberIndex][0];
                gtSum += ps[memberIndex][1];
            }
            if (ywSum + ps[index][0] == gtSum + ps[index][1]) {
                group.add(index);
                visited[index] = true;
                if (isPossible(ps, visited, groups, index + 1)) {
                    return true;
                }
                group.remove(group.size() - 1);
                visited[index] = false;
            }
        }
        // 创建新的分组
        List<Integer> newGroup = new ArrayList<>();
        newGroup.add(index);
        groups.add(newGroup);
        visited[index] = true;
        if (isPossible(ps, visited, groups, index + 1)) {
            return true;
        }
        groups.remove(groups.size() - 1);
        visited[index] = false;
        return false;
    }

}
