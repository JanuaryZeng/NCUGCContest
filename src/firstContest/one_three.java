package firstContest;

import java.util.Scanner;

public class one_three {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String string[] = str.split(",");
		String date[] = string[0].split("-");
		int day = Integer.parseInt(date[2]);
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int status = Integer.parseInt(string[1]);
		int Month1[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		int Month2[] = {0,31,29,31,30,31,30,31,31,30,31,30,31};
		int Month[];
		while(status!=0) {
			if(year%4==0||year%400==0&&year%100!=0) 
				Month = Month2;
			else
				Month = Month1;
			if(status>Month[month]-day) {
				status = status-Month[month]+day-1;
				day = 1;
				if(month<12) 
					month++;
				else {
					month = 1;
					year++;}
			}
			else {
				day = day + status;
				status = 0;
			}
			StringBuffer stb = new StringBuffer();
			stb.append(String.valueOf(year));
			stb.append("-");
			stb.append(String.valueOf(month));
			stb.append("-");
			stb.append(String.valueOf(day));
			System.out.print(stb);
			System.out.println(" "+status);
		}
	}
}
