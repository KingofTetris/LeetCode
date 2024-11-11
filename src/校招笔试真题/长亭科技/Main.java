package 校招笔试真题.长亭科技;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] peaches = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (st.hasMoreTokens()) {
                peaches[i] = Integer.parseInt(st.nextToken());
            }
        }

        int J = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < J; j++) {
            st = new StringTokenizer(br.readLine());
            if (st.hasMoreTokens()) {
                int type = Integer.parseInt(st.nextToken());
                if (st.hasMoreTokens()) {
                    int treeIndex = Integer.parseInt(st.nextToken());
                    if (st.hasMoreTokens()) {
                        int numPeaches = Integer.parseInt(st.nextToken());

                        if (type == 0) {
                            int sum = 0;
                            for (int k = treeIndex; k <= numPeaches; k++) {
                                sum += peaches[k];
                            }
                            sb.append(sum).append("\n");
                        } else if (type == 1) {
                            peaches[treeIndex] += numPeaches;
                        } else if (type == 2) {
                            peaches[treeIndex] -= numPeaches;
                        }
                    }
                }
            }
        }
        System.out.print(sb);
    }
}
