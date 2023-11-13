package 校招笔试真题.阅文集团;

/**
 * @author by KingOfTetris
 * @date 2023/11/3
 */
public class 回文数判断 {

    public Boolean palindromeNumber(int number) {

        //write your code here......
        String s = String.valueOf(number);
        char[] chars = s.toCharArray();
        int left = 0,right = chars.length - 1;
        while (left < right){
            if (chars[left] == chars[right]){
                left++;
                right--;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
