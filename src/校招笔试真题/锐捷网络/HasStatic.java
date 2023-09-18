package 校招笔试真题.锐捷网络;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class HasStatic {

    private static int x = 100;
    //static只会初始化一份。所有的对象都共享同一份。
    public static void main(String[] args) {
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);
    }
    @Test
    public void test(){
        String s = null;
       /* if ((s==null)&&(s.length()>0)){

        }*/
        if ((s==null)||(s.length()>0)){

        }
    }
}
