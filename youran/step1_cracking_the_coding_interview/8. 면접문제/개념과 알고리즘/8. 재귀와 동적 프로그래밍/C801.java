
import java.util.Arrays;
/*
    1. 트리플 스텝 
    한 번에 3계단까지 오를 수 있을 때 n개의 계단을 오르는 방법의 경우의 수
*/
public class C801 {

    //내가 풀어본 방법 O(n)
    public static int countWays(int n){
        int[] d = new int[n+1];
        d[0] = 1;
        if(n>0) d[1] = 1;
        if(n>1) d[2] = d[1]+1;
        for(int i=3; i<d.length; i++){
            d[i] = d[i-1] + d[i-2] + d[i-3];
        }
        return d[n];
    }

    //책의 해답 1. 단순 재귀 O(3^n)
    public static int countWays2(int n){
        if(n<0){
            return 0;
        }else if(n==0){
            return 1;
        }else{
            return countWays2(n-1) + countWays2(n-2) + countWays2(n-3);
        }
    }

    //책의 해답 2. 메모이제이션( 이렇게 하면 3 미만의 초기사례를 미리 대비하지 않아도 되겠다.)
    public static int countWays3(int n){
        int[] memo = new int [n+1];
        Arrays.fill(memo, -1); //배열의 모든 값을 -1로 초기화
        return countWays3(n, memo);
    }

    public static int countWays3(int n, int[] memo){
        if(n<0) return 0;
        else if(n==0) return 1;
        else if(memo[n]>-1) return memo[n]; //이미 계산된 경우 저장된 값을 반환
        else{
            memo[n] = countWays3(n-1, memo) + countWays3(n-2,memo) + countWays3(n-3, memo);
            return memo[n];
        }
    }

    /*
    *  1. 일반적으로 캐시를 저장할 때 HashMap을 사용하지만 여기서는 키값이 정확하게 1부터 n까지의 정수이므로 배열이
    *  공간면에서 더 효율적이다
    *  2. 이 문제에서 전체 경우의 수는 n이 37만 되어도 정수 표현의 범위를 넘어서 오버플로우를 발생시킨다.
    *  문제를 완벽하게 해결할 방법을 찾을 필요는 없더라도 문제를 인식하고 있을 필요는 있다.
    * */
    public static void main(String[] args){

        for (int i = 0; i < 30; i++) {
            int c1 = countWays(i);
            int c2 = countWays2(i);
            int c3 = countWays3(i);
            System.out.println(i + ": " + c1 + " " + c2 + " "+ c3);
        }

    }
}
