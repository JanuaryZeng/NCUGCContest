package nullKe_11_2;

import java.util.Scanner;

public class first {
	private static int[] Month = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	private static int[] MonthR = {0,31,29,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numb = sc.nextInt();
		int []number = new int[numb];
		for(int i = 0; i < numb; i++) {
			String str1 = sc.next();
			String str2 = sc.next();
			String [] str3 = str1.split("-");
			String [] str4 = str2.split("-");
			int yearStart = Integer.parseInt(str3[0]);
			int yearEnd = Integer.parseInt(str4[0]);
			int temp = 0;
			if(yearStart==yearEnd) {
				StringBuffer sb1 = new StringBuffer();
				sb1.append(str3[0]);
				sb1 = sb1.reverse();
				int number1 = Integer.parseInt(sb1.toString());
				StringBuffer sb2 = new StringBuffer();
				sb2.append(str3[1]);
				sb2.append(str3[2]);
				int number2 = Integer.parseInt(sb2.toString());
				StringBuffer sb3 = new StringBuffer();
				sb3.append(str4[1]);
				sb3.append(str4[2]);
				int number3 = Integer.parseInt(sb3.toString());
				if(number1>=number2&&number1<=number3)
					temp++;
			}
			if(judge(yearStart)&&yearStart!=yearEnd) {
				StringBuffer sb1 = new StringBuffer();
				sb1.append(str3[0]);
				sb1 = sb1.reverse();
				int number1 = Integer.parseInt(sb1.toString());
				StringBuffer sb2 = new StringBuffer();
				sb2.append(str3[1]);
				sb2.append(str3[2]);
				int number2 = Integer.parseInt(sb2.toString());
				if(number1>=number2)
					temp++;
			}
			if(judge(yearEnd)&&yearStart!=yearEnd) {
				StringBuffer sb1 = new StringBuffer();
				sb1.append(str4[0]);
				sb1 = sb1.reverse();
				int number1 = Integer.parseInt(sb1.toString());
				StringBuffer sb3 = new StringBuffer();
				sb3.append(str4[1]);
				sb3.append(str4[2]);
				int number3 = Integer.parseInt(sb3.toString());
				if(number1<=number3)
					temp++;
			}

			for(int j = yearStart+1; j <yearEnd; j++) {
				if(judge(j))
					temp++;
			}

			number[i] = temp;
		}
		for(int i = 0; i < numb; i++) 
			System.out.println(number[i]);
	}
	
	public static boolean judge(int year) {
		String str = String.valueOf(year);
		char c4;
		char c3;
		char c2;
		if(str.length()<=3)
			 c4 = '0';
		else
			c4 = str.charAt(3);
		if(str.length()<=2)
			c3 = '0';
		else
			c3 = str.charAt(2);
		if(str.length()<=1)
			c2 = '0';
		c2 = str.charAt(1);
		char c1 = str.charAt(0);
		StringBuffer sb1 = new StringBuffer();
		sb1.append(c4);
		sb1.append(c3);
		String str1 = sb1.toString();
		int month = Integer.parseInt(str1);
		StringBuffer sb2 = new StringBuffer();
		sb2.append(c2);
		sb2.append(c1);
		String str2 = sb2.toString();
		int day = Integer.parseInt(str2);
		if(month>12 || month<1)
			return false;
		else {
			if(year%4==0 && year%100!=0 || year%400==0) {
				if(day>MonthR[month] || day <1)
					return false;
				else
					return true;
			}
			else {
				if(day>Month[month] || day <1)
					return false;
				else
					return true;
			}
		}
	}
}

