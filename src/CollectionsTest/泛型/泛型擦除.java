package CollectionsTest.泛型;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class 泛型擦除 {
    public static void main(String[] args) {
        ArrayList<String> l1 = new ArrayList<String>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        l1.add("1");
//        l1.add(18);
        l2.add(1);
        System.out.println(l1.get(0).getClass());
        System.out.println(l2.get(0).getClass());
        //注意是l1 和 l2 不是l1.get(0)和l2.get(0)
        //Java 中泛型在编译时会进行类型擦除，因此l1和 l2 类型擦除后的结果都是 java.util.ArrayList
        System.out.println(l1.getClass() == l2.getClass());
        Class clazz = l1.getClass();
        try {
            Method add = clazz.getMethod("add", Object.class);
            add.invoke(l1,18);//利用反射 泛型擦除的特性 跳过编译时异常。
            System.out.println(l1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
