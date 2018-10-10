package coding;

import java.io.File;

public class thirdCoding {
	static int number = 0;
	public static void traverseFolder2(String path) {
        File file = new File(path);
        int n = number;
        while(n > 0) {
        	System.out.print("  ");
        	n--;
        }
        System.out.println("+--" + file.getName());
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                    	number++;
                    	traverseFolder2(file2.getAbsolutePath());
                        number--;
                    } else {
                        String filename = file2.getName();
                        String str = filename.substring(filename.lastIndexOf(".") + 1);
                        if("jpg".equals(str) || "png".equals(str) || "bmp".equals(str)) {
                        	n = number+1;
                            while(n > 0) {
                            	System.out.print("  ");
                            	n--;
                            }
                            System.out.println("--" + file2.getName());
                        }
                    }
                }
            }
        }
    }
    
	public static void main(String args[]) {
		traverseFolder2("D:\\大学\\大三上\\比赛\\绿色计算机大赛\\dir");
	}
}
