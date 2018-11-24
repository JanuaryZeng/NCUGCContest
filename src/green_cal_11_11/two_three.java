package green_cal_11_11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class two_three { 
	private static Map<Set<Integer>,Integer> map = new HashMap<Set<Integer>,Integer>();
	private static PriorityQueue<DeliveryCost> pq = new PriorityQueue<DeliveryCost>();
	private static Set<Set<Integer>> SE;
	private static int[] employee;
	private static int[] father;
	static Set<Integer> allEmp = new HashSet<Integer>();
	private static int min = 100000;
	private static int len;
	public static int solve(int n, List<DeliveryCost> cost_e, int[] employees, int[] cost_b) {
		//合并普通员工节点然后进行最小生成树算法kruskal		
		employee = new int[employees.length + 1];
		Iterator<DeliveryCost> it = cost_e.iterator();
		while(it.hasNext()) {
			DeliveryCost dc = it.next();
			Set<Integer> set = new HashSet<Integer>();
			set.add(dc.u);
			set.add(dc.v);
			map.put(set, dc.cost);
			allEmp.add(dc.u);
			allEmp.add(dc.v);
		}
		int host = allEmp.size()+1;
		for(int i  = 0; i < employees.length; i++) {
			Set<Integer> set = new HashSet<Integer>();
			set.add(host);
			set.add(employees[i]);
			map.put(set, cost_b[i]);
		}
		father = new int[allEmp.size()+2];
		for(int i = 0; i <= allEmp.size()+1; i++) {
			father[i] = i;
		}
		len = allEmp.size()-employees.length;
		for(int i : employees) {
			allEmp.remove(i);
		}

		for(int i = 0; i < employees.length; i++) {
			employee[i] = employees[i];
		}
		employee[employees.length] = host;
		
		//父节点初始化

//		Set<Set<Integer>> ss = map.keySet();
//		Iterator<Set<Integer>> it1 = ss.iterator();
//		while(it1.hasNext()) {
//			Set<Integer> sss= it1.next();
//			System.out.print(sss);
//			System.out.println(map.get(sss));
//		}
		
		//allEmp输出
//		Iterator<Integer> it3 = allEmp.iterator();
//		while(it3.hasNext()) {
//			System.out.println(it3.next());
//		}
		
		merge();
//		Iterator<DeliveryCost> it2 = pq.iterator();
//		while(it2.hasNext()) {
//			DeliveryCost dd = it2.next();
//			System.out.println(dd.u + " " + dd.v + " " + dd.cost);
//		}
		
		int temp = kruskal();
		return temp;
	}
	
	public static void merge() {
		for(int i : employee)
			for(int j : employee) {
				if(i<j) {
					Set<Integer> set = new HashSet<Integer>();
					set.add(i);
					set.add(j);
					SE = new HashSet<Set<Integer>>();
					allEmp.add(j);
					contant(i, j, 0);
					allEmp.remove(j);
					DeliveryCost D;
					if(map.containsKey(set)) {
					if(map.get(set) > min)
						D = new DeliveryCost(i, j, min);
					else
						D = new DeliveryCost(i, j, map.get(set));
					}
					else {
						map.put(set, min);
						D = new DeliveryCost(i, j, min);
					}
					pq.add(D);
					min = 100000;
				}
			}
	}
	
	private static Collection<Integer> setter= new HashSet<Integer>(allEmp);
	private static PriorityQueue<DeliveryCost> pqueue;
	private static int [] distance;
	private static int [] point;
	public static void dijkstra(int x,int y) {
		pqueue = new PriorityQueue<DeliveryCost>();
		setter.add(x);
		setter.add(y);
		Set<Integer> ss = new HashSet();
		distance = new int[len+2];
		point = new int[len+2];
		
		int cal = 0;
		int start = 0;
		int end = 0;
		Iterator<Integer> it3 = setter.iterator();
		while(it3.hasNext()) {
			int k = it3.next();
			if(k==x)
				start = cal;
			if(k==y)
				end = cal;
			point[cal] = k;
			distance[cal] = 1000000;
			cal++;
		}
		distance[start] = 0;
		
		Iterator<Integer> it1 = setter.iterator();

		Set<Integer> set = new HashSet<Integer>();
		set.add(x);
		while(it1.hasNext()) {
			int j = it1.next();
			if(j==x)
				continue;
			set.add(j);
			if(map.containsKey(set)) {
				DeliveryCost D = new DeliveryCost(x,j,map.get(set));
				pqueue.add(D);
			}
			else{
				DeliveryCost D = new DeliveryCost(x,j,100000);
				pqueue.add(D);
			}
		}

		ss.add(x);
		while(ss.size() < len+2) {
			DeliveryCost D1 = pqueue.poll();
			int u = D1.u;
			int v = D1.v;
			int cost = D1.cost;
			int temp;
			if(u==x)
				temp = v;
			else
				temp = u;
			
		}
		
	}
	
	public static void contant(int x, int y, int numb) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(x);

		if(allEmp.contains(x)) {
			allEmp.remove(x);
		}

		Collection<Integer> ss = new HashSet<Integer>(allEmp); 
		Iterator<Integer> it = ss.iterator();
		while(it.hasNext()) {
			int i = it.next();
			set.add(i);	
			if(!SE.contains(set) && map.containsKey(set)) {
				SE.add(set);
				numb += map.get(set);
				if(i == y && min > numb) {
						min = numb;
						allEmp.add(x);
						SE.remove(set);
						return;
				}
				else {
					allEmp.add(x);
					contant(i, y, numb);
				}
			}
			set.remove(i);
		}
	}
	
	public static int find(int x) {
		while(father[x]!=x)
			x = father[x];
		return x;
	}
	
	public static void combine(int a, int b) {
		int temp_a, temp_b;
		temp_a = find(a);
		temp_b = find(b);
		
		if(temp_a != temp_b)
			father[temp_a] = temp_b;
	}
	
	public static int kruskal() {
		DeliveryCost dc;
		int res = 0;

		while(!pq.isEmpty()) {
			dc = pq.poll();
		
			if(find(dc.u) != find(dc.v)) {
				combine(dc.u, dc.v);
				res += dc.cost;
			}
		}
		return res;
	}
	
	public static void main(String [] args) {
		int n = 3;
		List<DeliveryCost> cost_e =new ArrayList<DeliveryCost>();
		int[] employees = {1,2,7};
		int[] cost_b = {1,10000,10000};
		DeliveryCost dc1 = new DeliveryCost(1,7,100);
		cost_e.add(dc1);
		DeliveryCost dc2 = new DeliveryCost(2,7,100);
		cost_e.add(dc2);
		DeliveryCost dc3 = new DeliveryCost(1,3,10);
		cost_e.add(dc3);
		DeliveryCost dc4 = new DeliveryCost(3,4,10);
		cost_e.add(dc4);
		DeliveryCost dc5 = new DeliveryCost(3,5,10);
		cost_e.add(dc5);
		DeliveryCost dc6 = new DeliveryCost(5,6,10);
		cost_e.add(dc6);
		DeliveryCost dc7 = new DeliveryCost(4,6,10);
		cost_e.add(dc7);
		DeliveryCost dc8 = new DeliveryCost(2,6,10);
		cost_e.add(dc8);
		
		System.out.println(solve(n, cost_e, employees, cost_b));
	}
}

class DeliveryCost implements Comparable {
	public int u;
	public int v;
	public int cost;

	public DeliveryCost(int u, int v, int cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;
	}

	public int compareTo(Object o) {
		DeliveryCost D = (DeliveryCost)o;
		return this.cost < D.cost ? -1 : (this.cost == D.cost ? 0 : 1);
	}
}