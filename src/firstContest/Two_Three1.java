
package firstContest;

import java.util.*;

class Node implements Comparable{
    public int i,j;
    public int val;
    public int dist;
    public int compareTo(Object obj){
        Node node = (Node)obj;

        if(node.val == val) {
        	if(node.dist>dist)
        		return -1;
        	else return 1;
        }
        else if(node.val > val)
        	return -1;
        else
        	return 1;
    }
    public Node(){}
    public Node(int i,int j,int val,int dist)
    {
        this.i = i;
        this.j = j;
        this.val = val;
        this.dist = dist;
    }
}

public class Two_Three1 {

    /********* Begin *********/
    public static List<List<Integer>>stones;
    public static int[][] f;
    public static int k = 0;
    public int getMinimumSteps (List<List<Integer>> stones){
        this.stones = stones;
        int x = stones.size();
        int y = stones.get(0).size();
        int n = 0;
        for(int i = 0;i < x;i ++)
        {
            for(int j = 0;j < y;j ++)
            {
                if(stones.get(i).get(j) > 1)
                    n ++;
            }
        }
        f = new int[x][y];
        PriorityQueue<Node> queue = new PriorityQueue();
        int rs = 0;
        int o = 0,u = 0;
        while(n -- > 0){
        	for(int i = 0; i < f[0].length;i++)
        		Arrays.fill(f[i],0);
        	k = 0;
            bfs(queue,o,u);
            if(queue.isEmpty())
            	return -1;
            Node node = queue.poll();
            o = node.i;
            u = node.j;
            stones.get(node.i).set(node.j,1);
            rs += node.dist;
            queue.clear();
            System.out.println(o+" "+u+" "+rs+"+++"+node.dist+" ---"+node.val	);
        }
        
        return rs;
    }
    
    public static void bfs(PriorityQueue<Node> queue,int i,int j)
    {
        int x = stones.size();
        int y = stones.get(0).size();

        if(i>=x || j >= y) return;
        if(i < 0 || j < 0) return;
        if(f[i][j] == 1 || stones.get(i).get(j) == 0) return;
        if(stones.get(i).get(j) == 1)
        	f[i][j] = 1;
        if(stones.get(i).get(j) > 1){
            Node temp = new Node(i,j,stones.get(i).get(j),k);
            queue.add(temp);
            return;
        }

        k ++;
        bfs(queue,i - 1,j);
        bfs(queue,i,j -1);
        bfs(queue,i + 1,j);
        bfs(queue,i,j + 1);
        k --;
        f[i][j] = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> s = new ArrayList();
        int xlen = sc.nextInt();
        for(int i = 0;i < xlen;i ++){
            List<Integer> temp = new ArrayList();
            for(int j = 0;j < xlen;j ++)
                temp.add(sc.nextInt());
            s.add(temp);
        }

        Two_Three1 ss = new Two_Three1();
        System.out.println(ss.getMinimumSteps(s));
    }

    /********* End *********/
}