/*
package 算法设计与分析.重构三角形;

import java.util.Scanner;

*/
/**
 * @author KingofTetris
 * @File 重构三角形
 * @Time 2021/9/29  17:51
 *//*


*/
/*#include<bits/stdc++.h>
        using namespace std;
        typedef long long ll;
        typedef pair<ll,ll> pii;
        pii operator + (const pii &a,const pii &b) { return pii(a.first+b.first,a.second+b.second); }
        pii operator - (const pii &a,const pii &b) { return pii(a.first-b.first,a.second-b.second); }
        int main(){
        pii Pnt[5];
        for(int i=1;i<=3;i++) cin>>Pnt[i].first>>Pnt[i].second;
        pii Ans[5];
        Ans[1]=Pnt[1]+Pnt[2]-Pnt[3];
        Ans[2]=Pnt[1]+Pnt[3]-Pnt[2];
        Ans[3]=Pnt[2]+Pnt[3]-Pnt[1];
        sort(Ans+1,Ans+4);
        for(int i=1;i<=3;i++)
        cout<<Ans[i].first<<" "<<Ans[i].second<<endl;
        return 0;
        }*//*


class Point{
    long first,second;
    public Point(long a,long b){
        first = a;
        second = b;
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point[] p = new Point[3];
        for(int i=0;i<3;i++){
            p[i]  = new Point(sc.nextLong(),sc.nextLong());
        }
        reshapeTrangle(p);
    }

    public static void reshapeTrangle(Point[] p){
        Point[] ans = new Point[3];
        ans[0] = minus(add(p[0],p[1]),p[2]);
        ans[1] = minus(add(p[0],p[2]),p[1]);
        ans[2] = minus(add(p[1],p[2]),p[0]);
        sort(ans);
        for (int i = 0; i < 3; i++) {
            System.out.println(ans[i].first +" " +ans[i].second);
        }
    }

    public static Point add(Point a, Point b){
        return new Point(a.first+b.first,a.second+b.second);
    }

    public static Point minus(Point a, Point b){
        return new Point(a.first-b.first,a.second-b.second);
    }

    public static void sort(Point[] p){
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j +1 < p.length - i ; j++) {
                if(p[j].first > p[j+1].first){
                    Point temp  = p[j];
                    p[j] = p[j+1];
                    p[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j +1 < p.length - i ; j++) {
                if(p[j].second > p[j+1].second){
                    Point temp  = p[j];
                    p[j] = p[j+1];
                    p[j+1] = temp;
                }
            }
        }
    }
}
*/
