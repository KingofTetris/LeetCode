package 校招笔试真题.三七互娱;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/17
 */
public class 矩阵和圆是否相交 {

    @Test
    public void test(){
        boolean circleCover = isCircleCover(0, 0, 2.5f, 3.1f, 5, 3, 3);
        System.out.println(circleCover);
    }
    public boolean isCircleCover(float x,float y,float L,float W,float cx,float cy,float r){
        float left = x - L / 2;
        float right = x + L / 2;
        float top = y + W / 2;
        float bottom = y -  W / 2;
        //四个有一个成立，就不会相交。
        if (cy - top > r || bottom - cy > r || left - cx > r || cx - right > r) return false;
        return true;
    }
}
