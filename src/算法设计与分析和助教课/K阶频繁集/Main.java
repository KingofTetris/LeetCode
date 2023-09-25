package 算法设计与分析和助教课.K阶频繁集;




/*import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] numbers = input.nextLine().split(" ");
        int n=Integer.parseInt(numbers[0]);
        int k=Integer.parseInt(numbers[1]);
        String str[]=new String[n];
        ArrayList<String> arr=new ArrayList<String>();
        for(int i=0;i<n;++i) {
            str[i]=input.nextLine();
        }
        input.close();
        if(k>=2) {
            for(int i=0;i<n;++i) {
                for(int j=i+1;j<n;j++) {
                    String a=str[i].substring(0,k-1);
                    String b=str[j].substring(0,k-1);
                    if(a.equals(b)) {
                        if(str[i].charAt(k-1)<str[j].charAt(k-1))
                            arr.add(str[i]+str[j].substring(k-1));
                        else
                            arr.add(str[j]+str[i].substring(k-1));
                    }
                }
            }
            System.out.println(arr.size());
            Collections.sort(arr);
            for(int i=0;i<arr.size();++i) {
                System.out.println(arr.get(i));
            }
        }
        else
            System.out.println("a[1...k-2]表示空字符串");

    }
}*/
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String[] lk = new String[n];
        for (int i = 0; i < n; i++) {
            lk[i] = sc.next();
        }
        sc.close();
        String[] res = deleteArrayNull(aprioriGen(lk));
        System.out.println(res.length);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
    public static String[] aprioriGen(String[] lk){
        String[] res = new String[100];
        int count = 0;
        for (int i = 0; i < lk.length; i++) {
            for (int j = i + 1; j < lk.length; j++) {
                res[count] = GenString(lk[i],lk[j]);
                count++;
            }
        }
        return res;
    }

    public static String GenString(String a,String b){
        for (int i = 0; i < a.length(); i++) {
            if(i<a.length() - 1){
                if(a.charAt(i) != b.charAt(i)){
                    break;
                }
            }
            else if(a.charAt(i) - 'a' < b.charAt(i) - 'a'){
                 return (a + b.charAt(i));
            }
        }
        return "";
    }

//**
//     * 去除String数组中的空值

    public static String[] deleteArrayNull(String string[]) {
        String strArr[] = string;

        // step1: 定义一个list列表，并循环赋值
        ArrayList<String> strList = new ArrayList<String>();
        for (int i = 0; i < strArr.length; i++) {
            strList.add(strArr[i]);
        }

        // step2: 删除list列表中所有的空值
        while (strList.remove(null));
        while (strList.remove(""));

        // step3: 把list列表转换给一个新定义的中间数组，并赋值给它
        String strArrLast[] = strList.toArray(new String[strList.size()]);
        return strArrLast;
    }
}
