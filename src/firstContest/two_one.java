package firstContest;

import java.math.BigInteger;

public class two_one {
	public static void main(String[]args) {
		int [] numb = new int[10000];
		int [] number = new int[3];
		boolean [] bool = new boolean[10001];
		for(int i = 0; i <=10000;i++) {
			bool[i] = true;
		}
		for(int j = 0; j <10000;j++) {
			bool[numb[j]] = false;
		}
		int i = 0;
		for(int j = 0; j <10000;j++) {
			if(bool[j]) {
				number[i] = j;
				i++;
			}
		}
        for(int k=0; k < 3; k++){
            for(int j = 0; j < number.length-1-k; j++){
                if(number[j] > number[j+1]){
                    int temp = number[j];
                    number[j] = number[j+1];
                    number[j+1] = temp;
                }
            }
        }
        String string = String.valueOf(number[0])+String.valueOf(number[1])+String.valueOf(number[2]);
        BigInteger a = new BigInteger(string);
        BigInteger b = new BigInteger("11");
        BigInteger c = a.mod(b);
        System.out.println(Integer.valueOf(c.toString()));
	}
}
