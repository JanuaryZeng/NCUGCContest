package coding;

import java.util.Random;

public class two_one {
	public static  int[] dailyTemps(int[] temperatures){
		int len = temperatures.length;
        int[] number = new int[len];
		for (int i = 0; i < len-1;i++){
        	int j = i +1;
            while(j<len && temperatures[j]-temperatures[i]<=0){
            	j++;
            }
            if(j==len){
            	number[i] = 0;
            }
            else{
            	number[i] = j - i;
            }    
        }
		return number;
	}
	public static void main(String[] args) {
		int[] a = new int[10];
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			a[i] = random.nextInt(10);
		}
		
		int[] b =dailyTemps(a);
		for(int i = 0; i < 10; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
		for(int i = 0; i < 10; i++) {
			System.out.print(b[i]+" ");
		}
	}
}
