import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class C101 {

    /*
    *  문자열에 중복되는 문자가 있는지 확인하기
    *
    *  *** 문제를 풀기 전 :  ASCII 문자열인지 유니코드 문자열인지 확인하자!
    *    (이 풀이에서는 ASCII문자열로 가정함)
    * */

    // 1. 배열 사용하기 (time O(N), space O(1))
    public static boolean isUniqueCharacters1(String str){
        int len = str.length();
        if(len > 256) return false; // ASCII 문자 전체 가짓수보다 크면 중복 문자가 존재할 수 밖에 없음
        boolean chk[] = new boolean[256];
        for(int i=0; i<len; i++){
            char c = str.charAt(i);
            if(chk[c]) return false;
            chk[c] = true;
        }
        return true;
    }

    //2. 해시테이블(해시맵) 사용하기
    public static boolean isUniqueCharacters2(String str){
        int len = str.length();
        if(len > 256) return false;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<len; i++){
            char c = str.charAt(i);
            if(map.containsKey(c)) return false;
            map.put(c,1);
        }
        return true;
    }

    //3. 비트벡터 사용하기 --- 비트연산 공부한 후에 도전..!
    public static boolean isUniqueCharacters3(String str){
        return true;
    }

    //4. 자료구조를 추가로 사용할 수 없을 때 1 : 모든 문자를 한 번씩 비교 (time O(N^2), space O(1)
    public static boolean isUniqueCharacters4(String str){
        int len = str.length();
        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                if(str.charAt(i)==str.charAt(j)) return false;
            }
        }
        return true;
    }

    //5. 자료구조를 추가로 사용할 수 없을 때 2 : 정렬 후 인접한 문자끼리 비교 (time O(NlogN), space O(1) 이상)
    // 문자열 수정이 가능한지 확인해야 한다.
    /*
     Arrays.sort()
     Dual-Pivot Quicksort 로 구현되며 수행시간은 O(NlogN) 이라고 한다.
     https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
     참조) Arrays.sort()로 구현했을 때 시간초과가 발생하는 이슈
     https://www.acmicpc.net/board/view/36536
     */
    public static boolean isUniqueCharacters5(String str){
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        for(int i=0; i<arr.length-1; i++){
            if(arr[i]==arr[i+1]) return false;
        }
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(isUniqueCharacters1(str));
        System.out.println(isUniqueCharacters2(str));
        //System.out.println(isUniqueCharacters3(str));
        System.out.println(isUniqueCharacters4(str));
        System.out.println(isUniqueCharacters5(str));
    }
}
