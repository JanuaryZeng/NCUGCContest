package firstContest;

public class two_two {

    /********* Begin *********/
    public static Long getBestTeam(int numbers, int[] abilities, int selectedNum, int distance){
        return get(numbers, abilities, selectedNum, distance,-1);
    }

    public static Long get(int numbers, int[] abilities, int selectedNum, int distance,int rear)
    {
        if(numbers == 0 || selectedNum == 0)
            return Long.valueOf("1");
        if(selectedNum > numbers)
            return Long.valueOf("1");
        
        if(rear - numbers > distance && rear != -1) return Long.valueOf("1");
        Long a = Long.valueOf(Integer.toString(abilities[numbers]));
        return Math.max(get(numbers - 1,abilities,selectedNum - 1,distance,numbers) * a , get(numbers - 1,abilities, selectedNum, distance,rear));
    }
    public static void main(String[] args) {
        int[] a = {7,4,7};

        System.out.print(getBestTeam(3,a,2,50));
    }
    /********* End *********/
}
