package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author January
 */

public class dijkstraPriorityQueue{
	static Node[] node;
	static distance[] dis;
	static HashSet<Integer> set;
	static PriorityQueue<distance> pq;
	static int x;
	static int y;
	static int INF = 1000000;
	
	public static void main(String [] args) {
		Scanner sc =new Scanner(System.in);
		x = sc.nextInt();
		y = sc.nextInt();
		node = new Node[x+1];
		for (int i = 0 ; i <= x; i++)
			node[i] = new Node();
		for(int i = 0; i < y; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int value = sc.nextInt();
			node[start].append(end, value);
			node[end].append(start, value);
			node[i].append(i, 0);
		}
		
		int minStart = sc.nextInt();
		int minEnd = sc.nextInt();
		dijkstra(minStart);
		System.out.println(dis[minEnd].value);
		sc.close();
	}
	
	public static void dijkstra(int num) {
		set = new HashSet<Integer>();
		pq = new PriorityQueue<distance>();
		dis = new distance[x+1];
		HashMap<Integer, Integer> tempMap = node[num].map;
		for(int i = 1; i <= x; i++) {
			if(tempMap.containsKey(i)) {
				distance d = new distance(i,tempMap.get(i));
				pq.add(d);
				dis[i] = d;
			}
			else {
				distance d = new distance(i,INF);
				pq.add(d);
				dis[i] = d;
			}
		}				

		dis[num] = new distance(num, 0);
		set.add(num);
		while(set.size() < x) {
			distance d = pq.poll();
			int index = d.index;
			int value = d.value;
			set.add(index);

			HashMap<Integer, Integer> h = node[index].map;
			if(h==null)
				continue;
			Set<Integer> s = h.keySet();
			Iterator<Integer> it = s.iterator();
			while(it.hasNext()) {
				int number = it.next();
				distance tempDist = dis[number];
				if(h.get(number) + value < tempDist.value) {
					pq.remove(tempDist);
					tempDist.value = h.get(number) + value;
					pq.add(tempDist);
					dis[number] = tempDist;
				}
			}
		}
	}
}
class Node{
	HashMap<Integer, Integer> map = null;
	public void append(int end, int value) {
		if(this.map == null)
			map = new HashMap<Integer, Integer>();
		this.map.put(end, value);
	}
	public int get(int num) {
		return this.map.get(num);
	}
}

class distance implements Comparable{
	int index;
	int value;
	public distance(int index, int value) {
		this.index = index;
		this.value = value;
	}
	public int compareTo(Object O) {
		distance dis = (distance)O;
		return dis.value < this.value ? 1 : (dis.value == this.value ? 0 : -1);
	}
}
