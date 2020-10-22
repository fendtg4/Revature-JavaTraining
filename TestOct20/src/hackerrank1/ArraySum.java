package hackerrank1;

import java.util.Arrays;
import java.util.Scanner;

 public class ArraySum {

     public static int simpleArraySum(int length, int[] ar) {
         int sum = 0;
       
         for (int i=0;i<length;i++) {
            sum+=ar[i];
         }
         return sum;
     }
     public static void main (String[] args) {
        
    	
        Scanner sc = new Scanner(System.in);
        
        int n=sc.nextInt();
        
        int length = n;
        int[] ar = new int[length];
        for (int i=0;i<length;i++) {
        	ar[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(simpleArraySum(length, ar));
     }
 }