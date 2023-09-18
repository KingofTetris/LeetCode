package 校招笔试真题.锐捷网络;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */

/**
 * 在Java中，方法参数按值传递。这意味着在调用方法时，方法参数会被复制一份，
 * 然后将副本传递给方法。所以在test方法中修改参数a的值并不会影响到main方法中的原始变量a。
 *
 * 在main方法中，初始值a为1，然后执行a += 10;语句，将a的值增加为11。接着调用test(a);方法，
 * 将a的值作为参数传递给test方法。
 *
 * 在test方法中，参数a被复制为一个新的变量，也被命名为a，并将其值设置为10。
 * 这个修改只影响到了test方法内部的局部变量a，而不会影响到main方法中的原始变量a。
 *
 * 因此，当回到main方法后，执行System.out.println(a);语句输出的仍然是原始变量a的值，即11。
 *
 * 总结起来，Java中方法参数按值传递，所以在test方法中修改参数的值并不会影响到调用方法的原始变量。
 */
public class test3 {
    public static void main(String[] args) {
        int  a = 1;
        a += 10;
        test(a);
        System.out.println(a);
    }

    public static void test(int a){
        a = 10;
    }
}
