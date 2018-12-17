package algorithmExperiment;

/*
 * ��̰���㷨�ⱳ������
 */
public class Greedy {
    public static void main(String[] args) {
        // ��λ������ֵ�ֱ�Ϊ:10 5 7 6 3 8 90 100
        double w[] = { 0, 50, 80, 30, 40, 20, 60, 10 ,1};//���������

        double v[] = { 0, 500, 400, 210, 240, 60, 480, 900,100 };//����ļ�ֵ

        double M = 170;// �����������ɵ�����

        int n = w.length - 1;// ����ĸ���

        double[] x = new double[n + 1];// ÿ������װ���ı���,���ڵ���0����С�ڵ���1

        f(w, v, M, n, x);//����̰���㷨����

        System.out.println("���������������:");
        for(int i=1;i<=n;i++){
            System.out.print(w[i]+"\t");
        }
        System.out.println();

        System.out.println("����������ļ�ֵ:");
        for(int i=1;i<=n;i++){
            System.out.print(v[i]+"\t");
        }

        double[]t=new double[n+1];//����һ�������ʾ��λ��������ļ�ֵ
        for(int i=1;i<=n;i++){
            t[i]=v[i]/w[i];
        }
        //��ð�������double[]t��������(�����ǰ)
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

        System.out.println("�ź����ĵ�λ����ļ�ֵ: ");
        for(int i=1;i<=n;i++){
            System.out.print(t[i]+"\t");
        }

        double maxValueSum=0;   //������ű�����װ�µ����������ֵ�ܺ�
        for(int i=1;i<x.length;i++){
            maxValueSum+=x[i]*v[i];
        }
        System.out.println();

        System.out.println("�����ÿ������װ�������ı���:");
        for(int i=1;i<=n;i++){
            System.out.print(x[i]+"\t");
        }
        System.out.println();

        System.out.println("������װ�µ����������ֵ�ܺ�Ϊ: "+maxValueSum);

    }

    /**
     * 
     * @param w ���������
     * @param v ����ļ�ֵ
     * @param M ����������
     * @param n ����ĸ���
     * @param x ÿ������װ�������ı���,ȡֵ0<=x[i]<=1,(1<=i<=n)
     */
    private static void f(double[] w, double[] v, double M, int n, double[] x) {
        sort(w, v, n);// ���Ȱ�������ĵ�λ�����ļ�ֵ��������,��λ������ֵ�������ǰ��

        double c = M;   //����ʣ�������,�տ�ʼʱ��û��װ����,ΪM
        int i;//��ʾ�ڼ�������
        for (i = 1; i <= n; i++) {
            if (w[i] <= c){//�������ʣ����������ڵ��ڵ�i�����������
            x[i] = 1;   //�ѵ�i����������װ������
            c -= w[i];  //������ʣ�����������˵�i�����������
            }else {     
                break;  //�˳�ѭ��
            }
        }
        if (i <= n){//�ж��Ƿ��n����������װ��ȥ��������,���i<=n��ʾ��
            x[i] = c / w[i];    
        }
    }

    /*
     * ���԰�ð��������д���ȼ���W��ÿ�����嵥λ��ֵ���һ�����飬Ȼ��ð������
     * ����Ԫ�ؽ�������ӦW����ͬ��ŵ�Ԫ��Ҳ�����Ϳ�����
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