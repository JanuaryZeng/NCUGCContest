package green_cal_11_11;

public class one_four {
	 public static void aiXin2(){
		   String tmp = "I love you!";
		   char t [] = tmp.toCharArray();
		   int i = 0;
	          for(float y = (float) 1.3;y>=-1.1;y -=0.06)  { 
	        	  i=0;
	              for(float x= (float) -1.1;x<=1.1;x+= 0.025){
	            	  
	                    float a = (float) (5.0*y/4.0-Math.sqrt(Math.abs(x)));  
	                  
	                    if((a*a+x*x)-1<=1e-6)  {    
	                           System.out.print(t[i]); 
	                           i++;
	                           if(i==t.length)
	                        	   i=0;
	                    }  
	                   
	                    else  
	                           System.out.print(" ");  
	              }  
	              System.out.println();  
	       }  
	         
	    }
	 public static void main(String[]args) {
		 aiXin2();
	 }
}
