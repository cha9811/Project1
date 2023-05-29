package test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int control;
        int j =0;
        int k  =0;
        for (int i = 0; i < a+1; i++) {
            int b = sc.nextInt();
            String c = sc.next();

            for (j =0; j<b; j++){
                for(k =0; k<b; k++){
                    System.out.print(c.charAt(j));
                }
            }
            j = 0;
            k = 0;
        }
    }
}