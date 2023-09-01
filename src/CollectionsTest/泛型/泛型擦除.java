package CollectionsTest.泛型;

import java.util.ArrayList;

public class 泛型擦除 {
    public static void main(String[] args) {
        ArrayList<String> l1 = new ArrayList<String>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        l1.add("1");
        l2.add(1);
        System.out.println(l1.get(0).getClass());
        System.out.println(l2.get(0).getClass());
        //注意是l1 和 l2 不是l1.get(0)和l2.get(0)
        //Java 中泛型在编译时会进行类型擦除，因此l1和 l2 类型擦除后的结果都是 java.util.ArrayList
        System.out.println(l1.getClass() == l2.getClass());
    }
}
