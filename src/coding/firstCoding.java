package coding;
import java.util.Scanner;

public class firstCoding {
	public static int getSum(int num1, int num2) {
        String len;
        int sum = 0;
        int length;
        int temp;
        int number;
		for (int i = num1 ; i <=num2; i++){
            number = i;
            len = String.valueOf(i);
            length = len.length();
            while(length>0){
            	temp = (int) (number/Math.pow(10,(length-1)));
                sum += temp;
                number -= temp*Math.pow(10,(length-1));
                length--;
            }
		}
		return sum;
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println(getSum(a,b));
		}
	}
}
