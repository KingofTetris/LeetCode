package 校招笔试真题.锐捷网络;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class Test2 {
    static Map<String,Object> datas = new ConcurrentHashMap<>();

    public static void main(String[] args) {

    }

    public static Object get(String key){
        Object o = null;
        if (!datas.containsKey(key)){
            o = createObject(key);
            datas.put(key,o);
        }else {
            o = datas.get(key);
        }
        return o;
    }


    public static Object createObject(String key){
        return new Object();
    }

    @Test
    public void test(){
        System.out.println(new Integer(100) == new Integer(100));//false
        System.out.println(5%2==1?false:true);//false
        System.out.println(new String("abc")==new String("abc"));//false
        System.out.println("abc".equals("ab" + "c"));//true
    }
}
