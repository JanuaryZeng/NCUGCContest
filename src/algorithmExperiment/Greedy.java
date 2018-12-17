package algorithmExperiment;

/*
 * 用贪心算法解背包问题
 */
public class Greedy {
    public static void main(String[] args) {
        // 单位重量价值分别为:10 5 7 6 3 8 90 100
        double w[] = { 0, 50, 80, 30, 40, 20, 60, 10 ,1};//物体的重量

        double v[] = { 0, 500, 400, 210, 240, 60, 480, 900,100 };//物体的价值

        double M = 170;// 背包所能容纳的重量

        int n = w.length - 1;// 物体的个数

        double[] x = new double[n + 1];// 每个物体装进的比例,大于等于0并且小于等于1

        f(w, v, M, n, x);//调用贪心算法函数

        System.out.println("排序后的物体的重量:");
        for(int i=1;i<=n;i++){
            System.out.print(w[i]+"\t");
        }
        System.out.println();

        System.out.println("排序后的物体的价值:");
        for(int i=1;i<=n;i++){
            System.out.print(v[i]+"\t");
        }

        double[]t=new double[n+1];//定义一个数组表示单位重量物体的价值
        for(int i=1;i<=n;i++){
            t[i]=v[i]/w[i];
        }
        //用冒泡排序对double[]t进行排序(大的在前)
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n-i;j++){
                if(t[j]<t[j+1]){
                    double temp=t[j];
                    t[j]=t[j+1];
                    t[j+1]=temp;
                }
            }
        }
        System.out.println();

        System.out.println("排好序后的单位物体的价值: ");
        for(int i=1;i<=n;i++){
            System.out.print(t[i]+"\t");
        }

        double maxValueSum=0;   //用来存放背包能装下的物体的最大价值总和
        for(int i=1;i<x.length;i++){
            maxValueSum+=x[i]*v[i];
        }
        System.out.println();

        System.out.println("排序后每个物体装进背包的比例:");
        for(int i=1;i<=n;i++){
            System.out.print(x[i]+"\t");
        }
        System.out.println();

        System.out.println("背包能装下的物体的最大价值总和为: "+maxValueSum);

    }

    /**
     * 
     * @param w 物体的重量
     * @param v 物体的价值
     * @param M 背包的容量
     * @param n 物体的个数
     * @param x 每个物体装进背包的比例,取值0<=x[i]<=1,(1<=i<=n)
     */
    private static void f(double[] w, double[] v, double M, int n, double[] x) {
        sort(w, v, n);// 首先按照物体的单位重量的价值进行排序,单位重量价值大的排在前面

        double c = M;   //背包剩余的容量,刚开始时还没用装东西,为M
        int i;//表示第几五物体
        for (i = 1; i <= n; i++) {
            if (w[i] <= c){//如果背包剩余的容量大于等于第i个物体的重量
            x[i] = 1;   //把第i个物体整个装进背包
            c -= w[i];  //背包的剩余容量减少了第i个物体的重量
            }else {     
                break;  //退出循环
            }
        }
        if (i <= n){//判断是否第n个物体整个装进去背包里了,如果i<=n表示否定
            x[i] = c / w[i];    
        }
    }

    /*
     * 可以按冒泡排序来写，先计算W中每个物体单位价值存成一个数组，然后冒泡排序，
     * 若有元素交换，对应W中相同标号的元素也交换就可以了
     */
    private static void sort(double[] w, double[] v, int n) {
        double []t=new double[n+1];
        for(int i=1;i<=n;i++){
            t[i]=v[i]/w[i];
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n-i;j++){
                if(t[j]<t[j+1]){

                    double temp=t[j];
                    t[j]=t[j+1];
                    t[j+1]=temp;

                    double temp2=w[j];
                    w[j]=w[j+1];
                    w[j+1]=temp2;

                    double temp3=v[j];
                    v[j]=v[j+1];
                    v[j+1]=temp3;
                }
            }
        }
    }
}