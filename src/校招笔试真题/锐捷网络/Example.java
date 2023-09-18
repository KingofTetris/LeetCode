package 校招笔试真题.锐捷网络;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class Example {
    String str = new String("good");
    char[] ch = {'a','b','c'};
    public static void main(String[] args) {
       Example ex = new Example();
       ex.change(ex.str,ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }

    public void change(String str,char[] ch){
        str = "test ok";
        ch[0] = 'g';
    }
}
