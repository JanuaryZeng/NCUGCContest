package coding;

import java.util.Scanner;

public class two_two {
	public static int gongyue(int a, int b) {
		if(a%b==0)
			return b;
		else {
			int c= gongyue(b,a%b);
			return c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int length = sc.nextInt();	//³¤
		int width = sc.nextInt();	//¿í
		int numb = gongyue(length, width);
		System.out.println(numb);
		int mutli = length * width / (numb*numb);
		System.out.println(mutli);
	}
}
