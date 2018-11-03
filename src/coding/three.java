package coding;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class three {
	
	static List<List<String>> record = new ArrayList<List<String>>();//数据集
	static List<List<String>> frequentItemset = new ArrayList<>();//存储所有的频繁项集
	static HashMap<List<String>,Double> UTFCmap = new HashMap<>();
	static HashMap<Set<String>,List<String>> Itemset = new HashMap<Set<String>,List<String>>();
	static HashMap<HashMap<Integer,String>,Double> hhd = new HashMap<>();
	static List<String> print = new ArrayList<String>();
	public static int times=3;//迭代次数
	private static double MIN_SUPPROT = 0.01;//最小支持度百分比
	private static double MIN_CONFIDENCE = 0.6;//最小置信度
	private static boolean endTag = false;//循环状态，迭代标识
	private static List<Mymap> map = new ArrayList();//存放频繁项集和对应的支持度技术
	
	public static void main(String[]args) throws IOException {
		List<List<String>> data = new ArrayList<>();
		data = getUTFC();
		ShowData(data);
		
		Set<List<String>> set = UTFCmap.keySet();
		System.out.println(set);
		Iterator<List<String>> in = set.iterator();
//		while(in.hasNext()) {
//			System.out.println(UTFCmap.containsKey(in.next()));
//		}
		List<String> ii = new ArrayList();
		ii.add(String.valueOf(3));
		ii.add("<mybatis>");
		System.out.println(UTFCmap.containsKey(ii));
		
		secondMain();

		calculate();
		
		showHHD();
	}
		
	public static void showHHD() {
		Set<HashMap<Integer, String>> his = hhd.keySet();
		for(HashMap<Integer, String> his1 : his) {
			Set<Integer> his2 = his1.keySet();
			for(Integer i : his2) {
				System.out.print(i+" "+his1.get(i));
			}
			System.out.println(" "+hhd.get(his1));
		}
	}

	public static void secondMain() throws IOException {
		record = getTagsFromCsvList();
		System.out.println("读取数据集record成功===================================");
		Apriori();//调用Apriori算法获得频繁项集
		System.out.println("频繁模式挖掘完毕。\n\n\n\n\n进行关联度挖掘，最小支持度百分比为："+MIN_SUPPROT+"  最小置信度为："+MIN_CONFIDENCE);
		AssociationRulesMining();//挖掘关联规则
	}
	
	public static void Apriori() {
		System.out.println("第一次扫描后的1级 备选集CanndateItemset");
		List<List<String>> CandidateItemset = findFirstCandidate();
		
		System.out.println("第一次扫描后的1级 频繁集FrequentItemset");
		List<List<String>> FrequentItemset = getSupprotedItemset(CandidateItemset);
		AddToFrequenceItem(FrequentItemset);
		
		times=2;
		while(endTag!=true){
			System.out.println("*******************************第"+times+"次扫描后备选集");
			//**********连接操作****获取候选times项集**************
			List<List<String>> nextCandidateItemset = getNextCandidate(FrequentItemset);
			//输出所有的候选项集
			ShowData(nextCandidateItemset);


			/**************计数操作***由候选k项集选择出频繁k项集****************/
			System.out.println("*******************************第"+times+"次扫描后频繁集");
			List<List<String>> nextFrequentItemset = getSupprotedItemset(nextCandidateItemset);
			AddToFrequenceItem(nextFrequentItemset);//添加到所有的频繁项集中
			//输出所有的频繁项集
			ShowData(nextFrequentItemset);


			//*********如果循环结束，输出最大模式**************
			if(endTag == true){
				System.out.println("\n\n\nApriori算法--->最大频繁集==================================");
				ShowData(FrequentItemset);
			}
			//****************下一次循环初值********************
			FrequentItemset = nextFrequentItemset;
			times++;//迭代次数加一
		}
	}
	
	public static void showUTCmap() {
		Set<List<String>> uSet = UTFCmap.keySet();
		Iterator<List<String>> in = uSet.iterator();
		while(in.hasNext()) {
			List<String> ss =  in.next();
			
			Iterator<String> in2 = ss.iterator();
			while(in2.hasNext()) {
				System.out.print(in2.next()+" ");
			}
			System.out.print("------>");
			System.out.println(UTFCmap.get(ss));
		}
		
	}
	
	public static void showItemset() {
		Set<Set<String>> ss = Itemset.keySet();
		Iterator<Set<String>> in = ss.iterator();
		while(in.hasNext()) {
			Set<String> ss1 = in.next();
			Iterator<String> in2 = ss1.iterator();
			while(in2.hasNext()) {
				System.out.print(in2.next()+" ");
			}
			System.out.print("------>>");
			List<String> li = Itemset.get(ss1);
			Iterator<String> in1 = li.iterator();
			while(in1.hasNext()) {
				System.out.print(in1.next()+" ");
			}
			System.out.println();
		}
	}

	public static void calculate() {
//		UTFCmap Itemset print
		Set<List<String>> uSet = UTFCmap.keySet();
//		showUTCmap();
		
		for(int i = 1; i <=20; i++) {
			//将每一为工程师的五个标签取出来
			Set<String> SetValue = new HashSet<>();
			for(List<String> setStr : uSet) {
				if(setStr.contains(String.valueOf(i))) {
					
					setStr.remove(String.valueOf(i));
					for(String s:setStr) {
						SetValue.add(s);
					}
				}
			}
			//一个标签的推荐结果
			for(String str:SetValue) {
				Set<String> Set1 = new HashSet<>();
				Set1.add(str);
			 	List<String> l = new ArrayList<>();
			 	l.add(String.valueOf(i));
			 	l.add(str);
			 	/**
			 	 * 错误记录：程序员的匹配错误
			 	 */
				if(Itemset.containsKey(Set1)) {
				 	List<String> lst = Itemset.get(Set1);
				 	double d1 = Double.parseDouble(lst.get(1));
				 	System.out.println(UTFCmap);
				 	System.out.println(UTFCmap.containsKey(l));
				 	System.out.println(str);

				 	double d2 = UTFCmap.get(l);
				 	double d3 = d2*d1;
				 	String str1 = lst.get(0);
				 	HashMap<Integer,String> his = new HashMap<Integer,String>();
				 	his.put(i, str1);
				 	hhd.put(his,d3);
				}
			}
			for(String str1:SetValue) {
				for(String str2:SetValue) {
					Set<String> setStr1 = new HashSet<String>();
					setStr1.add(str1);
					Set<String> l = new HashSet<>();
				 	l.add(String.valueOf(i));
				 	l.add(str1);
					Set<String> ll = new HashSet<>();
				 	ll.add(String.valueOf(i));
				 	ll.add(str2);
					if(setStr1.contains(str1))
						setStr1.add(str2);
					if(UTFCmap.containsKey(ll)&&UTFCmap.containsKey(l)&&setStr1.size()==2 && Itemset.containsKey(setStr1)) {
						List<String> lst = Itemset.get(setStr1);
						double d1 = Double.parseDouble(lst.get(1));
					 	double d2 = UTFCmap.get(l);
					 	double d3 = UTFCmap.get(ll);
					 	double d4 = (d2+d3)*d1;
					 	String str3 = lst.get(0);
					 	HashMap<Integer,String> his = new HashMap<Integer,String>();
					 	his.put(i, str3);
					 	hhd.put(his,d4);
					}
				}
			}
			for(String str1:SetValue) {
				for(String str2:SetValue) {
					for(String str3:SetValue) {
						Set<String> setStr1 = new HashSet<String>();
						setStr1.add(str1);
						Set<String> l = new HashSet<>();
					 	l.add(String.valueOf(i));
					 	l.add(str1);
						if(setStr1.contains(str1))
							setStr1.add(str2);
						if(setStr1.contains(str2))
							setStr1.add(str3);
						if(UTFCmap.containsKey(l)&&setStr1.size()==3 && Itemset.containsKey(setStr1)) {
							List<String> lst = Itemset.get(setStr1);
							double d1 = Double.parseDouble(lst.get(1));
						 	double d2 = UTFCmap.get(l);
						 	l.remove(str1);
						 	l.add(str2);
						 	double d3 = UTFCmap.get(l);
						 	l.remove(str2);
						 	l.add(str3);
						 	double d4 = UTFCmap.get(l);
						 	double d5 = (d2+d3+d4)*d1;
						 	String str4 = lst.get(0);
						 	HashMap<Integer,String> his = new HashMap<Integer,String>();
						 	his.put(i, str4);
						 	hhd.put(his,d5);
						}
					}
				}
			}
		}
	}
	
	public static void AssociationRulesMining()//关联规则挖掘
	{
		List<String> value = new ArrayList<>();
		
		for(int i=0;i<frequentItemset.size();i++)
		{
			List<String> tem=frequentItemset.get(i);
			if(tem.size()>1) {
				List<String> temclone=new ArrayList<>(tem);
				List<List<String>> AllSubset = getSubSet(temclone);//得到频繁项集tem的所有子集
				for (int j = 0; j < AllSubset.size(); j++) {
					List<String> s1 = AllSubset.get(j);
					List<String> s2 = gets2set(tem, s1);
					double conf = isAssociationRules(s1, s2, tem);
					if (conf > 0) {
						System.out.println("置信度为：" + conf);
						Set<String> key = new HashSet<>();
						for(String str:s1) {
							key.add(str);
						}
						value = s2;
						value.add(String.valueOf(conf));
						Itemset.put(key,value);
					}
				}
			}
		}
	}
	
	private static List<List<String>> getNextCandidate(List<List<String>> FrequentItemset) {
		List<List<String>> nextCandidateItemset = new ArrayList<List<String>>();

		for (int i=0; i<FrequentItemset.size(); i++){
			HashSet<String> hsSet = new HashSet<String>();
			HashSet<String> hsSettemp = new HashSet<String>();
			for (int k=0; k< FrequentItemset.get(i).size(); k++)//获得频繁集第i行
				hsSet.add(FrequentItemset.get(i).get(k));
			int hsLength_before = hsSet.size();//添加前长度
			hsSettemp=(HashSet<String>) hsSet.clone();
			for(int h=i+1; h<FrequentItemset.size(); h++){//频繁集第i行与第j行(j>i)连接   每次添加且添加一个元素组成    新的频繁项集的某一行，
				hsSet=(HashSet<String>) hsSettemp.clone();//！！！做连接的hasSet保持不变
				for(int j=0; j< FrequentItemset.get(h).size();j++)
					hsSet.add(FrequentItemset.get(h).get(j));
				int hsLength_after = hsSet.size();
				if(hsLength_before+1 == hsLength_after && isnotHave(hsSet,nextCandidateItemset)){
	//如果不相等，表示添加了1个新的元素       同时判断其不是候选集中已经存在的一项
					Iterator<String> itr = hsSet.iterator();
					List<String> tempList = new ArrayList<String>();
					while(itr.hasNext()){
						String Item = (String) itr.next();
						tempList.add(Item);
					}
					nextCandidateItemset.add(tempList);
				}
			}
		}
		return nextCandidateItemset;
	}
	
	private static boolean isnotHave(HashSet<String> hsSet, List<List<String>> nextCandidateItemset) {//判断hsset是不是candidateitemset中的一项
		List<String> tempList = new ArrayList<String>();
		Iterator<String> itr = hsSet.iterator();
		while(itr.hasNext()){//将hsset转换为List<String>
			String Item = (String) itr.next();
		tempList.add(Item);
		}
		for(int i=0; i<nextCandidateItemset.size();i++)//遍历candidateitemset，看其中是否有和templist相同的一项
		if(tempList.equals(nextCandidateItemset.get(i)))
			return false;
		return true;
	}
	
	public static double isAssociationRules(List<String> s1,List<String> s2,List<String> tem)//判断是否为关联规则
	{
		double confidence=0;
		int counts1;
		int countTem;
		if(s1.size()!=0&&s1!=null&&tem.size()!=0&&tem!=null){
			counts1= getCount(s1);
			countTem=getCount(tem);
			confidence=countTem*1.0/counts1;
	
			if(confidence>=MIN_CONFIDENCE){
				System.out.print("关联规则："+ s1.toString()+"=>>"+s2.toString()+"   ");
				return confidence;
			}
			else
				return 0;
		}
		else
			return 0;
	}
	
	public static int getCount(List<String> in)//根据频繁项集得到其支持度计数
	{
		int rt=0;
		for(int i=0;i<map.size();i++){
			Mymap tem=map.get(i);
			if(tem.isListEqual(in)) {
				rt = tem.getcount();
				return rt;
			}
		}
		return rt;
	}

	public static List<String> gets2set(List<String> tem, List<String> s1)//计算tem减去s1后的集合即为s2
		{
		List<String> result=new ArrayList<>();

		for(int i=0;i<tem.size();i++)//去掉s1中的所有元素
		{
		String t=tem.get(i);
		if(!s1.contains(t))
		result.add(t);
		}
		return result;
	}
	
	public static List<List<String>> getSubSet(List<String> set){
		List<List<String>> result = new ArrayList<>(); //用来存放子集的集合，如{{},{1},{2},{1,2}}
		int length = set.size();
		int num = length==0 ? 0 : 1<<(length); //2的n次方，若集合set为空，num为0；若集合set有4个元素，那么num为16.

		//从0到2^n-1（[00...00]到[11...11]）
		for(int i = 1; i < num-1; i++){
			List<String> subSet = new ArrayList<>();

		int index = i;
		for(int j = 0; j < length; j++){
			if((index & 1) == 1){//每次判断index最低位是否为1，为1则把集合set的第j个元素放到子集中
			subSet.add(set.get(j));
			}
			index >>= 1;//右移一位
		}
		result.add(subSet); //把子集存储起来
		}
		return result;
	}
	//对所有的商品进行支持度计数
	private static List<List<String>> getSupprotedItemset(List<List<String>> CandidateItemset) { 
		boolean end = true;
		List<List<String>> supportedItemset = new ArrayList<List<String>>();
		for(int i = 0; i<CandidateItemset.size(); i++) {
			int count = countFrequent1(CandidateItemset.get(i));
			if (count >= MIN_SUPPROT*(record.size()-1)) {
				supportedItemset.add(CandidateItemset.get(i));
				map.add(new Mymap(CandidateItemset.get(i),count));
				end = false;
			}
		}
		endTag = end;
		if(endTag==true)
			System.out.println("*****************无满足支持度的"+times+"项集,结束连接");
		return supportedItemset;
	}
	//将文件中的数据的不重复项通过set导成List
	private static List<List<String>> findFirstCandidate() {
		List<List<String>> tableList = new ArrayList<List<String>>();
		HashSet<String> hs = new HashSet<String>();
		for(int i = 1; i<record.size(); i++) {
			for(int j = 1;j<record.get(i).size(); j++) {
				hs.add(record.get(i).get(j));
			}
		}
		Iterator<String> itr = hs.iterator();
		while(itr.hasNext()) {
			List<String> tempList = new ArrayList<String>();
			String Item = (String)itr.next();
			tempList.add(Item);
			tableList.add(tempList);
		}
		return tableList;
	}
	
	public static boolean AddToFrequenceItem(List<List<String>> fre)
	{

		for(int i=0;i<fre.size();i++)
		{
			frequentItemset.add(fre.get(i));
		}
		return true;
	}

	private static int countFrequent1(List<String> list) {//遍历所有数据集record，对单个候选集进行支持度计数
		int count =0;
		for(int i=0;i<record.size();i++)//从record的第一个开始遍历
		{
			boolean flag=true;
			for (int j=0;j<list.size();j++)//如果record中的第一个数据集包含list中的所有元素
			{
				String t=list.get(j);
				if(!record.get(i).contains(t)) {
					flag = false;
					break;
				}
			}
				if(flag)
				count++;//支持度加一
		}
		return count;//返回支持度计数
	}
	
	public static List<List<String>> getTagsFromCsvList() throws IOException{
		List<List<String>> data = new ArrayList<>();
		List<label> userlist = getTagsFromCsv();
		for (label user : userlist) {
            data.add(user.getString());
            System.out.println(user.getString());
        }
		return data;
	}
	
	public static List<label> getTagsFromCsv() throws IOException{
		String path = "D:/大学/大三上/比赛/绿色计算机大赛/data/tag_cooccurrence.csv";
		List<label> labelList = new ArrayList<>();
		try {
		CsvReader csvReader = new CsvReader(new FileInputStream(new File(path)),Charset.forName("UTF-8"));
		boolean readHeaders = csvReader.readHeaders();
	     while (csvReader.readRecord()) {
             // 读一整行
             // 读这行的第一列
             String tagName = csvReader.get(1);    //标签名
             String[] tagItems = tagName.split(",");
             List<String> tags = new ArrayList<>();
             label L = new label();
             for (int j = 0; j < tagItems.length; j++) {
                 String tag = tagItems[j];
                 tags.add(tag);
             }
             L.setString(tags);
             labelList.add(L);
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
		
		return labelList;
	}
	
	public static List<List<String>> getUTFC() throws IOException{
		String path = "D:/大学/大三上/比赛/绿色计算机大赛/data/user_tag.csv";
		List<User> user = getUserTagsFromCsv(path);
		List<List<String>> data = new ArrayList<>();
		
		for(User U : user) {
			List<String> D = new ArrayList<>();
			String str1 = String.valueOf((U.getUserId()));
			for (Tag tag : U.getTags()) {
				List<String> LLS = new ArrayList<>();	
				LLS.add(str1);
				D.add(tag.getName());
				LLS.add(tag.getName());
				D.add(String.valueOf(tag.getWeight()));
				UTFCmap.put(LLS,tag.getWeight());
			}
			data.add(D);
		}

		return data;
	}
	
    public static List<User> getUserTagsFromCsv(String path) throws IOException {
        // 创建创建用户集合并读取文件数据
        List<User> userList = new ArrayList<>();
        try {
            // 创建CSV读对象
           CsvReader csvReader = new CsvReader(new FileInputStream(new File(path)),Charset.forName("UTF-8"));
            // 读表头
            boolean readHeaders = csvReader.readHeaders();
            while (csvReader.readRecord()) {
                // 读一整行
                // 读这行的第一列
                String id = csvReader.get(0);        //读id
                String tagName = csvReader.get(1);    //标签名
                String weight = csvReader.get(2);    //对应权重
                String[] tagItems = tagName.split(",");
                String[] weightItems = weight.split(",");
                List<Tag> tags = new ArrayList<>();
                User user = new User();
                user.setTagStr(tagName);
                user.setUserId(Integer.valueOf(id));
                for (int j = 0; j < tagItems.length; j++) {
                    Tag tag = new Tag();
                    tag.setName(tagItems[j]);
                    tag.setWeight(Double.valueOf(weightItems[j].replace("[", "").replace("]", "")));
                    tags.add(tag);
                }
                user.setTags(tags);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 读取tag_coocurrence.csv
     * @param path 路径
     * @return 读取后生成map集合
     * @throws IOException
     */
    public static Map<Integer, Tag> getTagFromCsv(String path) throws IOException {
        return null;
    }

    /**
     * 写结果文件
     * 
     * @param filePath 文件路径
     * @param data 要写入的数据
     * @throws IOException
     */
    public static void writeCsvFile(String filePath, List<String> data) throws IOException {
        // 创建CSV写对象
        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        // CsvWriter csvWriter = new CsvWriter(filePath);
        // 写表头
        String[] headers = {"id","recommand_tags"};
        csvWriter.writeRecord(headers);
        //循环写数据
       
        int id = 1;
        for (String tag : data) {
            String[] content = {id++ +"", tag};
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }
    
    public static void ShowData(List<List<String>> CandidateItemset) {
    	for(int i=0;i<CandidateItemset.size();i++) {
    		List<String> list = new ArrayList<String>(CandidateItemset.get(i));
    		for(int j=0;j<list.size();j++) {
    			System.out.print(list.get(j)+" ");
    		}
    		System.out.println();
    	}
    }
}

// 标签
class Tag {
    private String name; // 标签名
    private double weight; // 权重值

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

// 用户
class User {
    private int userId;
    private List<Tag> tags;
    private String tagStr;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTagStr() {
        return tagStr;
    }

    public void setTagStr(String tagStr) {
        this.tagStr = tagStr;
    }
}

class label{
	private List<String> string;
	
	public List<String> getString(){
		return string;
	}
	
	public void setString(List<String> string) {
		this.string = string;
	}
}

class Mymap{//自定义的map类，一个对象存放一个频繁项集以及其支持度计数
	public List<String> li=new LinkedList<>();
	public int count;

	public Mymap(List<String> l,int c)//构造函数  新建一个对象
	{
		li = l;
		this.count = c;
	}

	public int getcount()//返回得到当前频繁项集的支持度计数
	{
		return count;
	}

    public boolean isListEqual(List<String> in)//判断传入的频繁项集是否和本频繁项集相同
	{
    	if(in.size()!=li.size())
    		return false;
    	else {
    		for(int i = 0;i<in.size();i++) {
    			if(!li.contains(in.get(i)))
    				return false;
    		}
    	}
    return true;
	}
}

